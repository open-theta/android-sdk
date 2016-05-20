package net.adsplay.vast;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import net.adsplay.vast.model.VASTMediaFile;
import net.adsplay.vast.processor.VASTMediaPicker;

import org.junit.Test;
import org.junit.runner.RunWith;
import net.adsplay.util.DefaultMediaPicker;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class DefaultMediaPickerTest {

	private static BigInteger INVALID_MEDIAFILE_DIMENSION = new BigInteger("999999");

	@Test
	public void testArea() {

		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;

		VASTMediaFile m1 = createMediaFile("video/mp4", "http://linkToSomeVideo.com/1", new BigInteger("100"), new BigInteger("100"));
		VASTMediaFile m2 = createMediaFile("video/x-mp4", "http://linkToSomeVideo.com/2", new BigInteger("500"), new BigInteger("500"));
		VASTMediaFile m3 = createMediaFile("video/mp4", "http://linkToSomeVideo.com/3", new BigInteger("300"), new BigInteger("50"));
		VASTMediaFile m4 = createMediaFile("video/mp4", "http://linkToSomeVideo.com/4", new BigInteger("30"), new BigInteger("50"));
	
		List<VASTMediaFile> lst = new ArrayList<VASTMediaFile>();
		
		lst.add(m1);
		lst.add(m2);
		lst.add(m3);
		lst.add(m4);

		res = picker.pickVideo(lst);
		
		assertThat(res, notNullValue());
		assertThat(res.getValue(), equalTo("http://linkToSomeVideo.com/1"));
		
	}
	

	@Test
	public void testValidMediaFileList() {

		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;

		//valid
		VASTMediaFile m1 = createMediaFile("video/mp4", "http://linkToSomeVideo.com/1", new BigInteger("100"), new BigInteger("100"));
	

		//missing type
		VASTMediaFile m2 = createMediaFile(null, "http://linkToSomeVideo.com/2", new BigInteger("100"), new BigInteger("100"));
		
		//missing height
		VASTMediaFile m3 = createMediaFile("video/mp4", "http://linkToSomeVideo.com/1", null, new BigInteger("100"));
		
		
		//missing width
		VASTMediaFile m4 = createMediaFile("video/mp4", "http://linkToSomeVideo.com/1", new BigInteger("100"), null);
		
		//missing url
		VASTMediaFile m5 = createMediaFile("video/mp4", null, new BigInteger("100"), new BigInteger("100"));

		List<VASTMediaFile> mediaFiles = new ArrayList<VASTMediaFile>();

		mediaFiles.add(m1);
		mediaFiles.add(m2);
		mediaFiles.add(m3);
		mediaFiles.add(m4);
		mediaFiles.add(m5);
		
		res = picker.pickVideo(mediaFiles);
		assertThat(res, notNullValue());
		assertThat(res.getValue(), equalTo("http://linkToSomeVideo.com/1"));
		
		
	}
	
	@Test
	public void testMissingMediaTypeAttribute() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
	
		//missing type
		VASTMediaFile mediaFile = createMediaFile(null, "http://linkToSomeVideo.com/1", new BigInteger("100"), new BigInteger("100"));
		
		List<VASTMediaFile> mediaFiles = new ArrayList<VASTMediaFile>();

		mediaFiles.add(mediaFile);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, nullValue());				
	}
	
	@Test
	public void testEmptyMediaTypeAttribute() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
	
		//missing type
		VASTMediaFile mediaFile = createMediaFile("", "http://linkToSomeVideo.com/1", new BigInteger("100"), new BigInteger("100"));
		
		List<VASTMediaFile> mediaFiles = new ArrayList<VASTMediaFile>();

		mediaFiles.add(mediaFile);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, nullValue());				
	}
	
	@Test
	public void testInvalidMediaTypeAttribute() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
	
		//missing type
		VASTMediaFile mediaFile = createMediaFile("123ABC", "http://linkToSomeVideo.com/1", new BigInteger("100"), new BigInteger("100"));
		
		List<VASTMediaFile> mediaFiles = new ArrayList<VASTMediaFile>();

		mediaFiles.add(mediaFile);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, nullValue());				
	}
	
	@Test
	public void testMissingWidthAttribute() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
	
		//missing width
		VASTMediaFile mediaFile = createMediaFile("video/mp4", "http://linkToSomeVideo.com/1", new BigInteger("100"), null);

		List<VASTMediaFile> mediaFiles = new ArrayList<VASTMediaFile>();

		mediaFiles.add(mediaFile);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, nullValue());			
	}
	

	@Test
	public void testInvalidWidthAttribute() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
	
		//missing width
		VASTMediaFile mediaFile = createMediaFile("video/mp4", "http://linkToSomeVideo.com/1", new BigInteger("100"), INVALID_MEDIAFILE_DIMENSION);

		List<VASTMediaFile> mediaFiles = new ArrayList<VASTMediaFile>();

		mediaFiles.add(mediaFile);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, nullValue());			
	}
	
	
	@Test
	public void testMissingHeightAttribute() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
	
		//missing height
		VASTMediaFile mediaFile = createMediaFile("video/mp4", "http://linkToSomeVideo.com/1", null, new BigInteger("100"));

		List<VASTMediaFile> mediaFiles = new ArrayList<VASTMediaFile>();

		mediaFiles.add(mediaFile);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, nullValue());		
	}
	
	@Test
	public void testInvalidHeightAttribute() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
	
		//missing height
		VASTMediaFile mediaFile = createMediaFile("video/mp4", "http://linkToSomeVideo.com/1", INVALID_MEDIAFILE_DIMENSION, new BigInteger("100"));

		List<VASTMediaFile> mediaFiles = new ArrayList<VASTMediaFile>();

		mediaFiles.add(mediaFile);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, nullValue());		
	}
	
	@Test
	public void testMissingURLAttribute() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
	
		//missing url
		VASTMediaFile mediaFile = createMediaFile("video/mp4", null, new BigInteger("100"), new BigInteger("100"));
		
		List<VASTMediaFile> mediaFiles = new ArrayList<VASTMediaFile>();

		mediaFiles.add(mediaFile);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, nullValue());				
	}
	
	
	@Test
	public void testInvalidURLAttribute() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
	
		//missing url
		VASTMediaFile mediaFile = createMediaFile("video/mp4", "", new BigInteger("100"), new BigInteger("100"));
		
		List<VASTMediaFile> mediaFiles = new ArrayList<VASTMediaFile>();

		mediaFiles.add(mediaFile);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, nullValue());				
	}
	
	
	@Test
	public void testMp4Type() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
		String type = "video/mp4";
	
		List<VASTMediaFile> mediaFiles = createMediaFilesByType(type);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, notNullValue());
		assertThat(res.getType(), equalTo(type));		
	}
	
	@Test
	public void test3gppType() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
		String type = "video/3gpp";
	
		List<VASTMediaFile> mediaFiles = createMediaFilesByType(type);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, notNullValue());
		assertThat(res.getType(), equalTo(type));			
	}
	
	@Test
	public void testMp2tType() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
		String type = "video/mp2t";
	
		List<VASTMediaFile> mediaFiles = createMediaFilesByType(type);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, notNullValue());
		assertThat(res.getType(), equalTo(type));			
	}
	
	@Test
	public void testMatroskaType() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
		String type = "video/matroska";
	
		List<VASTMediaFile> mediaFiles = createMediaFilesByType(type);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, notNullValue());
		assertThat(res.getType(), equalTo(type));			
	}
	
	@Test
	public void testWebmType() {
		VASTMediaPicker picker = new DefaultMediaPicker(100, 100);
		VASTMediaFile res = null;
		String type = "video/webm";
	
		List<VASTMediaFile> mediaFiles = createMediaFilesByType(type);

		res = picker.pickVideo(mediaFiles);
		assertThat(res, notNullValue());
		assertThat(res.getType(), equalTo(type));			
	}
	
	private List<VASTMediaFile> createMediaFilesByType (String type) {
		
		VASTMediaFile mf1 = createMediaFile(type, "http://testVideo.com", new BigInteger("100"), new BigInteger("100"));
		VASTMediaFile mf2 = createMediaFile("a", "http://badtype.com/a", new BigInteger("100"), new BigInteger("100"));
		VASTMediaFile mf3 = createMediaFile("b", "http://badtype.com/b", new BigInteger("100"), new BigInteger("100"));
		VASTMediaFile mf4 = createMediaFile("c", "http://badtype.com/c", new BigInteger("100"), new BigInteger("100"));
		
		List<VASTMediaFile> mediaFiles = new ArrayList<VASTMediaFile>();

		mediaFiles.add(mf1);
		mediaFiles.add(mf2);
		mediaFiles.add(mf3);
		mediaFiles.add(mf4);
		
		return mediaFiles;
		
	}
	private VASTMediaFile createMediaFile(String type, String url, BigInteger height, BigInteger width) {
		
		VASTMediaFile mediaFile = new VASTMediaFile();
		mediaFile.setType(type);
		mediaFile.setValue(url);
		mediaFile.setWidth(width);
		mediaFile.setHeight(height);
		
		return mediaFile;			
		
	}
	
}
