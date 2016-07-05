package shopping.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import shopping.model.ProductDTO;
import shopping.util.DataSourceManager;

@Repository
public class ProductDAO
{
	private final static String CollectionName = "products";

	public ProductDAO() {

	}

	static Document mapProduct(ProductDTO product)
	{
		Document dbObj = new Document();
		dbObj.append("productId", product.getId());
		dbObj.append("name", product.getName());
		dbObj.append("price", product.getPrice());
		dbObj.append("imageId", product.getImageId());

		return dbObj;
	}

	static ProductDTO mapDocument(Document doc)
	{
		return new ProductDTO(doc.getString("productId"), doc.getString("name"), doc.getString("imageId"), doc.getLong("price"));
	}
	
	static List<Document> mapProducts(List<ProductDTO> products)
	{
		List<Document> docList = new ArrayList<>();		
		
		for (ProductDTO product : products){
			docList.add(ProductDAO.mapProduct(product));
		}
		
		return docList;
	}

	static List<ProductDTO> mapDocuments(List<Document> docList)
	{
		List<ProductDTO> products = new ArrayList<>();
		
		for (Document pd : docList){
			products.add(ProductDAO.mapDocument(pd));
		}
		
		return products;
	}

	public void addProduct(ProductDTO product)
	{
		MongoDatabase db = DataSourceManager.getInstance().getDatabase();
		MongoCollection<Document> coll = db.getCollection(CollectionName);
		coll.insertOne(mapProduct(product));

	}

	public List<ProductDTO> getProducts(long offset)
	{
		MongoDatabase db = DataSourceManager.getInstance().getDatabase();
		MongoCollection<Document> coll = db.getCollection(CollectionName);

		List<ProductDTO> list = new ArrayList<>();

		System.out.println(coll.count() + " items in collection " + CollectionName);
		MongoCursor<Document> cursor = coll.find().iterator();
		try {
			while (cursor.hasNext()) {
//				if (--offset < 0)
				System.out.println("Found product!");
					list.add(mapDocument(cursor.next()));
			}
		} finally {
			cursor.close();
		}

		return list;
	}

}
