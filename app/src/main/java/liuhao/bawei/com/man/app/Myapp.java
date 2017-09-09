package liuhao.bawei.com.man.app;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.zhy.http.okhttp.https.HttpsUtils;

import org.xutils.BuildConfig;
import org.xutils.x;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.net.HttpUtils;

/**
 * Created by 15218 on 2017/8/31.
 */
public class Myapp extends Application {

    private HttpUtils httpUtils;
    private ImageLoader instance;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化xutils包
                x.Ext.init(this);
                x.Ext.setDebug(BuildConfig.DEBUG);
        httpUtils = HttpUtils.getHttpUtils(this);

                    //设置图片的显示
                            DisplayImageOptions options = new DisplayImageOptions.Builder()
                                    .cacheInMemory(true)
                                    .cacheOnDisk(true)
                                    .showImageOnFail(R.mipmap.ic_launcher)
                                    .cacheInMemory(true)    //设置下载的图片是否缓存在内存中
                                    .cacheOnDisk(true)  //设置下载的图片是否缓存在SD卡中
                                    .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                                    .imageScaleType(ImageScaleType.EXACTLY)//设置图片以如何的编码方式显示
                                    .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//</span>
//                                    <span style="background-color: #ffffff; color: #ff0000;">
                                    .build();
                            ImageLoaderConfiguration i = new ImageLoaderConfiguration.Builder(this)
                                    .defaultDisplayImageOptions(options)
                                    .build();
                                    //初始化
        instance = ImageLoader.getInstance();
        instance.init(i);



    }

    public ImageLoader getInstance() {
        return instance;
    }

    public HttpUtils getHttpUtils() {
        return httpUtils;
    }
}
