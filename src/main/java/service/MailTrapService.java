package service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import models.Email;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class MailTrapService {

    private Message message;

    public MailTrapService(String username, String password, Email email) throws MessagingException {

        Session session = Session.getInstance(config(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        message = new MimeMessage(session);

        message.setFrom(new InternetAddress(email.getFrom()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
        message.setSubject(email.getSubject());

        MimeBodyPart body = new MimeBodyPart();
        body.setContent(email.getBody(), "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(body);

        for(String file : email.getImgs()){
            try{
                MimeBodyPart attachment = new MimeBodyPart();

                attachment.attachFile(new File(file));

                multipart.addBodyPart(attachment);
            }catch(IOException e){
                System.out.println(e);
            }
        }
        message.setContent(multipart);
    }

    private Properties config(){
        Properties prop = new Properties();

        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "2525");
        prop.put("mail.smtp.auth", "true");

        return prop;
    }

    public void send() throws MessagingException {
        Transport.send(this.message);
    }
}
