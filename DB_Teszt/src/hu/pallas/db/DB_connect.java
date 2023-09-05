package hu.pallas.db;

import java.sql.DriverManager;
import java.sql.*;

public class DB_connect {
	
	private String url;    
	private String driverName; 
    private String username;
    private String password;
    private String db_nev;
    private String tabla;
    private static DB_connect con;
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
        
    public String getDb_nev() {
		return db_nev;
	}

	public void setDb_nev(String db_nev) {
		this.db_nev = db_nev;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public DB_connect readData() {

    	try{  
    		Class.forName(driverName);  
    		Connection con=DriverManager.getConnection(url, username, password);  
    		    		
    		Statement stmt=con.createStatement();  
    		ResultSet rs=stmt.executeQuery("select * from "+getTabla());  
    		System.out.println("Kapcsolat a(z) "+db_nev+" adatbazissal LETREJOTT!");
    		
    		while(rs.next())  
    			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
    			con.close();  
    		
    	}catch(Exception e){ 
    		System.out.println(e);
    		}	
    return con;  
    }
	
    public DB_connect insertData(int id, int szam, String name) {
    	
    	try{  
    		Class.forName(driverName);  
    		Connection con=DriverManager.getConnection(url, username, password);  
    			
    		String myStatement = " INSERT INTO "+tabla+" VALUES (?,?,?)";
    		PreparedStatement statement= con.prepareStatement (myStatement);
    		statement.setString(1,String.valueOf(id));
    		statement.setString(2,String.valueOf(szam));
    		statement.setString(3,name);
    		statement.executeUpdate();
    		
    		System.out.println("Adat sikeresen BETOLTVE! ID["+String.valueOf(id)+"], szam["+String.valueOf(szam)+"], nev["+name+"]");
    		
    		con.close();
    		readData();
    		
    	}catch(Exception e){ 
    		System.out.println(e);
    		}
	return con;  
    }  
	
    public DB_connect updateData(int id, int updateId) {
    	

    	try{  
    		Class.forName(driverName);  
    		Connection con=DriverManager.getConnection(url, username, password);  
    		
    		String myStatement = "UPDATE "+tabla+" SET ID=? WHERE ID=?";
    		PreparedStatement statement= con.prepareStatement (myStatement);
    		statement.setString(1,String.valueOf(updateId));
    		statement.setString(2,String.valueOf(id));
    		
    		statement.executeUpdate();
    		
    		System.out.println("Adat FRISSITVE! regiID["+String.valueOf(id)+"] az ujID("+String.valueOf(updateId)+")" );
    		
    		con.close();
    		readData();
    		
    	}catch(Exception e){ 
    		System.out.println(e);
    		}
	return con;  
    }  
    
    public DB_connect deleteData(int id) {
    	
    	try{  
    		Class.forName(driverName);  
    		Connection con=DriverManager.getConnection(url, username, password);  
    		
    		String myStatement = "DELETE FROM "+tabla+" WHERE ID=?";
    		PreparedStatement statement= con.prepareStatement (myStatement);
    		statement.setString(1,String.valueOf(id));
    		
    		statement.executeUpdate();
    		
    		System.out.println("Adat TOROLVE! az azonosito ID["+String.valueOf(id)+"]");
    		
    		con.close();
    		readData();
    		
    	}catch(Exception e){ 
    		System.out.println(e);
    		}
    	
	return con;  
    } 
    
    public DB_connect dropData() {
    	
    	try{  
    		Class.forName(driverName);  
    		Connection con=DriverManager.getConnection(url, username, password);  
    		
    		String myStatement = "TRUNCATE TABLE "+tabla;
    		PreparedStatement statement= con.prepareStatement (myStatement);
    		statement.executeUpdate();
    		
    		System.out.println("A(z) "+getTabla()+" nevu tabla adatai KIURITVE!");
    		
    		con.close();
    		readData();
    		
    	}catch(Exception e){ 
    		System.out.println(e);
    		}
	return con;  
    }  
	

}

