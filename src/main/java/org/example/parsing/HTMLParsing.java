package org.example.parsing;

import org.example.Sneaker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HTMLParsing {

    //parses data from Sneaker Freaker's upcoming page into a list of Sneaker objects
    public static ArrayList<Sneaker> getSneakerFreakerData(String Html) {

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

//            System.out.println(sneakerInfo.child(2).getElementsContainingOwnText("Colourway").text());
//            System.out.println(sneakerInfo.child(2).getElementsContainingOwnText("Style Code").text());

            sneakerData.add(sneaker);
        }

        //System.out.println(sneakerData);
        return sneakerData;
    }

    //grabs all urls linking shoes on the snkrs upcoming page
    public static ArrayList<String> parseSnrksUpcommingPage(String Html) {

        String className = "pb2-sm va-sm-t ncss-col-sm-12 ncss-col-md-6 ncss-col-lg-4 pb4-md prl0-sm prl2-md ncss-col-sm-6 ncss-col-lg-3 pb4-md prl2-md pl0-md pr1-md d-sm-h d-md-ib";
        String host = "https://www.nike.com";

        Document doc = Jsoup.parse(Html);
        Elements sneakers = doc.getElementsByClass(className);

        ArrayList<String> snrksProductUrls = new ArrayList<String>();
        for (Element element : sneakers) {

            String url = host + element.child(0).child(0).getElementsByClass("card-link d-sm-b").attr("href");
            snrksProductUrls.add(url);

        }

        return snrksProductUrls;
    }
}
