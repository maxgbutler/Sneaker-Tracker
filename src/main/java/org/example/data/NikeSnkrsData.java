package org.example.data;

import org.example.HTTPRequests;
import org.example.Sneaker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NikeSnkrsData {

    private static final String nikeSnkrsUrl = "https://www.nike.com/launch?s=upcoming";

    //parses data from each sneaker page
    public static ArrayList<Sneaker> getNikeSnkrsData() {

        ArrayList<String> sneakerLinks = parseSnrksUpcommingPage();
        ArrayList<Sneaker> nikeSnkrs = new ArrayList<>();

        //parses each sneaker on the /launch?s=upcoming page into sneaker objects then adds them to nikeSnkrs
        for (String sneakerLink : sneakerLinks) {

            if (sneakerLink.equals("https://www.nike.com/launch/t/upcoming-drops-static-na-only")) continue;

            String sneakerHtml = null;
            try {
                sneakerHtml = HTTPRequests.requestSneakerPage(sneakerLink);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Document doc = Jsoup.parse(sneakerHtml);
            Elements productInfoContainer = doc.getElementsByClass("product-info ncss-col-sm-12 full");
            Element productInfo = null;

            if (!productInfoContainer.isEmpty()) {
                productInfo = productInfoContainer.get(0);

                Sneaker nikeSnkr = new Sneaker();

                nikeSnkr.setName(productInfo.child(0).text());
                nikeSnkr.setColorway(productInfo.child(1).text());
                nikeSnkr.setPrice(productInfo.child(2).text());
                nikeSnkr.setReleaseDate(productInfo.child(3).child(0).text());

                String description = productInfo.child(4).child(0).text();

                nikeSnkr.setDescription(description.substring(0, description.length() - 10));
                nikeSnkr.setStyleCode(description.substring(description.length() - 10, description.length()));

                nikeSnkrs.add(nikeSnkr);
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //parses each sneaker listed in upcoming-drops-static-na-only into sneaker objects then adds them to nikeSnkrs list
        String sneakerHtml = null;
        try {
            sneakerHtml = HTTPRequests.requestSneakerPage("https://www.nike.com/launch/t/upcoming-drops-static-na-only");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Document doc = Jsoup.parse(sneakerHtml);
        Elements productInfos = doc.getElementsByClass("product-info ncss-col-sm-12 full");

        for (Element productInfo : productInfos) {
            Sneaker nikeSnkr = new Sneaker();

            nikeSnkr.setName(productInfo.child(0).text());
            nikeSnkr.setColorway(productInfo.child(1).text());
            nikeSnkr.setPrice(productInfo.child(2).text());
            nikeSnkr.setReleaseDate(productInfo.child(3).child(0).text());

            String description = productInfo.child(4).child(0).text();

            nikeSnkr.setDescription(description.substring(0, description.length() - 10));
            nikeSnkr.setStyleCode(description.substring(description.length() - 10, description.length()));

            nikeSnkrs.add(nikeSnkr);
        }

        return nikeSnkrs;
    }

    //grabs all urls linking shoes on the snkrs upcoming page
    public static ArrayList<String> parseSnrksUpcommingPage() {

        String Html = null;
        try {
            Html = HTTPRequests.requestSneakerPage(nikeSnkrsUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String className = "upcoming-section bg-white ncss-row prl2-md prl5-lg pb4-md pb6-lg";
        String host = "https://www.nike.com";

        Document doc = Jsoup.parse(Html);
        Elements sneakerContainer = doc.getElementsByClass(className);
        Elements sneakers = sneakerContainer.get(0).children();

        ArrayList<String> snrksProductUrls = new ArrayList<String>();
        for (Element element : sneakers) {

            String url = host + element.child(0).child(0).getElementsByClass("card-link d-sm-b").attr("href");
            snrksProductUrls.add(url);

        }

        return snrksProductUrls;
    }

}
