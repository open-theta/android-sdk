//
//  ParserTest.java
//
//  Copyright (c) 2014 Nexage. All rights reserved.
//

package net.adsplay.vast;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import junit.framework.Assert;
import net.adsplay.vast.model.TRACKING_EVENTS_TYPE;
import net.adsplay.vast.model.VASTMediaFile;
import net.adsplay.vast.model.VASTModel;
import net.adsplay.vast.model.VideoClicks;
import net.adsplay.vast.processor.VASTMediaPicker;
import net.adsplay.vast.processor.VASTProcessor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;

@RunWith(RobolectricTestRunner.class)
public class ParserTest {

	@Rule
	public TestName name = new TestName();

	VASTProcessor parser;

	private class myMediaPicker implements VASTMediaPicker {

		@Override
		public VASTMediaFile pickVideo(List<VASTMediaFile> list) {
			// pick the first
			return list.get(0);
		}

	}

	@Before
	public void setup() throws IOException {
		ShadowLog.stream = System.out;

		System.out.println();
		System.out
				.println("*******************************************************");
		System.out.println("*****     TEST:  " + name.getMethodName()
				+ "    *****");
		System.out
				.println("*******************************************************");
		System.out.println();

		parser = new VASTProcessor(new myMediaPicker());

	}

	// Get content from a URL
	private String getURLContent(String urlString) {
		StringBuffer sb = new StringBuffer();
		;
		BufferedReader in = null;
		try {
			URL url = new URL(urlString);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line).append(System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// ignore
			}
		}

		return sb.toString();
	}

	private String getFileAsString(String fileName) throws Exception {

		URL url2 = getClass().getClassLoader().getResource(fileName);

		String file = new String(Files.readAllBytes(Paths.get(url2.toURI())));

		return file;
	}
	
	
	@Test
	public void getTrackingsInlineLinearTest() throws Exception {

		int totalEvents = 0;

		String file = getFileAsString("resources/vast_liverail_linear_comp.xml");
		int err = parser.process(file);

		Assert.assertEquals(VASTPlayer.ERROR_NONE, err);
		VASTModel mVastDto = parser.getModel();
		HashMap<TRACKING_EVENTS_TYPE, List<String>> trackings = mVastDto
				.getTrackingUrls();

		assertThat(trackings, notNullValue());

		System.out.println("trackings" + trackings.size());

		for (Entry<TRACKING_EVENTS_TYPE, List<String>> entry : trackings.entrySet()) {
			System.out.println("Event:" + entry.getKey());

			List<String> urls = entry.getValue();
			totalEvents = totalEvents + urls.size();
			for (String eventUrl : urls) {
				System.out.println(eventUrl);
			}

		}

		assertThat(totalEvents, equalTo(11));
	}

	@Test
	public void getTrackingsWrapperLinearTest() throws Exception {

		int totalEvents = 0;
		String file = getFileAsString("resources/vast_wrapper_linear_1.xml");
		int err = parser.process(file);
		Assert.assertEquals(VASTPlayer.ERROR_NONE, err);

		VASTModel mVastDto = parser.getModel();
		HashMap<TRACKING_EVENTS_TYPE, List<String>> trackings = mVastDto
				.getTrackingUrls();

		assertThat(trackings, notNullValue());

		System.out.println("trackings" + trackings.size());

		for (Entry<TRACKING_EVENTS_TYPE, List<String>> entry : trackings.entrySet()) {
			System.out.println("Event:" + entry.getKey());

			List<String> urls =  entry.getValue();
			totalEvents = totalEvents + urls.size();
			for (String eventUrl : urls) {
				System.out.println(eventUrl);
			}

		}

		assertThat(totalEvents, equalTo(20));
	}

	@Test
	public void getMediaFilesInlineLinearTest() throws Exception {

		String file = getFileAsString("resources/vast_liverail_linear_comp.xml");
		int err = parser.process(file);	
		Assert.assertEquals(VASTPlayer.ERROR_NONE, err);

		VASTModel mVastDto = parser.getModel();
		List<VASTMediaFile> mediaFiles = mVastDto.getMediaFiles();

		assertThat(mediaFiles, notNullValue());

		System.out.println("Number of media Files:" + mediaFiles.size());

		for (VASTMediaFile mf : mediaFiles) {
			System.out.println(mf.toString());
		}

		assertThat(mediaFiles.size(), equalTo(12));
	}

	@Test
	public void getVideoClicksInlineLinearTest() throws Exception {

		String file = getFileAsString("resources/vast_doubleclick_inline_comp.xml");
		int err = parser.process(file);
		
		Assert.assertEquals(VASTPlayer.ERROR_NONE, err);

		VASTModel mVastDto = parser.getModel();
		VideoClicks videoClicks = mVastDto.getVideoClicks();

		assertThat(videoClicks, notNullValue());

		System.out.println(videoClicks.toString());

		assertThat(videoClicks.getClickTracking().size(), equalTo(1));
		assertThat(videoClicks.getCustomClick().size(), equalTo(0));
		assertThat(videoClicks.getClickThrough(),
				equalTo("http://google.com"));
	}

	@Test
	public void getDurationInlineLinearTest() throws Exception {

		String file = getFileAsString("resources/vast_liverail_linear_comp.xml");
		int err = parser.process(file);
		Assert.assertEquals(VASTPlayer.ERROR_NONE, err);

		VASTModel mVastDto = parser.getModel();
		String duration = mVastDto.getDuration();

		System.out.println("duration=" + duration);

		assertThat(duration, equalTo("00:00:09"));
	}

	@Test
	public void validInlineLinearDocParserTest() throws Exception {

		String file = getFileAsString("resources/vast_liverail_linear_comp.xml");
		int err = parser.process(file);
		
		Assert.assertEquals(VASTPlayer.ERROR_NONE, err);

		VASTModel mVastDto = parser.getModel();
		assertThat(mVastDto, notNullValue());
	}

	@Test
	public void multipleMediaFileTest() throws Exception {
		String file = getFileAsString("resources/vast_liverail_linear_comp.xml");
		int err = parser.process(file);
		
		Assert.assertEquals(VASTPlayer.ERROR_NONE, err);

		VASTModel mVastDto = parser.getModel();
		assertThat(mVastDto, notNullValue());

		List<VASTMediaFile> mediaFiles = mVastDto.getMediaFiles();

		System.out.println("Number of media Files:" + mediaFiles.size());

		for (VASTMediaFile mf : mediaFiles) {
			System.out.println(mf.toString());
		}

		assertThat(mediaFiles.size(), equalTo(12));
	}

	@Test
	public void impressionTest() throws Exception {

		String file = getFileAsString("resources/vast_liverail_linear_comp.xml");
		int err = parser.process(file);
		
		Assert.assertEquals(VASTPlayer.ERROR_NONE, err);

		VASTModel mVastDto = parser.getModel();
		assertThat(mVastDto, notNullValue());

		List<String> impressions = mVastDto.getImpressions();

		assertThat(impressions, notNullValue());
		System.out.println("impressions size:" + impressions.size());

		for (String impression : impressions) {
			System.out.println(impression);
		}
	}

	@Test
	public void getErrorUrlTest() throws Exception {

		String file = getFileAsString("resources/vast_liverail_linear_comp.xml");
		int err = parser.process(file);
		
		Assert.assertEquals(VASTPlayer.ERROR_NONE, err);

		VASTModel mVastDto = parser.getModel();
		List<String> errorUrl = mVastDto.getErrorUrl();

		System.out.println("Error url size=" + errorUrl.size());

		assertThat(errorUrl.get(0), equalTo("http://t4.liverail.com/?metric=error&erc=[ERRORCODE]&pos=0&coid=135&pid=1331&nid=1331&oid=229&olid=2291331&cid=7969&tpcid=&vid=&amid=&cc=default&pp=&vv=&tt=&sg=&tsg=&pmu=0&pau=0&psz=0&ctx=&tctx=&coty=0&adt=0&did=&buid=&scen=&url=&cb=4641.173.48.24.154.0.690&ver=1&w=&wy=&x=&y=&xy=&redirect="));
	}

	@Test
	public void getErrorUrlslTest() throws Exception {

		String file = getFileAsString("resources/vast_wrapper_linear_1.xml");
		int err = parser.process(file);
				
		Assert.assertEquals(VASTPlayer.ERROR_NONE, err);

		VASTModel mVastDto = parser.getModel();
		List<String> errorUrl = mVastDto.getErrorUrl();

		System.out.println("Error url size=" + errorUrl.size());

		assertThat(errorUrl.size(), equalTo(2));

		assertThat(errorUrl.get(0),equalTo("http://myErrorURL/wrapper/error"));
	}

	@Test
	public void getMediaFileWrapperLinearTest() throws Exception {

		String file = getFileAsString("resources/vast_wrapper_linear_1.xml");
		int err = parser.process(file);
		
		assertThat(err, equalTo(VASTPlayer.ERROR_NONE));
		
		VASTModel mVastDto = parser.getModel();
		List<VASTMediaFile> mediaFiles = mVastDto.getMediaFiles();

		assertThat(mediaFiles, notNullValue());

		int numberOfMediaFiles = mediaFiles.size();
		System.out.println("number of mediaFiles:" + numberOfMediaFiles);

		assertThat(numberOfMediaFiles, equalTo(1));

	}


}
