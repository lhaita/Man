package liuhao.bawei.com.man.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liuhao.bawei.com.man.R;

public class GuideActivity extends AppCompatActivity {

    @ViewInject(R.id.tv_guide)
    private TextView tvguide ;
    private int count = 3;
    //创建handler对象并且实现其方法
        private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            if(count==0){
                Intent i = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
            tvguide.setText(count + "秒");
            count--;
            sendEmptyMessageDelayed(0,1000);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        x.view().inject(this);
        handler.sendEmptyMessage(0);


    }
}
