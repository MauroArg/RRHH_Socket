/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entity;
import com.bitlab.app.AppProcess;
import com.bitlab.dao.Conexion;
import com.bitlab.dao.DaoDepartament;
import com.bitlab.dao.DaoUser;
import com.bitlab.utility.Encryption;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.mail.FetchProfile.Item;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author gusst
 */
public class pruebaJSONapp {
    
    private static Logger log = Logger.getLogger(pruebaJSONapp.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DaoDepartament dao = new DaoDepartament();
        JSONParser parser = new JSONParser();
        JSONObject responseData = new JSONObject();
        ArrayList<Departament> list = new ArrayList();
        Departament dep = new Departament();
        String response = "{\"departaments\":[{\"id\":1,\"nombre\":\"Informatica\"},{\"id\":2,\"nombre\":\"RRHH\"},{\"id\":3,\"nombre\":\"Ventas\"}]}";
        try {
            //- Update action
            JSONObject obj = (JSONObject) parser.parse(response);
            JSONArray depArray = (JSONArray) obj.get("departaments");
            for(Object item:depArray){
                JSONObject depObj = (JSONObject)item;
                dep = new Departament();
                dep.setDep_id(Integer.parseInt(depObj.get("id").toString()));
                dep.setDep_nombre(depObj.get("nombre").toString());
                //System.out.println(dep.getDep_nombre());
                list.add(dep);
            }
            
            System.out.println(list.get(0).getDep_nombre());
            /*JSONObject depObj = (JSONObject)depArray.get(2);
            dep.setDep_id(Integer.parseInt(depObj.get("id").toString()));
            dep.setDep_nombre(depObj.get("nombre").toString());
            System.out.println(dep.getDep_nombre());*/
            
        }  catch (ParseException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        /*Encryption enc = new Encryption();
        System.out.println(enc.decrypt("BxV/e/4IwIerSxs/Hm6PFgSgGNyL8hm7myA30MCYEE0="));
        System.out.println(enc.decrypt("EEOzJdKrAdoaoXshuDfoMw=="));
        */
        /*DaoUser dao = new DaoUser();
        User us = new User();
        us = new User("admin","2njkIyJSFeR2lqO/jv0gNw==");
        int res = dao.iniciar(us);
        System.out.println(res);
        ArrayList<User> list = new ArrayList();
        ArrayList<User> userListJSON = new ArrayList();
        JSONObject responseDetailsJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();*/
        
        /*try {
            list = dao.getAll();
            for(User user:list){
                JSONObject formDetailsJson = new JSONObject();
                formDetailsJson.put("id", user.getUs_id());
                formDetailsJson.put("username", user.getUs_usuario());
                formDetailsJson.put("password", user.getUs_contra());
                //formDetailsJson.put("rol", user.getRol_id());
                jsonArray.add(formDetailsJson);
            }
            responseDetailsJson.put("users", jsonArray);
            System.out.println(responseDetailsJson);
            
            //-JSON to object
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(responseDetailsJson.toJSONString());
            JSONArray array = (JSONArray) obj.get("users");
            for (Object item : array) {
                JSONObject object = (JSONObject) item;
                //us.setRol_id(Byte.parseByte(object.get("rol").toString()));
                us.setUs_usuario(object.get("username").toString());
                us.setUs_contra(object.get("password").toString());
                us.setUs_id(Byte.parseByte(object.get("id").toString()));
                userListJSON.add(us);
            }
            System.out.print("The 2nd element of array, username: ");
            System.out.println(userListJSON.get(0));
            
        } catch (ParseException ex) {
            Logger.getLogger(pruebaJSONapp.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(pruebaJSONapp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(pruebaJSONapp.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
           
       /* JSONObject responseDetailsJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();*/
        
        //-Object to JSON
        /*ArrayList<User> userList = new ArrayList();
        ArrayList<User> userListJSON = new ArrayList();
        User us = new User("usuario1","ejemplo");
        userList.add(us);
        us = new User("usuario2","ejemplo");
        userList.add(us);
        
        for(var user:userList){
            JSONObject formDetailsJson = new JSONObject();
            formDetailsJson.put("username", user.username);
            formDetailsJson.put("password", user.password);
            jsonArray.add(formDetailsJson);
        }
        
        responseDetailsJson.put("users", jsonArray);
        System.out.println(responseDetailsJson);
        //-JSON to object
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(responseDetailsJson.toJSONString());
            JSONArray array = (JSONArray) obj.get("users");
            for (Object item : array) {
                JSONObject object = (JSONObject) item;
                us = new User(object.get("username").toString(),object.get("username").toString());
                userListJSON.add(us);
            }
            System.out.print("The 2nd element of array, username: ");
            System.out.println(userListJSON.get(1).username);
            
        } catch (ParseException ex) {
            Logger.getLogger(pruebaJSONapp.class.getName()).log(Level.SEVERE, null, ex);
        }*/
                
    }
    
}
