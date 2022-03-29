package notification;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailSender {
    private static MailSender instance;
    private final Session session;

    private MailSender() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        //prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.debug", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.port", "587");
        this.session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mrozon.mastermind@gmail.com", "D={-<Pfz8a^f");
            }
        });
    }

    public static MailSender getInstance() {
        if (instance == null) {
            instance = new MailSender();
        }
        return instance;
    }

    public void sendMail(String address, String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mrozon.mastermind@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(address));
            message.setSubject("Notification from Mrozon-Mastermind");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(text, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println(e);
        }

    }
}
