package printing_shop.tests.products;

import org.junit.jupiter.api.Test;
import printing_shop.domain.products.AddProductRequest;
import printing_shop.domain.products.MySqlBaseProductRepository;

public class BaseProductRepository_Tests {
	
	private final String connectionString;
	
	public BaseProductRepository_Tests(
		String connectionString){
		this.connectionString = "";
	}
	
	@Test
	public void add_given_valid_input_when_stored_procedure_runs_then_each_field_will_have_the_value_used_to_create_the_object(){
		// arrange
		var repository = new MySqlBaseProductRepository(this.connectionString);
		// act
		var product = repository.add(
			new AddProductRequest(
				35.00,
				"description",
				"make",
				"model",
				10,
				"name",
				"type"
			)
		);
		// assert
	}
}
