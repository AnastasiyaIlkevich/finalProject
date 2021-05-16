package com.finalProject.model;

public class Book {

    private Long id;
    private String title;
    private String IBSN;// код для продажи
    private String author;
    private String genre;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", IBSN='" + IBSN + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIBSN() {
        return IBSN;
    }

    public void setIBSN(String IBSN) {
        this.IBSN = IBSN;
    }

    public Book(Long id) {
        this.id = id;
    }
    public Book() {

    }
}
