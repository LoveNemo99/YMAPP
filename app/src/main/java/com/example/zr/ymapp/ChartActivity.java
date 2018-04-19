package com.example.zr.ymapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zr.model.EnterpriseInfo;
import com.example.zr.model.HourData;
import com.example.zr.model.RealtimeData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {
    int[] mColors = new int[] { Color.rgb(137, 230, 81), Color.rgb(240, 240, 30),
            Color.rgb(89, 199, 250), Color.rgb(250, 104, 104),Color.rgb(249,242,191),
            Color.rgb(218,252,214),Color.rgb(255,201,206),Color.rgb(201,234,255),
            Color.rgb(219,201,249),Color.rgb(247,249,210),Color.rgb(248,216,217),
            Color.rgb(222,189,249),Color.rgb(210,226,250)};
    private LineData data;
    private LineChart[] chart = new LineChart[7];
    private Spinner spinner;
    private List<String> data_list;//监测点下拉菜单数据
    //List<SiteMonitorInfo> equipDatas = new ArrayList<SiteMonitorInfo>();//监测点信息
    List<EnterpriseInfo> enterpriseDatas = new ArrayList<EnterpriseInfo>();//企业信息
    private ArrayAdapter<String> arr_adapter;
    private boolean realDataSwitch = true;//读取实时数据的开关
    private int realDataInterval = 30*1000;//每隔30秒读取一次实时数据
    List<RealtimeData> realtimeDatas = new ArrayList<RealtimeData>();//实时数据
    List<HourData> hourData1 = new ArrayList<HourData>();
    List<HourData> hourData2 = new ArrayList<HourData>();
    List<HourData> hourData3 = new ArrayList<HourData>();
    List<HourData> hourData4 = new ArrayList<HourData>();
    List<HourData> hourData5 = new ArrayList<HourData>();
    List<HourData> hourData6 = new ArrayList<HourData>();
    List<HourData> hourData7 = new ArrayList<HourData>();
    int readTimes = 10;
    int t1 = 0;int t2 = 0;int t3 = 0;int t4 = 0;int t5 = 0;int t6 = 0;int t7 = 0;
//    int dev = 5;
//    int factor = 0;
    int textViewNum = 28;
    private TextView[] textView=new TextView[textViewNum];

    RealDataThread thread = new RealDataThread();

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    data = getData(12, 100);
                    chart[0].setData(data);
                    //setupChart(chart, data, mColors[0]);
                    chart[0].invalidate();
                    break;
                case 2:
                    loadRealtimeData();
                    break;
                case 3:
                    loadDropdown();
                    if(enterpriseDatas.size()!=0){
                        if(enterpriseDatas.get(0).getNamePort()==null){
                            setData(enterpriseDatas.get(0).getId()+"");
                        }
                        else{
                            setData(enterpriseDatas.get(0).getNamePort());
                        }
                    }
                    break;
                case 11:
                    t1 = 0;
                    LineData data1 = getData1(hourData1,"COD");
                    chart[0].setData(data1);
                    chart[0].invalidate();
                    break;
                case 12:
                    t2 = 0;
                    LineData data2 = getData1(hourData2,"氨氮");
                    chart[1].setData(data2);
                    chart[1].invalidate();
                    break;
                case 13:
                    t3 = 0;
                    LineData data3 = getData1(hourData3,"PH值");
                    chart[2].setData(data3);
                    chart[2].invalidate();
                    break;
                case 14:
                    t4 = 0;
                    LineData data4 = getData1(hourData4,"污水");
                    chart[3].setData(data4);
                    chart[3].invalidate();
                    break;
                case 15:
                    t5 = 0;
                    LineData data5 = getData1(hourData5,"");
                    chart[4].setData(data5);
                    chart[4].invalidate();
                    break;
                case 16:
                    t6 = 0;
                    LineData data6 = getData1(hourData6,"");
                    chart[5].setData(data6);
                    chart[5].invalidate();
                    break;
                case 17:
                    t7 = 0;
                    LineData data7 = getData1(hourData7,"");
                    chart[6].setData(data7);
                    chart[6].invalidate();
                    break;
                case 21:
                    LineData d1 = new LineData();
                    chart[0].setData(d1);
                    chart[0].invalidate();
                    break;
                case 22:
                    LineData d2 = new LineData();
                    chart[1].setData(d2);
                    chart[1].invalidate();
                    break;
                case 23:
                    LineData d3 = new LineData();
                    chart[2].setData(d3);
                    chart[2].invalidate();
                    break;
                case 24:
                    LineData d4 = new LineData();
                    chart[3].setData(d4);
                    chart[3].invalidate();
                    break;
                case 25:
                    LineData d5 = new LineData();
                    chart[4].setData(d5);
                    chart[4].invalidate();
                    break;
                case 26:
                    LineData d6 = new LineData();
                    chart[5].setData(d6);
                    chart[5].invalidate();
                    break;
                case 27:
                    LineData d7 = new LineData();
                    chart[6].setData(d7);
                    chart[6].invalidate();
                    break;

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        textViewInit();
        spinner = (Spinner) findViewById(R.id.spinner);
        //数据
        data_list = new ArrayList<String>();
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr_adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                textViewInit();
                System.out.println(enterpriseDatas.get(pos).getNamePort());
                if(enterpriseDatas.get(pos).getNamePort()==null){
                    setData(enterpriseDatas.get(pos).getId()+"");
                }else{
                    setData(enterpriseDatas.get(pos).getNamePort());
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        System.out.println("before init");
        initEquipmentData();
        System.out.println("after init");

        chart[0] = (LineChart) findViewById(R.id.chart);
        chart[1] = (LineChart) findViewById(R.id.chart2);
        chart[2] = (LineChart) findViewById(R.id.chart3);
        chart[3] = (LineChart) findViewById(R.id.chart4);
//        chart[4] = (LineChart) findViewById(R.id.chart5);
//        chart[5] = (LineChart) findViewById(R.id.chart6);
//        chart[6] = (LineChart) findViewById(R.id.chart7);

        for(int i=0;i<4;i++){
            //data = getData(12, 100);
            YAxis yAxis = chart[i].getAxisRight();
            yAxis.setEnabled(false);
            setupChart(chart[i], data, mColors[i+4]);
        }

    }
    private void setData(String dev){
        initRealtimeData(dev);
        initChartData1(dev);
        initChartData2(dev);
        initChartData3(dev);
        initChartData4(dev);
//        initChartData5(dev);
//        initChartData6(dev);
//        initChartData7(dev);
    }
    @Override
    protected void onDestroy() {
        realDataSwitch = false;
        super.onDestroy();
    }
    // 设置显示的样式
    private void setupChart(LineChart chart, LineData data, int color) {
        chart.setDescription("");
        chart.setDrawGridBackground(true); // 是否显示表格颜色
        chart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度
        chart.setBorderWidth(1.25f);
        chart.setTouchEnabled(true); // 设置是否可以触摸
        chart.setDragEnabled(true);// 是否可以拖拽
        chart.setScaleEnabled(true);// 是否可以缩放
        chart.setPinchZoom(false);
        chart.setBackgroundColor(color);// 设置背景

        XAxis xa = chart.getXAxis();
        xa.setPosition(XAxis.XAxisPosition.BOTTOM);

        Legend l = chart.getLegend(); // 设置标示，就是那个一组y的value的
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setForm(Legend.LegendForm.CIRCLE);// 样式
        l.setFormSize(6f);// 字体
        l.setTextColor(Color.GRAY);// 颜色
        chart.setData(data); // 设置数据
    }
    LineData getData(int count, float range) {
        ArrayList<String> xVals = new ArrayList<String>();
        String[] mMonths = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        for (int i = 0; i < count; i++) {
            xVals.add(mMonths[i % 12]);
        }

        // y轴的数据
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            yVals1.add(new Entry(val, i));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            yVals2.add(new Entry(val, i));
        }

        LineDataSet set1 = new LineDataSet(yVals1, "类型1");
        //set1.setFillAlpha(20);
        //set1.setFillColor(Color.RED);
        set1.setLineWidth(3f); // 线宽
        //set1.setCircleSize(3f);// 显示的圆形大小
        set1.setColor(Color.GREEN);// 显示颜色
        set1.setCircleColor(Color.GREEN);// 圆形的颜色
        //set1.setHighLightColor(Color.GREEN); // 高亮的线的颜色
        set1.setValueTextSize(8f);

        LineDataSet set2 = new LineDataSet(yVals2, "类型2");
        set2.setColor(Color.RED);
        set2.setCircleColor(Color.RED);
        set2.setValueTextSize(8f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);
        //dataSets.add(set2);
        LineData data = new LineData(xVals, dataSets);
        return data;
    }
    LineData getData1(List<HourData> hdata, String type) {
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH");
        for(int i = 0; i < hdata.size(); i++){
            HourData d = hdata.get(hdata.size()-1-i);
            xVals.add(d.getMonitorTime().getHours()+"时");
            if(type.equals("污水")){
                float val = (float) d.getCou();
                yVals1.add(new Entry(val, i));
            }else{
                float val = (float) d.getAvg();
                yVals1.add(new Entry(val, i));
            }

        }

        LineDataSet set1 = new LineDataSet(yVals1, type);
        set1.setLineWidth(3f); // 线宽
        set1.setColor(Color.GREEN);// 显示颜色
        set1.setCircleColor(Color.GREEN);// 圆形的颜色
        set1.setValueTextSize(8f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);
        LineData data = new LineData(xVals, dataSets);
        System.out.println("linedata:"+data);
        return data;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chart, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void textViewInit(){
        textView[0]=(TextView)findViewById(R.id.text_pollutant_name_1);
        textView[1]=(TextView)findViewById(R.id.text_pollutant_name_2);
        textView[2]=(TextView)findViewById(R.id.text_pollutant_name_3);
        textView[3]=(TextView)findViewById(R.id.text_pollutant_name_4);
//        textView[4]=(TextView)findViewById(R.id.text_pollutant_name_5);
//        textView[5]=(TextView)findViewById(R.id.text_pollutant_name_6);
//        textView[6]=(TextView)findViewById(R.id.text_pollutant_name_7);

        textView[7]=(TextView)findViewById(R.id.text_pollutant_value_1);
        textView[8]=(TextView)findViewById(R.id.text_pollutant_value_2);
        textView[9]=(TextView)findViewById(R.id.text_pollutant_value_3);
        textView[10]=(TextView)findViewById(R.id.text_pollutant_value_4);
//        textView[11]=(TextView)findViewById(R.id.text_pollutant_value_5);
//        textView[12]=(TextView)findViewById(R.id.text_pollutant_value_6);
//        textView[13]=(TextView)findViewById(R.id.text_pollutant_value_7);

        textView[14]=(TextView)findViewById(R.id.text_pollutant_time_1);
        textView[15]=(TextView)findViewById(R.id.text_pollutant_time_2);
        textView[16]=(TextView)findViewById(R.id.text_pollutant_time_3);
        textView[17]=(TextView)findViewById(R.id.text_pollutant_time_4);
//        textView[18]=(TextView)findViewById(R.id.text_pollutant_time_5);
//        textView[19]=(TextView)findViewById(R.id.text_pollutant_time_6);
//        textView[20]=(TextView)findViewById(R.id.text_pollutant_time_7);

        textView[21]=(TextView)findViewById(R.id.text_pollutant_unit_1);
        textView[22]=(TextView)findViewById(R.id.text_pollutant_unit_2);
        textView[23]=(TextView)findViewById(R.id.text_pollutant_unit_3);
        textView[24]=(TextView)findViewById(R.id.text_pollutant_unit_4);
//        textView[25]=(TextView)findViewById(R.id.text_pollutant_unit_5);
//        textView[26]=(TextView)findViewById(R.id.text_pollutant_unit_6);
//        textView[27]=(TextView)findViewById(R.id.text_pollutant_unit_7);
        List<String> data = new ArrayList<String>();
        for(int i=0;i<28;i++){
            String s="";
            data.add(s);
        }
        setTextViewData(data);
    }
    public void setTextViewData(List<String> data){
        for(int i=0;i<4;i++){
            textView[i].setText(data.get(i));
        }
        for(int i=7;i<11;i++){
            textView[i].setText(data.get(i));
        }
        for(int i=14;i<18;i++){
            textView[i].setText(data.get(i));
        }
        for(int i=21;i<25;i++){
            textView[i].setText(data.get(i));
        }
    }
    public void initChartData1(final String dev){
        new Thread(){
            @Override
            public void run(){
                hourData1=ConnUtil.getHourData(dev,2);
                if(hourData1!=null && hourData1.size()!=0 ){
                    handler.sendMessage(handler.obtainMessage(11, ""));
                }
                else if(t1<readTimes){
                    initChartData1(dev);
                    t1++;
                }
                else{handler.sendMessage(handler.obtainMessage(21, ""));}
            }
        }.start();
    }
    public void initChartData2(final String dev){
        new Thread(){
            @Override
            public void run(){
                hourData2=ConnUtil.getHourData(dev,3);
                if(hourData2!=null && hourData2.size()!=0 ){
                    handler.sendMessage(handler.obtainMessage(12, ""));
                }
                else if(t2<readTimes){
                    initChartData2(dev);
                    t2++;
                }
                else{handler.sendMessage(handler.obtainMessage(22, ""));}
            }
        }.start();
    }
    public void initChartData3(final String dev){
        new Thread(){
            @Override
            public void run(){
                hourData3=ConnUtil.getHourData(dev,4);
                if(hourData3!=null && hourData3.size()!=0 ){
                    handler.sendMessage(handler.obtainMessage(13, ""));
                }
                else if(t3<readTimes){
                    initChartData3(dev);
                    t3++;
                }
                else{handler.sendMessage(handler.obtainMessage(23, ""));}
            }
        }.start();
    }
    public void initChartData4(final String dev){
        new Thread(){
            @Override
            public void run(){
                hourData4=ConnUtil.getHourData(dev,1);
                if(hourData4!=null && hourData4.size()!=0 ){
                    handler.sendMessage(handler.obtainMessage(14, ""));
                }
                else if(t4<readTimes){
                    initChartData4(dev);
                    t4++;
                }
                else{handler.sendMessage(handler.obtainMessage(24, ""));}
            }
        }.start();
    }
//    public void initChartData4(final int dev){
//        new Thread(){
//            @Override
//            public void run(){
//                hourData4=ConnUtil.getHourData(dev,4);
//                if(hourData4!=null && hourData4.size()!=0 ){
//                    handler.sendMessage(handler.obtainMessage(14, ""));
//                }
//                else if(t4<readTimes){
//                    initChartData4(dev);
//                    t4++;
//                }
//                else{handler.sendMessage(handler.obtainMessage(24, ""));}
//            }
//        }.start();
//    }
//    public void initChartData5(final int dev){
//        new Thread(){
//            @Override
//            public void run(){
//                hourData5=ConnUtil.getHourData(dev,5);
//                if(hourData5!=null && hourData5.size()!=0 ){
//                    handler.sendMessage(handler.obtainMessage(15, ""));
//                }
//                else if(t5<readTimes){
//                    initChartData5(dev);
//                    t5++;
//                }
//                else{handler.sendMessage(handler.obtainMessage(25, ""));}
//            }
//        }.start();
//    }
//    public void initChartData6(final int dev){
//        new Thread(){
//            @Override
//            public void run(){
//                hourData6=ConnUtil.getHourData(dev,6);
//                if(hourData6!=null && hourData6.size()!=0 ){
//                    handler.sendMessage(handler.obtainMessage(16, ""));
//                }
//                else if(t6<readTimes){
//                    initChartData6(dev);
//                    t6++;
//                }
//                else{handler.sendMessage(handler.obtainMessage(26, ""));}
//            }
//        }.start();
//    }
//    public void initChartData7(final int dev){
//        new Thread(){
//            @Override
//            public void run(){
//                hourData7=ConnUtil.getHourData(dev,8);
//                if(hourData7!=null && hourData7.size()!=0 ){
//                    handler.sendMessage(handler.obtainMessage(17, ""));
//                }
//                else if(t7<readTimes){
//                    initChartData7(dev);
//                    t7++;
//                }
//                else{handler.sendMessage(handler.obtainMessage(27, ""));}
//            }
//        }.start();
//    }
    public void initRealtimeData(final String dev){
        thread.close();
        thread = new RealDataThread();
        thread.dev = dev;
        thread.start();
    }
    public class RealDataThread extends Thread{
        private volatile boolean isRun = true;
        String dev;
        @Override
        public void run() {
            while (isRun) {
                realtimeDatas=ConnUtil.getRealtimeData(dev);
                if(realtimeDatas!=null && realtimeDatas.size()!=0 ){
                    handler.sendMessage(handler.obtainMessage(2, ""));
                }
                try {
                    Thread.sleep(realDataInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void close() {
            this.isRun = false;
        }
    }
    public void initEquipmentData(){
        new Thread(){
            @Override
            public void run(){
                enterpriseDatas=ConnUtil.getEnterpriseInfo();
                if(enterpriseDatas!=null && enterpriseDatas.size()!=0 ){
                    handler.sendMessage(handler.obtainMessage(3, ""));
                }
                else{
                    //initEquipmentData();
                }
            }
        }.start();
    }
    public void loadDropdown(){
        data_list.clear();
        if(enterpriseDatas==null)
            return;
        List<EnterpriseInfo> enterpriseDatas1 = new ArrayList<EnterpriseInfo>();
        for(int i=0;i<enterpriseDatas.size();i++){
            enterpriseDatas1.add(enterpriseDatas.get(i));
        }
        for(int i=0;i<enterpriseDatas1.size();i++){
            if(!enterpriseDatas1.get(i).getName().equals("通州区恒达印染有限公司")
                    && !enterpriseDatas1.get(i).getName().equals("通州区万达染整有限公司")
                    && !enterpriseDatas1.get(i).getName().equals("通州区东盛印染有限公司")
                    && !enterpriseDatas1.get(i).getName().equals("南通市丰杰印染有限公司")
                    && !enterpriseDatas1.get(i).getName().equals("丽王化工(南通)有限公司")){
                data_list.add(enterpriseDatas1.get(i).getName());
            }
            else{
                for(int j=0;j<enterpriseDatas.size();j++){
                    if(enterpriseDatas.get(j).getName().equals(enterpriseDatas1.get(i).getName())){
                        enterpriseDatas.remove(enterpriseDatas.get(j));
                    }
                }

            }

        }
        arr_adapter.notifyDataSetChanged();
    }
    public void loadRealtimeData(){
        SimpleDateFormat sFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        List<String> data = new ArrayList<String>();
        for(int i=0;i<28;i++){
            String s="";
            data.add(s);
        }
        for(int i=0;i<realtimeDatas.size();i++){
            data.set(i,realtimeDatas.get(i).getFactorName());
            data.set(i+7,realtimeDatas.get(i).getRtd()+"");
            data.set(i+14,sFormat.format(realtimeDatas.get(i).getMonitorTime()));
            data.set(i+21,realtimeDatas.get(i).getUnit());
        }
        setTextViewData(data);
    }
}
