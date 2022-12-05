package printing_shop.domain.products;

public interface IProductManager<TProduct extends BaseProduct, AddT extends AddProductRequest>{
	
	TProduct add(AddProductRequest request);
	boolean delete(String id);
	Iterable<TProduct> get();
	TProduct get(String id);
	TProduct updateDescription(String id, String newDescription);
	TProduct updateName(String id, String newName);
	TProduct updateCost(String id, double newCost);
	
}