package com.model;


import java.sql.*;
public class Conexion
{
	private Statement St; 
	private String driver;
	private String user;
	private String pwd;
	private String cadena;
	private Connection con;

	String getDriver()
	{
		return this.driver;
	}
	String getUser()
	{
		return this.user;
	}
	String getPwd()
	{
		return this.pwd;
	}
	String getCadena()
	{
		return this.cadena;
	}
	public Connection getConexion()
	{ 
		return this.con; 
	}
	
	public Conexion() {
		
		this.driver ="org.postgresql.Driver";
		this.user="postgres";
		this.pwd="123456";
		this.cadena="jdbc:postgresql://localhost:5432/sesion";
		this.con=this.crearConexion();
		
	}
	
	Connection crearConexion()
	{
		try {
			Class.forName("org.postgresql.Driver");
			}
			catch (ClassNotFoundException e) {
			
			}
		
		try
		{
			Class.forName(getDriver()).newInstance();
			Connection con=DriverManager.getConnection(getCadena(),getUser(),getPwd());
			return con;
		}
	catch(Exception ee)
	{
		System.out.println("Error: " + ee.getMessage());
		return null;
	}
	}


	public String Ejecutar(String sql)
	{
	String error="";
	try
	{
	St=getConexion().createStatement();
	St.execute(sql);
	error="Datos insertados";
	}
	catch(Exception ex)
	{
	error = ex.getMessage();
            System.out.println(error);
	}
	return(error);
	}



	public ResultSet Consulta(String sql)
	{
	String error="";
	ResultSet reg=null;
	
	try
	{
	St=getConexion().createStatement();
	reg=St.executeQuery(sql);
	

	}
	catch(Exception ee)
	{
	error = ee.getMessage();
            System.out.println(error);
	}
	return(reg);
	}
}