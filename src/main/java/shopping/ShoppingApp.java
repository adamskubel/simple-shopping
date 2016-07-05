package shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import shopping.util.Config;
//import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ShoppingApp // extends SpringBootServletInitializer
{

	// @Override
	// protected SpringApplicationBuilder configure(SpringApplicationBuilder
	// application) {
	// return application.sources(ShoppingApp.class);
	// }

	public static void main(String[] args) throws Exception
	{
		Config.init("");
		SpringApplication.run(ShoppingApp.class, args);
	}

}
