package springwork.dao;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class OracleConnection{
	
	public static Connection getConnection() throws ClassNotFoundException,
	IOException, SQLException {
	final Properties prop = new Properties();
	
	final InputStream inputStream = OracleConnection.class.getClassLoader()
			.getResourceAsStream(
					"./springwork/resources/db.properties");
	prop.load(inputStream);
	
//	System.out.println("url: "+prop.getProperty("url"));
//	System.out.println("user: "+prop.getProperty("user"));
//	System.out.println("user: "+prop.getProperty("password"));
	
	Class.forName(prop.getProperty("driver"));
	final Connection connection = DriverManager.getConnection(prop.getProperty("url"), 
			prop.getProperty("user"), prop.getProperty("password"));
	
	return connection;
	}

}

