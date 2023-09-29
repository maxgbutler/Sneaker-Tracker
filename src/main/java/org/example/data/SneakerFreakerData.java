package org.example.data;

import org.example.HTTPRequests;
import org.example.Sneaker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class SneakerFreakerData {

    private static final String sneakerFreakerUrl = "https://www.sneakerfreaker.com/releases";

    //parses data from Sneaker Freaker's upcoming page into a list of Sneaker objects
    public static ArrayList<Sneaker> getSneakerFreakerData() {

        String Html = null;
        try {
            Html = HTTPRequests.requestSneakerPage(sneakerFreakerUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Document doc = Jsoup.parse(Html);
        Elements sneakerDataPackage = doc.getElementsByClass("c02188 c02227");

        ArrayList<Sneaker> sneakerData = new ArrayList<>();

        for(Element element : sneakerDataPackage) {

            Sneaker sneaker = new Sneaker();
            Element sneakerInfo = element.child(1);

            sneaker.setName(sneakerInfo.child(0).ownText());
            sneaker.setReleaseDate(sneakerInfo.child(1).child(0).ownText());
            sneaker.setColorway(sneakerInfo.child(2).getElementsContainingOwnText("Colourway").text());
            sneaker.setStyleCode(sneakerInfo.child(2).getElementsContainingOwnText("Style Code").text());
            sneaker.setSneakerUrl("https://www.sneakerfreaker.com" + element.attr("href"));


            sneakerData.add(sneaker);
        }

        //System.out.println(sneakerData);
        return sneakerData;
    }

}
