import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private HashSet<String> uniquePaths;
    private HashMap<String, Integer> operatingSystemRate;

    public Statistics() {
        this.minTime = null;
        this.maxTime = null;
        this.totalTraffic = 0;
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
        }
    }

    public HashSet<String> getUniquePaths() {
        return new HashSet<>(new HashSet<>(uniquePaths));
    }
    public HashMap<String, Double> getOperatingSystemRate() {

    }

    public int getTrafficRate() {
        int difference = (int) minTime.until(maxTime, ChronoUnit.HOURS);
        if (difference == 0) {
            difference = 1;
        }
        return totalTraffic / difference;
    }

}


