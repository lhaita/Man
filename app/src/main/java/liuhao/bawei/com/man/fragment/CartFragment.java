package liuhao.bawei.com.man.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.adapter.GouWuCheAdapter;
import liuhao.bawei.com.man.app.Myapp;
import liuhao.bawei.com.man.bean.User;
import liuhao.bawei.com.man.bean.UserDao;

/**
 * Created by 15218 on 2017/8/31.
 */


public class CartFragment extends Fragment {


    private RecyclerView recycle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(),R.layout.cartfragment,null);

        recycle = view.findViewById(R.id.recycle);
        Myapp myapp = (Myapp) getActivity().getApplication();
        UserDao db = myapp.getDb();
        List<User> users = db.loadAll();
        GouWuCheAdapter adapter = new GouWuCheAdapter(getActivity(),users);
        adapter.notifyDataSetChanged();
        recycle.setAdapter(adapter);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recycle.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        return view;

    }
}
