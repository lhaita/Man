package liuhao.bawei.com.man.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.adapter.ExpandableListViewAdapter;
import liuhao.bawei.com.man.bean.BaseBean;
import liuhao.bawei.com.man.bean.EXLVgroupbean;
import liuhao.bawei.com.man.bean.ExlvChildbean;
import liuhao.bawei.com.man.bean.FenLeiBean;
import liuhao.bawei.com.man.net.HttpXutils;
import liuhao.bawei.com.man.net.netClickListenr;
import liuhao.bawei.com.man.untils.JIEkou;

/**
 * Created by 15218 on 2017/8/31.
 */


public class EXLVFragment extends Fragment {
    private HttpXutils httpXutils = HttpXutils.getHttpXutils(getActivity());
    private ExpandableListViewAdapter mExpandableListViewAdapter ;
    private List<List<ExlvChildbean.DatasBean.ClassListBean>> child_list = new ArrayList<List<ExlvChildbean.DatasBean.ClassListBean>>();
    private String url = JIEkou.fenlei;
    private  List<EXLVgroupbean.DatasBean.ClassListBean> group_list = new ArrayList<EXLVgroupbean.DatasBean.ClassListBean>();
    private ExpandableListView mExpandableListView;
    List<List<ExlvChildbean.DatasBean.ClassListBean>> childlist = new ArrayList<List<ExlvChildbean.DatasBean.ClassListBean>>();

    //保存group的url集合
    private List<String> gc_id_list  = new ArrayList<>() ;
    private List<String> list = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(),R.layout.exlvfragment,null);
        mExpandableListView = view.findViewById(R.id.expandableListView);
        Bundle arguments = getArguments();
        int position = arguments.getInt("position");
        //log测试输出
                Log.d("qq","传过来的值: position    " + position);
        List<FenLeiBean.DatasBean.ClassListBean> fenLei_list  = (List<FenLeiBean.DatasBean.ClassListBean>) arguments.getSerializable("key");
        //log测试输出
                Log.d("qq","传过来的集合 值:"  + fenLei_list.toString());

        loadData(position,fenLei_list);

        return view;

    }

    private void loadData(final int position, List<FenLeiBean.DatasBean.ClassListBean> fenLei_list) {


        //拼接group的url
        for (int i = 0; i < fenLei_list.size(); i++) {
            gc_id_list.add(url + "&gc_id=" + fenLei_list.get(i).getGc_id());
        }
        //执行网络请求group的数据
//log测试输出
//        Log.d("qq","保存group的url集合 : "  + gc_id_list.toString() + "             "
//           +  gc_id_list.get(position));


            httpXutils.get(gc_id_list.get(position), EXLVgroupbean.class, new netClickListenr() {

                @Override
                public void onsuccess(BaseBean baseBean) {
                    EXLVgroupbean baseBean1 = (EXLVgroupbean) baseBean;

                    List<EXLVgroupbean.DatasBean.ClassListBean> baseBean2 = baseBean1.getDatas().getClass_list();
                    //group元素
                    group_list.addAll(baseBean2);


                    List<String> list = new ArrayList<String>();
                    for (int j = 0; j < group_list.size(); j++) {
//                                        Log.d("qq",group_list.get(j).getGc_name());
                        list.add(gc_id_list.get(j) + "&gc_id=" + group_list.get(j).getGc_id());

                    }

                    for (String str : list) {
                        httpXutils.get(str, ExlvChildbean.class, new netClickListenr() {
                            @Override
                            public void onsuccess(BaseBean baseBean) {

                                ExlvChildbean baseBean2 = (ExlvChildbean) baseBean;
                                List<ExlvChildbean.DatasBean.ClassListBean> class_list = baseBean2.getDatas().getClass_list();

                                childlist.add(class_list);

                                initadapter();
//log测试输出
                                Log.d("qq", "传过来的集合 值:   +1+1+1" + childlist.toString());

                            }
                        });

                    }
                }
            });

        }

    private void initadapter() {

        //log测试输出
        mExpandableListViewAdapter = new ExpandableListViewAdapter(getActivity(),group_list,childlist);
        mExpandableListView.setAdapter(mExpandableListViewAdapter);

//        int count = mExpandableListView.getCount();
//        for (int i = 0; i < count; i++) {
//            mExpandableListView.expandGroup(i);
//        }


        //设置父item的点击事件
        mExpandableListView
                .setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent,
                                                View v, int groupPosition, long id) {
                        return false;
                    }
                });

        //设置子item的点击事件
        mExpandableListView
                .setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent,
                                                View v, int groupPosition, int childPosition,
                                                long id) {

//                        Intent intent = new Intent();

                        return false;
                    }
                });

    }

}
