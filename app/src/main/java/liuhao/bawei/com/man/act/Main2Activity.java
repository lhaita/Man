package liuhao.bawei.com.man.act;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.untils.JIEkou;

public class Main2Activity extends AppCompatActivity {

    private ViewPager vip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String qq = intent.getStringExtra("qq");
        String yip = JIEkou.yip;
        String[] split = qq.split(yip);
        StringBuffer sb = new StringBuffer();
        sb.append(JIEkou.ip);
        for (String str: split) {
            sb.append(str);
        }
        vip = (ViewPager) findViewById(R.id.vip);



    }
}
