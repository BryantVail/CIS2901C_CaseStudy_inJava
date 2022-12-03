package printing_shop.domain.products;

import printing_shop.domain.exceptions.ExceptionMessages;

import java.util.Dictionary;
import java.util.Set;

public class TShirtProduct extends ClothingProduct{
	
	public final String SleeveType;
	public final String NeckCut;
	
	public TShirtProduct(
		String id,
		String description,
		String make,
		String model,
		String name,
		String type,
		int markupPercentage,
		Set<String> sizesAvailable,
		Dictionary<String, Integer> materialComposition,
		String sleeveType,
		String neckCut) {
		super(id, description, make, model, name, type, markupPercentage, sizesAvailable, materialComposition);
		
		if(sleeveType.isEmpty() == true){
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("sleeveType"));
		}else{
			this.SleeveType = sleeveType;
		}
		
		if(neckCut.isEmpty() == true){
			throw new IllegalArgumentException(
				ExceptionMessages.fieldCannotBeNullMessage("neckCut"));
		}else{
			this.NeckCut = neckCut;
		}
	}
}
