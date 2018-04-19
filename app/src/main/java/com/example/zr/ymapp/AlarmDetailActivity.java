package com.example.zr.ymapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.zr.model.AlarmRecordData;
import com.example.zr.model.AlarmRecordDataSingleton;

import java.text.SimpleDateFormat;

public class AlarmDetailActivity extends AppCompatActivity {
    int index;
    TextView text[] = new TextView[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_detail);
        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("index");
        AlarmRecordDataSingleton ardsi = AlarmRecordDataSingleton.getInstance();
        initText();
        setText(ardsi.getData().get(index));
    }

    public void initText(){
        text[0] = (TextView) findViewById(R.id.text_1);
        text[1] = (TextView) findViewById(R.id.text_2);
        text[2] = (TextView) findViewById(R.id.text_3);
        text[3] = (TextView) findViewById(R.id.text_4);
        text[4] = (TextView) findViewById(R.id.text_5);
        text[5] = (TextView) findViewById(R.id.text_6);
        text[6] = (TextView) findViewById(R.id.text_7);
        text[7] = (TextView) findViewById(R.id.text_8);
        text[8] = (TextView) findViewById(R.id.text_11);
    }

    public void setText(AlarmRecordData data){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
        text[0].setText(data.getSiteName());
        text[1].setText(data.getMonitorName());
        text[2].setText(sdf.format(data.getDatetime()));
        text[3].setText(data.getContent());
        text[4].setText(data.getReceiveMan());
        text[5].setText(data.getReceiveNumber());
        text[6].setText(data.getRemark());
        //text[7].setText("报警限值: "+data.getAlarmLimit());
        //text[8].setText(data.getContent());
    }
}
