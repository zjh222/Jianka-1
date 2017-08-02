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
    private int itemType;
    private int cardType;

    private String fileName;
    private String cardTitle;
    private String modifiedTime;
    private String filePath;
    // TODO: 2017/7/31 content的保存方式
    private Object cardContent;

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

    public Item(int itemType, String fileName, String filePath, String modifiedTime) {
        this.itemType = itemType;
        this.fileName = fileName;
        this.filePath = filePath;
        this.modifiedTime = modifiedTime;
    }
    public Item(int itemType) {
        this.itemType = itemType;
    }

    public int getItemType() {
        return itemType;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}

