package printing_shop.domain.products;

import printing_shop.domain.exceptions.DatabaseInternalException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlBaseProductRepository implements IProductRepository<BaseProduct, AddProductRequest>{
	
	private final String connectionString;
	
	public MySqlBaseProductRepository(String connectionString) {
		this.connectionString = connectionString;
	}
	
	private BaseProduct createProduct(ResultSet resultSet){
		try {
			return new BaseProduct(
				String.valueOf(resultSet.getInt("Id")),
				resultSet.getDouble("cost"),
				resultSet.getString("description"),
				resultSet.getString("make"),
				resultSet.getString("model"),
				resultSet.getString("name"),
				resultSet.getString("type"),
				resultSet.getInt("markupPercentage"));
		} catch (SQLException e) {
			throw new DatabaseInternalException("code command with database object error");
		}
	}
	@Override
	public BaseProduct add(AddProductRequest request) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
				this.connectionString);
			
			// add TShirtProduct
			var callableStatement = connection.prepareCall(
				"{call addBaseProduct(?,?,?,?,?,?,?)}");// 7 params
			
			callableStatement.setDouble("cost", request.getCost());
			callableStatement.setString("description", request.Description);
			callableStatement.setString("make", request.Make);
			callableStatement.setInt("markupPercentage", request.getMarkupPercentage());
			callableStatement.setString("model", request.Model);
			callableStatement.setString("name", request.Name);
			callableStatement.setString("type", request.Type);
			
			var resultSet = callableStatement.executeQuery();
			if(resultSet.next()){
				var product = createProduct(resultSet);
				connection.close();
				return product;
			}else{
				connection.close();
				throw new SQLException("no values returned from database call");
			}
			
		} catch (SQLException e) {
			throw new DatabaseInternalException("Database System error");
		} catch (ClassNotFoundException e) {
			throw new DatabaseInternalException("package to connect to database error");
		}
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
