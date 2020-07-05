package com.bitlab.dao;

import com.bitlab.entity.Departament;
import com.bitlab.entity.Employe;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author Gustavo Gómez
 * class: DaoEmploye
 * fecha: 2020-07-02
 */
public class DaoEmploye extends Conexion{
    
    PreparedStatement ps;
    ResultSet rs;
    Employe emp;
    Departament dep;
    Employe jef;
    
    public String getData() throws ClassNotFoundException, SQLException{
        ArrayList<Employe> ar = new ArrayList<>();
        ArrayList<String> jef_nombres = new ArrayList<>();
        String sql = "SELECT emp.*,e.EMP_NOMBRES as EMP_JEFE FROM rh_empleado emp INNER JOIN rh_empleado e ON emp.EMP_JEF_ID=e.EMP_NOMBRES;";
        ps = super.con().prepareStatement(sql);
        
        try 
        {
            rs = ps.executeQuery();
            while(rs.next()){
                dep = new Departament(rs.getByte(12),rs.getString(15));
                emp = new Employe(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getString(10),rs.getByte(11),dep,rs.getInt(13));
                ar.add(emp);
                jef_nombres.add(rs.getString(14));
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
        
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonData = new JSONObject();
        int i = 0;
        for(Employe em:ar){
            JSONObject formDetailsJson = new JSONObject();
            formDetailsJson.put("id",em.getEmp_id());
            formDetailsJson.put("nombres",em.getEmp_nombres());
            formDetailsJson.put("apellidos",em.getEmp_apellidos());
            formDetailsJson.put("dui",em.getEmp_dui());
            formDetailsJson.put("nit",em.getEmp_nit());
            formDetailsJson.put("correo",em.getEmp_correo());
            formDetailsJson.put("telefono",em.getEmp_telefono());
            formDetailsJson.put("sueldo",em.getEmp_sueldo());
            formDetailsJson.put("direccion",em.getEmp_direccion());
            formDetailsJson.put("estado",em.getEmp_estado());
            formDetailsJson.put("id_departamento",em.getDepartamento().getDep_id());
            formDetailsJson.put("nombre_departamento",em.getDepartamento().getDep_nombre());
            formDetailsJson.put("id_jefe",em.getEmp_jef_id());
            formDetailsJson.put("nombre_jefe",jef_nombres.get(i));
            jsonArray.add(formDetailsJson);
            i++;
        }
        
        jsonData.put("employees", jsonArray);
        return jsonData.toJSONString();
    }
    
    public String add(Employe emp){
        String response = "";
        
        return response;
    }
    
    public String update(Employe emp){
        String response = "";
        
        return response;
    }
}
