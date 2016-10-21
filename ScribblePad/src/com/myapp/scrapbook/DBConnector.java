package com.myapp.scrapbook;


import java.sql.Connection;
import java.sql.DriverManager;
import java.time.format.*;

public class DBConnector {

	private String DBurl = "jdbc:postgresql://localhost:5432/scrapboard";
	private String DBUsername = "postgres";
	private String DBPassword = "admin";	
	
    public Connection getConnection()
            throws ClassNotFoundException, Exception {
        Connection objConnection = null;
        Class.forName("org.postgresql.Driver").newInstance();
        objConnection = DriverManager.getConnection(DBurl, DBUsername, DBPassword);        
        return objConnection;
    }
    

}
