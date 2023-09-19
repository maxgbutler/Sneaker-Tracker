package org.example;

import java.util.Random;

public class UserAgent {

    public static String[] userAgents = {
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0",
            "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0",
    };

    public static String getRandomUserAgent() {
        Random random = new Random();
        return userAgents[random.nextInt(userAgents.length)];
    }
}
