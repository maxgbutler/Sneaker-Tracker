package org.example;

import org.example.parsing.HTMLParsing;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

//        String url = "https://www.sneakerfreaker.com/releases";
//        String Html = null;
//
//        try {
//            Html = HTMLRequests.requestSneakerPage(url);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        HTMLParsing.getSneakerFreakerData(Html);

        String url = "https://www.nike.com/launch?s=upcoming";
        String Html = null;

        try {
            Html = HTMLRequests.requestSneakerPage(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(HTMLParsing.parseSnrksUpcommingPage(Html));
    }
}