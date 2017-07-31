package tech.jianka.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Richa on 2017/7/30.
 */

public class Item implements Serializable{
    private String title;
    private String author;
    private Date date;
    private String content;

    private DataType dataType;

    private ArrayList<Item> items;


    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

}

