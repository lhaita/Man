package liuhao.bawei.com.man.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.adapter.ClassLVadapter;
import liuhao.bawei.com.man.adapter.ExpandableListViewAdapter;
import liuhao.bawei.com.man.app.Myapp;
import liuhao.bawei.com.man.bean.BaseBean;
import liuhao.bawei.com.man.bean.EXLVgroupbean;
import liuhao.bawei.com.man.bean.ExlvChildbean;
import liuhao.bawei.com.man.bean.FenLeiBean;
import liuhao.bawei.com.man.net.HttpUtils;
import liuhao.bawei.com.man.net.HttpXutils;
import liuhao.bawei.com.man.net.netClickListenr;
import liuhao.bawei.com.man.untils.JIEkou;

/**
 * Created by 15218 on 2017/8/31.
 */


public class ClassFragment extends Fragment {
    ExpandableListView mExpandableListView;
    ExpandableListViewAdapter mExpandableListViewAdapter;
    private static final String TAG = "Main";
    private Myapp myapp;
    private HttpUtils httpUtils;
    private ListView lv;
    private ClassLVadapter lVadapter ;
    private ExpandableListView exlv;
    private String url = JIEkou.fenlei;
    private HttpXutils httpXutils = HttpXutils.getHttpXutils(getActivity());
    private List<FenLeiBean.DatasBean.ClassListBean> class_list;
    List<EXLVgroupbean.DatasBean.ClassListBean> group_list = new ArrayList<EXLVgroupbean.DatasBean.ClassListBean>();
    List<FenLeiBean.DatasBean.ClassListBean> fenLei_list = new ArrayList<FenLeiBean.DatasBean.ClassListBean>();
    List<List<ExlvChildbean.DatasBean.ClassListBean>> child_list = new ArrayList<List<ExlvChildbean.DatasBean.ClassListBean>>();
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.classfragment,null);

        initview(view);

        loadData();



        return view;

    }

//    private void initadapter() {
//
//        //log测试输出
//        mExpandableListViewAdapter = new ExpandableListViewAdapter(getActivity(),group_list,child_list);
//        mExpandableListView.setAdapter(mExpandableListViewAdapter);
//
////        mExpandableListView.expandGroup(0);   //默认打开第一条item
//
//        //设置父item的点击事件
//        mExpandableListView
//                .setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//                    @Override
//                    public boolean onGroupClick(ExpandableListView parent,
//                                                View v, int groupPosition, long id) {
//                        return false;
//                    }
//                });
//
//        //设置子item的点击事件
//        mExpandableListView
//                .setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//                    @Override
//                    public boolean onChildClick(ExpandableListView parent,
//                                                View v, int groupPosition, int childPosition,
//                                                long id) {
//                        Log.e(TAG, "groupPosition=" + groupPosition
//                                + ",childPosition=" + childPosition);
//                        return false;
//                    }
//                });
//
//    }



    private void loadData() {

            httpXutils.get(url, FenLeiBean.class, new netClickListenr() {


                @Override
                public void onsuccess(BaseBean baseBean) {
                    FenLeiBean  fenLeiBean= (FenLeiBean) baseBean;
                    List<FenLeiBean.DatasBean.ClassListBean>  baseBean1    =   fenLeiBean.getDatas().getClass_list();
                    fenLei_list.addAll(baseBean1);

                    lVadapter = new ClassLVadapter(getActivity(),fenLei_list,R.layout.class_listview_layout);

                    lv.setAdapter(lVadapter);

                    int index = lVadapter.index;

                    EXLVFragment exlvFragment = new EXLVFragment();
                    Bundle b = new Bundle();
                    b.putSerializable("key", (Serializable) fenLei_list);
                    b.putInt("position",index);

                    exlvFragment.setArguments(b);

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag, exlvFragment).commit();

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, final View view, final int position, final long l) {
                           EXLVFragment exlvFragment = new EXLVFragment();
                            Bundle b = new Bundle();
                            b.putSerializable("key", (Serializable) fenLei_list);
                            b.putInt("position",position);
                            exlvFragment.setArguments(b);

                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag, exlvFragment).commit();
//                            List<String> gc_id_list  = new ArrayList<>() ;
//                            for (int i = 0 ; i < fenLei_list.size() ; i++){
//                                gc_id_list.add(url + "&gc_id=" + fenLei_list.get(i).getGc_id());
//                            }
//                            httpXutils.get(gc_id_list.get(position), EXLVgroupbean.class, new netClickListenr() {
//                                @Override
//                                public void onsuccess(BaseBean baseBean) {
//                                    EXLVgroupbean baseBean1 = (EXLVgroupbean) baseBean;
//
//                                    List<EXLVgroupbean.DatasBean.ClassListBean> baseBean2 = baseBean1.getDatas().getClass_list();
//                                    //group元素
//
//                                    group_list.addAll(baseBean2);
//
//
//
//                                     List<String> list = new ArrayList<String>();
//                                    for (int j = 0 ; j < group_list.size() ; j++){
////                                        Log.d("qq",group_list.get(j).getGc_name());
//                                        list.add(url + "&gc_id=" + fenLei_list.get(j).getGc_id()+ "&gc_id=" + group_list.get(j).getGc_id());
//
//                                    }
//                                    for (String str : list){
//
//                                        httpXutils.get(str, ExlvChildbean.class, new netClickListenr() {
//                                            @Override
//                                            public void onsuccess(BaseBean baseBean) {
//
//                                                ExlvChildbean baseBean2 = (ExlvChildbean) baseBean;
//                                                List<ExlvChildbean.DatasBean.ClassListBean> class_list = baseBean2.getDatas().getClass_list();
//
//                                                    List<List<ExlvChildbean.DatasBean.ClassListBean>> childlist = new ArrayList<List<ExlvChildbean.DatasBean.ClassListBean>>();
//                                                    childlist.add(class_list);
//                                                    child_list.addAll(childlist);
//
//
//                                                initadapter();
//
//                                            }
//                                        });
//
//                                    }
//
//
//                                }
//                            });
                        }
                    });
                }
            });


    }


    private void initview(View v) {

        lv = (ListView) v.findViewById(R.id.lv);
        mExpandableListView = (ExpandableListView) v.findViewById(R.id.expandableListView);
//        exlv = (ExpandableListView)  v.findViewById(R.id.exlv);
        myapp = (Myapp) getActivity().getApplication();
        httpUtils = myapp.getHttpUtils();

    }
}
