package printing_shop.domain.products;

public interface IProductManager<BaseProduct, AddProductRequest>{
	
	BaseProduct add(AddProductRequest request);
	boolean delete(String id);
	Iterable<BaseProduct> get();
	BaseProduct get(String id);
	BaseProduct updateDescription(String id, String newDescription);
	BaseProduct updateName(String id, String newName);
	BaseProduct updateCost(String id, double newCost);
	
}