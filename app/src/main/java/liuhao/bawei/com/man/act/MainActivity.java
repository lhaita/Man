package liuhao.bawei.com.man.act;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hjm.bottomtabbar.BottomTabBar;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.fragment.CartFragment;
import liuhao.bawei.com.man.fragment.ClassFragment;
import liuhao.bawei.com.man.fragment.HomeFragment;
import liuhao.bawei.com.man.fragment.UserFragment;

//@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       BottomTabBar tabBar = (BottomTabBar) findViewById(R.id.tabbar);

        tabBar.init(getSupportFragmentManager())
                .addTabItem("首页", R.mipmap.ic_nav_home, HomeFragment.class)
                .addTabItem("分类", R.mipmap.ic_nav_class, ClassFragment.class)
                .addTabItem("购物车", R.mipmap.ic_nav_cart, CartFragment.class)
                .addTabItem("个人", R.mipmap.ic_nav_user, UserFragment.class);

    }
}
