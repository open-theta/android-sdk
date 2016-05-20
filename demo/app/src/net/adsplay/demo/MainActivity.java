//
//  MainActivity.java
//
//  http://code.tutsplus.com/tutorials/streaming-video-in-android-apps--cms-19888
//

package net.adsplay.demo;

import net.adsplay.demo.adapter.MainPagerAdapter;
import net.adsplay.util.VASTLog;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {
	private static final String TAG = "MainActivity";

	private String[] TAB_LABELS = { "AdsPLAY Samples", "About" };

	private ViewPager viewPager;
	private PagerAdapter pagerAdapter;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		VASTLog.d(TAG, "onCreate");

		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.activity_main);

		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(pagerAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (String label : TAB_LABELS) {
			actionBar.addTab(actionBar.newTab().setText(label).setTabListener(this));
		}

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

}
