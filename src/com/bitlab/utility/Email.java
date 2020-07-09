package com.bitlab.utility;
//-
//Imports
import java.io.File;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * @author Gustavo Gómez
 * class: Email
 * fecha: 2020-07-02
 */
public class Email {
    private static final String[] MDATA = {"BxV/e/4IwIerSxs/Hm6PFgSgGNyL8hm7myA30MCYEE0=","EEOzJdKrAdoaoXshuDfoMw=="};

    public Email() {
    }
    
    public int sendMail(String to){
        try{
            int rNum = (int) Math.floor(Math.random()*(99999999-100000+1)+100000);
            StringBuilder content = new StringBuilder();
            content.append("<div style='padding:10px;background-color:black;");
            content.append("border-radius:5px;color:yellow;");
            content.append("font-size:19px;");
            content.append("background: lightblue url(\"https://www.thefinishingtouch.co.uk/wp-content/uploads/2019/09/coffee-and-laptop.jpg\") no-repeat fixed center;");
            content.append("background-size:cover;padding:10px;'>");
            content.append("<center><div style='background-color: rgba(17, 17, 17 , 0.8);border-radius:20px;'>");
            content.append("<h4 style='color:white;padding:5px;'>Este es tu código de verificación</h4>");
            content.append("<h4 style='color:white;padding:5px;'>").append(rNum).append("</h4>");
            content.append("</div></center>");
            content.append("</div>");
            Encryption enc = new Encryption();
            String ed = enc.decrypt(MDATA[0]);
            String md = enc.decrypt(MDATA[1]);
            String asunto = "Código de verificación.";
            String mensaje = "Código "+rNum;
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session s = Session.getDefaultInstance(props, null);
            BodyPart tex = new MimeBodyPart();
            tex.setText("Este código de verificación solo se utiliza una vez.");
            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(content.toString(), "text/html; charset=utf-8");
            
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(tex);
            m.addBodyPart(htmlPart);
            
            MimeMessage msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(ed));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(asunto);
            
            msg.setContent(m);
            Transport t = s.getTransport("smtp");
            t.connect("smtp.gmail.com",587,ed,md);
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
            return rNum;
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
}
