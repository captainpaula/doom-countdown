package ie.ucd.countdown.doomsday2012;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	// milliseconds will hold the number of milliseconds between now and the date
	// 'interval' is how long in milliseconds will the countdown wait until it updates: 1000ms = 1 second
	long milliseconds = 1000;
	long interval = 1000;
	String date = "21-12-2012";
	String time = "00:00";
	MyCount counter;
	String doomsday;
	TextView count_down;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Scanner sc = new Scanner(date).useDelimiter("-");
		int day = Integer.parseInt(sc.next());
		int month = Integer.parseInt(sc.next()) - 1; // 0 is January
		int year = Integer.parseInt(sc.next());

		sc = new Scanner(time).useDelimiter(":");
		int hour = Integer.parseInt(sc.next());
		int min = Integer.parseInt(sc.next());

        
        		//get the date of Christmas
     			Calendar doomsDay = new GregorianCalendar(year, month, day, hour, min);
     			
     			//get the time right now
     			Calendar rightNow = new GregorianCalendar();
 
     			// get difference in milliseconds
     			milliseconds = (doomsDay.getTimeInMillis()-rightNow.getTimeInMillis());
     			
     			count_down = (TextView) findViewById(R.id.count_down);
     			Typeface countdownTf = Typeface.createFromAsset(getAssets(),
     					"fonts/digit.ttf");
     			count_down.setTypeface(countdownTf);
     			
     			counter = new MyCount(milliseconds, interval);
     			counter.start();
    }
    
    public class MyCount extends CountDownTimer {
		ComponentName thisWidget;
		AppWidgetManager appWidgetManager;
		Context context;
		int[] appWidgetIds;

		// this is where the counter object is sent when it was made previously
		public MyCount(long milliseconds, long interval) {
			super(milliseconds, interval);
		}
		
		@Override
		// the onFinish() function allows you to decide what happens when the
		// timer reaches 00:00:00:00
		// It'll execute this code only when the time runs out
		// the countdown timer class requires an onFinish() function to be
		// created
		public void onFinish() {
		}

		// onTick() is executed as often as the 'interval' dictates, so again
		// 1000ms=1second ie. the function runs each second.
		// It takes in the amount of milliseconds until the time reaches
		// 00:00:00:00, and calls it 'millisUntilFinished'. This is value is
		// decided
		// when the 'counter' object was created previously.
		// Again, it is a required method that the CoundownTimer needs to work.
		@Override
		public void onTick(long millisUntilFinished) {

			
			final long ms = millisUntilFinished;

			// convert the time in milliseconds into days, hours,
			// minutes, and seconds.

			String seconds = Integer.toString((int) ((ms) / (1000)) % 60);
			if (seconds.length() < 2) {
				seconds = "0" + seconds;
			}
			String minutes = Integer.toString((int) (ms / (1000 * 60)) % 60);
			if (minutes.length() < 2) {
				minutes = "0" + minutes;
			}
			String hours = Integer.toString((int) (ms / (1000 * 60 * 60)) % 24);
			if (hours.length() < 2) {
				hours = "0" + hours;
			}
			String days = Integer.toString((int) (ms / 1000) / 86400);
			if (days.length() < 2) {
				days = "0" + days;
			}
			
			doomsday = days + ":" + hours + ":" + minutes + ":" + seconds;
			count_down.setText(doomsday);

		}
	}



}
