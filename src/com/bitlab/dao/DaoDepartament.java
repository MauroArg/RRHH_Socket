/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entity.Departament;
import com.bitlab.entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gusst
 */
public class DaoDepartament extends Conexion{
    PreparedStatement ps;
    ResultSet rs;
    User usuario;
    
    public ArrayList<Object> mostrarProducto() throws ClassNotFoundException, SQLException{
        ArrayList<Object> ar = new ArrayList<>();
        String sql = "SELECT * FROM rh_usuario;";
        ps = super.con().prepareStatement(sql);
        try 
        {
            rs = ps.executeQuery();
            while(rs.next()){
                usuario = new User();
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            super.con().close();
        }
        
        return ar;
    }
}
