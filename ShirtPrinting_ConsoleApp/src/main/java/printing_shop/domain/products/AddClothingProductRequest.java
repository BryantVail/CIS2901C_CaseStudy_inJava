package printing_shop.domain.products;

import java.util.Dictionary;
import java.util.Set;

public class AddClothingProductRequest extends AddProductRequest{
	public final Set<String> SizesAvailable;
	public final Dictionary<String, Integer> MaterialComposition;
	
	public AddClothingProductRequest(
		double cost,
		String description,
		String make,
		String model,
		int markupPercentage,
		String name,
		String type,
		Dictionary<String, Integer> materialComposition,
		Set<String> sizesAvailable) {
		super(cost, description, make, model, markupPercentage, name, type);
		
		this.SizesAvailable =
			ClothingProduct
			.verifySizesAvailableHasNoEmptyStringValues(sizesAvailable);
		
		this.MaterialComposition =
			ClothingProduct
			.verifyMaterialComposition(materialComposition);
	}
}
