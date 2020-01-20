package com.saeefmd.official.miublooddonors.database;

public class Quote {

    public static final String TABLE_NAME = "quotes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUOTE = "quote";
    public static final String COLUMN_AUTHOR = "author";

    private int id;
    private String quote;
    private String author;

    public Quote() {
    }

    public Quote(int id, String quote, String author) {
        this.id = id;
        this.quote = quote;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
