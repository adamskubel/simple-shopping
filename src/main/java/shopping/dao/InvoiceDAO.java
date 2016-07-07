package shopping.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.tomcat.util.buf.HexUtils;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import shopping.model.InvoiceDTO;
import shopping.model.ProductDTO;
import shopping.util.DataSourceManager;

@Repository
public class InvoiceDAO extends BaseDAO<InvoiceDTO>
{
	private final static String CollectionName = "invoices";

	public InvoiceDAO() {

	}

	
	public String getNextInvoiceId()
	{
		MongoDatabase db = DataSourceManager.getInstance().getDatabase();
		MongoCollection<Document> coll = db.getCollection(CollectionName);		
		return hashInvoiceId(coll.count());
	}

	//Simple hashing to obfuscate our order statistics
	//SHA-256 then take least significant quarter the output
	private String hashInvoiceId(long id)
	{
		MessageDigest digest;
		try {
			digest = java.security.MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);			
		}
		digest.update(String.valueOf(id).getBytes());
		return HexUtils.toHexString(digest.digest()).substring(48);
	}
	
	@Override
	Document convertToDocument(InvoiceDTO product)
	{
		Document dbObj = new Document();
		dbObj.append("invoiceId", product.getInvoiceId());
		dbObj.append("total", product.getTotalCost());
		dbObj.append("itemsBought", ProductDAO.mapProducts(product.getItemsBought()));

		return dbObj;
	}

	@Override
	InvoiceDTO convertToDTO(Document doc)
	{			
		InvoiceDTO invoice = new InvoiceDTO(doc.getString("invoiceId"), ProductDAO.mapDocuments((List<Document>)doc.get("itemsBought")),doc.getLong("price"));
		return invoice;
	}

	@Override
	public void add(InvoiceDTO product)
	{
		MongoDatabase db = DataSourceManager.getInstance().getDatabase();
		MongoCollection<Document> coll = db.getCollection(CollectionName);
		coll.insertOne(convertToDocument(product));

	}

	public List<InvoiceDTO> getInvoices(long offset)
	{
		MongoDatabase db = DataSourceManager.getInstance().getDatabase();
		MongoCollection<Document> coll = db.getCollection(CollectionName);

		List<InvoiceDTO> list = new ArrayList<>();

		MongoCursor<Document> cursor = coll.find().iterator();
		try {
			while (cursor.hasNext()) {
				if (--offset < 0)
					list.add(convertToDTO(cursor.next()));
			}
		} finally {
			cursor.close();
		}

		return list;
	}

}
