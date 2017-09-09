package liuhao.bawei.com.man.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.act.XiangQingLieBiaoMainActivity;
import liuhao.bawei.com.man.bean.EXLVgroupbean;
import liuhao.bawei.com.man.bean.ExlvChildbean;

/**
 * Created by 15218 on 2017/9/5.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
//    public String[] group = { "测试1", "测试2", "测试3", "测试4", "测试5", "测试6", "测试7", "测试8" };
    List<EXLVgroupbean.DatasBean.ClassListBean> exlVgroupbeen = new ArrayList<>();
    List<List<ExlvChildbean.DatasBean.ClassListBean>> exlvchildbean = new ArrayList<>();
//    public String[][] gridViewChild = {
//            { "测试1", "测试1", "测试1", "测试1", "测试1", "测试1", "测试1", "测试1" },
//            { "测试2", "测试2", "测试2", "测试2", "测试2" },
//            { "测试3", "测试3", "测试3", "测试3", "测试3", "测试3", "测试3" },
//            { "测试4", "测试4", "测试4", "测试4", "测试4" },
//            { "测试5", "测试5", "测试5", "测试5", "测试5", "测试5" },
//            { "测试6", "测试6", "测试6", "测试6", "测试6" },
//            { "测试7", "测试7", "测试7", "测试7", "测试7", "测试7" },
//            { "测试8", "测试8" } };
    String[][] child = { { "" }, { "" }, { "" }, { "" }, { "" }, { "" },
            { "" }, { "" } };
    LayoutInflater mInflater;
    Context context;


    public ExpandableListViewAdapter(Context context, List<EXLVgroupbean.DatasBean.ClassListBean> exlVgroupbeen,
                                     List<List<ExlvChildbean.DatasBean.ClassListBean>> exlvchildbean) {
// TODO Auto-generated constructor stub
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.exlVgroupbeen = exlVgroupbeen ;
        this.exlvchildbean = exlvchildbean ;

    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
// TODO Auto-generated method stub
        //log测试输出
                Log.d("qq","返回的子布局的数据:"+ exlvchildbean.get(groupPosition).get(childPosition));
        return exlvchildbean.get(groupPosition).get(childPosition);
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
// TODO Auto-generated method stub
        return childPosition;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(
                    R.layout.channel_expandablelistview_item, null);
            mViewChild.gridView = (GridView) convertView
                    .findViewById(R.id.channel_item_child_gridView);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }


        SimpleAdapter mSimpleAdapter = new SimpleAdapter(context,
                setGridViewData(exlvchildbean.get(groupPosition)),
                R.layout.channel_gridview_item,
                new String[] { "channel_gridview_item" },
                new int[] { R.id.channel_gridview_item });
        mViewChild.gridView.setAdapter(mSimpleAdapter);
        setGridViewListener(mViewChild.gridView);
        mViewChild.gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

        return convertView;
    }


    /**
     * 设置gridview点击事件监听
     *
     * @param gridView
     */
    private void setGridViewListener(final GridView gridView) {
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
// TODO Auto-generated method stub
                if (view instanceof TextView) {
// 如果想要获取到哪一行，则自定义gridview的adapter，item设置2个textview一个隐藏设置id，显示哪一行
                    TextView tv = (TextView) view;

//                    Toast.makeText(context,
//                            "position=" + position + "||" + tv.getText(),
//                            Toast.LENGTH_SHORT).show();
                    Log.e("hefeng", "gridView listaner position=" + position
                            + "||text=" + tv.getText());
                    if(tv.getText().equals("瑞士品牌")){
                        Intent intent = new Intent(context, XiangQingLieBiaoMainActivity.class);
                        context.startActivity(intent);
                    }else {
//                        Intent intent = new Intent(context, WebMainActivity.class);
//                        context.startActivity(intent);
                    }
                }
            }
        });
    }


    /**
     * 设置gridview数据
     *
     * @param data
     * @return
     */
     ArrayList<HashMap<String, Object>> setGridViewData(List<ExlvChildbean.DatasBean.ClassListBean> data) {
        ArrayList<HashMap<String, Object>> gridItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < data.size(); i++) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("channel_gridview_item", data.get(i).getGc_name() );
            gridItem.add(hashMap);

        }
        return gridItem;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
// TODO Auto-generated method stub
        return 1;
    }


    @Override
    public Object getGroup(int groupPosition) {
// TODO Auto-generated method stub
        return   exlVgroupbeen.get(groupPosition).getGc_name();
//        exlVgroupbeen.get(groupPosition)
    }


    @Override
    public int getGroupCount() {
// TODO Auto-generated method stub
        return exlVgroupbeen.size();
    }


    @Override
    public long getGroupId(int groupPosition) {
// TODO Auto-generated method stub
        return groupPosition;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(
                    R.layout.channel_expandablelistview, null);
            mViewChild.textView = (TextView) convertView
                    .findViewById(R.id.channel_group_name);
            mViewChild.imageView = (ImageView) convertView
                    .findViewById(R.id.channel_imageview_orientation);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }

        mViewChild.textView.setText(getGroup(groupPosition).toString());

        return convertView;
    }


    @Override
    public boolean hasStableIds() {
// TODO Auto-generated method stub
        return true;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
// TODO Auto-generated method stub
        return true;
    }


    ViewChild mViewChild;


    static class ViewChild {
        ImageView imageView;
        TextView textView;
        GridView gridView;
    }
}
