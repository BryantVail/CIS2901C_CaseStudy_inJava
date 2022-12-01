package printing_shop.domain;

public class AddInventoryProduct {
	private final String Description;
	private final double Cost;
	private final String Make;
	private final String Model;
	private final String Name;
	private final String Type;
	
	public AddInventoryProduct(
		double cost,
		String description, 
		String make, 
		String model,
		String name, 
		String type) {
		
		if(cost > 0){
			this.Cost = cost;
		}else {
			throw new IllegalArgumentException("cost cannot be negative");
		}
		
		if(description.isEmpty() == false){
			this.Description = description;
		}else{
			throw new IllegalArgumentException("description cannot be null/empty");
		}
		
		if(make.isEmpty() == false){
			this.Make = make;
		}else {
			throw new IllegalArgumentException("make cannot be null/empty");
		}
		
		this.Model = model;
		
		if(name.isEmpty() == false){
			this.Name = name;
		}else{
			throw new IllegalArgumentException("Name cannot be null/empty");
		}
		
		if(type.isEmpty() == false){
			this.Type = type;
		}else{
			throw new IllegalArgumentException("type cannot be null/empty");
		}
	}
}
