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
        //String sql = "SELECT emp.*,e.EMP_NOMBRES as EMP_JEFE FROM rh_empleado emp INNER JOIN rh_empleado e ON emp.EMP_JEF_ID=e.EMP_ID;";
        String sql = "SELECT emp.*,e.EMP_NOMBRES,dep.DEP_NOMBRE FROM rh_empleado emp INNER JOIN rh_empleado e ON emp.EMP_JEF_ID=e.EMP_ID INNER JOIN rh_departamento dep ON dep.DEP_ID=emp.DEP_ID;";
        ps = super.con().prepareStatement(sql);
        try 
        {
            rs = ps.executeQuery();
            while(rs.next()){
                dep = new Departament();
                dep.setDep_id(rs.getInt(12));
                dep.setDep_nombre(rs.getString(15));
                jef_nombres.add(rs.getString(14));
                
                emp  = new Employe();
                emp.setEmp_id(rs.getInt(1));
                emp.setEmp_codigo(rs.getString(2));
                emp.setEmp_nombres(rs.getString(3));
                emp.setEmp_apellidos(rs.getString(4));
                emp.setEmp_dui(rs.getString(5));
                emp.setEmp_nit(rs.getString(6));
                emp.setEmp_correo(rs.getString(7));
                emp.setEmp_telefono(rs.getString(8));
                emp.setEmp_sueldo(rs.getDouble(9));
                emp.setEmp_direccion(rs.getString(10));
                emp.setEmp_estado(rs.getByte(11));
                emp.setEmp_jef_id(rs.getInt(13));
                emp.setDepartamento(dep);
                ar.add(emp);
                jef_nombres.add("prueba");
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            emp = new Employe();
            emp.setEmp_nombres(e.getMessage());
            ar.add(emp);
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
            formDetailsJson.put("codigo",em.getEmp_codigo());
            formDetailsJson.put("nombres",em.getEmp_nombres());
            formDetailsJson.put("apellidos",em.getEmp_apellidos());
            formDetailsJson.put("dui",em.getEmp_dui());
            formDetailsJson.put("nit",em.getEmp_nit());
            formDetailsJson.put("correo",em.getEmp_correo());
            formDetailsJson.put("telefono",em.getEmp_telefono());
            formDetailsJson.put("sueldo",em.getEmp_sueldo());
            formDetailsJson.put("direccion",em.getEmp_direccion());
            formDetailsJson.put("estado",em.getEmp_estado());
            formDetailsJson.put("dep_id",em.getDepartamento().getDep_id());
            formDetailsJson.put("dep_nombre",em.getDepartamento().getDep_nombre());
            formDetailsJson.put("jefe_id",em.getEmp_jef_id());
            formDetailsJson.put("jefe_nombre",jef_nombres.get(i));
            jsonArray.add(formDetailsJson);
            i++;
        }
        
        jsonData.put("employees", jsonArray);
        return jsonData.toJSONString();
    }
    
    public String add(Employe emp){
        int res = 0;
        String response = "", psQuery = "No cambia";
        try 
        {
            String sql = "INSERT INTO rh_empleado(EMP_CODIGO,EMP_NOMBRES,EMP_APELLIDOS,EMP_DUI,EMP_NIT,EMP_CORREO,EMP_TELEFONO,EMP_SUELDO,EMP_DIRECCION,EMP_ESTADO,DEP_ID,EMP_JEF_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
            ps = super.con().prepareStatement(sql);
            ps.setString(1, emp.getEmp_codigo());
            ps.setString(2, emp.getEmp_nombres());
            ps.setString(3, emp.getEmp_apellidos());
            ps.setString(4, emp.getEmp_dui());
            ps.setString(5, emp.getEmp_nit());
            ps.setString(6, emp.getEmp_correo());
            ps.setString(7, emp.getEmp_telefono());
            ps.setDouble(8, emp.getEmp_sueldo());
            ps.setString(9, emp.getEmp_direccion());
            ps.setInt(10, emp.getEmp_estado());
            ps.setInt(11, emp.getDepartamento().getDep_id());
            ps.setInt(12, emp.getEmp_jef_id());
            psQuery = ps.toString();
            
            res=ps.executeUpdate();
            if(res>0){response="exitoso";}else{response="ha ocurrido un error." + psQuery;}
        } 
        catch (Exception e) 
        {
            response = "ERROR: "+e.getMessage();
        }  
        return response;
    }
    
    public String update(Employe emp){
        int res = 0;
        String response = "";
        try 
        {
            String sql = "UPDATE rh_empleado SET EMP_CODIGO=?,EMP_NOMBRES=?,"
            + "EMP_APELLIDOS=?,EMP_DUI=?,EMP_NIT=?,EMP_CORREO=?,EMP_TELEFONO=?,EMP_SUELDO=?,EMP_DIRECCION=?,"
            + "EMP_ESTADO=?,DEP_ID=?,EMP_JEF_ID=? WHERE EMP_ID=?;";
            ps = super.con().prepareStatement(sql);
            ps.setString(1, emp.getEmp_codigo());
            ps.setString(2, emp.getEmp_nombres());
            ps.setString(3, emp.getEmp_apellidos());
            ps.setString(4, emp.getEmp_dui());
            ps.setString(5, emp.getEmp_nit());
            ps.setString(6, emp.getEmp_correo());
            ps.setString(7, emp.getEmp_telefono());
            ps.setDouble(8, emp.getEmp_sueldo());
            ps.setString(9, emp.getEmp_direccion());
            ps.setByte(10, emp.getEmp_estado());
            ps.setInt(11, emp.getDepartamento().getDep_id());
            ps.setInt(12, emp.getEmp_jef_id());
            ps.setInt(13, emp.getEmp_id());
            res=ps.executeUpdate();
            if(res>0){response="exitoso";}else{response="ha ocurrido un error.";}
        } 
        catch (Exception e) 
        {
            response = "ERROR: "+e.getMessage();
        }  
        return response;
    }
    
    public String disable(Employe emp){
         int res = 0;
        String response = "";
        try 
        {
            String sql = "UPDATE rh_empleado SET EMP_ESTADO=1 WHERE EMP_ID=?;";
            ps = super.con().prepareStatement(sql);
            ps.setInt(1, emp.getEmp_id());
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
