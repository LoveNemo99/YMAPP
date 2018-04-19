package com.example.zr.ymapp;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtil {
	public ProgressDialog proDia;
	public Context context;
	
	public DialogUtil(Context context) {
		super();
		this.context = context;
	}
	public void showPro(String title,String message){
		proDia=new ProgressDialog(context);
		proDia.setCancelable(true);//���ý�����Ƿ���԰��˻ؼ�ȡ��   
		proDia.setCanceledOnTouchOutside(false);//���õ����ȶԻ����������Ի�����ʧ 
		proDia.setTitle(title);
		proDia.setMessage(message);
		proDia.show();
	}
	public void hidePro(){
		if(proDia!=null){
			proDia.dismiss();
		}
	}
}
