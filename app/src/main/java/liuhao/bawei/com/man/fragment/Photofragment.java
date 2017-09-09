package liuhao.bawei.com.man.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.act.Main2Activity;
import liuhao.bawei.com.man.untils.JIEkou;

/**
 * Created by 15218 on 2017/9/8.
 */
public class Photofragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = View.inflate(getActivity(), R.layout.viewone,null);
        Bundle arguments = getArguments();
        final  String url = arguments.getString("url");
        String yip = JIEkou.yip;
        String[] split = url.split(yip);
        StringBuffer sb = new StringBuffer();
        sb.append(JIEkou.ip);
        for (String str: split) {
            sb.append(str);
        }
        ImageView im = (ImageView)  v.findViewById(R.id.viewimage);
        ImageLoader.getInstance().displayImage(sb.toString(), im);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Main2Activity.class);
                i.putExtra("qq",url);
                startActivity(i);
            }
        });
        return v;
    }
}
