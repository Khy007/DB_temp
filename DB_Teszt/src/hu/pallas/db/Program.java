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
		db_init.readData("adatok");	   
//		db_init.insertData("adatok", 1, 121, "Zsolti");
//		db_init.insertData("adatok", 2, 232, "Pisti");
//		db_init.insertData("adatok", 3, 323, "Sanyi");
//		db_init.insertData("adatok", 4, 434, "Erika");
//		db_init.insertData("adatok", 5, 535, "Andrea");
//		db_init.insertData("adatok", 6, 646, "Panka");
//		db_init.updateData("adatok", 6, 11);
//		db_init.deleteData("adatok", 11);
//		db_init.dropData("adatok");
//		db_init.readData("adatok");
	}
	

}