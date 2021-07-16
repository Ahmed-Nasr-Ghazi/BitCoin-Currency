package com.currency.cryptocurrency.entity;

public class BitCoinEntity {

	private String date;
	private String price;
	private String generatedCoins;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "BitCoinEntity [date=" + date + ", price=" + price + ", generatedCoins=" + generatedCoins + "]";
	}
	
	public String getGeneratedCoins() {
		return generatedCoins;
	}
	public void setGeneratedCoins(String string) {
		this.generatedCoins = string;
	}
	
	
	
	
}
