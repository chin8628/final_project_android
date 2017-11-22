package kmitl.fina.boonyarith58070077.bnk48feed;

import android.app.Application;
import android.content.Context;

public class BNK48Feed extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        BNK48Feed.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return BNK48Feed.context;
    }

}
