package domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailServerConfig {
    private String configName;
    private String smtpServer;
    private int port;
    private boolean tlsEnabled;
}
