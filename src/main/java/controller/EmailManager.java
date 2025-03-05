package controller;

import java.io.IOException;
import java.util.List;

import config.EmailConfigManager;
import domain.EmailAccount;
import domain.EmailMessage;
import domain.EmailServerConfig;
import exception.EmailException;
import service.GenericEmailSender;

public class EmailManager {
    private final EmailConfigManager configManager;
    private final GenericEmailSender emailSender;

    public EmailManager(String configFilePath) {
        this.configManager = new EmailConfigManager(configFilePath);
        this.emailSender = new GenericEmailSender();
    }

    public void sendEmail(EmailMessage message, String configName, EmailAccount account) throws EmailException {
        try {
            EmailServerConfig config = configManager.getConfiguration(configName);
            emailSender.sendEmail(message, config, account);
        } catch (IOException e) {
            throw new EmailException("Error al cargar configuración: " + e.getMessage(), e);
        }
    }

    public List<EmailServerConfig> getAvailableConfigs() {
        return configManager.getAllConfigs();
    }
}
