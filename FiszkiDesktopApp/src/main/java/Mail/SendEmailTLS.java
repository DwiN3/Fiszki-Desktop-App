package Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailTLS {

    private String mailToSend, subjectToSend, messageToSend;
    private String myMail ="student35196ans@gmail.com", myPassword="plwjpeasxptkbstk";
    public SendEmailTLS(String _mail, String _subject, String _message){
        mailToSend = _mail;
        subjectToSend = _subject;
        messageToSend = _message;
    }

    public void sendMessage() {
        final String username = myMail;
        final String password = myPassword;

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myMail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailToSend)
            );
            message.setSubject(subjectToSend);
            message.setText(messageToSend);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}