package com.lianbi.mezonenew.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ab.view.titlebar.AbTitleBar;
import com.lianbi.mezonenew.R;
/**
 * 程序的主入口
 * @author fenfei.she
 * @date 2014年8月7日
 * @version 3.0
 */
public class MainActivity extends MeZone3BaseActivity implements BottomFragement.Callbacks{
	
	private final static int SCANNIN_QREQUEST_CODE = 1;
	private TextView mTextView ;
	private ImageView mImageView;
	private BottomFragement mFragement;
	private AbTitleBar mAbTitleBar;
	/**ITEM区分底部菜单*/
	public static String ITEM = "ITEM";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mAbTitleBar = this.getTitleBar();
        mAbTitleBar.setTitleText("首页");
        mAbTitleBar.setLogo(R.drawable.ic_launcher);
        mAbTitleBar.setTitleBarBackground(R.drawable.mm_title_back_btn);
        mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
        //mAbTitleBar.setLogoLine(R.drawable.line);
		
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch(v.getId()){
		/*
			case R.id.button1:
				intent.setClass(MainActivity.this, MipcaActivityCapture.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(intent, SCANNIN_QREQUEST_CODE);
				break;
		*/
		}
	}
	
	/**
	 * 作QRScan的回调数据处理
	 */
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
		case SCANNIN_QREQUEST_CODE:
			if(resultCode == RESULT_OK){
				Bundle bundle = data.getExtras();
				mTextView.setText(bundle.getString("result"));
				mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
			}
			break;
		}
    }

	/**
	 * Fragment向Activity传入数据要有回调,Activity向Fragment要有Bundle
	 */
	@Override
	public void onItemSelected(int item) {
		FragmentManager manager = getSupportFragmentManager();
		Bundle bundle = new Bundle();
		switch (item) {
		case R.id.fragment_bottom_market:
			MarketView marketView = new MarketView();
			gotoTraget(manager,marketView,bundle,"MARKET");
			break;

		case R.id.fragment_bottom_operate:
			ExtraServiceView serviceView = new ExtraServiceView();
			gotoTraget(manager, serviceView, bundle, "SERVICE");
			break;
			
		case R.id.fragment_bottom_extra:
			OperateServiceView operateServiceView = new OperateServiceView();
			gotoTraget(manager, operateServiceView, bundle, "OPERATE");
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * 跳转到指定的Fragment
	 * @param manager FragmentManager
	 * @param fragment 目标fragment
	 * @param bundle 传值对象
	 * @param item 值
	 */
	private void gotoTraget(FragmentManager manager,Fragment fragment,Bundle bundle,String item){
		bundle.putString(MainActivity.ITEM, item);
		fragment.setArguments(bundle);
		manager.beginTransaction().replace(R.id.main_detail_framelayout, fragment).commit();
	}
}
