package ir.mhm.codechallange.model;

public class AppEvent {

    private String event;
    private long timestamp;

    public AppEvent(String event, long timestamp) {
        this.event = event;
        this.timestamp = timestamp;
    }

    public AppEvent(){}


    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
