package com.bitlab.app;

import com.bitlab.utility.Encryption;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                clientSocket.setSoTimeout(20000);
                //-
                //LOGIN PROCESS
                //
                loginProcess(in,out);
                //Setting Email
                //String email = in.readLine();
                //- 
                boolean activeFlag = true;
                while(activeFlag){
                    //Show menu to Client
                    //out.println("initMenu");
                    try{
                       if((inputLine = in.readLine()) != null){
                            switch(inputLine){
                                case "1":

                                    break;
                                case "2":

                                case "3":

                                    activeFlag = false;
                                    break;
                                default:
                                    out.println("unrecognized command.");
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
    public static void loginProcess(BufferedReader in, PrintWriter out){
        String user = "";
        String pass = "";
        boolean log = true;
        try {
            while(log){
                out.println("Digite su usuario: ");
                user = in.readLine();
                out.println("Digite su contraseña: ");
                pass = in.readLine();
                System.out.println("usuario: " + user + ", password: " + pass);
                if(user.equals("a")){
                    out.println("logSuccessful");
                    log = false;
                }else{
                    out.println("logIncorrect");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
