package liuhao.bawei.com.man.net;

import android.app.AlertDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import liuhao.bawei.com.man.bean.BaseBean;

/**
 * Created by 15218 on 2017/9/2.
 */
public class HttpXutils {

    private static volatile HttpXutils httpUtils ;
    private final Gson gson;
    private Context context ;

    private  HttpXutils(Context context){

        gson = new Gson();
        this.context = context;

    }

    public static HttpXutils getHttpXutils(Context context){

        if(httpUtils == null){

            synchronized (HttpUtils.class){

                if(httpUtils == null){

                    httpUtils = new HttpXutils(context);

                }

            }

        }
        return  httpUtils ;
    }

    public void get(String Url, final Class t, final netClickListenr netClickListenr){


        x.http().get(new RequestParams(Url), new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {

                BaseBean baseBean = gson.fromJson(result, BaseBean.class);

                if(baseBean.getCode().equals("400") ){

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("服务器请求错误，请稍后再试");
                    builder.create();
                }else if(baseBean.getCode().equals("200")){
                    if(netClickListenr!=null){
                        BaseBean o = (BaseBean) gson.fromJson(result, t);
                        netClickListenr.onsuccess(o);
                    }
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });


    }


}
