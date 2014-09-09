package com.lianbi.mezonenew.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lianbi.mezonenew.R;
import com.pm.librarypm.fragment.BaseFragment;

/**
 * 市场的Fragment
 * @author fenfei.she
 * @date 2014年9月1日
 * @version 3.0
 */
public class MarketView extends BaseFragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.market_view, container, false);
		
		TextView textView = (TextView)view.findViewById(R.id.title);
		Button btn_update = (Button)view.findViewById(R.id.btn_update);
		
		textView.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		btn_update.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Builder dialog = new AlertDialog.Builder(getActivity());
				dialog.setTitle("软件更新");
				dialog.setMessage("1,有新版本更新\n2,更新内容");
				dialog.setNegativeButton("取消", null);//否
				dialog.setPositiveButton("更新", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						final ProgressDialog progressDialog = new ProgressDialog(getActivity());
						progressDialog.setMessage("正在下载新版本...");
						progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
						progressDialog.setIndeterminate(true);
						progressDialog.show();
						final int totalProgressTime = 100;

						   final Thread t = new Thread(){

						   @Override
						   public void run(){
						 
						      int jumpTime = 0;
						      while(jumpTime < totalProgressTime){
						         try {
						            sleep(200);
						            jumpTime += 5;
						            progressDialog.setProgress(jumpTime);
						         } catch (InterruptedException e) {
						           // TODO Auto-generated catch block
						           e.printStackTrace();
						         }

						      }

						   }
						   };
						   t.start();
						
					}
				});
				dialog.show();
			}
		});
		
		return view;
	}
}
