import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogEntry {
    private final String ipAddress;
    private final LocalDateTime dateTime;
    private final HttpMethods httpMethod;
    private final String path;
    private final int statusCode;
    private final long bytesSent;
    private final String referer;
    private final String userAgent;

    public LogEntry(String logString) {
        String pattern = "(\\d+\\.\\d+\\.\\d+\\.\\d+) - - \\[(.*?)\\] \"(\\w+) (.*?)\" (\\d+) (\\d+) \"(.*?)\" \"(.*?)\"";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(logString);

        if (matcher.matches()) {
            this.ipAddress = matcher.group(1);
            this.dateTime = LocalDateTime.parse(matcher.group(2), DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss +0300",  Locale.ENGLISH));
            this.httpMethod = HttpMethods.valueOf(matcher.group(3));
            this.path = matcher.group(4);
            this.statusCode = Integer.parseInt(matcher.group(5));
            this.bytesSent = Long.parseLong(matcher.group(6));
            this.referer = matcher.group(7);
            this.userAgent = matcher.group(8);
        } else {
            throw new IllegalArgumentException("Line doesn't match pattern" + logString);
        }
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HttpMethods getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long getBytesSent() {
        return bytesSent;
    }

    public String getReferer() {
        return referer;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
