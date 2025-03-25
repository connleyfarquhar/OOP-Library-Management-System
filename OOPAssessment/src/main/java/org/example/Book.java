package org.example;

public class Book extends LendItem {
    private String author;
    private Integer numberOfPages;
    private String publisher;

    // Book Constructor.
    public Book(String author, Integer numberOfPages, String publisher, Integer ID, Boolean issued, String title, String type, Float cost, String location) {
        super(ID, title, issued, type, cost, location);
        this.author = author;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;
    }

    // Setter, used to set the author name.
    public void setAuthor(String author) {
        this.author = author;
    }

    // Setter, used to set the publisher name.
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    // Setter, used to set the number of pages.
    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}

