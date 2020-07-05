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
    
    public void update(Departament dep){
        
    }
    
    public void add(Departament dep){
        
    }
}
