import java.util.Arrays;

import static java.util.Collections.replaceAll;


public class UserAgent {
    private String operatingSystem;
    private String browser;

    public UserAgent(String userAgent) {
        String[] parts = userAgent.split("\"");
        if (parts.length >= 2) {
            String fragment = parts[5];
            String[] fragments = fragment.split(" ");
            String[] os = fragments[1].split(";");
            this.operatingSystem = os[0].trim();

            this.browser = fragments[0];
            this.browser = fragments[fragments.length - 1];
            String[] browserParts = this.browser.split("/");

        }
    }
}


