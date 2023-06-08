package com.polentzi.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.polentzi.products.models.Category;
import com.polentzi.products.models.Product;
import com.polentzi.products.repositories.CategoryRepository;
import com.polentzi.products.repositories.ProductRepository;

@Service
public class ProductsAndCategoriesService {
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
    
    public ProductsAndCategoriesService(CategoryRepository categoryRepository,ProductRepository productRepository ) {
        this.categoryRepository = categoryRepository;
        this.productRepository=productRepository;
    }
    public List<Product> allProducts() {
        return productRepository.findAll();
    }
    
    
    public Product createProduct(Product b) {
        return productRepository.save(b);
    }
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }
    
    public Category createCategory(Category b) {
        return categoryRepository.save(b);
    }
    public Product findProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            return null;
        }
    }
    public Category findCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            return null;
        }
    }
    public Product addProduct(Long prodId, Long catId) {
		Product product = productRepository.findById(prodId).orElse(null);
		Category category = categoryRepository.findById(catId).orElse(null);
		product.getCategories().add(category);
		return productRepository.save(product);
	}
    public Category addCategory(Long catId, Long prodId) {
		Product product = productRepository.findById(prodId).orElse(null);
		Category category = categoryRepository.findById(catId).orElse(null);
		category.getProducts().add(product);
		return categoryRepository.save(category);
	}
    public List<Product> otherProducts(Category category) {
		return this.productRepository.findByCategoriesNotContains(category);
	}
    public List<Category> otherCategories(Product product) {
		return this.categoryRepository.findByProductsNotContains(product);
	}

}