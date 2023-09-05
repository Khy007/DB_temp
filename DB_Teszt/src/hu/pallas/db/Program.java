package hu.pallas.db;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Program {

	public static void main(String[] args) {

		Resource resource= new ClassPathResource("applicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		
		DB_connect db_init =(DB_connect)factory.getBean("csatlakozas");
		db_init.readData();	    
//		db_init.insertData(1, 121, "Zsolti");
//		db_init.insertData(2, 232, "Pisti");
//		db_init.insertData(3, 323, "Sanyi");
//		db_init.insertData(4, 434, "Erika");
//		db_init.insertData(5, 535, "Andrea");
//		db_init.insertData(6, 646, "Panka");
//		db_init.updateData(6, 11);
//		db_init.deleteData(11);
//		db_init.dropData();
	}

}
