package org.example;

public class Journal extends LendItem {

    private Integer issueNo;
    private String publisher;
    private Integer numberOfPages;
    private String subject;

    // Journal Constructor
    public Journal(Integer issueNo, String publisher, Integer numberOfPages, String subject, Integer ID, Boolean issued, String title, String type, Float cost, String location) {
        super(ID, title, issued, type, cost, location);

        this.issueNo = issueNo;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;
        this.subject = subject;
    }

    // Setter, used to set the issue number.
    public void setIssueNo(Integer issueNo) {
        this.issueNo = issueNo;
    }

    // Setter, used to set the publisher name.
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    // Setter, used to set the number of pages.
    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    // Setter, used to set the subject.
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
