import java.time.LocalDateTime;

public class LogEntry {
    private final String ipAddress;
    private final LocalDateTime dateTime;
    private final HttpMethods httpMethod;
    private final String path;
    private final int statusCode;
    private final int bytesSent;
    private final String referer;
    private final String userAgent;

    public LogEntry(String line){
        String[] fields = line.split(" ");
        this.ipAddress = fields[0];
        this.dateTime = LocalDateTime.parse(fields[3]);
        this.httpMethod = HttpMethods.valueOf(fields[5].replace("\"", ""));
        this.path = fields[6].replace("\"", "");
        this.statusCode = Integer.parseInt(fields[8]);
        this.bytesSent = Integer.parseInt(fields[9]);
        this.referer = fields[10].replace("\"", "");
        this.userAgent = fields[12].replace("\"", "");
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

    public int getBytesSent() {
        return bytesSent;
    }

    public String getReferer() {
        return referer;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
