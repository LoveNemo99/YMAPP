package com.example.zr.ymapp;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zr.model.AlarmRecordData;
import com.example.zr.model.AlarmRecordDataSingleton;
import com.example.zr.util.CacheData;
import com.example.zr.util.CacheManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlarmManageActivity extends AppCompatActivity {
	ListView mListView;
	MyAdapter mAdapter;
	ImageButton leftBtn;//前一天按钮
	ImageButton rightBtn;//后一天按钮
	TextView dateText;
	int gap = 0;//和今天偏移的天数
	//String searchword = "company";
	List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	View view;
	CacheManager cManager = CacheManager.getInstance();
	Handler handler = new Handler() {
        public void handleMessage(Message msg) {
        	mAdapter.notifyDataSetChanged();
        }
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CacheManager.init(getApplicationContext());
		final LayoutInflater mInflater = LayoutInflater.from(this);
		view = mInflater.inflate(R.layout.activity_alarm, null);
		setContentView(view);
		//setContentView(R.layout.alarm_manage_layout);
		leftBtn = (ImageButton)findViewById(R.id.left);
		rightBtn = (ImageButton)findViewById(R.id.right);
		dateText = (TextView)findViewById(R.id.title);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		dateText.setText("今日报警("+sdf.format(now)+")");

		leftBtn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				gap--;

				/*将数据清空*/
				clearData();

				/*更改显示的日期*/
				Date n = new Date();
				Date d = new Date(n.getTime() + gap*24*60*60*1000l);
				dateText.setText(""+sdf.format(d));

				/*获取数据*/
				getData();
			}
		});

		rightBtn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//handler.removeCallbacks(thread);
				if(gap >= 0){
					return;
				}
				gap++;
				/*将数据清空*/
				clearData();

				Date n = new Date();
				Date d = new Date(n.getTime() + gap*24*60*60*1000l);
				if(gap == 0){
					dateText.setText("今日报警("+sdf.format(d)+")");
				} else{
					dateText.setText(""+sdf.format(d));
				}
				getData();
			}
		});

		mListView = (ListView)findViewById(R.id.listview_alarm_main);
		mListView.setTextFilterEnabled(true);
		mAdapter = new MyAdapter(this, data, R.layout.alarm_list_item,
                new String[]{"site","factor","monitor","time"},
                new int[]{R.id.text_1, R.id.text_2, R.id.text_3,R.id.text_4});
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
				Intent NextInt = new Intent();
				NextInt.setClass(AlarmManageActivity.this, AlarmDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("index", arg2);
				NextInt.putExtras(bundle);
				startActivity(NextInt);
			}
		});
		getData();

		if(!isServiceWork(AlarmManageActivity.this,"com.example.zr.charttest.AlarmService")){
			startService(new Intent(AlarmManageActivity.this,AlarmService.class));
		}
	}

	public boolean isServiceWork(Context mContext, String serviceName) {
		boolean isWork = false;
		ActivityManager myAM = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(Integer.MAX_VALUE);
		if (myList.size() <= 0) {
			return false;
		}
		for (int i = 0; i < myList.size(); i++) {
			String mName = myList.get(i).service.getClassName().toString();
			if (mName.equals(serviceName)) {
				isWork = true;
				break;
			}
		}
		return isWork;
	}

	private void clearData(){
		AlarmRecordDataSingleton ws = AlarmRecordDataSingleton.getInstance();
		List<AlarmRecordData> list = new ArrayList<AlarmRecordData>();
		ws.setData(list);
		loadData(ws.getData());
	}

	private void getData() {
        //List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		new Thread(){
            @Override
            public void run(){
				int flag = gap;
				final AlarmRecordDataSingleton ws = AlarmRecordDataSingleton.getInstance();
            	List<AlarmRecordData> list = ConnUtil.getAlarmRecordData(gap);
				if (flag==0) {
					CacheData<List<AlarmRecordData>> cData = new CacheData<List<AlarmRecordData>>("alarm",list);
					cManager.addCache(cData);
				}
            	if(list != null && flag == gap){
            		ws.setData(list);
            		loadData(ws.getData());
					//CacheData<List<AlarmRecordData>> cData = new CacheData<List<AlarmRecordData>>("alarm",ws.getData());
            	}
			}
		}.start();
    }
	
	private void loadData(List<AlarmRecordData> list){
		data.clear();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
		for(int i = 0; i < list.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
			AlarmRecordData ard = list.get(i);
			map.put("site",ard.getSiteName());
			map.put("factor",ard.getContent());
			map.put("monitor",ard.getMonitorName());
			map.put("time",sdf.format(ard.getDatetime()));

            data.add(map);
        }
		handler.sendMessage(handler.obtainMessage(1, ""));
	}
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//		switch (resultCode) {
//		   case 1:
//		    Bundle b=intent.getExtras();
//		    String state=b.getString("state");
//		    int index = b.getInt("id");
//		    data.get(index).put("state", state);
//		    mAdapter.notifyDataSetChanged();
//		    break;
//		default:
//		    break;
//		}
//	}
}
