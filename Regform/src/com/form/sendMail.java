package com.form;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.MessagingException;

public class sendMail 
{ 
public static void send(String to, String sub, String msg, final String user,final String pass)
{ 

Properties props = new Properties();

props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.user",user);
props.put("mail.smtp.password",pass);
props.put("mail.smtp.starttls.enable", "true"); 
props.setProperty("mail.smtp.port", "587");   
props.put("mail.smtp.auth", "true");

Session session = Session.getInstance(props,new javax.mail.Authenticator()
{
protected PasswordAuthentication getPasswordAuthentication() 
{
return new PasswordAuthentication(user,pass);
}
});

try
{

MimeMessage message = new MimeMessage(session);
message.setFrom(new InternetAddress(user));
message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
message.setSubject(sub);
message.setText(msg);

Transport.send(message);


}
catch (MessagingException e) {
    e.printStackTrace();
}
catch(Exception e)
{
e.printStackTrace();
}
} 
}