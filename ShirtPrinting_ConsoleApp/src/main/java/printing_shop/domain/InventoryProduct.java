package printing_shop.domain;

public class InventoryProduct implements IPriceable {
	public String Id;
	public double Cost;
	public String Description;
	public String Make;
	public String Model;
	public String Name;
	public String Type;
	
	public int MarkupPercentage;
	
	@Override
	public double getRetailPrice() {
		// make markup percentage a percentage from int
		// > 25% markup int becomes 1.25 * {cost}
		return (this.Cost *(1+(this.MarkupPercentage/100)));
	}
	
}
