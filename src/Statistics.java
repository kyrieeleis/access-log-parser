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

    public Statistics() {
        this.minTime = null;
        this.maxTime = null;
        this.totalTraffic = 0;
        this.uniquePaths = new HashSet<>();
        this.nonExistentPaths = new HashSet<>();
        this.browserRate = new HashMap<>();
        this.operatingSystemRate = new HashMap<>();
    }


    public void addEntry(LogEntry o) {
        this.totalTraffic += o.getBytesSent();
        if (minTime == null || o.getDateTime().isBefore(minTime)) {
            this.minTime = o.getDateTime();
        }
        if (maxTime == null || o.getDateTime().isAfter(maxTime)) {
            this.maxTime = o.getDateTime();
        }
        if (o.getStatusCode() == 200) {
            uniquePaths.add(o.getPath());
        }if (o.getStatusCode() == 404) {
            nonExistentPaths.add(o.getPath());
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
        return Math.abs(totalTraffic / difference);
    }

}



