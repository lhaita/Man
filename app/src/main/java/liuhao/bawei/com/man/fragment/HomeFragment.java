package liuhao.bawei.com.man.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.act.SearchActivity;

/**
 * Created by 15218 on 2017/8/31.
 */


public class HomeFragment extends Fragment {


    private Button button_search;
    private ImageView normal_code;
    private ImageView normal_message;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(),R.layout.homefragment,null);

         initview(view);

         setsearchOclick();



        return view;

    }
     //为按钮设置监听事件
    private void setsearchOclick() {

        normal_code.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                //switch判断
                switch (motionEvent.getAction()) {
                		case MotionEvent.ACTION_DOWN:
                            normal_code.setBackgroundResource(R.color.color_bg);
                			break;
                        case MotionEvent.ACTION_UP :
                            normal_code.setBackgroundResource(R.color.color_bgchage);
                		}
                return true;
            }
        });



        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), SearchActivity.class);
                startActivity(i);
//                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.right, R.anim.left);
            }
        });


    }

    /**
     * 初始化控件
     */
    private void initview(View v) {

        button_search = (Button) v.findViewById(R.id.button_search);
        normal_code = (ImageView) v.findViewById(R.id.normal_code);
        normal_message = (ImageView) v.findViewById(R.id.normal_message);
    }
}
