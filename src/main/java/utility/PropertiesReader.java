package utility;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {
		Properties properties;
		
		public PropertiesReader() {
			properties = new Properties();
			try {
				FileInputStream fileInputStream = new FileInputStream( System.getProperty("user.dir")
						+"\\src\\main\\resources\\config.properties");
				properties.load(fileInputStream);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public String getBrowserName() {
			return properties.getProperty("browser");
		}
		
		public String getUrl() {
			return properties.getProperty("url");
		}
}
