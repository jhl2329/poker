package poker;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Application {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Application.class);
        JSONParser  parser = new JSONParser();
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            ArrayList<CardHand> handList = new ArrayList<CardHand>();
            String line;
            while ((line = br.readLine()) != null) {
                logger.info(line);
                JSONArray jsonArray = (JSONArray)parser.parse(line);
                ArrayList<String> handBuilder = new ArrayList<String>();
                for(int i = 0; i < jsonArray.size(); i++) {
                    String s = jsonArray.get(i).toString();
                    handBuilder.add(s);
                }
//                logger.info(handBuilder.toString());
//                logger.info(Arrays.toString(handBuilder.toArray(new String[0])));
                handList.add(new CardHand(handBuilder.toArray(new String[0])));
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
