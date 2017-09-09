package liuhao.bawei.com.man.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import liuhao.bawei.com.man.R;

/**
 * Created by 15218 on 2017/8/31.
 */


public class UserFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(),R.layout.userfragment,null);

        return view;

    }
}
