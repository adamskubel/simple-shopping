package shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import shopping.dao.ProductDAO;
import shopping.model.ProductDTO;

@Controller
@RequestMapping("/store")
public class StoreController
{
	@Autowired
	private ProductDAO dao;


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
	public List<ProductDTO> loadProducts(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset, Model model)
	{
		return dao.getProducts(offset);
	}

}
