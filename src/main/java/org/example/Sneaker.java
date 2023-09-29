package org.example;

import java.util.Date;

public class Sneaker {

    private String name;

    private String description;

    private String releaseDate;

    private String price;

    private String colorway;

    private String sneakerUrl;

    private String photoUrl;

    private String styleCode;

    public Sneaker() {}

    public Sneaker(String name, String description, String releaseDate, String price, String colorway, String sneakerUrl, String photoUrl, String styleCode) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
        this.colorway = colorway;
        this.sneakerUrl = sneakerUrl;
        this.photoUrl = photoUrl;
        this.styleCode = styleCode;
    }

    public String getSneakerUrl() {
        return sneakerUrl;
    }

    public void setSneakerUrl(String sneakerUrl) {
        this.sneakerUrl = sneakerUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColorway() {
        return colorway;
    }

    public void setColorway(String colorway) {
        this.colorway = colorway;
    }

    public String getStyleCode() {
        return styleCode;
    }

    public void setStyleCode(String styleCode) {
        this.styleCode = styleCode;
    }

    @Override
    public String toString() {
        return '\n' + "{" +
                "name= " + name + '\n' +
                "description= " + description + '\n' +
                "releaseDate= " + releaseDate + '\n' +
                "price= " + price + '\n' +
                "colorway= " + colorway + '\n' +
                "sneakerUrl= " + sneakerUrl + '\n' +
                "photoUrl= " + photoUrl + '\n' +
                "styleCode= " + styleCode +
                '}' + '\n';
    }
}
