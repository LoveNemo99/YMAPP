package com.example.zr.ymapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zr.model.UserInfoSingleton;

/**
 * Created by ZR on 2016/6/14.
 */
public class LoginActivity extends Activity {

    private TextView username;
    private TextView password;
    private Button loginButton;
    private SharedPreferences sp;
    private DialogUtil dia = new DialogUtil(this);

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    dia.hidePro();
                    goWorkActivity();
                    break;
                case 2:
                    dia.showPro("登录中", "请耐心等待");
                    break;
                case 3:
                    dia.hidePro();
                    break;
                case 4:
                    Toast.makeText(getApplicationContext(), "用户名或密码错误",Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        sp = this.getSharedPreferences("tinz", Activity.MODE_PRIVATE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        username = (TextView) findViewById(R.id.userNameText);
        password = (TextView) findViewById(R.id.passwdText);
        loginButton = (Button) findViewById(R.id.bnLogin);
        String sharedUsername = sp.getString("username", "");
        String sharedUserPassword = sp.getString(sharedUsername, "");
        username.setText(sharedUsername);
        password.setText(sharedUserPassword);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                handler.sendMessage(handler.obtainMessage(2, ""));
                final String u=username.getText().toString();
                final String p=password.getText().toString();
                String s=null;
                if(u!=null&&p!=null){
                    new Thread(){
                        @Override
                        public void run(){
                            String s=ConnUtil.login(u,p);
                            if(s!=null && s.equals("1") ){
                                sp.edit().putString("username", u).commit();
                                sp.edit().putString(u, p).commit();
                                handler.sendMessage(handler.obtainMessage(1, ""));
                            }
                            else{
                                handler.sendMessage(handler.obtainMessage(3, ""));
                                handler.sendMessage(handler.obtainMessage(4, ""));
                            }
                        }
                    }.start();
                }

            }

        });
        update();
    }

    public void update(){
        //final UpdateManager manager = new UpdateManager(LoginActivity.this);
        // 检查软件更新
        //manager.checkUpdate();
    }

    private void goWorkActivity() {
        UserInfoSingleton uis = UserInfoSingleton.getInstance();
        Intent NextInt = new Intent();
        //Bundle SendStr = new Bundle();
        //SendStr.putString("PageNum", String.valueOf(1));
        //NextInt.putExtras(SendStr);
//        if(uis.getUserInfo().getAdministrator()){
//            System.out.println("admin");
//            NextInt.setClass(LoginActivity.this, ChartActivity.class);
//        }else{
//            NextInt.setClass(LoginActivity.this, ChartActivity.class);
//        }
        //NextInt.setClass(LoginActivity.this, ChartActivity.class);
        NextInt.setClass(LoginActivity.this, HomeActivity.class);
        startActivity(NextInt);
        this.finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(getCurrentFocus()!=null && getCurrentFocus().getWindowToken()!=null){
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

}
