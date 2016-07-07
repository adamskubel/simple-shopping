package shopping.model;

public class ProductDTO
{
	private String name;
	private String imageId;
	//Price of the product in cents
	private long price;
	private String productId;
	
	public ProductDTO()
	{
		
	}

	public ProductDTO(String productId, String name, String imageId, long price) {
		this.productId = productId;
		this.name = name;
		this.imageId = imageId;
		this.price = price;
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

	public String getId()
	{
		return productId;
	}

	public void setId(String id)
	{
		this.productId = id;
	}

}
