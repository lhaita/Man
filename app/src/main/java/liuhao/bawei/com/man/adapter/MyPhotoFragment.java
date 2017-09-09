package liuhao.bawei.com.man.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15218 on 2017/9/8.
 */
public class MyPhotoFragment extends FragmentPagerAdapter {

    private Context context ;
    private List<Fragment> fragments = new ArrayList<>();



    public MyPhotoFragment(FragmentManager fm,Context context,List<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragments =  fragments ;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


}
