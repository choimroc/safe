package com.cmp.data;

/**
 * 作者：cmp on 2016/9/14 08:37
 */
public class BurglarItem {
    private int itemImg;
    private String itemName;

    public int getItemImg() {
        return itemImg;
    }

    public void setItemImg(int itemImg) {
        this.itemImg = itemImg;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public BurglarItem(int itemImg, String itemName) {
        this.setItemImg(itemImg);
        this.setItemName(itemName);
    }

}
