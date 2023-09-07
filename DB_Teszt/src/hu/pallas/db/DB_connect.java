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
    private int tablaOlszopSzamlalo;
    private String[] tablaOszlopNev;
    private boolean uzenetKiir = true;
    
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

	public int getTablaOlszopSzamlalo() {
		return tablaOlszopSzamlalo;
	}

	public void setTablaOlszopSzamlalo(int tablaOlszopSzamlalo) {
		this.tablaOlszopSzamlalo = tablaOlszopSzamlalo;
	}

	public String[] getTablaOszlopNev() {
		return tablaOszlopNev;
	}

	public void setTablaOszlopNev(String[] tablaOszlopNev) {
		this.tablaOszlopNev = tablaOszlopNev;
	}

	public boolean isUzenetKiir() {
		return uzenetKiir;
	}

	public void setUzenetKiir(boolean uzenetKiir) {
		this.uzenetKiir = uzenetKiir;
	}

	public void readData(String tabla) { //Adatok kiolvasása a táblából
		
		setTabla(tabla);
		
    	try{  
    		Class.forName(driverName);  
    		Connection con=DriverManager.getConnection(url, username, password);  
    		Statement stmt=con.createStatement();  
    		
    			if (isUzenetKiir()){ 
    				System.out.println("Kapcsolat a(z) "+getDb_nev()+" adatbazissal LETREJOTT!");
    			}
    		
    		setUzenetKiir(false);
    		
    		ResultSet rs = stmt.executeQuery("SELECT * FROM "+getTabla());
    		ResultSetMetaData rsMetaData = rs.getMetaData();
   		
	    	//Az adat tábla oszlopainak megszámolása és változóban történő tárolása        
	    	setTablaOlszopSzamlalo(rsMetaData.getColumnCount());
		    tablaOszlopNev = new String[getTablaOlszopSzamlalo()];
		         
		    //Kiolvasott oszlop nevek feltöltése egy String tömbbe az oszlop számláló alapján
		    for (int i = 1; i <= getTablaOlszopSzamlalo(); i++) {
		        System.out.print(rsMetaData.getColumnName(i)+"\t");
		        tablaOszlopNev[i-1] = rsMetaData.getColumnName(i);
		     }
		     System.out.println();
 		
		     
		     //Tábla tartalmának kiiratása
		     while(rs.next() && getTablaOlszopSzamlalo()>0) {  
		    	 for (int i=1; i<=getTablaOlszopSzamlalo(); i++) {
		    		 System.out.print(rs.getString(i)+"\t");
		    		 }
		    	 System.out.println();
		     }
    		
	         con.close();  
	         
	         System.out.println();
    		
    	}catch(Exception e){ 
    		System.out.println(e);
    		}	 
    }
	
    public void insertData(String tabla, int id, int szam, String name) { //Adatok beirása a táblába
    	
    	setTabla(tabla);
    	
    	try{  
    		Class.forName(driverName);  
    		Connection con=DriverManager.getConnection(url, username, password);  
    			
    		String myStatement = " INSERT INTO "+getTabla()+" VALUES (?,?,?)";
    		PreparedStatement statement= con.prepareStatement (myStatement);
    		statement.setString(1,String.valueOf(id));
    		statement.setString(2,String.valueOf(szam));
    		statement.setString(3,name);
    		statement.executeUpdate();
    		
    		System.out.println("Adat sikeresen BETOLTVE! a(z) "+getTabla()+" nevu tablaba: ID["+String.valueOf(id)+"], szam["+String.valueOf(szam)+"], nev["+name+"]");
    	
    		con.close();
    		
    	}catch(Exception e){ 
    		System.out.println(e);
    		}
    }  
	
    public void updateData(String tabla, int id, int updateId) {   //Adatok felülirása a táblában
    	
    	setTabla(tabla);

    	try{  
    		Class.forName(driverName);  
    		Connection con=DriverManager.getConnection(url, username, password);  
    		
    		String myStatement = "UPDATE "+getTabla()+" SET ID=? WHERE ID=?";
    		PreparedStatement statement= con.prepareStatement (myStatement);
    		statement.setString(1,String.valueOf(updateId));
    		statement.setString(2,String.valueOf(id));
    		
    		statement.executeUpdate();
    		
    		System.out.println("Adat FRISSITVE! a(z) "+getTabla()+" nevu tablaban: regiID["+String.valueOf(id)+"] ujID("+String.valueOf(updateId)+")" );
    		
    		con.close();
    		
    	}catch(Exception e){ 
    		System.out.println(e);
    		} 
    }  
    
    public void deleteData(String tabla, int id) { //Adatok törlése a táblából
    	
    	setTabla(tabla);
    	
    	try{  
    		Class.forName(driverName);  
    		Connection con=DriverManager.getConnection(url, username, password);  
    		
    		String myStatement = "DELETE FROM "+getTabla()+" WHERE ID=?";
    		PreparedStatement statement= con.prepareStatement (myStatement);
    		statement.setString(1,String.valueOf(id));
    		
    		statement.executeUpdate();
    		
    		System.out.println("Adat TOROLVE! a(z) "+getTabla()+" nevu tablaban az azonosito ID["+String.valueOf(id)+"]");
    		
    		con.close();
    		
    	}catch(Exception e){ 
    		System.out.println(e);
    		} 
    } 
    
    public void dropData(String tabla) { //Adat teljes törlése a táblából
    	
    	setTabla(tabla);
    	
    	try{  
    		Class.forName(driverName);  
    		Connection con=DriverManager.getConnection(url, username, password);  
    		
    		String myStatement = "TRUNCATE TABLE "+getTabla();
    		PreparedStatement statement= con.prepareStatement (myStatement);
    		statement.executeUpdate();
    		
    		System.out.println("A(z) "+getTabla()+" nevu tabla adatai KIURITVE!");
    		
    		con.close();
    		
    	}catch(Exception e){ 
    		System.out.println(e);
    		}
    }  
}