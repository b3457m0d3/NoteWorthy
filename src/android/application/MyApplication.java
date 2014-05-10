package android.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.lib.sqlite.Migration;
import android.lib.sqlite.Sprinkles;
import android.lib.sqlite.TableMaker;

public class MyApplication extends Application {

	private static MyApplication instance;
	private Context context;
	
	MyApplication(){
		instance = this;
		context = getApplicationContext();
	}
	
    @Override
    public void onCreate() {
        super.onCreate();

        Sprinkles sprinkles = Sprinkles.init(context);

        sprinkles.addMigration(new Migration() {
            @Override
            protected void onPreMigrate() {}

            @Override
            protected void doMigration(SQLiteDatabase db) {
                db.execSQL( TableMaker.buildDefaultTable());
            }

            @Override
            protected void onPostMigrate() {}
        });
    }
    public static MyApplication getInstance(){
		if(instance == null)
			instance = new MyApplication();
		return instance;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

}