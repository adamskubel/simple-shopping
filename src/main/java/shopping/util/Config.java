package shopping.util;

public class Config
{
	private String databaseHost;
	private int databasePort;
	private String databaseName;
	
	private static Config instance;
	
	private Config(){}
	
	public static void init(String sourceFile)
	{
		instance = new Config();
		instance.databaseHost = "localhost";
		instance.databasePort = 27017;
		instance.databaseName = "db";
	}
	
	public static Config getInstance()
	{
		if (instance == null)
			throw new RuntimeException("Remember to call .init() to initialize the configuration first");
		return instance;
	}
	
	public String getDatabaseHost()
	{
		return databaseHost;
	}
	
	public int getDatabasePort()
	{
		return databasePort;
	}
	
	public String getDatabaseName(){
		return databaseName;
	}
	
}
