/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entity.Departament;
import com.bitlab.entity.Rol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author gusst
 */
public class DaoRol extends Conexion{
    PreparedStatement ps;
    ResultSet rs;
    Rol rol;
    
    public DaoRol() {
    }
    
    public String getData() throws ClassNotFoundException, SQLException{
        ArrayList<Rol> ar = new ArrayList<>();
        String sql = "SELECT * FROM rh_rol;";
        ps = super.con().prepareStatement(sql);
        try 
        {
            rs = ps.executeQuery();
            while(rs.next()){
                rol = new Rol(rs.getInt(1),rs.getString(2));
                ar.add(rol);
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            rol = new Rol(1,"error: "+e.getMessage());
            ar.add(rol);
        }
        finally
        {
            super.con().close();
        }
        
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonData = new JSONObject();
        for(Rol rol:ar){
            JSONObject formDetailsJson = new JSONObject();
            formDetailsJson.put("id", rol.getRol_id());
            formDetailsJson.put("nombre", rol.getRol_nombre());
            jsonArray.add(formDetailsJson);
        }
        jsonData.put("rols", jsonArray);
        return jsonData.toJSONString();
    }
    
    
    public String add(Rol rol){
        int res = 0;
        String response = "";
        try 
        {
            String sql = "INSERT INTO rh_rol(ROL_NOMBRE) VALUES(?);";
            ps = super.con().prepareStatement(sql);
            ps.setString(1, rol.getRol_nombre());
            res=ps.executeUpdate();
            if(res>0){response="exitoso";}else{response="ha ocurrido un error.";}
        } 
        catch (Exception e) 
        {
            response = "ERROR: "+e.getMessage();
        }  
        return response;
    }
    
    public String update(Rol rol){
        int res = 0;
        String response = "";
        try 
        {
            String sql = "UPDATE rh_rol SET ROL_NOMBRE=? WHERE ROL_ID=?;";
            ps = super.con().prepareStatement(sql);
            ps.setString(1, rol.getRol_nombre());
            ps.setInt(2, rol.getRol_id());
            res=ps.executeUpdate();
            if(res>0){response="exitoso";}else{response="ha ocurrido un error.";}
        } 
        catch (Exception e) 
        {
            response = "ERROR: "+e.getMessage();
        }  
        return response;
    }
}
