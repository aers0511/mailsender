package service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import domain.EmailAccount;
import domain.EmailMessage;
import domain.EmailServerConfig;
import exception.EmailException;

public class GenericEmailSender implements EmailSender {

    @Override
    public void sendEmail(EmailMessage message, EmailServerConfig serverConfigName, EmailAccount account)
            throws EmailException {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", serverConfigName.getSmtpServer());
            props.put("mail.smtp.port", serverConfigName.getPort());
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", String.valueOf(serverConfigName.isTlsEnabled()));

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(account.getUsername(), account.getPassword());
                }
            });

            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(account.getUsername()));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(message.getRecipient()));
            mimeMessage.setSubject(message.getSubject());
            mimeMessage.setText(message.getBody());

            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailException("Fallo en el envío del correo: " + e.getMessage(), e);
        }
    }
}
