package printing_shop.domain.products;

import printing_shop.domain.exceptions.ExceptionMessages;

public class BaseProduct implements IPriceable {
	public String Id;
	private double Cost;
	public final String Description;
	public final String Make;
	public final String Model;
	public final String Name;
	public final String Type;
	public int MarkupPercentage;
	
	
	public BaseProduct(
		String id,
		String description,
		String make,
		String model,
		String name,
		String type,
		int markupPercentage) {
		
		if(id.isEmpty() == true){
			throw new IllegalArgumentException(ExceptionMessages.fieldCannotBeNullMessage("\"id\""));
		}else{
			this.Id = id;
		}
		
		if(description.isEmpty() == true){
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("\"description\""));
		}else{
			this.Description = description;
		}
		
		if(make.isEmpty() == true){
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("make"));
		}else{
			this.Make = make;
		}
		
		if(model.isEmpty() == true){
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("model"));
		}else{
			this.Model = model;
		}
		
		if(name.isEmpty() == true){
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("name"));
		}else{
			this.Name = name;
		}
		
		if(type.isEmpty() == true){
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("type"));
		}else{
			this.Type = type;
		}
		
	}
	

	public double setCost(double cost){
		if(cost < 0){
			throw new IllegalArgumentException("cost cannot be less than 0");
		}else{
			this.Cost = cost;
			return this.Cost;
		}
	}
	
	public double getCost(){
		return this.Cost;
	}
	
	public int setMarkupPercentage(int markupPercentage){
		if(markupPercentage < 0){
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("markupPercentage"));
		}else{
			this.MarkupPercentage = markupPercentage;
			return this.MarkupPercentage;
		}
	}
	
	
	
	@Override
	public double getRetailPrice() {
		// make markup percentage a percentage from int
		// > 25% markup int becomes 1.25 * {cost}
		return (this.Cost *(1+(this.MarkupPercentage/100)));
	}
	
}
