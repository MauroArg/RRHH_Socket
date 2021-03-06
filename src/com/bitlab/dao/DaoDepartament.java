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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author gusst
 */
public class DaoDepartament extends Conexion{
    PreparedStatement ps;
    ResultSet rs;
    Departament depa;
    
    public String getData() throws ClassNotFoundException, SQLException{
        ArrayList<Departament> ar = new ArrayList<>();
        String sql = "SELECT * FROM rh_departamento;";
        ps = super.con().prepareStatement(sql);
        try 
        {
            rs = ps.executeQuery();
            while(rs.next()){
                depa = new Departament(rs.getInt(1),rs.getString(2));
                ar.add(depa);
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            depa = new Departament(1,"error: "+e.getMessage());
            ar.add(depa);
        }
        finally
        {
            super.con().close();
        }
        
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonData = new JSONObject();
        for(Departament dep:ar){
            JSONObject formDetailsJson = new JSONObject();
            formDetailsJson.put("id", dep.getDep_id());
            formDetailsJson.put("nombre", dep.getDep_nombre());
            jsonArray.add(formDetailsJson);
        }
        jsonData.put("departaments", jsonArray);
        return jsonData.toJSONString();
    }
    
    public String add(Departament dep){
        int res = 0;
        String response = "";
        try 
        {
            String sql = "INSERT INTO rh_departamento(DEP_NOMBRE) VALUES(?);";
            ps = super.con().prepareStatement(sql);
            ps.setString(1, dep.getDep_nombre());
            res=ps.executeUpdate();
            if(res>0){response="exitoso";}else{response="ha ocurrido un error.";}
        } 
        catch (Exception e) 
        {
            response = "ERROR: "+e.getMessage();
        }  
        return response;
    }
    
    public String update(Departament dep){
        int res = 0;
        String response = "";
        try 
        {
            String sql = "UPDATE rh_departamento SET DEP_NOMBRE=? WHERE DEP_ID=?;";
            ps = super.con().prepareStatement(sql);
            ps.setString(1, dep.getDep_nombre());
            ps.setInt(2, dep.getDep_id());
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
