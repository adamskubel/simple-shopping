package shopping.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DataSourceManager
{
	private static DataSourceManager instance;

	public static DataSourceManager getInstance()
	{
		if (instance == null)
			instance = new DataSourceManager();
		return instance;
	}
	
	private MongoClient mongoClient;
	
	private DataSourceManager()
	{
		mongoClient = new MongoClient(Config.getInstance().getDatabaseHost(), Config.getInstance().getDatabasePort());
		System.out.println("Connected to database");
	}
	
	public MongoClient getClient(){
		return mongoClient;
	}
	
	public MongoDatabase getDatabase(){
		return getClient().getDatabase(Config.getInstance().getDatabaseName());
	}
	
}
