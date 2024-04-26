import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private HashSet<String> uniquePaths;
    private HashSet<String> nonExistentPaths;
    private HashMap<String, Integer> operatingSystemRate;
    private HashMap<String, Integer> browserRate;
    private int bots;
    private int uniquePathsCounter;
    private int nonExistentPathsCounter;
    private int errorsCounter;
    private int totalRequests;
    private HashSet<String> uniqueVisitors;

    public Statistics() {
        this.minTime = null;
        this.maxTime = null;
        this.totalTraffic = 0;
        this.uniquePaths = new HashSet<>();
        this.nonExistentPaths = new HashSet<>();
        this.browserRate = new HashMap<>();
        this.operatingSystemRate = new HashMap<>();
        this.bots = 0;
        this.uniquePathsCounter = 0;
        this.nonExistentPathsCounter = 0;
        this.totalRequests = 0;
        this.uniqueVisitors = new HashSet<>();
    }


    public void addEntry(LogEntry o) {
        UserAgent ua = new UserAgent(o.getUserAgent());
        if (!ua.isBot()) {
            this.uniqueVisitors.add(o.getIpAddress());
        } else bots++;
        this.totalTraffic += o.getBytesSent();
        if (minTime == null || o.getDateTime().isBefore(minTime)) {
            this.minTime = o.getDateTime();
        }
        if (maxTime == null || o.getDateTime().isAfter(maxTime)) {
            this.maxTime = o.getDateTime();
        }
        if (o.getStatusCode() / 100 == 2) {
            uniquePaths.add(o.getPath());
            uniquePathsCounter++;
        }
        if (o.getStatusCode() == 404) {
            nonExistentPaths.add(o.getPath());
            nonExistentPathsCounter++;
        }
        if (o.getStatusCode() / 100 == 4 || o.getStatusCode() / 100 == 5) {
            errorsCounter++;
        }
        UserAgent ua = new UserAgent(o.getUserAgent());
        if (operatingSystemRate.containsKey(ua.getOperatingSystem())) {
            operatingSystemRate.put(ua.getOperatingSystem(), operatingSystemRate.get(ua.getOperatingSystem()) + 1);
        } else {
            operatingSystemRate.put(ua.getOperatingSystem(), 1);
        }
        if (browserRate.containsKey(ua.getBrowser())) {
            browserRate.put(ua.getBrowser(), browserRate.get(ua.getBrowser()) + 1);
        } else {
            browserRate.put(ua.getBrowser(), 1);

        }
    }

    public HashSet<String> getUniquePaths() {
        return new HashSet<>(new HashSet<>(uniquePaths));
    }

    public HashMap<String, Double> getOperatingSystemRate() {
        int total = operatingSystemRate.values().stream().mapToInt(Integer::intValue).sum();
        HashMap<String, Double> result = new HashMap<>();
        operatingSystemRate.forEach((key, value) -> {
            result.put(key, (double) value / (double) total);
        });
        return result;
    }

    public HashMap<String, Double> getBrowserRate() {
        int total = browserRate.values().stream().mapToInt(Integer::intValue).sum();
        HashMap<String, Double> result = new HashMap<>();
        browserRate.forEach((key, value) -> {
            result.put(key, (double) value / (double) total);
        });
        return result;
    }

    public int getTrafficRate() {
        int difference = (int) ChronoUnit.HOURS.between(minTime, maxTime);
        if (difference == 0) {
            difference = 1;
            System.out.println(difference);
        }
        return totalTraffic / difference;
    }

    public int hourRate(UserAgent ua) {
        int result = 0;
        int difference = (int) ChronoUnit.HOURS.between(minTime, maxTime);
        if (!ua.isBot()) {
            result = (totalRequests) / difference;
        }
        return result;
    }

    public int hourRateErrors() {
        int difference = (int) ChronoUnit.HOURS.between(minTime, maxTime);
        return errorsCounter / difference;
    }

    public int visitsPerUniqueUser() {
        int uniqueUsers = (int) (uniqueVisitors.stream().map(value -> Integer.parseInt(value)).count());
        return uniqueUsers / bots;
    }

}



