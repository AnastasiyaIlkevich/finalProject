package com.finalProject.model;

public class Book {

    private Long id;
    private String title;
    private String IBSN;// код для продажи
    private Author author;
    private Genre genre;

    public Book(String title, String IBSN, Author author, Genre genre) {
        this.title = title;
        this.IBSN = IBSN;
        this.author = author;
        this.genre = genre;
    }

    public Book() {

    }

    @Override
    public String toString() {
        return "Book{" +
                "id --> " + id +
                ", title --> '" + title + '\'' +
                ", IBSN --> '" + IBSN + '\'' +
                ", author --> " + author +
                ", genre --> " + genre +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
