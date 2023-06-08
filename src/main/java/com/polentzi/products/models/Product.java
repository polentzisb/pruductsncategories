package com.polentzi.products.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String description;
	    private double price;
	    @Column(updatable=false)
	    private Date createdAt;
	    private Date updatedAt;
	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	        name = "categories_products", 
	        joinColumns = @JoinColumn(name = "product_id"), 
	        inverseJoinColumns = @JoinColumn(name = "category_id")
	    )
	    private List<Category> categories;
	    
	    public Product() {
	        
	    }

		public Product(Long id, String name, String description, double price, List<Category> categories) {
			this.id = id;
			this.name = name;
			this.description = description;
			this.price = price;
			this.categories = categories;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public List<Category> getCategories() {
			return categories;
		}

		public void setCategories(List<Category> categories) {
			this.categories = categories;
		}
		@PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.setUpdatedAt(new Date());
	    }

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}

}