package tech.jianka.data;

import java.io.Serializable;

/**
 * Created by Richard on 2017/7/30.
 */

public class Item implements Serializable {
    private static final long serialVersionUID = 7995824715641496969L;

    public static final int GROUP = 744;
    public static final int CARD = 500;
    private int itemType;
    private int cardType;

    private String fileName;
    private String suffix;
    private String filePath;
    private long fileSize;
    private String modifiedTime;
    private String cardTitle;
    private String cardModifiedTime;
    private String cardAuthor;
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

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardModifiedTime() {
        return cardModifiedTime;
    }

    public void setCardModifiedTime(String cardModifiedTime) {
        this.cardModifiedTime = cardModifiedTime;
    }

    public String getCardAuthor() {
        return cardAuthor;
    }

    public void setCardAuthor(String cardAuthor) {
        this.cardAuthor = cardAuthor;
    }

    public Object getCardContent() {
        return cardContent;
    }

    public void setCardContent(Object cardContent) {
        this.cardContent = cardContent;
    }

    public void setData(ItemData data) {
        this.data = data;
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

    public ItemData getData() {
        return data;
    }

    private ItemData data;

    public int getItemType() {
        return itemType;
    }
}

