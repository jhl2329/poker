package poker;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;


public class Application {

    static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        JSONParser  parser = new JSONParser();
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            if(args[1].equals("determine")) {
                doDetermine(br, parser);
            }
            else if(args[1].equals("compare")) {
                doComparison(br, parser);

            }
            else if (args[1].equals("besthand")) {
                doBestHand(br, parser);
            }
            else {
                System.out.println("Please refer to readme for instructions on how to run.");
            }
        } catch (FileNotFoundException e) {
            logger.error("File Not Found, please try again with a valid file.", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } catch (ParseException e) {
            logger.error("ParseException", e);
        }
    }

    private static void doBestHand(BufferedReader br, JSONParser parser) throws IOException, ParseException {
        ArrayList<CardHand> handList = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            logger.info(line);
            JSONArray jsonArray = (JSONArray) parser.parse(line);
            ArrayList<String> handBuilder = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String s = jsonArray.get(i).toString();
                handBuilder.add(s);
            }
            if(handBuilder.size() == 5 && validCardHand(handBuilder))
                handList.add(new CardHand(handBuilder.toArray(new String[0])));
            else
                System.out.println(handBuilder.toString() + " is not a valid hand");
        }
        System.out.println("Best hands are:");
        for(CardHand ch : handList) {
            System.out.println(ch.findBestHand());
        }
    }

    private static void doComparison(BufferedReader br, JSONParser parser) throws IOException, ParseException {
        ArrayList<CardHand> handList = new ArrayList<>();
        //Determines category
        String line;
        while ((line = br.readLine()) != null) {
            logger.info(line);
            JSONArray jsonArray = (JSONArray) parser.parse(line);
            ArrayList<String> handBuilder = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String s = jsonArray.get(i).toString();
                handBuilder.add(s);
            }
            if(handBuilder.size() == 5 && validCardHand(handBuilder))
                handList.add(new CardHand(handBuilder.toArray(new String[0])));
            else
                System.out.println(handBuilder.toString() + " is not a valid hand");
        }
        Collections.sort(handList);
        System.out.println("In order from highest to lowest");
        for(CardHand ch : handList)
            System.out.println(ch.toString());
    }

    private static void doDetermine(BufferedReader br, JSONParser parser) throws IOException, ParseException {
        ArrayList<CardHand> handList = new ArrayList<>();
        //Determines category
        String line;
        while ((line = br.readLine()) != null) {
            logger.info(line);
            JSONArray jsonArray = (JSONArray) parser.parse(line);
            ArrayList<String> handBuilder = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String s = jsonArray.get(i).toString();
                handBuilder.add(s);
            }
            if(handBuilder.size() == 5 && validCardHand(handBuilder)) {
                handList.add(new CardHand(handBuilder.toArray(new String[0])));
            }
            else {
                System.out.println(handBuilder.toString() + " is not a valid hand");
            }
        }
        for(CardHand ch : handList)
            System.out.println(ch.toString());
    }

    //Returns whether or not a hand is valid based on fact that there cannot be duplicates
    private static boolean validCardHand(ArrayList<String> handBuilder) {
        HashSet<String> unique = new HashSet<>();
        for(String card : handBuilder) {
            if(!unique.add(card))
                return false;
        }
        return true;
    }
}
