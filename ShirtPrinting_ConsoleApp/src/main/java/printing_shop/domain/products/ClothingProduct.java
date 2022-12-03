package printing_shop.domain.products;

import printing_shop.domain.exceptions.ExceptionMessages;

import java.util.Dictionary;
import java.util.Set;

public class ClothingProduct extends BaseProduct{
	
	public final Set<String> SizesAvailable;
	public final Dictionary<String, Integer> MaterialComposition;
	public ClothingProduct(
		String id,
		String description,
		String make,
		String model,
		String name,
		String type,
		int markupPercentage,
		Set<String> sizesAvailable,
		Dictionary<String, Integer> materialComposition) {
		super(id, description, make, model, name, type, markupPercentage);
		
		for(String size : sizesAvailable){
			if(size.isEmpty() == true){
				throw new IllegalArgumentException(
					ExceptionMessages.iterableFieldCannotBeNullMessage("sizesAvailable"));
			}else{
				//
			}
		}
		this.SizesAvailable = sizesAvailable;
		
		this.MaterialComposition = materialComposition;
		
	}
	
	
	
}
