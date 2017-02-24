package poker;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by bryan on 2/21/2017.
 */
public class Application {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Application.class);
        JSONParser  parser = new JSONParser();
        try {
            ArrayList<CardHand> handList = new ArrayList<CardHand>();

            JSONArray jsonArray = (JSONArray)parser.parse(new FileReader(args[0]));

            for(int i = 0; i < jsonArray.size(); i++) {
                String s = jsonArray.get(i).toString();
                String[] cardString = s.substring(1, s.length() -1).split(",");
                handList.add(new CardHand(cardString));
            }
        }
        catch(FileNotFoundException e) {
            logger.error("File Not Found", e);
        }
        catch(IOException e) {
            logger.error("IOException", e);
        }
        catch(ParseException e){
            logger.error("ParseException", e);
        }
    }
}
