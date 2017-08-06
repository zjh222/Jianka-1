package tech.jianka.data;

import java.io.Serializable;

/**
 * Created by Richard on 2017/7/30.
 */

public class Item implements Serializable {
    private static final long serialVersionUID = 7995824715641496969L;

    public static final int PARENT = 123;
    public static final int GROUP = 744;
    public static final int CARD = 500;
    public static final int REGULAR = 456;
    private int itemType;
    private int cardType;

    private String fileName;
    private String cardTitle;
    private long modifiedTime;
    private String filePath;
    // TODO: 2017/7/31 content的保存方式
    private Object cardContent;

    public Item(String fileName,String filePath ) {
        this.itemType = GROUP;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public Item(String cardTitle, int itemType,String filePath,Object cardContent) {
        this.itemType = CARD;
        this.itemType = itemType;
        this.filePath = filePath;
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
    }

    public Item(String fileName, String filePath, long lastModified) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.modifiedTime = lastModified;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public Object getCardContent() {
        return cardContent;
    }

    public void setCardContent(Object cardContent) {
        this.cardContent = cardContent;
    }

    public Item() {
    }

    public int getItemType() {
        return itemType;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}

