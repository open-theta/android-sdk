package net.adsplay.vast.model;

import android.util.Log;

public class VASTLog {
		
	public static void d(String tag, String o){
		Log.d(tag,o);
	}
	public static void i(String tag, String o){
		Log.i(tag,o);
	}
	public static void v(String tag, String o){
		Log.v(tag,o);
	}
	public static void w(String tag, String o){
		Log.w(tag,o);
	}
	public static void e(String tag, String o){
		Log.e(tag,o);
	}
	public static void e(String tag, String o, Throwable e){
		Log.e(tag , o + ":" + e.getMessage());				
	}
}
