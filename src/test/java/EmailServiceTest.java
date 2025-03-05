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
                "TU PATH" + "\\ProyectoMail\\email_config.txt");

        EmailAccount account = new EmailAccount("TU CORREO O USERNAME", "TU CONTRASEÑA");
        EmailMessage message = new EmailMessage("CORREO DESTINO", "Prueba", "Hola");

        try {

            List<EmailServerConfig> configuraciones = new ArrayList<>(manager.getAvailableConfigs());

            for (EmailServerConfig configuracion : configuraciones) {
                System.out.println(configuracion.toString());
            }

            // Enviar el correo
            manager.sendEmail(message, "config1", account);

        } catch (EmailException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
