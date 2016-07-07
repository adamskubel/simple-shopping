package shopping.model;

public class ProductDTO
{
	private String name;
	private String imageId;
	//Price of the product in cents
	private long price;
	private String productId;
	private long quantity;
	private long itemTotal;
	
	public ProductDTO()
	{
		
	}

	public ProductDTO(String productId, String name, String imageId, long price) {
		this.productId = productId;
		this.name = name;
		this.imageId = imageId;
		this.price = price;
		this.quantity = 1;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getImageId()
	{
		return imageId;
	}

	public void setImageId(String imageId)
	{
		this.imageId = imageId;
	}

	public long getPrice()
	{
		return price;
	}

	public void setPrice(long price)
	{
		this.price = price;
	}

	public String getProductId()
	{
		return productId;
	}

	public void setProductId(String id)
	{
		this.productId = id;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(long itemTotal) {
		this.itemTotal = itemTotal;
	}
	
	

}
