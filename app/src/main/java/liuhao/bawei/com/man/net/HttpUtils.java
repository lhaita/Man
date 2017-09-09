package liuhao.bawei.com.man.net;

import android.app.AlertDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import liuhao.bawei.com.man.bean.BaseBean;

/**
 * Created by 15218 on 2017/9/1.
 */
public class HttpUtils {

    private static volatile HttpUtils httpUtils ;
    private final Gson gson;
    private Context context ;

    private  HttpUtils(Context context){

        gson = new Gson();
        this.context = context;

    }

    public static HttpUtils getHttpUtils(Context context){

        if(httpUtils == null){

            synchronized (HttpUtils.class){

                if(httpUtils == null){

                    httpUtils = new HttpUtils(context);

                }

            }

        }
            return  httpUtils ;
    }

    public  void post(String url, final Class t , Map<String,String> map, final netClickListenr netClickListenr){

        PostFormBuilder url1 = OkHttpUtils.post().url(url);

        for (Map.Entry<String,String> entry : map.entrySet()){

            url1.addParams(entry.getKey(),entry.getValue());

        }

        url1.build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {


            }

            @Override
            public void onResponse(String response) {

                BaseBean baseBean = gson.fromJson(response, BaseBean.class);

                if(baseBean.getCode().equals("400") ){

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("服务器请求错误，请稍后再试");
                    builder.create();
                }else if(baseBean.getCode().equals("200")){
                    if(netClickListenr!=null){
                        BaseBean o = (BaseBean) gson.fromJson(response, t);
                        netClickListenr.onsuccess(o);
                    }
                }


            }
        });



    }

    public  void get(String url, final Class t , Map<String,String> map, final netClickListenr netClickListenr){
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        sb.append("?");
        for (Map.Entry<String,String> entry : map.entrySet()){
              sb.append(entry.getKey());
              sb.append("=");
              sb.append(entry.getValue());
            sb.append("&");
        }
        OkHttpUtils.get().url(sb.toString()).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                BaseBean baseBean = gson.fromJson(response, BaseBean.class);

                if(baseBean.getCode().equals("400") ){

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("服务器请求错误，请稍后再试");
                    builder.create();
                }else if(baseBean.getCode().equals("200")){
                            if(netClickListenr!=null){
                                BaseBean o = (BaseBean) gson.fromJson(response, t);
                                netClickListenr.onsuccess(o);
                            }
                }
            }
        });
    }

    public  void get(String url, final Class t , final netClickListenr netClickListenr){

        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                BaseBean baseBean = gson.fromJson(response, BaseBean.class);

                if(baseBean.getCode().equals("400") ){

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("服务器请求错误，请稍后再试");
                    builder.create();
                }else if(baseBean.getCode().equals("200")){
                    if(netClickListenr!=null){
                        BaseBean o = (BaseBean) gson.fromJson(response, t);
                        netClickListenr.onsuccess(o);
                    }
                }
            }
        });
    }
}
