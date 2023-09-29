package org.example;

import org.example.data.NiceKicksData;
import org.example.data.NikeSnkrsData;
import org.example.data.SneakerFreakerData;

public class Main {
    public static void main(String[] args) {

        System.out.println(SneakerFreakerData.getSneakerFreakerData());
        System.out.println(NikeSnkrsData.getNikeSnkrsData());
        System.out.println(NiceKicksData.getNiceKicksData());

    }
}