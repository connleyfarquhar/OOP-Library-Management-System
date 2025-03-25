package org.example;

public class LendItem {
    private Float cost;
    private Integer ID;
    private Boolean issued;
    private String location;
    private String title;
    private String type;

    // LendItem Constructor.
    public LendItem(Integer ID, String title, Boolean issued, String type, Float cost, String location) {
        this.ID = ID;
        this.title = title;
        this.issued = issued;
        this.type = type;
        this.cost = cost;
        this.location = location;
    }

    // Getter, gets the cost of the item.
    public float getCost() {
        return cost;
    }

    // Getter, gets the unique identifier of the item.
    public Integer getID() {
        return ID;
    }

    // Setter method, sets the cost of the item
    public void setCost(Float cost) {
        this.cost = cost;
    }

    // Setter method, sets the location of the item.
    public void setLocation(String location) {
        this.location = location;
    }

    // Setter method, sets the title of the item.
    public void setTitle(String title) {
        this.title = title;
    }

    // toString() method allows for the output from the library system to display the content rather than a random output.
    @Override
    public String toString() {
        return "LendItem{" +
                "ID=" + ID +
                ", Title='" + title + '\'' +
                ", Issued=" + issued +
                ", Type='" + type + '\'' +
                ", Cost=" + cost +
                ", Location='" + location + '\'' +
                '}';
    }
}
