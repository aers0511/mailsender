import java.util.ArrayList;
import java.util.List;

import controller.EmailManager;
import domain.EmailAccount;
import domain.EmailMessage;
import domain.EmailServerConfig;
import exception.EmailException;

public class EmailServiceTest {
    public static void main(String[] args) {

        EmailManager manager = new EmailManager(
                "C:\\Users\\angel\\OneDrive\\Escritorio\\Arquitectura_Software2025\\ProyectoMail\\email_config.txt");

        EmailAccount account = new EmailAccount("angelrs-2000@hotmail.com", "DanielReyes@12");
        EmailMessage message = new EmailMessage("angelrs-2000@hotmail.com", "Prueba", "Hola");

        try {

            List<EmailServerConfig> configuraciones = new ArrayList<>(manager.getAvailableConfigs());

            for (EmailServerConfig configuracion : configuraciones) {
                System.out.println(configuracion.toString());
            }

            // Enviar el correo
            manager.sendEmail(message, "config1", account);

        } catch (EmailException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
