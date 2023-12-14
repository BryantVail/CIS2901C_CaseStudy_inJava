package printing_shop.domain.products;

import printing_shop.domain.exceptions.ExceptionMessages;

import java.time.LocalDateTime;
import java.util.*;

public class ClothingProduct extends BaseProduct{
	
	public final Set<String> SizesAvailable;
	public final Dictionary<String, Integer> MaterialComposition;
	public ClothingProduct(
		String id,
		double cost,
		LocalDateTime createdDateTime,
		String description,
		LocalDateTime lastUpdated,
		String make,
		String model,
		String name,
		String type,
		int markupPercentage,
		// new to this object below
		Set<String> sizesAvailable,
		Dictionary<String, Integer> materialComposition) {
		super(id, cost, createdDateTime, description, lastUpdated, make, model, name, type, markupPercentage);
		
		
		this.SizesAvailable =
			ClothingProduct
			.verifySizesAvailableHasNoEmptyStringValues(sizesAvailable);
		
		this.MaterialComposition =
			ClothingProduct.verifyMaterialComposition(materialComposition);
		
	}
	
	public ClothingProduct(
		BaseProduct baseProduct,
		Set<String> sizesAvailable,
		Dictionary<String, Integer> materialComposition){
		super(
			baseProduct.Id,
			baseProduct.getCost(),
			baseProduct.CreatedDateTime,
			baseProduct.Description,
			baseProduct.LastUpdated,
			baseProduct.Make,
			baseProduct.Model,
			baseProduct.Name,
			baseProduct.Type,
			baseProduct.MarkupPercentage);
		
		this.SizesAvailable =
			ClothingProduct
				.verifySizesAvailableHasNoEmptyStringValues(sizesAvailable);
		
		this.MaterialComposition =
			ClothingProduct.verifyMaterialComposition(materialComposition);
	}
	
	
	
	public static Set<String> verifySizesAvailableHasNoEmptyStringValues(
		Set<String> sizesAvailable){
		var sizesAvailableHashSet = new HashSet<String>();
		for(String size : sizesAvailable){
			if(size.isEmpty() == true){
				throw new IllegalArgumentException(
					ExceptionMessages.iterableFieldCannotBeNullMessage("sizesAvailable"));
			}else{
				sizesAvailableHashSet.add(size);
			}
		}
		return sizesAvailableHashSet;
	}
	
	public static Dictionary<String, Integer> verifyMaterialComposition(
		Dictionary<String, Integer> dictionaryOfMaterialAndPercentage){
		var dictionaryToAddVerifiedValuesInto = (Dictionary) new Hashtable<String, Integer>();
		var keys = dictionaryOfMaterialAndPercentage.keys();
		
		for(int i = 0; i < dictionaryOfMaterialAndPercentage.size(); i++){
			var currentKey = keys.nextElement();
			if(currentKey.isEmpty() == true){
				throw new IllegalArgumentException("material name cannot be empty/null");
			}else if(dictionaryOfMaterialAndPercentage.get(currentKey) <= 0){
				throw new IllegalArgumentException(
					"percentage cannot be less than or equal to zero|0");
			}else{
				dictionaryToAddVerifiedValuesInto.put(
					currentKey,
					dictionaryOfMaterialAndPercentage.get(currentKey));
			}// end if
		}// end for loop
		return dictionaryToAddVerifiedValuesInto;
	}
	
}
