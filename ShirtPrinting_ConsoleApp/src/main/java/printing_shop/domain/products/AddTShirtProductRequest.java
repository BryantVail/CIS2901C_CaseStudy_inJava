package printing_shop.domain.products;

import printing_shop.domain.exceptions.ExceptionMessages;

import java.util.Dictionary;
import java.util.Set;

public class AddTShirtProductRequest extends AddClothingProductRequest{
	public final String NeckCut;
	public final String SleeveType;
	
	
	public AddTShirtProductRequest(
		double cost,
		String description,
		String make,
		String model,
		int markupPercentage,
		String name,
		String type,
		Dictionary<String, Integer> materialComposition,
		Set<String> sizesAvailable,
		String neckCut,
		String sleeveType) {
		super(cost, description, make, model, markupPercentage, name, type, materialComposition, sizesAvailable);
		
		if(neckCut.isEmpty() == true){
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("neckCut"));
		}else{
			this.NeckCut = neckCut;
		}
		
		if(sleeveType.isEmpty() == true){
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("sleeveType"));
		}else{
			this.SleeveType = sleeveType;
		}
	}
}
