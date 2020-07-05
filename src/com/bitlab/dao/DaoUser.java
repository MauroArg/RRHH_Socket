package com.bitlab.dao;

import com.bitlab.entity.Rol;
import com.bitlab.entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        catch(Exception e)
        {
            resp=0;
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
    
    public ArrayList<User> getAll() throws ClassNotFoundException, SQLException{
        ArrayList<User> ar = new ArrayList<>();
        String sql = "SELECT * FROM rh_usuario;";
        ps = super.con().prepareStatement(sql);
        
        try 
        {
            rs = ps.executeQuery();
            while(rs.next()){
                rolOb = new Rol(rs.getInt(5));
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
        
        return ar;
    }
}
