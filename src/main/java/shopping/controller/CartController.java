package shopping.controller;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import shopping.dao.InvoiceDAO;
import shopping.model.InvoiceDTO;
import shopping.model.ProductDTO;

@Controller
@RequestMapping("/checkout")
public class CartController
{

	@Autowired
	private InvoiceDAO dao;

	
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public InvoiceDTO processCheckout(@RequestBody List<ProductDTO> products)
    {
    	validateCheckout(products);	
    	InvoiceDTO invoice = InvoiceDTO.buildInvoice(products, dao.getNextInvoiceId());
    	dao.add(invoice);
    	System.out.println("Checkout for transaction " + invoice.getInvoiceId() + " complete");
    	return invoice;
    }
    
    private void validateCheckout(List<ProductDTO> products)
    {
    	if (products.isEmpty()) throw new ValidationException("Oh no, there's no products!");
    }
}
