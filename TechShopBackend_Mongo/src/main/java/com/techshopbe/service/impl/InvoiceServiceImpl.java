package com.techshopbe.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.techshopbe.dto.DetailedInvoiceDTO;
import com.techshopbe.dto.InvoiceDTO;
import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.entity.Category;
import com.techshopbe.entity.DetailedInvoice;
import com.techshopbe.entity.Invoice;
import com.techshopbe.entity.Product;
import com.techshopbe.entity.ShippingInfo;
import com.techshopbe.entity.User;
import com.techshopbe.repository.CategoryRepository;
import com.techshopbe.repository.DetailedInvoiceRepository;
import com.techshopbe.repository.InvoiceRepository;
import com.techshopbe.repository.ProductRepository;
import com.techshopbe.repository.ShippingInfoRepository;
import com.techshopbe.repository.UserRepository;
import com.techshopbe.security.CustomUserDetails;
import com.techshopbe.service.InvoiceService;
import com.techshopbe.service.UserService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	ShippingInfoRepository shippingInfoRepository;
	@Autowired
	DetailedInvoiceRepository detailedInvoiceRepository;

	@Override
	public void add(String invoice) {
		Gson g = new Gson();
		InvoiceDTO invoiceDTO = g.fromJson(invoice, InvoiceDTO.class);

		boolean otherShippingAddress = true;

		// set email
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (auth != null && auth.isAuthenticated()) {
			Object principal = auth.getPrincipal();
			if (principal instanceof UserDetails) {
				userDetails = (UserDetails) principal;
			}
		}
		String email = userDetails.getUsername();
		invoiceDTO.setEmail(email);

		// set detailed invoice (calculate price and total price)
		invoiceDTO.setDetailedInvoices(getDetailedInvoices(invoiceDTO.getDetailedInvoices()));

		// set shipping info
		if (invoiceDTO.getShippingInfo() == null) {
			invoiceDTO.setShippingInfo(getShippingInfo(email));
			otherShippingAddress = false;
		}

		// set total price of invoice
		invoiceDTO.setTotalPrice(calculateTotalInvoiceCost(invoiceDTO.getDetailedInvoices()));

		/*
		 * Insert new invoice
		 */
		// INVOICE userID, totalCost, invoiceDate, shippingDate, note,
		// otherShippingAddress, statusInvoice
		// shipping Date: now + 10 ngày
		User user = userRepository.findFirstByEmail(email);

		String userID = user.getUserID();
		LocalDateTime invoiceDate = LocalDateTime.now();
		LocalDateTime shippingDate = invoiceDate.plusDays(3);

		// totalInvoices: lưu tổng số hoá đơn của người dùng
		// userInvoiceIndex: là key phân biệt các invoice (không lấy newest id)
		// (userInvoiceIndex = email + totalInvoices)
		int totalInvoices = 0;
		String userInvoiceIndex = email + String.valueOf(totalInvoices);
		Invoice invoiceEntity = new Invoice();
		invoiceEntity.setUserID(userID);
		invoiceEntity.setTotalCost(invoiceDTO.getTotalPrice());
		invoiceEntity.setInvoiceDate(invoiceDate.toString());
		invoiceEntity.setShippingDate(shippingDate.toString());
		invoiceEntity.setNote(invoiceDTO.getNote());
		invoiceEntity.setOtherShippingAddress(otherShippingAddress);
		invoiceEntity.setUserInvoiceIndex(userInvoiceIndex);
		invoiceEntity = invoiceRepository.save(invoiceEntity);

		String invoiceID = null;
		List<Invoice> listInvoices = invoiceRepository.findAll();
		for (Invoice invoice1 : listInvoices) {
			if (invoice1.getUserInvoiceIndex() == userInvoiceIndex)
				invoiceID = invoice1.getInvoiceID();
		}
		// SHIPPINGINFO invoiceID, fullname, phone, address
		ShippingInfo shippingInfo = new ShippingInfo();
		shippingInfo.setInvoiceID(invoiceEntity.getInvoiceID());
		shippingInfo.setAddress(invoiceDTO.getShippingInfo().getAddress());
		shippingInfo.setFullname(invoiceDTO.getShippingInfo().getFullname());
		shippingInfo.setPhone(invoiceDTO.getShippingInfo().getPhone());
		shippingInfoRepository.save(shippingInfo);

		/*
		 * Insert new DETAILED INVOICE
		 */

		// DETAILEDINVOICE (invoiceID, productID, quantity, price)
		for (DetailedInvoiceDTO detailedInvoiceDTO : invoiceDTO.getDetailedInvoices()) {
			DetailedInvoice detailedInvoice = new DetailedInvoice();
			detailedInvoice.setInvoiceID(invoiceID);
			detailedInvoice.setPrice(detailedInvoiceDTO.getProductPrice());
			detailedInvoice.setProductID(detailedInvoiceDTO.getProductID());
			detailedInvoice.setQuantity(detailedInvoiceDTO.getQuantity());
			detailedInvoice.setTotalPrice(detailedInvoiceDTO.getTotalPrice());
			detailedInvoiceRepository.save(detailedInvoice);
		}

		User user2 = userRepository.findFirstByEmail(email);
		user2.setTotalInvoices(totalInvoices + 1);
	}

	public List<DetailedInvoiceDTO> getDetailedInvoices(List<DetailedInvoiceDTO> detailedInvoices) {

		for (DetailedInvoiceDTO detailedInvoice : detailedInvoices) {

			Product product = productRepository.findById(detailedInvoice.getProductID()).get();
			Category category = categoryRepository.findById(product.getCategoryID()).get();

			detailedInvoice.setProductID(product.getProductID());
			detailedInvoice.setProductName(product.getProductName());
			detailedInvoice.setProductPrice(product.getProductPrice());
			detailedInvoice.setImages(product.getImages());
			detailedInvoice.setCategorySlug(category.getCategorySlug());
			detailedInvoice.setTotalPrice(product.getProductPrice() * detailedInvoice.getQuantity());
		}
		return detailedInvoices;
	}

	public ShippingInfoDTO getShippingInfo(String email) {
		List<User> listUsers = userRepository.findAll();
		ShippingInfoDTO shippingInfoDTO = null;
		for (User user : listUsers) {
			if (user.getEmail() == email)
				;
			shippingInfoDTO = new ShippingInfoDTO(user);
		}

		return shippingInfoDTO;
	}

	public int calculateTotalInvoiceCost(List<DetailedInvoiceDTO> detailedInvoices) {
		int totalInvoiceCost = 0;
		for (DetailedInvoiceDTO detailedInvoice : detailedInvoices) {
			totalInvoiceCost += detailedInvoice.getTotalPrice();
		}
		return totalInvoiceCost;

	}

	@Override
	public List<Invoice> getAllUserInvoices() {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		if (auth != null && auth.isAuthenticated()) {
			Object principal = auth.getPrincipal();
			if (principal instanceof UserDetails) {
				userDetails = (UserDetails) principal;
			}
		}
		String email = userDetails.getUsername();
		User user = userRepository.findFirstByEmail(email);

		return invoiceRepository.findByUserID(user.getUserID());
	}

	@Override
	public InvoiceDTO getByInvoiceID(String invoiceID) {
		List<DetailedInvoiceDTO> detailedInvoices = new ArrayList<>();
		List<DetailedInvoice> listDetailedInvoices = detailedInvoiceRepository.findAll();
		for (DetailedInvoice detailedInvoice : listDetailedInvoices) {
			if (detailedInvoice.getInvoiceID() == invoiceID) {
				Product product = productRepository.findById(detailedInvoice.getProductID()).get();
				Category category = categoryRepository.findById(product.getCategoryID()).get();
				DetailedInvoiceDTO detailedInvoiceDTO = new DetailedInvoiceDTO(detailedInvoice, null, null);
				detailedInvoices.add(detailedInvoiceDTO);
			}
		}
		ShippingInfoDTO shippingInfo = null;
		List<ShippingInfo> listShippingInfos = shippingInfoRepository.findAll();
		for (ShippingInfo shippingInfo2 : listShippingInfos) {
			if (shippingInfo2.getInvoiceID() == invoiceID) {
				User user = userRepository.findById(invoiceRepository.findByInvoiceID(invoiceID).getUserID()).get();
				shippingInfo = new ShippingInfoDTO(user);
			}
		}
		Invoice invoice = invoiceRepository.findByInvoiceID(invoiceID);

		InvoiceDTO invoiceDTO = new InvoiceDTO();
		invoiceDTO.setDetailedInvoices(detailedInvoices);
		invoiceDTO.setNote(invoice.getNote());
		invoiceDTO.setShippingInfo(shippingInfo);
		invoiceDTO.setStatusInvoice(invoice.getStatusInvoice());
		invoiceDTO.setTotalPrice(invoice.getTotalCost());
		invoiceDTO.setShippingDate(invoice.getShippingDate());
		invoiceDTO.setInvoiceDate(invoice.getInvoiceDate());

		return invoiceDTO;
	}

	@Override
	public void updateReviewStatus(String invoiceID, String productID) {

	}

}
