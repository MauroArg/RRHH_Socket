package com.bitlab.dao;

import com.bitlab.entity.Departament;
import com.bitlab.entity.Rol;
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
 * @author Gustavo Gómez
 * class: DaoUser
 * fecha: 2020-07-02
 */

public class DaoUser extends Conexion{
    PreparedStatement ps;
    ResultSet rs;
    User usuario;
    Rol rolOb;
    private int rol;
    private int id;
    
    public int iniciar(User u)
    {
        int resp = 0;
        ArrayList<User> ar = new ArrayList<>();
        try
        {
            
            ps=con().prepareStatement("select US_ID,ROL_ID from rh_usuario where US_USUARIO=? and US_CONTRA=?");
            ps.setString(1, u.getUs_usuario());
            ps.setString(2, u.getUs_contra());
            rs=ps.executeQuery();
            while(rs.next())
            {
                rolOb = new Rol(rs.getInt(2));
                u=new User(rs.getInt(1),rolOb);
                this.id=rs.getInt(1);
                this.rol=rs.getInt(2);
                ar.add(u);
            }
            
            if(ar.isEmpty())
            {
                resp=0;
            }
            else
            {
                resp=this.id;
            }
        }
        catch(Exception e)
        {
            
        }
        finally
        {
            try {
                con().close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resp;
    }
    
    public int emailVerification(String correo, int id){
        int resp = 0;
        ArrayList<User> ar = new ArrayList<>();
        
        try
        {
            User u;
            ps=con().prepareStatement("select ROL_ID from rh_usuario where US_ID=? and US_CORREO=?");
            ps.setInt(1, id);
            ps.setString(2, correo);
            rs=ps.executeQuery();
            while(rs.next())
            {
                Rol rol= new Rol();
                rol.setRol_id(rs.getInt(1));
                u=new User(rol);
                this.id=id;
                this.rol=rs.getInt(1);
                ar.add(u);
            }
            
            if(ar.isEmpty())
            {
                resp=0;
            }
            else
            {
                resp=this.rol;
            }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            resp=0;
        }
        finally
        {
            try {
                con().close();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resp;
    }
    
    public String getData() throws ClassNotFoundException, SQLException{
        ArrayList<User> ar = new ArrayList<>();
        String sql = "SELECT u.*,rol.ROL_NOMBRE FROM rh_usuario u INNER JOIN rh_rol rol ON rol.ROL_ID=u.ROL_ID;";
        ps = super.con().prepareStatement(sql);
        
        try 
        {
            rs = ps.executeQuery();
            while(rs.next()){
                rolOb = new Rol(rs.getInt(5),rs.getString(6));
                usuario = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rolOb);
                ar.add(usuario);
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
        for(User us:ar){
            JSONObject formDetailsJson = new JSONObject();
            formDetailsJson.put("id", us.getUs_id());
            formDetailsJson.put("username", us.getUs_usuario());
            formDetailsJson.put("correo", us.getUs_correo());
            formDetailsJson.put("rol_id", us.getRol().getRol_id());
            formDetailsJson.put("rol_nombre", us.getRol().getRol_nombre());
            jsonArray.add(formDetailsJson);
        }
        jsonData.put("users", jsonArray);
        return jsonData.toJSONString();
    }
    
    public String add(User us){
        int res = 0;
        String response = "";
        try 
        {
            String sql = "INSERT INTO rh_usuario(US_USUARIO,US_CONTRA,US_CORREO,ROL_ID) VALUES(?,?,?,?);";
            ps = super.con().prepareStatement(sql);
            ps.setString(1, us.getUs_usuario());
            ps.setString(2,us.getUs_contra());
            ps.setString(3, us.getUs_correo());
            ps.setInt(4, us.getRol().getRol_id());
            res=ps.executeUpdate();
            if(res>0){response="exitoso";}else{response="ha ocurrido un error.";}
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            response = "ERROR: "+e.getMessage();
        }  
        return response;
    }
    
    public String update(User us){
        int res = 0;
        String response = "";
        try 
        {
            String sql = "UPDATE rh_usuario SET US_USUARIO=?,US_CONRA=?,US_CORREO=?,ROL_ID=? WHERE US_ID=?;";
            ps = super.con().prepareStatement(sql);
            ps.setString(1, us.getUs_usuario());
            ps.setString(2,us.getUs_contra());
            ps.setString(3, us.getUs_correo());
            ps.setInt(4, us.getRol().getRol_id());
            ps.setInt(5, us.getUs_id());
            res=ps.executeUpdate();
            if(res>0){response="exitoso";}else{response="ha ocurrido un error.";}
        } 
        catch (Exception e) 
        {
            response = "ERROR: "+e.getMessage();
        }  
        return response;
    }
    
    public String delete(User us){
        int res = 0;
        String response = "";
        if(us.getUs_id()==1){response="No se pueden eliminar usuarios de tipo Administrador";}else{
            try 
            {
                String sql = "DELETE WHERE US_ID=?;";
                ps = super.con().prepareStatement(sql);
                ps.setInt(1, us.getUs_id());
                res=ps.executeUpdate();
                if(res>0){response="exitoso";}else{response="ha ocurrido un error.";}
            } 
            catch (ClassNotFoundException | SQLException e) 
            {
                response = "ERROR: "+e.getMessage();
            }  
        }
        return response;
    }
}
