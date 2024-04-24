import java.util.Arrays;

import static java.util.Collections.replaceAll;


public class UserAgent {
    private String operatingSystem;
    private String browser;

    public UserAgent(String userAgent) {
        String[] parts = userAgent.split(";");
        if (parts.length > 2) {

            if (parts[1].contains("Windows") || parts[1].contains("Win") || parts[1].contains("Win32") || parts[1].contains("Win64") || parts[1].contains("Win64; x64") || parts[1].contains("WOW64")) {
                this.operatingSystem = OperatingSystem.WINDOWS.toString();
            } else if (parts[1].contains("Mac") || parts[2].contains("MacOS") || parts[2].contains("iOS")) {
                this.operatingSystem = OperatingSystem.MAC.toString();
            } else if (parts[1].contains("Linux")) {
                this.operatingSystem = OperatingSystem.LINUX.toString();
            } else if (parts[1].contains("Android")) {
                this.operatingSystem = OperatingSystem.ANDROID.toString();
            } else if (parts[1].contains("iOS")) {
                this.operatingSystem = OperatingSystem.IOS.toString();
            } else {
                this.operatingSystem = OperatingSystem.OTHER.toString();
            }
            String[] browserFragment = parts[parts.length - 1].split("/");
            for (String s : browserFragment) {
                if (s.contains("Chrome")) {
                    this.browser = Browser.CHROME.toString();
                    break;
                } else if (s.contains("Edge")) {
                    this.browser = Browser.EDGE.toString();
                    break;
                } else if (s.contains("Safari")) {
                    this.browser = Browser.SAFARI.toString();
                    break;
                } else if (s.contains("Opera")) {
                    this.browser = Browser.OPERA.toString();
                    break;
                } else if (s.contains("Firefox")) {
                    this.browser = Browser.FIREFOX.toString();
                    break;
                }
                this.browser = Browser.OTHER.toString();
            }
        } else {
            this.operatingSystem = OperatingSystem.OTHER.toString();
            this.browser = Browser.OTHER.toString();
        }

    }

    public String getOperatingSystem() {
        return operatingSystem;
    }
}

enum Browser {
    CHROME("Google Chrome"), EDGE("Microsoft Edge"), SAFARI("Safari"), OPERA("Opera"), FIREFOX("Mozilla Firefox"), OTHER("Other browser");

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
    WINDOWS("Windows"), MAC("MacOS (or other MacOS-like OS)"), LINUX("Linux"), ANDROID("Android"), IOS("iOS"), OTHER("Other OS");

    private String title;

    OperatingSystem(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}


