package printing_shop.domain.products;

public class ShirtProductManager implements IProductManager<BaseProduct, AddProductRequest>{
	
	private final IProductRepository<BaseProduct, AddProductRequest> shirtProductRepository;
	
	public ShirtProductManager(
		IProductRepository<BaseProduct, AddProductRequest> shirtProductRepository
	){
		this.shirtProductRepository = shirtProductRepository;
	}
	@Override
	public BaseProduct add(AddProductRequest addProductRequest) {
		var newProduct =
			this.shirtProductRepository.add(addProductRequest);
		// log
		
		return newProduct;
		
	}
	
	@Override
	public boolean delete(String id) {
		return false;
	}
	
	@Override
	public Iterable<BaseProduct> get() {
		return null;
	}
	
	@Override
	public BaseProduct get(String id) {
		return null;
	}
	
	@Override
	public BaseProduct updateDescription(String id, String newDescription) {
		return null;
	}
	
	@Override
	public BaseProduct updateName(String id, String newName) {
		return null;
	}
	
	@Override
	public BaseProduct updateCost(String id, double newCost) {
		return null;
	}
}
