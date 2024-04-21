import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics(){
        this.minTime = null;
        this.maxTime = null;
        this.totalTraffic = 0;
    }


    public void addEntry(LogEntry o){
        this.totalTraffic += o.getBytesSent();
        if(minTime == null || o.getDateTime().isBefore(minTime)){
            this.minTime = o.getDateTime();
        }
        if(maxTime == null || o.getDateTime().isAfter(maxTime)){
            this.maxTime = o.getDateTime();
        }
    }
    public int getTrafficRate(){
        int difference = (int) minTime.until(maxTime, ChronoUnit.HOURS);
        if (difference == 0){
            difference = 1;
        }
        return totalTraffic / difference;
    }

}
