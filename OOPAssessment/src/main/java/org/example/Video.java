package org.example;

public class Video extends LendItem {
    private String format;
    private Float duration;

    // Video Constructor
    public Video(String format, Float duration, Integer ID, Boolean issued, String title, String type, Float cost, String location) {
        super(ID, title, issued , type, cost, location);
        this.format = format;
        this.duration = duration;
    }

    // Setter, used to set the format.
    public void setFormat(String format) {
        this.format = format;
    }

    // Setter, used to set the duration.
    public void setDuration(Float duration) {
        this.duration = duration;
    }
}
