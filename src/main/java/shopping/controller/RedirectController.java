package shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController
{
    
    @RequestMapping("/shopping")
    public String redirect()
    {
    	return "redirect:index.html";
    }
   
    @RequestMapping("/cart")
    public String redirectCart()
    {
    	return "redirect:index.html";
    }
}