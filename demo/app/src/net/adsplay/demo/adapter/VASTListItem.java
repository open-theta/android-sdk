//
//  VASTListItem.java
//
//  Copyright (c) 2014 Nexage. All rights reserved.
//

package net.adsplay.demo.adapter;

public class VASTListItem {
 
    private String title; // First line on the list
    private String description; // Second line on the list
 
    public VASTListItem(String title, String description) {
        super();
        this.setTitle(title);
        this.setDescription(description);
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
