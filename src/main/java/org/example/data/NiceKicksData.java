package org.example.data;

import org.example.HTTPRequests;
import org.example.Sneaker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NiceKicksData {

    private static final String niceKicksUrl = "https://www.nicekicks.com/sneaker-release-dates/?nk=upcoming";

    public static ArrayList<Sneaker> getNiceKicksData() {

        String Html = null;
        try {
            Html = HTTPRequests.requestSneakerPage(niceKicksUrl);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        Document doc = Jsoup.parse(Html);
        Elements sneakerElements = doc.getElementsByClass("post-summary featured-large");
        //Elements sneakerElements = doc.getElementsByClass("site-main").get(0).children();

        ArrayList<Sneaker> sneakerList = new ArrayList<>();
        //List<String> compareTo = Arrays.asList("p", "ul", "div", "nav");

        for (Element sneakerElement : sneakerElements) {

            //element inside the sneaker article which contains all the info about the sneaker
            Element sneakerInfo = sneakerElement.child(1);
            Element sneakerInfoA = sneakerInfo.child(0).child(0); // gets element containing the name and a url link to the sneaker
            Element sneakerInfoB = sneakerInfo.getElementsByClass("block-release-info").get(0).child(1); //gets element containing sneaker colorway, price, style code, & release date
            Sneaker sneaker = new Sneaker();

            sneaker.setName(sneakerInfoA.text());
            sneaker.setSneakerUrl(sneakerInfoA.attr("href"));
            System.out.println(sneakerInfoB.text());

            List<String> sneakerInfoList = getInfoValues(sneakerInfoB.text());
            sneaker.setColorway(sneakerInfoList.get(0));
            sneaker.setStyleCode(sneakerInfoList.get(1));
            sneaker.setReleaseDate(sneakerInfoList.get(2));
            sneaker.setPrice(sneakerInfoList.get(3));
            sneaker.setPhotoUrl(sneakerElement.child(0).child(0).child(0).attr("data-lazy-srcset"));

            System.out.println(sneaker);
            
        }

        return sneakerList;
    }

    //parses String containing sneaker values into Strings
    private static List<String> getInfoValues(String str) {
        List<String> infoValues = new ArrayList<>();

        int colorwayIndex = str.indexOf("Colorway:");
        int styleIndex = str.indexOf("Style #:");
        int releaseDateIndex = str.indexOf("Release Date:");
        int priceIndex = str.indexOf("Price:");

        if (colorwayIndex != -1) {
            infoValues.add(str.substring(colorwayIndex + 9, (styleIndex != -1) ? styleIndex : releaseDateIndex));
        } else {
            infoValues.add(null);
        }

        if (styleIndex != -1) {
            infoValues.add(str.substring(styleIndex + 8, (releaseDateIndex != -1) ? releaseDateIndex : priceIndex));
        } else {
            infoValues.add(null);
        }

        if (releaseDateIndex != -1) {
            infoValues.add(str.substring(releaseDateIndex + 13, (priceIndex != -1) ? priceIndex : str.length()));
        } else {
            infoValues.add(null);
        }

        if (priceIndex != -1) {
            infoValues.add(str.substring(priceIndex + 6));
        } else {
            infoValues.add(null);
        }

        return infoValues;
    }
}
