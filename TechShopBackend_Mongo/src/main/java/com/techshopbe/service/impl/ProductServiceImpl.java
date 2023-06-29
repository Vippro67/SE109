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
import org.springframework.data.domain.Sort;

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
			if (listProductDTO.size() >= 8)
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
			if (product.getCategoryID().equals(categoryID)) {
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

		Category category = categoryRepository.findByCategorySlug(categorySlug);
		List<Product> listProducts = productRepository.findByCategoryID(category.getCategoryID());
		List<ProductDTO> listProductDTOs = new ArrayList<ProductDTO>();
		Optional<Brand> brand;
		for (Product product : listProducts) {
			brand = brandRepository.findById(product.getBrandID());
			ProductDTO productDTO = new ProductDTO(product, brand.get(), category);
			listProductDTOs.add(productDTO);
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
		List<Product> listProducts = productRepository.findByCategoryID(product.get().getCategoryID());
		List<ProductDTO> relatedProducts = new ArrayList<ProductDTO>();
		for (Product product2 : listProducts) {
			ProductDTO productDTO = new ProductDTO(product2, brand.get(), category.get());
			relatedProducts.add(productDTO);
			if (relatedProducts.size() >= 4)
				break;
		}

		return relatedProducts;
	}

	@Override
	public List<ProductDTO> getRelatedBrandProducts(String productID) {
		Optional<Product> product = productRepository.findById(productID);
		Optional<Brand> brand = brandRepository.findById(product.get().getBrandID());
		Optional<Category> category = categoryRepository.findById(product.get().getCategoryID());
		List<Product> listProducts = productRepository.findByBrandID(product.get().getBrandID());
		List<ProductDTO> relatedProducts = new ArrayList<ProductDTO>();
		for (Product product2 : listProducts) {
			ProductDTO productDTO = new ProductDTO(product2, brand.get(), category.get());
			relatedProducts.add(productDTO);

			if (relatedProducts.size() >= 4)
				break;
		}

		return relatedProducts;
	}

	@Override
	public void updateRating(String productID, float rate) {
		Optional<Product> product = productRepository.findById(productID);

		RatingInfoDTO ratingInfoDTO = new RatingInfoDTO(product.get().getProductRate(),
				product.get().getTotalReviews());
		int newTotalReviews = ratingInfoDTO.getTotalReviews() + 1;
		float newRating = (ratingInfoDTO.getProductRate() * ratingInfoDTO.getTotalReviews() + rate) / newTotalReviews;

		product.get().setTotalReviews(newTotalReviews);
		product.get().setProductRate(newRating);

	}
	@Override
	public List<ProductDTO> getProductToAdvise(String categoryName, String brandName, double PriceMax,
			double PriceMin) {
		Sort sort = Sort.by(Sort.Direction.DESC, "productRate");
		List<Product> products = productRepository.findAll(sort);

		List<Brand> brands = brandRepository.findByBrandName(brandName);
		String brandID = brands.size() > 0 ? brands.get(0).getBrandID() : "";
		System.out.println(brandID);
		List<Category> categorys = categoryRepository.findByCategoryName(categoryName);
		String categoryID = categorys.size() > 0 ? categorys.get(0).getCategoryID() : "";
		System.out.println(categoryID);

		Optional<Brand> brand = brandRepository.findById(brandID);
		Optional<Category> category = categoryRepository.findById(categoryID);

		List<ProductDTO> ProductsToAdvise = new ArrayList<ProductDTO>();
		System.out.println(PriceMax);
		for (Product product : products) {
			if (product.getCategoryID().equals(categoryID) &&
					product.getBrandID().equals(brandID)
					&& product.getProductPrice() >= PriceMin && product.getProductPrice() <= PriceMax) {
				ProductDTO productDTO = new ProductDTO(product, brand.get(), category.get());
				ProductsToAdvise.add(productDTO);
			}
			if (ProductsToAdvise.size() == 1)
				break;
		}
		return ProductsToAdvise;
	}

}
