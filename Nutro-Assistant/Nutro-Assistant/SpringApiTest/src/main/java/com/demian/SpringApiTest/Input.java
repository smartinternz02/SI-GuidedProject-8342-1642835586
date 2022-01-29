package com.demian.SpringApiTest;

import org.springframework.stereotype.Component;

@Component
public class Input {
	private String ques;
	private String dishName;
	private String CalorieCount;
	private String itemName;
	private String weightUnit;
	private String sUnit;
	private String SAmount;
	
	public String getQues() {
		return ques;
	}
	public void setQues(String ques) {
		this.ques = ques;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public String getCalorieCount() {
		return CalorieCount;
	}
	public void setCalorieCount(String calorieCount) {
		CalorieCount = calorieCount;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	public String getsUnit() {
		return sUnit;
	}
	public void setsUnit(String sUnit) {
		this.sUnit = sUnit;
	}
	public String getSAmount() {
		return SAmount;
	}
	public void setSAmount(String sAmount) {
		SAmount = sAmount;
	}

}
