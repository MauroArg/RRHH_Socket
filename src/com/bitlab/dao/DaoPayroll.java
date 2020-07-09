/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.dao;

import com.bitlab.entity.Departament;
import com.bitlab.entity.Employe;
import com.bitlab.entity.Payroll;
import com.bitlab.entity.PayrollDetail;
import com.bitlab.entity.pruebaJSONapp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author Gustavo Gómez
 * class: DaoPayroll
 * fecha: 2020-07-02
 */
public class DaoPayroll extends Conexion{
    //- needed vars
    PreparedStatement ps;
    ResultSet rs;
    Payroll py;
    PayrollDetail pd;
    Employe emp;
    
    
            
    
    //- function to validate the payrollGenerate
    public String validatePayrollGenerable() throws ClassNotFoundException, SQLException{
        String response = "";
        ArrayList<Payroll> ar = new ArrayList<>();
        String sql = "SELECT * FROM rh_planilla WHERE DATE_FORMAT(PLN_FECHA,'%m')=DATE_FORMAT(CURRENT_DATE(),'%m');";
        ps = super.con().prepareStatement(sql);
        try 
        {
            rs = ps.executeQuery();
            while(rs.next()){
                py = new Payroll();
                py.setPln_fecha(rs.getString(2));
                ar.add(py);
            }
            if(ar.isEmpty()){
                response = "available";
            }else{
                response = "unavailable";
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
        return response;
    }
    
    //-
    public String getAll() throws ClassNotFoundException, SQLException{
        String response = "";
        ArrayList<Payroll> ar = new ArrayList();
        String sql = "SELECT * FROM rh_planilla;";
        ps = super.con().prepareStatement(sql);
        try 
        {
            rs = ps.executeQuery();
            while(rs.next()){
                py = new Payroll();
                py.setPln_id(rs.getInt(1));
                py.setPln_fecha(rs.getString(2));
                py.setPln_total(rs.getDouble(3));
                py.setPln_estado(rs.getByte(4));
                ar.add(py);
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
        for(Payroll py:ar){
            JSONObject formDetailsJson = new JSONObject();
            formDetailsJson.put("id",py.getPln_id());
            formDetailsJson.put("fecha",py.getPln_fecha());
            formDetailsJson.put("total",py.getPln_total());
            formDetailsJson.put("esatdo",py.getPln_estado());
            jsonArray.add(formDetailsJson);
        }
        jsonData.put("payrolls", jsonArray);
        return jsonData.toJSONString();
    }
    
    //- add function
    public String add(double total) throws ClassNotFoundException, SQLException{
        String response = "";
        int res = 0;
        int pln = 0;
        String sql = "INSERT INTO rh_planilla(PLN_FECHA,PLN_TOTAL,PLN_ESTADO) VALUES(CURRENT_DATE(),?,0);";
        ps = super.con().prepareStatement(sql);
        ps.setDouble(1, (double)Math.round(total * 100d) / 100d);
        try 
        {
            res=ps.executeUpdate();
            if(res>0){response="ok";}else{response="ha ocurrido un error.";}
        } 
        catch (Exception e) 
        {
            System.out.println("ERROR DE SQL: " + e.getMessage());
        }
        finally
        {
            super.con().close();
        }
        return response;
    }
    
    //- Get of actualPayroll
    public String getActual() throws ClassNotFoundException, SQLException{
        String response = "";
        ArrayList<Payroll> ar = new ArrayList();
        ArrayList<PayrollDetail> arDetail = new ArrayList();
        String sql = "SELECT * FROM rh_planilla WHERE DATE_FORMAT(PLN_FECHA,'%m')=DATE_FORMAT(CURRENT_DATE(),'%m');";
        ps = super.con().prepareStatement(sql);
        
        try 
        {
            rs = ps.executeQuery();
            while(rs.next()){
                py = new Payroll();
                py.setPln_fecha(rs.getString(2));
                py.setPln_total(rs.getDouble(3));
                ar.add(py);
            }
            sql = "SELECT * FROM rh_detalle_planilla WHERE PLN_ID=?;";
            ps = super.con().prepareStatement(sql);
            ps.setInt(1, ar.get(0).getPln_id());
            rs = ps.executeQuery();
            while(rs.next()){
                pd = new PayrollDetail();
                pd.setDet_pln_id(rs.getInt(1));
                pd.setDet_pln_total(rs.getDouble(2));
                pd.setDet_pln_total_descuentos(rs.getInt(3));
                pd.setDet_pln_renta(rs.getDouble(4));
                pd.setDet_pln_isss(rs.getDouble(5));
                pd.setDet_pln_afp(rs.getDouble(6));
                pd.setDet_pln_cantidad_horas_diurnas(rs.getByte(7));
                pd.setDet_pln_cantidad_horas_nocturnas(rs.getByte(8));
                pd.setDet_pln_bono_horas_extra(rs.getDouble(9));
                emp.setEmp_id(rs.getInt(10));
                pd.setEmploye(emp);
                py.setPln_id(rs.getInt(11));
                pd.setPayroll(py);
                arDetail.add(pd);
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
        for(PayrollDetail em:arDetail){
            JSONObject formDetailsJson = new JSONObject();
            formDetailsJson.put("id",em.getDet_pln_id());
            formDetailsJson.put("total",em.getDet_pln_total());
            formDetailsJson.put("descuentos",em.getDet_pln_total_descuentos());
            formDetailsJson.put("renta",em.getDet_pln_renta());
            formDetailsJson.put("isss",em.getDet_pln_isss());
            formDetailsJson.put("afp",em.getDet_pln_afp());
            formDetailsJson.put("diurnas",em.getDet_pln_cantidad_horas_diurnas());
            formDetailsJson.put("nocturnas",em.getDet_pln_cantidad_horas_nocturnas());
            formDetailsJson.put("bono",em.getDet_pln_bono_horas_extra());
            formDetailsJson.put("empleado",em.getEmploye().getEmp_id());
            formDetailsJson.put("planilla",em.getPayroll().getPln_id());
            jsonArray.add(formDetailsJson);
            i++;
        }
        jsonData.put("total",ar.get(0).getPln_total());
        jsonData.put("fecha",ar.get(0).getPln_fecha());
        jsonData.put("detail", jsonArray);
        return jsonData.toJSONString();
    }
    
    public String getPayrollDetailById(int id) throws ClassNotFoundException, SQLException{
        ArrayList<PayrollDetail> arDetail = new ArrayList();
        String sql = "SELECT * FROM rh_detalle_planilla WHERE PLN_ID=?;";
        try 
        {
            ps = super.con().prepareStatement(sql);
            ps.setInt(1, id);
            System.out.println("CONSULTA" + ps.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                emp = new Employe();
                pd = new PayrollDetail();
                pd.setDet_pln_id(rs.getInt(1));
                pd.setDet_pln_total(rs.getDouble(2));
                pd.setDet_pln_total_descuentos(rs.getInt(3));
                pd.setDet_pln_renta(rs.getDouble(4));
                pd.setDet_pln_isss(rs.getDouble(5));
                pd.setDet_pln_afp(rs.getDouble(6));
                pd.setDet_pln_cantidad_horas_diurnas(rs.getByte(7));
                pd.setDet_pln_cantidad_horas_nocturnas(rs.getByte(8));
                pd.setDet_pln_bono_horas_extra(rs.getDouble(9));
                emp.setEmp_id(rs.getInt(11));
                pd.setEmploye(emp);
                py.setPln_id(rs.getInt(10));
                pd.setPayroll(py);
                arDetail.add(pd);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("ERROR DE SQL: " + e.getMessage());
        }
        finally
        {
            super.con().close();
        }
        
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonData = new JSONObject();
        int i = 0;
        for(PayrollDetail em:arDetail){
            JSONObject formDetailsJson = new JSONObject();
            formDetailsJson.put("id",em.getDet_pln_id());
            formDetailsJson.put("total",em.getDet_pln_total());
            formDetailsJson.put("descuentos",em.getDet_pln_total_descuentos());
            formDetailsJson.put("renta",em.getDet_pln_renta());
            formDetailsJson.put("isss",em.getDet_pln_isss());
            formDetailsJson.put("afp",em.getDet_pln_afp());
            formDetailsJson.put("diurnas",em.getDet_pln_cantidad_horas_diurnas());
            formDetailsJson.put("nocturnas",em.getDet_pln_cantidad_horas_nocturnas());
            formDetailsJson.put("bono",em.getDet_pln_bono_horas_extra());
            formDetailsJson.put("empleado",em.getEmploye().getEmp_id());
            formDetailsJson.put("planilla",em.getPayroll().getPln_id());
            jsonArray.add(formDetailsJson);
            i++;
        }
        jsonData.put("detail", jsonArray);
        return jsonData.toJSONString();
    }
    
    public String payPayroll(Payroll py){
        String response = "";
        int res = 0;
        try 
        {
            String sql = "UPDATE rh_planilla SET PLN_ESTADO=1 WHERE PLN_ID=?;";
            ps = super.con().prepareStatement(sql);
            ps.setInt(1, py.getPln_id());
            
            res=ps.executeUpdate();
            if(res>0){response="exitoso";}else{response="ha ocurrido un error.";}
        } 
        catch (Exception e) 
        {
            response = "ERROR: "+e.getMessage();
        }  
        return response;
    }
    
    public int getPLNID() throws SQLException, ClassNotFoundException{
        int id = 0;
        String sql = "";
        sql = "SELECT MAX(PLN_ID) AS id FROM rh_planilla;";
        
        try 
        {
            ps = super.con().prepareStatement(sql);
            rs = ps.executeQuery();
            int i = 0;
            while(rs.next()){
                if(i==0){id=rs.getInt(1);}
                i++;
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
        return id;
    }
    
    public String insertdDetail(ArrayList<PayrollDetail> data, int pln ) throws ClassNotFoundException, SQLException{
        String response = "";
        StringBuilder insert = new StringBuilder();
        int res = 0;
        try 
        {
            insert.append("INSERT INTO rh_detalle_planilla(DET_PLN_TOTAL,DET_PLN_TOTAL_DESCUENTOS,");
            insert.append("DET_PLN_RENTA,DET_PLN_ISSS,DET_PLN_AFP,DET_PLN_CANTIDAD_HORAS_DIURNAS,");
            insert.append("DET_PLN_BONO_HORAS_EXTRA,DET_PLN_CANTIDAD_HORAS_NOCTURNAS,PLN_ID,EMP_ID) VALUES");
            int i = 1;
            for (PayrollDetail pyd : data) {
                insert.append("(").append((double)Math.round(pyd.getDet_pln_total() * 100d) / 100d).append(",");
                insert.append((double)Math.round(pyd.getDet_pln_total_descuentos() * 100d) / 100d).append(",");
                insert.append((double)Math.round(pyd.getDet_pln_renta() * 100d) / 100d).append(",");
                insert.append((double)Math.round(pyd.getDet_pln_isss() * 100d) / 100d).append(",");
                insert.append((double)Math.round(pyd.getDet_pln_afp() * 100d) / 100d).append(",");
                insert.append(pyd.getDet_pln_cantidad_horas_diurnas()).append(",");
                insert.append((double)Math.round(pyd.getDet_pln_bono_horas_extra() * 100d) / 100d).append(",");
                insert.append(pyd.getDet_pln_cantidad_horas_nocturnas()).append(",");
                insert.append(pln).append(",");
                insert.append(pyd.getEmploye().getEmp_id()).append(")");

                if (i < data.size()) {
                    insert.append(",");
                } else {
                    insert.append(";");
                }
                i++;
            }
            System.out.println(insert.toString());
            ps = super.con().prepareStatement(insert.toString());
            res = ps.executeUpdate();
            if (res > 0) {
                response = "exitoso";
            } else {
                response = "ha ocurrido un error.";
            }
        } 
        catch (Exception e) 
        {
            System.out.println("ERROR DE SQL: " + e.getMessage());
        }
        finally
        {
            super.con().close();
        }
        return response;
    }
}
