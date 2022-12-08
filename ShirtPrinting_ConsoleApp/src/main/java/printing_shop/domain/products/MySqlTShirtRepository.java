package printing_shop.domain.products;

import printing_shop.domain.exceptions.DatabaseInternalException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlTShirtRepository implements
	IProductRepository<TShirtProduct, AddTShirtProductRequest>{
	private final String connectionString;
	private final IProductRepository<BaseProduct, AddProductRequest> mySqlProductRepository;
	private final IProductRepository<ClothingProduct, AddClothingProductRequest> mySqlClothingProductRepository;
	
	public MySqlTShirtRepository(
		String connectionString,
		IProductRepository<ClothingProduct, AddClothingProductRequest> mySqlClothingProductRepository,
		IProductRepository<BaseProduct, AddProductRequest> mySqlProductRepository
	){
		this.connectionString = connectionString;
		this.mySqlProductRepository = mySqlProductRepository;
		this.mySqlClothingProductRepository = mySqlClothingProductRepository;
	}
	
	
	@Override
	public TShirtProduct add(AddTShirtProductRequest request) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
				this.connectionString);
			
			
			var resultSet = callableStatement.executeQuery();
			if(resultSet.next()){
				var product = createProduct(resultSet);
				connection.close();
				return product;
			}
		} catch (ClassNotFoundException e) {
			throw new DatabaseInternalException("connection error with provider");
		} catch (SQLException e) {
			throw new DatabaseInternalException("database technology error");
		}
	}
	
	private
	
	@Override
	public boolean delete(String id) {
		return false;
	}
	
	@Override
	public Iterable<TShirtProduct> get() {
		return null;
	}
	
	@Override
	public TShirtProduct get(String id) {
		return null;
	}
	
	@Override
	public TShirtProduct updateDescription(String id, String newDescription) {
		return null;
	}
	
	@Override
	public TShirtProduct updateName(String id, String newName) {
		return null;
	}
	
	@Override
	public TShirtProduct updateCost(String id, double newCost) {
		return null;
	}
}
