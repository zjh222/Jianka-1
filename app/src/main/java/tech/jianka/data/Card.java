package tech.jianka.data;

/**
 * Created by Richa on 2017/8/8.
 */

public class Card extends Item {
    private int cardType;
    private String cardTitle;
    // TODO: 2017/7/31 content的保存方式
    private Object cardContent;

    public Card(String cardTitle,String filePath ,Object cardContent) {
        super.setItemType(DataType.CARD);
        super.setFilePath(filePath);
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
    }

    public Card() {

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

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

}
