package com.arprast.sosmed.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class UserInterfacing extends RealmObject {

    @PrimaryKey
    String menuId;
    boolean isDisabled = true;
    Date createDate;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "UserInterfacing{" +
                "menuId='" + menuId + '\'' +
                ", isDisabled=" + isDisabled +
                ", createDate=" + createDate +
                '}';
    }
}
