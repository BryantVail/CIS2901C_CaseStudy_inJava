package printing_shop.domain.products;

public interface IProductRepository<T extends BaseProduct, AddT extends AddProductRequest> {
	T add(AddT request);
	boolean delete(String id);
	Iterable<T> get();
	T get(String id);
	T updateDescription(String id, String newDescription);
	T updateName(String id, String newName);
	T updateCost(String id, double newCost);
}
