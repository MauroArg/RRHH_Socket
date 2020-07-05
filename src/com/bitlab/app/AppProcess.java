package com.bitlab.app;

import com.bitlab.dao.*;
import com.bitlab.entity.Departament;
import com.bitlab.entity.Employe;
import com.bitlab.entity.Rol;
import com.bitlab.entity.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
                    //- Update action
                    response = in.readLine();
                    JSONObject obj = (JSONObject) parser.parse(response);
                    JSONObject depObj = (JSONObject)obj.get("departament");
                    dep.setDep_id(Integer.parseInt(depObj.get("id").toString()));
                    dep.setDep_nombre(depObj.get("nombre").toString());
                    dao.update(dep);
                    break;
                case "create":
                    //- Create action
                    response = in.readLine();
                    dep.setDep_nombre(response);
                    dao.add(dep);
                    break;
                default:
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
    }
    
    public void gestUser(BufferedReader in, PrintWriter out){
        DaoUser dao = new DaoUser();
        User us;
        Rol rol;
        JSONParser parser = new JSONParser();
        JSONObject obj;
        JSONObject usObj;
        String response = "";
        try {
            //- Pass data to client
            out.println(dao.getData());
            //- Wait for the action order
            response = in.readLine();
            //-Switch to do an action
            switch(response){
                case "update":
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
                    us.setUs_correo(usObj.get("username").toString());
                    us.setUs_contra(usObj.get("password").toString());
                    us.setUs_correo(usObj.get("correo").toString());
                    rol.setRol_id(Integer.parseInt(usObj.get("id_rol").toString()));
                    us.setRol(rol);
                    //- Update and get of response
                    response = dao.update(us);
                    //- print response to client
                    out.print(response);
                    break;
                case "create":
                    //- Create action
                    //- GET JSON 
                    response = in.readLine();
                    obj = (JSONObject) parser.parse(response);
                    //- GET KEY user
                    usObj = (JSONObject)obj.get("user");
                    //-Initilize objects
                    us = new User();
                    rol = new Rol();
                    //- Parsing json and setting data to objects
                    us.setUs_correo(usObj.get("username").toString());
                    us.setUs_contra(usObj.get("password").toString());
                    us.setUs_correo(usObj.get("correo").toString());
                    rol.setRol_id(Integer.parseInt(usObj.get("id_rol").toString()));
                    us.setRol(rol);
                    //- Create and get of response
                    response = dao.add(us);
                    //- print response to client
                    out.print(response);
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
                    out.print(response);
                    break;
                default:
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
    }
    
    public void gestEmploye(BufferedReader in, PrintWriter out){
        DaoEmploye dao = new DaoEmploye();
        Employe emp;
        Departament dep;
        JSONParser parser = new JSONParser();
        JSONObject obj;
        JSONObject empObj;
        String response = "";
        try {
            //- Pass data to client
            out.println(dao.getData());
            //- Wait for the action order
            response = in.readLine();
            //-Switch to do an action
            switch(response){
                case "update":
                    //- Update action
                    //- GET JSON 
                    response = in.readLine();
                    obj = (JSONObject) parser.parse(response);
                    //- GET KEY user
                    empObj = (JSONObject)obj.get("employe");
                    //-Initilize objects
                    emp = new Employe();
                    dep = new Departament();
                    //- Parsing json and setting data to objects
                    emp.setEmp_id(Integer.parseInt(empObj.get("id").toString()));
                    emp.setEmp_nombres(empObj.get("nombres").toString());
                    emp.setEmp_apellidos(empObj.get("apellidos").toString());
                    emp.setEmp_dui(empObj.get("dui").toString());
                    emp.setEmp_nit(empObj.get("nit").toString());
                    emp.setEmp_correo(empObj.get("correo").toString());
                    emp.setEmp_telefono(empObj.get("telefono").toString());
                    emp.setEmp_sueldo(Double.parseDouble(empObj.get("sueldo").toString()));
                    emp.setEmp_direccion(empObj.get("direccion").toString());
                    emp.setEmp_estado(Byte.parseByte(empObj.get("estado").toString()));
                    dep.setDep_id(Integer.parseInt(empObj.get("id_dep").toString()));
                    emp.setDepartamento(dep);
                    emp.setEmp_jef_id(Integer.parseInt(empObj.get("id_jefe").toString()));
                    //- Update and get of response
                    response = dao.update(emp);
                    //- print response to client
                    out.print(response);
                    break;
                case "create":
                    //- Update action
                    //- GET JSON 
                    response = in.readLine();
                    obj = (JSONObject) parser.parse(response);
                    //- GET KEY user
                    empObj = (JSONObject)obj.get("employe");
                    //-Initilize objects
                    emp = new Employe();
                    dep = new Departament();
                    //- Parsing json and setting data to objects
                    emp.setEmp_nombres(empObj.get("nombres").toString());
                    emp.setEmp_apellidos(empObj.get("apellidos").toString());
                    emp.setEmp_dui(empObj.get("dui").toString());
                    emp.setEmp_nit(empObj.get("nit").toString());
                    emp.setEmp_correo(empObj.get("correo").toString());
                    emp.setEmp_telefono(empObj.get("telefono").toString());
                    emp.setEmp_sueldo(Double.parseDouble(empObj.get("sueldo").toString()));
                    emp.setEmp_direccion(empObj.get("direccion").toString());
                    emp.setEmp_estado(Byte.parseByte(empObj.get("estado").toString()));
                    dep.setDep_id(Integer.parseInt(empObj.get("id_dep").toString()));
                    emp.setDepartamento(dep);
                    emp.setEmp_jef_id(Integer.parseInt(empObj.get("id_jefe").toString()));
                    //- Update and get of response
                    response = dao.update(emp);
                    //- print response to client
                    out.print(response);
                    break;
                default:
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
    }
}
