package com.cy.driver.domain;

/**
 * Created by Administrator on 2014/10/13.
 */
public class AppReleasesDomain {
    private int id;
    private String filename;
    private String filemd5;
    private String versionNo;
    private int innerVersion;
    private int databaseUpdate;
    private int versionConstraint;
    private int type;
    private String releaseDate;
    private int releaseUserId;
    private int downCount;
    private String meta;

    public AppReleasesDomain() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilemd5() {
        return filemd5;
    }

    public void setFilemd5(String filemd5) {
        this.filemd5 = filemd5;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public int getInnerVersion() {
        return innerVersion;
    }

    public void setInnerVersion(int innerVersion) {
        this.innerVersion = innerVersion;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getReleaseUserId() {
        return releaseUserId;
    }

    public void setReleaseUserId(int releaseUserId) {
        this.releaseUserId = releaseUserId;
    }

    public int getDownCount() {
        return downCount;
    }

    public void setDownCount(int downCount) {
        this.downCount = downCount;
    }

    public int getDatabaseUpdate() {
        return databaseUpdate;
    }

    public void setDatabaseUpdate(int databaseUpdate) {
        this.databaseUpdate = databaseUpdate;
    }

    public int getVersionConstraint() {
        return versionConstraint;
    }

    public void setVersionConstraint(int versionConstraint) {
        this.versionConstraint = versionConstraint;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }
}
