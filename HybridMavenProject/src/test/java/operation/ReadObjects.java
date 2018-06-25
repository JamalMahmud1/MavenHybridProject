package operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadObjects {
	Properties p=new Properties();

	public Properties getObjectRepository() throws IOException {
		File f=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\object\\Object.property2");
		FileInputStream fis=new FileInputStream(f);
		p.load(fis);
		return p;
		
		

	}
	}


