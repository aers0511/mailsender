package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import domain.EmailServerConfig;

public class EmailConfigManager {

    private final Properties properties = new Properties();

    public EmailConfigManager(String configFilePath) {
        try {
            properties.load(new FileReader(configFilePath));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar el archivo de configuración.", e);
        }
    }

    public EmailServerConfig getConfiguration(String configName) throws IOException {
        EmailServerConfig config = new EmailServerConfig();
        config.setSmtpServer(properties.getProperty(configName + ".smtpServer"));
        config.setPort(Integer.parseInt(properties.getProperty(configName + ".port")));
        config.setTlsEnabled(Boolean.parseBoolean(properties.getProperty(configName + ".tlsEnabled")));

        return config;
    }

    public List<EmailServerConfig> getAllConfigs() {
        Set<String> keys = properties.stringPropertyNames();
        List<EmailServerConfig> configs = new ArrayList<>();

        for (String key : keys) {
            if (key.contains(".smtpServer")) {
                // Obtener el nombre de la configuración (suponiendo que el formato es
                // <config-name>.smtpServer)
                String configName = key.split("\\.")[0];

                // Obtener las propiedades asociadas al configName
                String smtpServer = properties.getProperty(configName + ".smtpServer");
                int port = Integer.parseInt(properties.getProperty(configName + ".port"));
                boolean tlsEnabled = Boolean.parseBoolean(properties.getProperty(configName + ".tlsEnabled"));

                // Crear una instancia de EmailServerConfig y asignarle el nombre de la
                // configuración
                EmailServerConfig config = new EmailServerConfig();
                config.setConfigName(configName); // Asignar el nombre de la configuración
                config.setSmtpServer(smtpServer);
                config.setPort(port);
                config.setTlsEnabled(tlsEnabled);

                configs.add(config);
            }
        }

        return configs;
    }
}
