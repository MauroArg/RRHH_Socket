package com.bitlab.dao;
import java.sql.*;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
/**
 * @author Gustavo Gómez
 * class: Connection
 * fecha: 2020-07-03
 */
public class Conexion {
    //-
    /*private static final String URL = "jdbc:postgresql://35.238.225.77:5432/postgres";
    private static final String USER = "echouser";
    private static final String PASSWORD = "EchoBitlab2020$$";
    private static final String DRIVER = "org.postgresql.Driver";*/
    //-
    private static final String URL = "jdbc:mysql://35.208.154.100:3306/db_rrhh";
    private static final String USER = "opUser";
    private static final String PASSWORD = "bitlabP@ssword123";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final Conexion main = new Conexion();
    private BasicDataSource basicDataSource;
    //-
    public Connection con() throws ClassNotFoundException, SQLException{
       Class.forName(DRIVER);
       return DriverManager.getConnection(URL, USER, PASSWORD);
    }
 }
