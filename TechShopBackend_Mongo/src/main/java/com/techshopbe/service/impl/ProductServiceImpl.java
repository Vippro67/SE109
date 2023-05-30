package com.techshopbe.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;
import com.techshopbe.dto.RatingInfoDTO;
import com.techshopbe.entity.Brand;
import com.techshopbe.entity.Category;
import com.techshopbe.entity.Product;
import com.techshopbe.repository.BrandRepository;
import com.techshopbe.repository.CategoryRepository;
import com.techshopbe.repository.ProductRepository;
import com.techshopbe.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<ProductDTO> getAll() {
		List<Product> listProduct = productRepository.findAll();
		List<ProductDTO> listProductDTO = new ArrayList<>();

		Optional<Brand> brand;
		Optional<Category> category;
		for (Product product : listProduct) {
			brand = brandRepository.findById(product.getBrandID());
			category = categoryRepository.findById(product.getCategoryID());

			ProductDTO productDTO = new ProductDTO(product, brand.get(), category.get());
			listProductDTO.add(productDTO);
		}
		return listProductDTO;
	}

	@Override
	public List<ProductDTO> getTrendingProducts() {
		List<Product> listProduct = productRepository.findAll();
		List<ProductDTO> listProductDTO = new ArrayList<>();

		Optional<Brand> brand;
		Optional<Category> category;
		for (Product product : listProduct) {
			brand = brandRepository.findById(product.getBrandID());
			category = categoryRepository.findById(product.getCategoryID());

			ProductDTO productDTO = new ProductDTO(product, brand.get(), category.get());
			listProductDTO.add(productDTO);
			if(listProductDTO.size()>=8)
				break;
		}
		return listProductDTO;
	}

	@Override
	public List<ProductDTO> getTopPurchasedProducts(String categoryID) {

		List<Product> listProducts = productRepository.findAll();
		List<ProductDTO> listProductDTOs = new ArrayList<ProductDTO>();
		Optional<Brand> brand;
		Optional<Category> category;
		for (Product product : listProducts) {
			if(product.getCategoryID().equals(categoryID))
			{
				brand = brandRepository.findById(product.getBrandID());
				category = categoryRepository.findById(categoryID);
				ProductDTO productDTO = new ProductDTO(product, brand.get(), category.get());
				listProductDTOs.add(productDTO);
			}
		}
		return listProductDTOs;
	}

	@Override
	public List<ProductDTO> getProductsByCategory(String categorySlug) {

		List<Product> listProducts = productRepository.findAll();
		List<ProductDTO> listProductDTOs = new ArrayList<ProductDTO>();
		Optional<Brand> brand;

		Optional<Category> category;
		for (Product product : listProducts) {
			brand = brandRepository.findById(product.getBrandID());

			category = categoryRepository.findById(product.getCategoryID());
			if (category.get().getCategorySlug().equals(categorySlug)) {
				ProductDTO productDTO = new ProductDTO(product, brand.get(), category.get());
				listProductDTOs.add(productDTO);

			}
		}
		return listProductDTOs;
	}

	@Override
	public DetailedProductDTO getDetailedProduct(String productID) {

		Optional<Product> product = productRepository.findById(productID);
		Optional<Brand> brand = brandRepository.findById(product.get().getBrandID());
		Optional<Category> category = categoryRepository.findById(product.get().getCategoryID());

		DetailedProductDTO detailedProductDTO = new DetailedProductDTO(product.get(), brand.get(), category.get());

		return detailedProductDTO;
	}

	@Override
	public List<ProductDTO> getRelatedCategoryProducts(String productID) {
		Optional<Product> product = productRepository.findById(productID);
		Optional<Brand> brand = brandRepository.findById(product.get().getBrandID());
		Optional<Category> category = categoryRepository.findById(product.get().getCategoryID());
		List<Product> listProducts = productRepository.findAll();
		List<ProductDTO> relatedProducts = new ArrayList<ProductDTO>();
			for (Product product2 : listProducts) {
				if(product2.getCategoryID().equals(category.get().getCategoryID()))
				{
					ProductDTO productDTO = new ProductDTO(product2, brand.get(), category.get());
					relatedProducts.add(productDTO);
				}
				if(relatedProducts.size()>=4)
					break;
			}
		
		return relatedProducts;
	}

	@Override
	public List<ProductDTO> getRelatedBrandProducts(String productID) {
		Optional<Product> product = productRepository.findById(productID);
		Optional<Brand> brand = brandRepository.findById(product.get().getBrandID());
		Optional<Category> category = categoryRepository.findById(product.get().getCategoryID());
		List<Product> listProducts = productRepository.findAll();
		List<ProductDTO> relatedProducts = new ArrayList<ProductDTO>();
			for (Product product2 : listProducts) {
				if(product2.getBrandID().equals(brand.get().getBrandID()))
				{
					ProductDTO productDTO = new ProductDTO(product2, brand.get(), category.get());
					relatedProducts.add(productDTO);
				}
				if(relatedProducts.size()>=4)
					break;
			}
		
		return relatedProducts;
	}

	@Override
	public void updateRating(String productID, float rate) {
		Optional<Product> product = productRepository.findById(productID);

		RatingInfoDTO ratingInfoDTO = new RatingInfoDTO(product.get().getProductRate(), product.get().getTotalReviews());
		int newTotalReviews = ratingInfoDTO.getTotalReviews() + 1;
		float newRating = (ratingInfoDTO.getProductRate() * ratingInfoDTO.getTotalReviews() + rate) / newTotalReviews;
		
		product.get().setTotalReviews(newTotalReviews);
		product.get().setProductRate(newRating);
	}

}
