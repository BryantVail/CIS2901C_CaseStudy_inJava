package printing_shop.domain.products;

import printing_shop.domain.exceptions.ExceptionMessages;

public class AddProductRequest {
	public final String Description;
	private double Cost;
	public final String Make;
	private int MarkupPercentage;
	public final String Model;
	public final String Name;
	public final String Type;
	
	public AddProductRequest(
		double cost,
		String description, 
		String make, 
		String model,
		int markupPercentage,
		String name, 
		String type) {
		
		setCost(cost);
		
		if(description.isEmpty() == false){
			this.Description = description;
		}else{
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("description"));
		}
		
		if(make.isEmpty() == false){
			this.Make = make;
		}else {
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("make"));
		}
		
		this.Model = model;
		
		if(name.isEmpty() == false){
			this.Name = name;
		}else{
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("name"));
		}
		
		if(type.isEmpty() == false){
			this.Type = type;
		}else{
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("type"));
		}
	}
	
	public double setCost(double cost){
		if(cost > 0){
			this.Cost = cost;
			return this.Cost;
		}else {
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("cost"));
		}
	}
	
	public double getCost(){
		return this.Cost;
	}
	
	public int setMarkupPercentage(int cost){
		if(cost > 0){
			this.MarkupPercentage = cost;
			return this.MarkupPercentage;
		}else {
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("markupPercentage"));
		}
	}
	
	public int getMarkupPercentage(){
		return this.MarkupPercentage;
	}
}
