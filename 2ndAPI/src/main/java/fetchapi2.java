package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


class Main {
    public static void main(String[] args) {
        System.out.println("Fetching-Chucknorris-Jokes-Api Data Using JAVA Code are showing Given Below ..... ");

        URL url = null;
        HttpURLConnection connection = null;
        int responseCode = 0;



        String urlString = "https://api.chucknorris.io/jokes/random";

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {

            System.out.println("Problem in URL... Please check it...");
        }

        try {
            connection = (HttpURLConnection) url.openConnection(); // create a open connection on this on url object

            responseCode = connection.getResponseCode();

        } catch (Exception e) {
            System.out.println("Connection Problem .... Please check it");
        }


        if(responseCode == 200) {


            BufferedReader bufferIn = null;
            try {
                bufferIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));  // getInputStream() -- Throws Exception
            } catch (IOException e) {
                System.out.println("InputStream Problem...");
            }


            StringBuilder apiData = new StringBuilder();


            String readLine = null;

//            Step -- 5.3 --> untill null, its add the reading in apiData --> Reader Form into apiData --> readerLine throws Exception
            while(true) {  // we will not get untill null , it will work
                try {
                    if (!((readLine = bufferIn.readLine()) != null)) break;
                } catch (IOException e) {
                    System.out.println("ReaderLine Problem...Please check it..");
                }

                apiData.append(readLine);
            }

            try {
                bufferIn.close();
            } catch (IOException e) {
                System.out.println("Close the connection Problem");
            }

            JSONObject jsonAPIResponse = new JSONObject(apiData.toString()); // pass apiData into constructor

            try {
                System.out.println(jsonAPIResponse.get("categories"));
                System.out.println(jsonAPIResponse.get("created_at"));
                System.out.println(jsonAPIResponse.get("icon_url"));
                System.out.println(jsonAPIResponse.get("id"));
                System.out.println(jsonAPIResponse.get("updated_at"));
                System.out.println(jsonAPIResponse.get("url"));
                System.out.println(jsonAPIResponse.get("value"));

                System.out.println();
                System.out.println("API Data in String Form.. Given Below");

                System.out.println(jsonAPIResponse.toString());

            }catch (Exception ee) {
                System.out.println("You pass wrong Parameter .. Please check it");
            }
        }else {
            System.out.println("API call could not be made!!!");
        }

    }
}