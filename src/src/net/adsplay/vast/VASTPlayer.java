//
//  VastPlayer.java
//
//  Copyright (c) 2014 Nexage. All rights reserved.
//

package net.adsplay.vast;

import java.util.ArrayList;
import java.util.List;

import net.adsplay.util.DefaultMediaPicker;
import net.adsplay.util.NetworkTools;
import net.adsplay.util.VASTLog;
import net.adsplay.vast.activity.VASTActivity;
import net.adsplay.vast.model.VASTModel;
import net.adsplay.vast.processor.VASTMediaPicker;
import net.adsplay.vast.processor.VASTProcessor;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class VASTPlayer {

    private static final String TAG = "VASTPlayer";

    public static final String VERSION = "1.3";

    // errors that can be returned in the vastError callback method of the
    // VASTPlayerListener
    public static final int ERROR_NONE                   = 0;
    public static final int ERROR_NO_NETWORK             = 1;
    public static final int ERROR_XML_OPEN_OR_READ       = 2;
    public static final int ERROR_XML_PARSE              = 3;
    public static final int ERROR_SCHEMA_VALIDATION      = 4; // not used in SDK, only in sourcekit
    public static final int ERROR_POST_VALIDATION        = 5;
    public static final int ERROR_EXCEEDED_WRAPPER_LIMIT = 6;
    public static final int ERROR_VIDEO_PLAYBACK		 = 7;

    private Context context;

    public interface VASTPlayerListener {
        public void vastReady(List<VASTModel> vastModels);
        public void vastError(int error);
        public void vastClick();
        public void vastComplete();
        public void vastDismiss();
    }
    
    public static VASTPlayerListener listener;
    private List<VASTModel> vastModels;

    public VASTPlayer(Context context, VASTPlayerListener listener) {
        this.context = context;
        VASTPlayer.listener = listener;
    }
    
    public List<VASTModel> getVastModels() {
    	if(vastModels == null){
    		vastModels = new ArrayList<VASTModel>(0);
    	}
		return vastModels;
	}

   

    public void loadVideoWithData(final String xmlData) {
        VASTLog.v(TAG, "loadVideoWithData\n" + xmlData);        
        if (NetworkTools.connectedToInternet(context)) {
            (new Thread(new Runnable() {
                @Override
                public void run() {                    
                    VASTProcessor processor = new VASTProcessor();
                    int error = processor.process(xmlData);                            
                    if (error == ERROR_NONE) {                    	
                        vastModels = processor.getVastModels();
                        Log.d(TAG, "vastModels.size " + vastModels.size());
                        sendReady();
                    } else {
                        sendError(error);
                    }
                }
            })).start();
        }
        else {
            sendError(ERROR_NO_NETWORK);
        }
    }

    public void play(VASTModel vastModel) {
        VASTLog.d(TAG, "play");
        if (vastModel != null) {
            if (NetworkTools.connectedToInternet(context)) {            
            	Intent vastPlayerIntent = new Intent(context, VASTActivity.class);
				vastPlayerIntent.putExtra("com.nexage.android.vast.player.vastModel",vastModel);
                context.startActivity(vastPlayerIntent);
            } else {
                sendError(ERROR_NO_NETWORK);
            }
        } else {
            VASTLog.w(TAG, "vastModel is null; nothing to play");
        }
    }

    private void sendReady() {
        VASTLog.d(TAG, "sendReady");
        if (listener != null) {
            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {                	
                    listener.vastReady(getVastModels());
                }
            });
        }
    }

    private void sendError(final int error) {
        VASTLog.d(TAG, "sendError");
        if (listener != null) {
            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.vastError(error);
                }
            });
        }
    }
}
