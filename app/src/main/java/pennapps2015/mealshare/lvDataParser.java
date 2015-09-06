package pennapps2015.mealshare;

import android.location.Location;
import android.os.Bundle;

import com.parse.ParseObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rob on 9/5/2015.
 */

public class lvDataParser extends MainActivity  {
    double distance;
    String content, category, location;
    Object pinPoint;


    public List<lvDataParser> getLvData(List<ParseObject> entryList) {
        List<lvDataParser> returnedDataList = new ArrayList<lvDataParser>();
        final int listSize = entryList.size();
        for (int i = 0; i < listSize; i++) {
            ParseObject currentObj = entryList.get(i);
            lvDataParser currentItem = new lvDataParser();
            currentItem.category = currentObj.get("category").toString();
            currentItem.content = currentObj.get("postContent").toString();
            currentItem.distance = 0.5; //temp debug
            currentItem.location = currentObj.get("givenLocation").toString();
            currentItem.pinPoint = currentObj.get("pinPoint"); //temp debug
            returnedDataList.add(currentItem);
        }

        return returnedDataList;


    }

}