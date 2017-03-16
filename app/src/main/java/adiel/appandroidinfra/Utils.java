package adiel.appandroidinfra;

import android.app.Activity;
import android.util.Log;

/**
 * Created by recntrek7 on 16/03/17.
 */

public class Utils {

    public static void printActivityState(Activity activity){
        MyApp myApp= (MyApp) activity.getApplication();
        String activityName = activity.getClass().getName();
        String activityState = myApp.getActivityState(activityName);
        Log.d("adiel",activityName+" activityState:"+activityState);
    }
}
