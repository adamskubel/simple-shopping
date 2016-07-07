package shopping.model;

import java.util.List;

public class InvoiceDTO
{
	private String message;
	private String invoiceId;
	private List<ProductDTO> itemsBought;
	private long totalCost;
		
	public InvoiceDTO(String invoiceId, List<ProductDTO> itemsBought, long totalCost) {
		this.invoiceId = invoiceId;
		this.itemsBought = itemsBought;
		this.totalCost = totalCost;
	}
	
	
	public static InvoiceDTO buildInvoice(List<ProductDTO> products, String invoiceId)
	{
		long totalCost = 0;
		for (ProductDTO product : products){
			product.setItemTotal(product.getPrice() * product.getQuantity());
			totalCost += product.getItemTotal();
		}
		return new InvoiceDTO(invoiceId, products, totalCost);
	}
	
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	public List<ProductDTO> getItemsBought()
	{
		return itemsBought;
	}
	public void setItemsBought(List<ProductDTO> itemsBought)
	{
		this.itemsBought = itemsBought;
	}

	/**
	 * Total paid by customer in cents
	 * @return
	 */
	public long getTotalCost()
	{
		return totalCost;
	}

	public void setTotalCost(long totalCost)
	{
		this.totalCost = totalCost;
	}

	public String getInvoiceId()
	{
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId)
	{
		this.invoiceId = invoiceId;
	}
	
	
	
}
