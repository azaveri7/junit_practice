package model;

import java.util.List;

public class Book {

    private final String title;
    private final String pageCount;
    private final Topic topic;
    private final Year publicationDate;
    private final List<String> authors;

    public Book(String title, String pageCount, Topic topic, Year publicationDate, List<String> authors) {
        this.title = title;
        this.pageCount = pageCount;
        this.topic = topic;
        this.publicationDate = publicationDate;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public String getPageCount() {
        return pageCount;
    }

    public Topic getTopic() {
        return topic;
    }

    public Year getPublicationDate() {
        return publicationDate;
    }

    public List<String> getAuthors() {
        return authors;
    }
}
