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
            if (os[1].contains("Windows")) {
                this.operatingSystem = OperatingSystem.WINDOWS.toString();
            } else if (os[1].contains("Mac")) {
                this.operatingSystem = OperatingSystem.MAC.toString();
            } else if (os[1].contains("Linux")) {
                this.operatingSystem = OperatingSystem.LINUX.toString();
            } else if (os[1].contains("Android")) {
                this.operatingSystem = OperatingSystem.ANDROID.toString();
            } else if (os[1].contains("iOS")) {
                this.operatingSystem = OperatingSystem.IOS.toString();
            } else {
                this.operatingSystem = OperatingSystem.OTHER.toString();

            }
            this.operatingSystem = os[0].trim();

            if (fragments[fragments.length - 1].contains("Chrome")) {
                this.browser = Browser.CHROME.toString();
            } else if (fragments[2].contains("Edge")) {
                this.browser = Browser.EDGE.toString();
            } else if (fragments[2].contains("Safari")) {
                this.browser = Browser.SAFARI.toString();
            } else if (fragments[2].contains("Opera")) {
                this.browser = Browser.OPERA.toString();
            } else if (fragments[2].contains("Firefox")) {
                this.browser = Browser.FIREFOX.toString();
            } else
                this.browser = Browser.OTHER.toString();


        }
    }
}

enum Browser {
    CHROME("Chrome"), EDGE("Edge"), SAFARI("Safari"), OPERA("Opera"), FIREFOX("Firefox"), OTHER("Other");

    private String title;

    Browser(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return title;
    }
}

enum OperatingSystem {
    WINDOWS("Windows"), MAC("Mac"), LINUX("Linux"), ANDROID("Android"), IOS("iOS"), OTHER("Other");

    private String title;

    OperatingSystem(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}


