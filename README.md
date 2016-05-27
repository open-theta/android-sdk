AdsPLAY-VAST - Open Source Android SDK for TVC Video Ad 
=====================================

AdsPLAY-VAST for Android is an open sourced IAB VAST 3.0 compliant rendering engine for HTML ad creatives.

For more information on IAB VAST 3.0 compliance please visit http://www.iab.com/guidelines/digital-video-suite/

**Features:**

- VAST 3 implementation
- Handles VMAP, VAST & VAST Wrapper
- Optionally choose to validate with Xerces

**Requirements:**

- Android 2.3+
- Eclipse + ADT

Getting Started
===============

Step 1: Import the VAST project into your Eclipse workspace.

Step 2: Include the VAST library in your project under Project Properties -> Android -> Library.

Step 3: Add the following Activity into AndroidManifest.xml under the <application> tag.

	<activity
		android:name="org.nexage.sourcekit.vast.activity.VASTActivity"
		android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />

Step 4: Add the following permissions to the AndroidManifest.xml under the <manifest> tag.

	<uses-permission android:name="android.permission.INTERNET"></uses-permission>
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>

Step 5: Use VASTPlayer in your project.
	check example code at net.adsplay.demo.AdsPlayVastRequest


Implement VASTPlayerListener in your activity to listen for VASTPlayer ready or fail events.

After you've received the vastReady callback you can play the video.

	vastPlayer.play();

That's it!
