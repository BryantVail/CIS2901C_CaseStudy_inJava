package printing_shop.domain.products;

public class MySqlClothingRepository implements IProductRepository<ClothingProduct, AddClothingProductRequest> {
	
	private final String connectionString;
	private final IProductRepository<BaseProduct, AddProductRequest> mySqlProductRepository;
	
	public MySqlClothingRepository(
		String connectionString,
		IProductRepository<BaseProduct, AddProductRequest> mySqlProductRepository
	){
		this.connectionString = connectionString;
		this.mySqlProductRepository = mySqlProductRepository;
	}
	@Override
	public ClothingProduct add(AddClothingProductRequest request) {
		AddProductRequest baseProductRequest = request;
		var baseProduct =
			this.mySqlProductRepository.add(baseProductRequest);
		
		// run stored procedure
		
	}
	
	@Override
	public boolean delete(String id) {
		return false;
	}
	
	@Override
	public Iterable<ClothingProduct> get() {
		return null;
	}
	
	@Override
	public ClothingProduct get(String id) {
		return null;
	}
	
	@Override
	public ClothingProduct updateDescription(String id, String newDescription) {
		return null;
	}
	
	@Override
	public ClothingProduct updateName(String id, String newName) {
		return null;
	}
	
	@Override
	public ClothingProduct updateCost(String id, double newCost) {
		return null;
	}
}
