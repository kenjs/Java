package com.cy.dctms.common.bo;

import java.io.Serializable;

/**
 * Created by haoy on 2014/9/23.
 */
public class DaycountDriverActive implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private int id;
    private int driverId;
    private String recordDay;
    private int initiativeLinks;        //主动通讯次数
    private int passiveLinks;           //被动通讯次数
    private int allLinks;               //主被动通讯次数和

// --------------------------- CONSTRUCTORS ---------------------------

    public DaycountDriverActive() {
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public int getAllLinks() {
        return allLinks;
    }

    public void setAllLinks(int allLinks) {
        this.allLinks = allLinks;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInitiativeLinks() {
        return initiativeLinks;
    }

    public void setInitiativeLinks(int initiativeLinks) {
        this.initiativeLinks = initiativeLinks;
    }

    public int getPassiveLinks() {
        return passiveLinks;
    }

    public void setPassiveLinks(int passiveLinks) {
        this.passiveLinks = passiveLinks;
    }

    public String getRecordDay() {
        return recordDay;
    }

    public void setRecordDay(String recordDay) {
        this.recordDay = recordDay;
    }
}
