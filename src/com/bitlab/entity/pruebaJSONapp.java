/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entity;
import com.bitlab.utility.Email;
import java.util.logging.Logger;

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
        Email email = new Email();
        email.sendMail("gusstavoandred123@gmail.com");
        
        int diurnas = 0, nocturnas = 0;//- Cantidad de horas extras
        Double sueldo = 0.0;//-Sueldo neto = sueldoEmpleado + (bonoHorasDiurnas) + (BonoHorasNocturnas);
        Double bonoDiurnas = 0.0;//- Bono total por horas diurnas
        Double bonoNocturnas = 0.0;///- Bono total por horas nocturnas
        Double bonoTotal = 0.0;//-Bono total por horas extra
        Double sueldoI = 0.0;//-Sueldo exacto del empelado
        Double sueldoPorHora = 0.0;//-Calculo de sueldo por hora
        //-Bonos por horas extras y sueldo para descuentos
        //-Sueldo por hora
        sueldoPorHora = (sueldoI / 30) / 8;
        //-Bono por hora extra diurna
        bonoDiurnas = diurnas * (sueldoPorHora * 2);
        //-Bono por hora extra nocturna
        bonoNocturnas = nocturnas * (sueldoPorHora * 2.25);
        //-Bono total por horas extras
        bonoTotal = bonoDiurnas + bonoNocturnas;
        //-sueldo para descuentos
        sueldo = sueldoI + bonoTotal;
        //-Variables para descuentos
        Double nSueldo = 0.0;//- Sueldo para renta
        Double afp = 0.0, isss = 0.0, renta = 0.0, cuota = 0.0;// descuentos
        Double tDescuentos = 0.0,total = 0.0;//- Total a pagar(Sueldo - descuentos) y total descuetnos
        //AFP 7.25%
        afp = sueldo * 0.0725;
        
        //ISSS 3% MÁXIMO $30 MENSUALES
        isss = sueldo * 0.03;
        if(isss>30.0){isss=30.0;}
        
        //- SUELDO TO RENTA
        nSueldo = sueldo - afp - isss;
        
        //----------- RENTA --------------
        //TRAMO 1 
        //- DE 0.01 A 472.0
        if(nSueldo>=0.01 && nSueldo<=472.0){
            cuota = 0.0;
            renta = 0.0;
        }
        
        //TRAMO 2
        //- DE 472.01 A 895.24
        if(nSueldo>=472.01 && nSueldo<=895.24){
            cuota = 17.67;
            renta =((sueldo - 472.0) * 0.1) + cuota;
        }
        
        //TRAMO 3
        //- DE 895.25 A 2038.10
        if(nSueldo>=895.25 && nSueldo<=2038.10){
            cuota = 60.00;
            renta =((sueldo - 895.24) * 0.2) + cuota;
        }
        
        //TRAMO 4
        //- DE 2038.11 ->
        if(nSueldo>=2038.11){
            cuota = 288.57;
            renta =((sueldo - 2038.10) * 0.3) + cuota;
        }
        
        //-Total de descuentos
        tDescuentos = renta + isss + afp;
        //-Total a pagar al empleado
        total = sueldo - tDescuentos;
        
        /*DaoDepartament dao = new DaoDepartament();
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
            System.out.println(dep.getDep_nombre());
            
        }  catch (ParseException ex) {
            Logger.getLogger(AppProcess.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
        
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
