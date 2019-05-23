package com.internet.inchirurgiademo.dto;

public class HeaderCell {

    private String name;
    private String wide;
    private String title;

    public HeaderCell(String name, String wide, String title) {
        this.wide = wide;
        this.name = name;
        this.title = title;
    }

    public String getWide() {
        return wide;
    }

    public void setWide(String wide) {
        this.wide = wide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
