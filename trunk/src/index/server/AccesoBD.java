package index.server;

import java.sql.DriverManager;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AccesoBD {
	
	private static String url = "jdbc:mysql://localhost:3306/";
	private static String db  = "comentariosgwt";
	private static String user = "comentariosgwt";
	private static String pass = "comentariosgwt";
	
	private static Connection conn  = null;
	
	public static boolean crearConexion()
	{
		//Registro del driver
        try {
        	Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}		
		//Establecer conexión con el servidor
		try {
			conn = DriverManager.getConnection(url+db, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static ResultSet select(String filter, String table)
	{
		return select(filter, table, null, null);
	}
	
	public static ResultSet select(String filter, String table, String condition)
	{
		return select(filter, table, condition, null);
	}
	
	public static ResultSet select(String filter, String table, String condition, String other)
	{
		
		//Se prepara el statement para realizar la consulta
		Statement s = null;
		try {
			s = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		//Se contruye la sentencia SQL
		String sql = "select "+ filter + " from " + table;
		
		if(condition!=null)
			sql += " where " + condition;
		
		if(other!= null)
			sql += " " + other;
		
		//Se realiza la consulta
		try {
			return s.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object selectEscalar(String filter, String table)
	{
		return selectEscalar(filter, table, null, null);
	}
	
	public static Object selectEscalar(String filter, String table, String condition)
	{
		return selectEscalar(filter, table, condition, null);
	}
	
	public static Object selectEscalar(String filter, String table, String condition, String other)
	{
		//Se prepara el statement para realizar la consulta
		Statement s = null;
		try {
			s = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		//Se contruye la sentencia SQL
		String sql = "select "+ filter + " from " + table;
		
		if(condition!=null)
			sql += " where " + condition;
		
		if(other!= null)
			sql += " " + other;
		
		//Se realiza la consulta
		ResultSet rs = null;
		try {
			 rs = s.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		try {
			if(rs.next())
				return rs.getObject(1);
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
