package service;

import domain.EmailAccount;
import domain.EmailMessage;
import domain.EmailServerConfig;
import exception.EmailException;

public interface EmailSender {
    void sendEmail(EmailMessage message, EmailServerConfig serverConfigName, EmailAccount account)
            throws EmailException;
}
