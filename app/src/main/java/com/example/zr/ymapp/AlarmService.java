package com.example.zr.ymapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

import com.example.zr.model.AlarmRecordData;
import com.example.zr.util.CacheData;
import com.example.zr.util.CacheManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlarmService extends Service {
    List<AlarmRecordData> oldData = new ArrayList<AlarmRecordData>();
    CacheManager cManager = CacheManager.getInstance();

    public AlarmService() {
    }
    Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stubm
        super.onStart(intent, startId);
        new Thread(){
            @Override
            public void run(){
                while(true){
                    //CacheManager.init(context);
                    List<AlarmRecordData> list=ConnUtil.getAlarmRecordData(0);
//                    AlarmRecordData ad = new AlarmRecordData((float)50.00,"x","s","ccccc",5,new Date(),"factor",2,"monitorName","siteName","siteType");
//                    List<AlarmRecordData> l = new ArrayList<AlarmRecordData>();
//                    l.add(ad);
//                    CacheData<List<AlarmRecordData>> cd = new CacheData<List<AlarmRecordData>>("alarm",l);
//                    cManager.addCache(cd);
//                    System.out.println(cManager.getLastModified("alarm"));
//                    List<AlarmRecordData> read = new ArrayList<AlarmRecordData>();
//                    read = (List<AlarmRecordData>)cManager.getCache("alarm").getData();
//                    System.out.println(read.size());
                    if (cManager.getCache("alarm")==null) {
                        System.out.println("ggggggggggggggggggggggggggggggggg");
                    }
                    if(list!=null && list.size()!=0 ){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        if(cManager.getLastModified("alarm")==null){
                            CacheData<List<AlarmRecordData>> cData = new CacheData<List<AlarmRecordData>>("alarm",list);
                            cManager.addCache(cData);
                            CreateInform();
                        }
                        else if(sdf.format(cManager.getLastModified("alarm")).equals(sdf.format(new Date()))){
                            if (cManager.getCache("alarm")==null) {
                                if(list.size()>0){
                                    CacheData<List<AlarmRecordData>> cData = new CacheData<List<AlarmRecordData>>("alarm",list);
                                    cManager.addCache(cData);
                                    CreateInform();
                                }
                            }
                            else{
                                List<AlarmRecordData> old = new ArrayList<AlarmRecordData>();
                                old = (List<AlarmRecordData>)cManager.getCache("alarm").getData();
                                if(list.size()>old.size()){
                                    CacheData<List<AlarmRecordData>> cData = new CacheData<List<AlarmRecordData>>("alarm",list);
                                    cManager.addCache(cData);
                                    CreateInform();
                                }
                            }
                        }
                        else{
                            CacheData<List<AlarmRecordData>> cData = new CacheData<List<AlarmRecordData>>("alarm",list);
                            cManager.addCache(cData);
                            CreateInform();
                        }
                    }
                    try {
                        Thread.sleep(10*1000);//5分钟检测一次
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        Intent sevice = new Intent(this, AlarmService.class);
        this.startService(sevice);
        super.onDestroy();
    }

    //创建通知
    public void CreateInform() {
        //定义一个PendingIntent，当用户点击通知时，跳转到某个Activity(也可以发送广播等)
        Intent intent = new Intent(context,AlarmManageActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        int smallIconId = R.mipmap.zhb;
        Bitmap largeIcon = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
        String info = "有新的报警";
        builder.setLargeIcon(largeIcon).setSmallIcon(smallIconId).setContentTitle("报警")
                .setContentText(info).setTicker(info).setContentIntent(pendingIntent);
        //.setContentIntent(PendingIntent.getActivity(this, 0, intent, 0))
        final Notification n = builder.getNotification();
        n.flags |= Notification.FLAG_AUTO_CANCEL;
        n.defaults = Notification.DEFAULT_SOUND;
        //用NotificationManager的notify方法通知用户生成标题栏消息通知
        NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nManager.notify(100, n);//id是应用中通知的唯一标识
        //如果拥有相同id的通知已经被提交而且没有被移除，该方法会用更新的信息来替换之前的通知。
    }
}
