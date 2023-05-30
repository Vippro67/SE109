package com.techshopbe.dto;

import java.util.List;

import com.techshopbe.entity.Invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// chi tiet hoa don
@Data
@AllArgsConstructor
@NoArgsConstructor
// hoa don
public class InvoiceDTO {
	List<DetailedInvoiceDTO> detailedInvoices;
	ShippingInfoDTO shippingInfo;

	private String email;

	private int totalPrice;

	private String note;
	private String statusInvoice;
	
	private String shippingDate;
	private String invoiceDate;
	

	public InvoiceDTO(List<DetailedInvoiceDTO> detailedInvoices, ShippingInfoDTO shippingInfo, Invoice invoice) {
		this.detailedInvoices = detailedInvoices;
		this.shippingInfo = shippingInfo;
		this.email = shippingInfo.getFullname();
		totalPrice = invoice.getTotalCost();
		note = invoice.getNote();
		statusInvoice = invoice.getStatusInvoice();
		shippingDate = invoice.getShippingDate();
		invoiceDate = invoice.getInvoiceDate();
	}


}
