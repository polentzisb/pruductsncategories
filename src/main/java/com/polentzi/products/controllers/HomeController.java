package com.polentzi.products.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.polentzi.products.models.Category;
import com.polentzi.products.models.Product;
import com.polentzi.products.services.ProductsAndCategoriesService;

import jakarta.validation.Valid;

@Controller
public class HomeController {
	private final ProductsAndCategoriesService productsAndCategoriesService;
    
    public HomeController(ProductsAndCategoriesService productsAndCategoriesService) {
        this.productsAndCategoriesService = productsAndCategoriesService;
        
    }
    @RequestMapping("/products/new")
    public String index(@ModelAttribute("product")Product product) {
    	return "index.jsp";
	}
    
    @RequestMapping(value="/products/new", method=RequestMethod.POST)
	public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
		if(result.hasErrors()) {
			
			return "redirect:/products/new";
		}else {
			productsAndCategoriesService.createProduct(product);
			return "redirect:/categories/new";
		}
	}
    
    @RequestMapping("/categories/new")
    public String index1(@ModelAttribute("category")Category category,Model model) {
		return "index1.jsp";
	}
    
    @RequestMapping(value="/categories/new", method=RequestMethod.POST)
	public String createcategory(@Valid @ModelAttribute("category") com.polentzi.products.models.Category category, BindingResult result, Model model) {
		if(result.hasErrors()) {
			
			return "redirect:/categories/new";
		}else {
			productsAndCategoriesService.createCategory(category);
			return "redirect:/products/new";
		}
	}
    
    @GetMapping("/products/{id}")
	public String showProduct(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", this.productsAndCategoriesService.findProduct(id));
		model.addAttribute("categories", this.productsAndCategoriesService.otherCategories(this.productsAndCategoriesService.findProduct(id)));
		return "show_product.jsp";
	}
    
    @PostMapping("/products/add/categories")
	public String addCategoryToProduct(@RequestParam(value = "product_id") Long product_id,
			@RequestParam(value = "category_id") Long category_id) {
		this.productsAndCategoriesService.addCategory(product_id, category_id);
		return "redirect:/products/new";
	}
    
    @GetMapping("/categories/{id}")
	public String showCategory(@PathVariable("id") Long id, Model model) {
		model.addAttribute("category", this.productsAndCategoriesService.findCategory(id));
		model.addAttribute("products", this.productsAndCategoriesService.otherProducts(this.productsAndCategoriesService.findCategory(id)));
		return "show_category.jsp";
	}
	
	@PostMapping("/categories/add/products")
	public String addProductToCategory(@RequestParam(value = "product_id") Long product_id,
			@RequestParam(value = "category_id") Long category_id) {
		this.productsAndCategoriesService.addProduct(product_id, category_id);
		return "redirect:/products/new";
	}
   
    

}