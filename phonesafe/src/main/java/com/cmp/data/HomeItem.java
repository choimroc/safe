package com.cmp.data;

/**
 * 作者：cmp on 2016/9/13 15:14
 */
public class HomeItem {
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


    public HomeItem(int itemImg, String itemName) {
        this.setItemImg(itemImg);
        this.setItemName(itemName);
    }
}
