package liuhao.bawei.com.man.net;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import liuhao.bawei.com.man.bean.BaseBean;

/**
 * Created by 15218 on 2017/9/3.
 */
public class HttpUrlUtils {

    private String str ;
    private static volatile HttpUrlUtils httpUtils ;
    private final Gson gson;
    private Context context ;

    private  HttpUrlUtils(Context context){

        gson = new Gson();
        this.context = context;

    }

    public static HttpUrlUtils getHttpXutils(Context context){

        if(httpUtils == null){

            synchronized (HttpUrlUtils.class){

                if(httpUtils == null){

                    httpUtils = new HttpUrlUtils(context);

                }

            }

        }
        return  httpUtils ;
    }

    public void get(final String Url, final Class t, final netClickListenr netClickListenr){
        new  Thread(){
            public void run() {

                 String str =  readUrlMethoed(Url);

            };
        }.start();
        new Runnable(){

            @Override
            public void run() {
                BaseBean baseBean = gson.fromJson(str, BaseBean.class);
                if(baseBean.getCode().equals("400") ){

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("服务器请求错误，请稍后再试");
                    builder.create();
                }else if(baseBean.getCode().equals("200")){
                    if(netClickListenr!=null){
                        BaseBean o = (BaseBean) gson.fromJson(str, t);
                        netClickListenr.onsuccess(o);
                    }
                }

            }

        };
    }

    public String readUrlMethoed(String url) {


        try {
            URL u = new URL(url);
            //得到连接
            try {
                HttpURLConnection connection  = (HttpURLConnection) u.openConnection();

                //那到方法
                connection.setRequestMethod("GET");
                //设置网络超时
                connection.setReadTimeout(3000);
                connection.setConnectTimeout(3000);
                //使用输入流获取内容
                InputStream inputStream = connection.getInputStream();

                String text = readText(inputStream);

                return text ;

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

         return  null ;
    }
    public String readText(InputStream inputStream){


        try {

            ByteArrayOutputStream b = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];

            int len = 0 ;

            while ((len = inputStream.read(buffer))!= -1) {

                b.write(buffer, 0, len);


            }

            return b.toString();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;



    }



}

