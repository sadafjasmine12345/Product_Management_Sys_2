package com.pace.product.bean;

public class Product {
		protected int id;
		protected String name;
		protected String country;
		protected float price;
		
		
		public Product() {
		}
		
		
		public Product(String name, String country, float price) {
			super();
			this.name = name;
			this.country = country;
			this.price=price;		}

		public Product(int id, String name, String country,float price) {
			super();
			this.id = id;
			this.name = name;
			this.country = country;
			this.price=price;
		}


		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

		
}
