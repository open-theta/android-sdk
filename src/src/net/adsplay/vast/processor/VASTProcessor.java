//
//  VASTProcessor.java
//
//  Copyright (c) 2014 Nexage. All rights reserved.
//

package net.adsplay.vast.processor;

import java.util.ArrayList;
import java.util.List;

import net.adsplay.util.VASTLog;
import net.adsplay.util.XmlTools;
import net.adsplay.vast.VASTPlayer;
import net.adsplay.vast.model.VASTModel;
import net.adsplay.vast.model.VmapModel;

import org.w3c.dom.Document;

import android.util.Log;

/**
 * This class is responsible for taking a VAST 2.0 XML file, parsing it,
 * validating it, and creating a valid VASTModel object corresponding to it.
 * 
 * It can handle "regular" VAST XML files as well as VAST wrapper files.
 */
public final class VASTProcessor {
	private static final String TAG = VASTProcessor.class.getName();
	
	private List<VASTModel> vastModels;
	
	
	public VASTProcessor() {}


	public List<VASTModel> getVastModels() {
		if(vastModels == null){
			vastModels = new ArrayList<VASTModel>(0);
		}
		return vastModels;
	}

	public int process(String xmlData) {					
		Log.d(TAG, "xmlData "+xmlData);
			
		Document mainDoc = XmlTools.stringToDocument(xmlData);
		if (mainDoc == null) {
			return VASTPlayer.ERROR_XML_PARSE;
		}		
		vastModels = new VmapModel(mainDoc).getVASTModels();
		Log.d(TAG, "vastModels.size "+vastModels.size());
		
		if (vastModels == null) {
			return VASTPlayer.ERROR_XML_PARSE;
		}

		return VASTPlayer.ERROR_NONE;
	}



}