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
    private CardType cardType;

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

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Card(String title, String author, Date date, String content, CardType cardType) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
        this.cardType = cardType;
    }
}
