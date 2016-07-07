package shopping;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import org.junit.Test;

import shopping.dao.ProductDAO;
import shopping.model.ProductDTO;
import shopping.util.Config;

public class TestUtil
{
//	@Test
	public void generateTestData() throws Exception
	{		
		//MongoCollection<Document> collection = DataSourceManager.getInstance().getDatabase().getCollection("products");
		//collection.insertOne(ProductDAO.);
		ProductDAO dao = new ProductDAO();
		Path dir = FileSystems.getDefault().getPath("src/main/webapp/img");
		Config.init("");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) 
		{
			for (Path entry: stream) {
				String id = entry.getFileName().toString();
				dao.addProduct(new ProductDTO(id, "Name_"+id, id, new Random().nextInt(1000)));
			}
		}
	}
}
