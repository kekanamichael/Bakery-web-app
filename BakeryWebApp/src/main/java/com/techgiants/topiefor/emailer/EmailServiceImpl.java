/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.emailer;

import com.techgiants.topiefor.model.EmailMessage;
import com.techgiants.topiefor.service.EmailService;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author VM JELE
 */
public class EmailServiceImpl implements EmailService {

    private final Properties properties;
    private final Session session;
    private Transport trans;
    private final String sender;
    private final String password;

    public EmailServiceImpl(Properties properties, String sender, String password) {
        this.properties = properties;
        session = Session.getInstance(this.properties);
        this.sender = sender;
        this.password = password;
        try {
            trans = session.getTransport("smtp");
        } catch (NoSuchProviderException ex) {

        }
    }

    @Override
    public void sendMail(EmailMessage msg) throws Exception {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msg.getReceiver()));
        message.setSubject(msg.getSubject());
        message.setContent(msg.getMessage(), msg.getMessageType());

        trans.connect(properties.getProperty("smtpHost"), 587, sender, password);
        trans.sendMessage(message, message.getAllRecipients());
        trans.close();

    }

    public static void main(String[] args) {
        String smtpHost = "smtp.gmail.com";
        int smtpPort = 587;
        
        String senderEmail = "topie4techg@gmail.com";
        String senderPassword = "zjyfcqtvpcnqxfpd";
        
        
        Properties  properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        
        EmailServiceImpl impl = new EmailServiceImpl(properties, senderEmail, senderPassword);
        
        EmailMessage msg = new EmailMessage("<htm><head></head><body>"
                + "<p>Dear customer,</p><br>"
                + "<p>We would like to confirm that you have successfully placed an order.</p>"
                + "<br><p>Kind regards,</p>"
                + "<p>ToPieFor</p>"
                + "</body></html>", "text/html","TOPIE4 ORDER confirmation","ToPieFor-techgiants","bfmokoena7@gmail.com");
        
        try {
            impl.sendMail(msg);
        } catch (Exception ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        
            
        
    }

}
