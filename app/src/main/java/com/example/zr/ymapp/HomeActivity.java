package com.example.zr.ymapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
	private GridView gridview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    
    private int[] icon = { R.mipmap.jianjie,R.mipmap.grid1, R.mipmap.grid2};
    private String[] iconName = { "简介","数据", "报警"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home_page);
		gridview = (GridView) findViewById(R.id.gridview);
        data_list = new ArrayList<Map<String, Object>>();
        getData();
        String [] from ={"image","text"};
        int [] to = {R.id.home_image,R.id.home_text};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.home_gridview_item, from, to);
        gridview.setAdapter(sim_adapter);
        gridview.setOnItemClickListener(new ItemClickListener());
        
//        ActionBar actionBar = getActionBar();
//        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.title_backg2));
	}
    
	public List<Map<String, Object>> getData(){
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }
    
	class  ItemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent NextInt = new Intent();
			switch (arg2) {
			case 0:
				//NextInt.setClass(HomeActivity.this, IntroductionActivity.class);
				break;
			case 1:
				NextInt.setClass(HomeActivity.this, ChartActivity.class);
				break;
			case 2:
				NextInt.setClass(HomeActivity.this, AlarmManageActivity.class);
				break;
			}
			if(arg2!=0){
				startActivity(NextInt);
			}

		}
		
	}
}
