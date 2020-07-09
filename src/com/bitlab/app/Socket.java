package com.bitlab.app;

import com.bitlab.dao.DaoUser;
import com.bitlab.entity.User;
import com.bitlab.utility.Email;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

/**
 * @author Gustavo Gómez
 * class: Socket
 * fecha: 2020-07-02
 */
public class Socket {
     //-
    //CONSTANTS
    private static final int PORT = 8080;
    //-
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initSocket();
    }
    
    //- GG
    //WebSocket Configurations
    public static void initSocket(){
        try {
            //- App Functions Process
            AppProcess app = new AppProcess();
            //- Validation var
            boolean vFlag = true;
            
            //-
            //Socket configurations
            ServerSocket serverSocket = new ServerSocket(PORT);
            java.net.Socket clientSocket;
            PrintWriter out = null;
            BufferedReader in = null;
            String inputLine = "";
            //-
            do{
                System.out.println("Daemon process is acepting connections in port " + PORT);
                clientSocket = serverSocket.accept();
                System.out.println("Daemon process acepted connection from client " + clientSocket.getInetAddress());
                //- 
                //data out of server
                //-
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                //- 
                //data read of client
                //-
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //-
                //Setting max wait time
                clientSocket.setSoTimeout(400000);
                //-
                //LOGIN PROCESS
                boolean activeFlag = true;
                loginProcess(in, out, activeFlag);
                
                //Setting Email
                //String email = in.readLine();
                //- 
                
                while(activeFlag){
                    //Show menu to Client
                    //out.println("initMenu");
                    try{
                       if((inputLine = in.readLine()) != null){
                           System.out.println(inputLine);
                            switch(inputLine){
                                case "gestDepartament":
                                    System.out.println("Entrando a gestion de departamentos.");
                                    app.gestDepartament(in, out);
                                    break;
                                case "gestUser":
                                    System.out.println("entrando a gestion de usuarios.");
                                    app.gestUser(in, out);
                                    break;
                                case "gestEmploye":
                                    System.out.println("entrando a gestion de empleados.");
                                    app.gestEmploye(in, out);
                                    break;
                                case "gestRol":
                                    System.out.println("entrando a gestion de roles.");
                                    app.gestRol(in, out);
                                    break;
                                case "gestPayroll":
                                    System.out.println("entrando a gestion de planilla.");
                                    app.gestPayroll(in, out);
                                    break;
                                case "systemExit":
                                    System.out.println("saliendo del sistema.");
                                    app.gestPayroll(in, out);
                                    activeFlag = false;
                                    break;
                                default:
                                    System.out.println("unrecognized command.");
                                    out.println("unrecognized command.");
                                    break;
                            }
                        }
                        Thread.sleep(1000); 
                    }catch (IOException ex) {
                        System.out.println("ERROR1: " + ex.getMessage());
                        activeFlag = false;
                    }
                }
                //-
                //Close of all objects
                if(in != null){in.close();}
                if(out != null){out.close();}
                clientSocket.close();
                System.out.println("Daemon process is closing   connection with client.");
                //-
            }while(!".Exit".equalsIgnoreCase(inputLine));
        } catch (IOException ex) {
            System.out.println("ERROR1: " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println("ERROR2: " + ex.getMessage());
        }
    }
    
    //- GG
    public static void loginProcess(BufferedReader in, PrintWriter out, boolean app){
        //- Validation vars
        String user = "",pass = "";
        DaoUser dao = new DaoUser();
        Email email = new Email();
        boolean log = true;
        int id = 0, rol = 0;
        User us;
        //- Validation conventional log
        while (log) {
            try {
                //- Petitions to LoginProcess
                //- username pt
                out.println("Digite su usuario: ");
                user = in.readLine();
                //- password pt
                out.println("Digite su contraseña: ");
                pass = in.readLine();
                System.out.println("usuario: " + user + ", password: " + pass);
                us = new User(user, pass);
                id = dao.iniciar(us);
                if (id != 0) {
                    out.println("logSuccessful");
                    log = false;
                } else {
                    out.println("logIncorrect");
                }
            } catch (IOException ex) {
                System.out.println("ERROR1: " + ex.getMessage());
                log = false;
            }
        }

        //-Vars to email Verification
        log = true;
        String correo = "";
        //- Validating emailExists
        while (log) {
            try {
                correo = in.readLine();
                rol = dao.emailVerification(correo, id);
                System.out.println(rol);
                if (rol != 0) {
                    out.println("vSuccessful");
                    log = false;
                } else {
                    out.println("wrongEmail");
                }
            } catch (IOException ex) {
                System.out.println("ERROR1: " + ex.getMessage());
                log = false;
            }
        }

        //-Vars to code verification
        log = true;
        int code = 0, rsCode = 0;
        code = email.sendMail(correo);
        System.out.println("correo enviado a: " + correo);
        //- Validating verification code
        while (log) {
            try {
                rsCode = Integer.parseInt(in.readLine());
                if (code == rsCode) {
                    out.println(rol);
                    log = false;
                } else {
                    out.println("Código incorrecto.");
                }
            } catch (IOException ex) {
                System.out.println("ERROR1: " + ex.getMessage());
                log = false;
            }

        }
    }
    
}
