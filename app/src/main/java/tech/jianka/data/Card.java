package tech.jianka.data;

import java.util.Date;

/**
 * Created by Richa on 2017/7/23.
 */

public class Card {
    private String title;
    private String author;
    private Date date;
    private String content;
    private DataType dataType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Card(String title, String author, Date date, String content, DataType dataType) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
        this.dataType = dataType;
    }
}
