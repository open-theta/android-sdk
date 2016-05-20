package net.adsplay.vast.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.adsplay.util.HttpTools;
import net.adsplay.util.VASTLog;
import net.adsplay.util.XmlTools;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public class VmapModel implements Serializable {
	
	public static String TAG = "VmapModel";

	private static final long serialVersionUID = -4060497826275236955L;

	String adBreakXPATH = "/AdBreak";

	private transient Document vmapDocument;

	public VmapModel(Document xmlDoc) {
		this.vmapDocument = xmlDoc;
	}

	public Document getVmapDocument() {
		return vmapDocument;
	}

	public List<VASTModel> getVASTModels() {
		List<VASTModel> models = new ArrayList<VASTModel>();		
		NodeList adbreakNodes = vmapDocument.getElementsByTagName("vmap:AdBreak");
		int l = adbreakNodes.getLength();
		for (int i = 0; i < l; i++) {
			Node node = adbreakNodes.item(i);
			String timeOffset = XmlTools.getAttributeValue(node, "timeOffset");						
			NodeList childs = node.getChildNodes();
			for (int j = 0; j < childs.getLength(); j++) {
				Node item = childs.item(j);
				if(item.getNodeName().equalsIgnoreCase("vmap:AdSource")){
					String vastUrl = item.getTextContent().trim();
					String vastXml = HttpTools.get(vastUrl);
					Log.d(TAG, "vastUrl " + vastUrl);
					Document doc = XmlTools.stringToDocument(vastXml);
					VASTModel vastModel = new VASTModel(doc,timeOffset);
					Log.d(TAG, timeOffset + " getMediaFiles.size:" + vastModel.getMediaFiles().size() + " getImpressions.size:" + vastModel.getImpressions().size() );		
					String url = vastModel.getPickedMediaFileURL();
					Log.d(TAG, "URL for media file:" + url);
					if(url != null){
						models.add(vastModel);	
					}					
				}
			}
		}		
		return models;
	}

}
