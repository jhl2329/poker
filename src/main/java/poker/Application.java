package poker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by bryan on 2/21/2017.
 */
public class Application {

    public static void main(String[] args) {
        JSONParser  parser = new JSONParser();
        try {
            ArrayList<CardHand> handList = new ArrayList<CardHand>();

            JSONArray jsonArray = (JSONArray)parser.parse(new FileReader(args[0]));

            for(int i = 0; i < jsonArray.size(); i++) {
                String s = jsonArray.get(i).toString();
//                System.out.println(jsonArray.get(i).toString().substring(1, s.length()-1));
                String[] cardString = s.substring(1, s.length() -1).split(",");
//                System.out.println(Arrays.toString(cardString));
                handList.add(new CardHand(cardString));
                System.out.println(handList.get(i).getIsConsecutive());
                System.out.println("Same suit? " + handList.get(i).getIsSameSuit());
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ParseException e){
            e.printStackTrace();
        }
    }
}
