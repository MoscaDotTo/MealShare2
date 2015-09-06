package pennapps2015.mealshare;

import android.app.Application;
import android.location.LocationListener;

import com.parse.Parse;

/**
 * Created by Rob on 9/5/2015.
 */
public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "PeATNbN9xBPWXkEEtPovi8tTsk1gTYRZ8sy4QTK2", "2PkQlU9HrFhjPvjrUCJtKWFy779ElezBUATqyHai");
    }
}


