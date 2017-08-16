package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionUtil {

	private Logger log = Logger.getRootLogger();

	private static final ConnectionUtil connUtil = new ConnectionUtil();
	private Properties prop = new Properties();

	private ConnectionUtil() {
		super();
		log.trace("creating ConnectionUtil singleton");
		try {
			log.trace("properties file is " + prop);
			prop.load(new FileReader("src/main/resources/database.properties"));
			log.trace(prop.getProperty("url"));
		} catch (FileNotFoundException e) {
			log.error("Could not find database.properties file");
			e.printStackTrace();
		} catch (IOException e) {
			log.error("failed to load the file");
			e.printStackTrace();
		}
	}

	public static ConnectionUtil getConnectionUtil() {
		return connUtil;
	}

	public Connection getConnection() {
		log.debug("Attempting to get connection to DB");
		try {
			log.trace(" " + prop);
			return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"),
					prop.getProperty("pwd"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
