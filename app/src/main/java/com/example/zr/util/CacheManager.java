package com.example.zr.util;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * Created by ZR on 2016/8/17.
 */
public class CacheManager {


    private static CacheManager instance;
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (instance == null) instance = new CacheManager();
        return instance;
    }

    public void addCache(CacheData cacheData) {
        if (cacheData == null) {
            return;
        }
        try {
            File file = new File(mContext.getExternalCacheDir(), cacheData.getKey());
            if (cacheData.getData() == null){
                if (file.exists()){
                    file.delete();
                }
                file.createNewFile();
                return;
            }

            if (!file.exists()) {
                System.out.println("not exists creat file");
                file.createNewFile();
            }
            else{
                System.out.println("exists delete file");
                file.delete();
                System.out.println("creat file");
                file.createNewFile();
            }

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(cacheData);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CacheData getCache(String key) {
        try {
            File file = new File(mContext.getExternalCacheDir(), key);
            if (file == null) return null;
            System.out.print("getTotalSpace:"+file.length());
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            CacheData cacheData = (CacheData) ois.readObject();
            ois.close();
            if (cacheData.isValid()) return cacheData;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date getLastModified(String key){
        File file = new File(mContext.getExternalCacheDir(), key);
        if (file == null) return null;
        return new Date(file.lastModified());
    }

}
