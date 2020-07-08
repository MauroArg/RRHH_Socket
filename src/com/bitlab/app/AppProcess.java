package com.bitlab.app;

import com.bitlab.dao.*;
import com.bitlab.entity.Departament;
import com.bitlab.entity.Employe;
import com.bitlab.entity.Payroll;
import com.bitlab.entity.PayrollDetail;
import com.bitlab.entity.Rol;
import com.bitlab.entity.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Gustavo Gómez
 * class: AppProcess
 * fecha: 2020-07-02
 */
public class AppProcess{
    
    public AppProcess() {
        
    }
    
    public void gestDepartament(BufferedReader in, PrintWriter out){
        DaoDepartament dao = new DaoDepartament();
        JSONParser parser = new JSONParser();
        JSONObject responseData = new JSONObject();
        Departament dep = new Departament();
        String response = "";
        try {
            //- Pass data to client
            out.println(dao.getData());
            //- Wait for the action order
            response = in.readLine();
            //-Switch to do an action
            switch(response){
                case "update":
                    System.out.println("UPDATE DEPARTAMENT FUNCTION IS ACTIVE");
                    //- Update action
                    response = in.readLine();
                    JSONObject obj = (JSONObject) parser.parse(response);
                    JSONObject depObj = (JSONObject)obj.get("departament");
                    dep.setDep_id(Integer.parseInt(depObj.get("id").toString()));
                    dep.setDep_nombre(depObj.get("nombre").toString());
                    response = dao.update(dep);
                    //- print response to client
                    out.println(response);
                    //- Pass data to client
                    out.println(dao.getData());
                    break;
                case "create":
                    System.out.println("CREATE DEPARTAMENT FUNCTION IS ACTIVE");
                    //- Create action
                    response = in.readLine();
                    dep.setDep_nombre(response);
                    response = dao.add(dep);
                    //- print response to client
                    out.println(response);
                    //- Pass data to client
                    out.println(dao.getData());
                    break;
                case "list":
                    break;
                default:
                    break;
            }
            System.out.println("GOING TO PRINCIPAL MENU");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void gestUser(BufferedReader in, PrintWriter out){
        DaoUser dao = new DaoUser();
        DaoRol daoR = new DaoRol();
        User us;
        Rol rol;
        JSONParser parser = new JSONParser();
        JSONObject obj;
        JSONObject usObj;
        String response = "";
        try {
            //- Pass data to client
            out.println(dao.getData());
            out.println(daoR.getData());
            System.out.println(dao.getData());
            System.out.println(daoR.getData());
            //- Wait for the action order
            response = in.readLine();
            //-Switch to do an action
            switch(response){
                case "update":
                    System.out.println("UPDATE USER FUNCTION IS ACTIVE");
                    //- Update action
                    //- GET JSON 
                    response = in.readLine();
                    obj = (JSONObject) parser.parse(response);
                    //- GET KEY user
                    usObj = (JSONObject)obj.get("user");
                    //-Initilize objects
                    us = new User();
                    rol = new Rol();
                    //- Parsing json and setting data to objects
                    us.setUs_id(Integer.parseInt(usObj.get("id").toString()));
                    us.setUs_usuario(usObj.get("username").toString());
                    us.setUs_contra(usObj.get("password").toString());
                    us.setUs_correo(usObj.get("correo").toString());
                    rol.setRol_id(Integer.parseInt(usObj.get("rol_id").toString()));
                    us.setRol(rol);
                    //- Update and get of response
                    response = dao.update(us);
                    //- print response to client
                    out.println(response);
                    break;
                case "create":
                    System.out.println("CREATE USER FUNCTION IS ACTIVE");
                    //- Create action
                    //- GET JSON 
                    response = in.readLine();
                    System.out.println("RESPONSE USER DATA: " + response);
                    obj = (JSONObject) parser.parse(response);
                    //- GET KEY user
                    usObj = (JSONObject)obj.get("user");
                    //-Initilize objects
                    us = new User();
                    rol = new Rol();
                    //- Parsing json and setting data to objects
                    us.setUs_usuario(usObj.get("username").toString());
                    us.setUs_contra(usObj.get("password").toString());
                    us.setUs_correo(usObj.get("correo").toString());
                    rol.setRol_id(Integer.parseInt(usObj.get("rol_id").toString()));
                    us.setRol(rol);
                    //- Create and get of response
                    response = dao.add(us);
                    System.out.println(response);
                    //- print response to client
                    out.println(response);
                    break;
                case "delete":
                    //- delete
                    response = in.readLine();
                    //-Initilize objects
                    us = new User();
                    //- setting user id of response
                    us.setUs_id(Integer.parseInt(response));
                    response = dao.delete(us);
                    //- print response to client
                    out.println(response);
                    break;
                case "list":
                    break;
                default:
                    break;
            }
            System.out.println("GOING TO PRINCIPAL MENU");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gestEmploye(BufferedReader in, PrintWriter out){
        DaoEmploye dao = new DaoEmploye();
        DaoDepartament daoD = new DaoDepartament();
        Employe emp;
        Departament dep;
        JSONParser parser = new JSONParser();
        JSONObject obj;
        JSONObject empObj;
        String response = "";
        try {
            //- Pass data to client
            out.println(dao.getData());
            System.out.println("DATA FINAL-->: " + dao.getData());
            out.println(daoD.getData());
            //- Wait for the action order
            response = in.readLine();
            //-Switch to do an action
            switch(response){
                case "update":
                    System.out.println("UPDATE EMPLOYE FUNCTION IS ACTIVE");
                    //- Update action
                    //- GET JSON 
                    response = in.readLine();
                    System.out.println(response);
                    obj = (JSONObject) parser.parse(response);
                    //- GET KEY user
                    empObj = (JSONObject)obj.get("employe");
                    //-Initilize objects
                    emp = new Employe();
                    dep = new Departament();
                    //- Parsing json and setting data to objects
                    emp.setEmp_id(Integer.parseInt(empObj.get("id").toString()));
                    emp.setEmp_codigo(empObj.get("codigo").toString());
                    emp.setEmp_nombres(empObj.get("nombres").toString());
                    emp.setEmp_apellidos(empObj.get("apellidos").toString());
                    emp.setEmp_dui(empObj.get("dui").toString());
                    emp.setEmp_nit(empObj.get("nit").toString());
                    emp.setEmp_correo(empObj.get("correo").toString());
                    emp.setEmp_telefono(empObj.get("telefono").toString());
                    emp.setEmp_sueldo(Double.parseDouble(empObj.get("sueldo").toString()));
                    emp.setEmp_direccion(empObj.get("direccion").toString());
                    emp.setEmp_estado(Byte.parseByte(empObj.get("estado").toString()));
                    dep.setDep_id(Integer.parseInt(empObj.get("dep_id").toString()));
                    emp.setDepartamento(dep);
                    emp.setEmp_jef_id(Integer.parseInt(empObj.get("jefe_id").toString()));
                    //- Update and get of response
                    response = dao.update(emp);
                    //- print response to client
                    out.println(response);
                    break;
                case "create":
                    System.out.println("CREATE EMPLOYE FUNCTION IS ACTIVE");
                    //- Update action
                    //- GET JSON 
                    response = in.readLine();
                    System.out.println(response);
                    obj = (JSONObject) parser.parse(response);
                    //- GET KEY user
                    empObj = (JSONObject)obj.get("employe");
                    //-Initilize objects
                    emp = new Employe();
                    dep = new Departament();
                    //- Parsing json and setting data to objects
                    emp.setEmp_codigo(empObj.get("codigo").toString());
                    emp.setEmp_nombres(empObj.get("nombres").toString());
                    emp.setEmp_apellidos(empObj.get("apellidos").toString());
                    emp.setEmp_dui(empObj.get("dui").toString());
                    emp.setEmp_nit(empObj.get("nit").toString());
                    emp.setEmp_correo(empObj.get("correo").toString());
                    emp.setEmp_telefono(empObj.get("telefono").toString());
                    emp.setEmp_sueldo(Double.parseDouble(empObj.get("sueldo").toString()));
                    emp.setEmp_direccion(empObj.get("direccion").toString());
                    emp.setEmp_estado(Byte.parseByte(empObj.get("estado").toString()));
                    dep.setDep_id(Integer.parseInt(empObj.get("dep_id").toString()));
                    emp.setDepartamento(dep);
                    emp.setEmp_jef_id(Integer.parseInt(empObj.get("jefe_id").toString()));
                    //- Update and get of response
                    response = dao.add(emp);
                    //- print response to client
                    out.println(response);
                    System.out.println("FUNCTION RESPONSE---->" + response);
                    break;
                case "disable":
                    System.out.println("DISABLE EMPLOYE FUNCTION IS ACTIVE");
                    //- Update action
                    //- GET JSON 
                    response = in.readLine();
                    //-Initilize objects
                    emp = new Employe();
                    emp.setEmp_id(Integer.parseInt(response));
                    //- Update and get of response
                    response = dao.disable(emp);
                    //- print response to client
                    out.println(response);
                    break;
                default:
                    break;
            }
            System.out.println("GOING TO PRINCIPAL MENU");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gestRol(BufferedReader in, PrintWriter out){
        DaoRol dao = new DaoRol();
        JSONParser parser = new JSONParser();
        JSONObject responseData = new JSONObject();
        Rol rol = new Rol();
        String response = "";
        try {
            //- Pass data to client
            out.println(dao.getData());
            //- Wait for the action order
            response = in.readLine();
            //-Switch to do an action
            switch(response){
                case "update":
                    System.out.println("UPDATE ROL FUNCTION IS ACTIVE");
                    //- Update action
                    response = in.readLine();
                    JSONObject obj = (JSONObject) parser.parse(response);
                    JSONObject depObj = (JSONObject)obj.get("rol");
                    rol.setRol_id(Integer.parseInt(depObj.get("id").toString()));
                    rol.setRol_nombre(depObj.get("nombre").toString());
                    response = dao.update(rol);
                    //- print response to client
                    System.out.println("FUNCTION UPDATE ROL RESPONSE:" + response);
                    out.println(response);
                    //- Pass data to client
                    out.println(dao.getData());
                    break;
                case "create":
                    System.out.println("CREATE ROL FUNCTION IS ACTIVE");
                    //- Create action
                    response = in.readLine();
                    rol.setRol_nombre(response);
                    response = dao.add(rol);
                    //- print response to client
                    out.println(response);
                    //- Pass data to client
                    out.println(dao.getData());
                    break;
                default:
                    System.out.println("UNRECOGNIZED COMMAND");
                    break;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
        System.out.println("GOING TO PRINCIPAL MENU");
    }
    
    public void gestPayroll(BufferedReader in, PrintWriter out){
        DaoPayroll dao = new DaoPayroll();
        JSONParser parser = new JSONParser();
        PayrollDetail pd;//Payroll Object to add in arrayList
        Employe emp;
        Payroll py;
        
        String response = "";
        try {
            //- Pass data to client
            out.println(dao.getAll());
            
            //- Wait for the action order
            response = in.readLine();
            //-Switch to do an action
            
            switch(response){
                case "generate":
                    //- sending the validate response
                    out.println(dao.validatePayrollGenerable());
                    //- waiting for the actionResponse of the client
                    response = in.readLine();
                    //- If the value of the response is generateNewPayroll, the server will 
                    //- wait for the data to do the generateAction
                    if(response.equals("generateNewPayroll")){
                        Double payrollTotal = 0.0;
                        ArrayList<PayrollDetail> data = new ArrayList();
                        //- Getting responseData
                        response = in.readLine();
                        //-Parsing to jsonObject
                        JSONObject obj = (JSONObject) parser.parse(response);
                        payrollTotal = Double.parseDouble(obj.get("total").toString());
                        JSONArray payrollDetail = (JSONArray) obj.get("detail");
                        //-Adding to ParyrollDetail ArrayList
                        for(Object item:payrollDetail){
                            JSONObject ob = (JSONObject)item;
                            pd = new PayrollDetail();
                            py = new Payroll();
                            emp = new Employe();
                            py.setPln_total(payrollTotal);
                            pd.setDet_pln_total(Double.parseDouble(ob.get("total").toString()));
                            pd.setDet_pln_total_descuentos(Double.parseDouble(ob.get("descuentos").toString()));
                            pd.setDet_pln_renta(Double.parseDouble(ob.get("renta").toString()));
                            pd.setDet_pln_afp(Double.parseDouble(ob.get("afp").toString()));
                            pd.setDet_pln_isss(Double.parseDouble(ob.get("isss").toString()));
                            pd.setDet_pln_cantidad_horas_diurnas(Byte.parseByte(ob.get("diurnas").toString()));
                            pd.setDet_pln_cantidad_horas_nocturnas(Byte.parseByte(ob.get("nocturnas").toString()));
                            pd.setDet_pln_bono_horas_extra(Double.parseDouble(ob.get("bono").toString()));
                            emp.setEmp_id(Integer.parseInt(ob.get("empleado").toString()));
                            pd.setEmploye(emp);
                            pd.setPayroll(py);
                            data.add(pd);
                        }
                        //- getting the add response
                        response = dao.add(data,payrollTotal);
                        //- print response to client
                        out.println(response);
                    }else if(response.equals("showActualPayroll")){
                        //- getting the add response
                        response = dao.getActual();
                        //- print response to client
                        out.println(response);
                    }
                    break;
                case "getPayrollDetail":
                    //- Waiting for the client response
                    response = in.readLine();
                    //- Response evaluation
                    if(response.contentEquals("getPayroll")){
                        //- Waiting for the payrollID
                        response = in.readLine();
                        //- Initializing object
                        py = new Payroll();
                        py.setPln_id(Integer.parseInt(response));
                        //- Print response to client
                        response = dao.getPayrollDetailById(py);
                        out.println(response);
                    }
                    break;
                case "payPayroll":
                    //- Waiting for the client response
                    response = in.readLine();
                    //- Response evaluation
                    if(response.contentEquals("pay")){
                        //- Waiting for the payrollID
                        response = in.readLine();
                        //- Initializing object
                        py = new Payroll();
                        py.setPln_id(Integer.parseInt(response));
                        //- Print response to client
                        response = dao.payPayroll(py);
                        out.println(response);
                    }
                    break;
                default:
                    break;
            }
            System.out.println("GOING TO PRINCIPAL MENU");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
