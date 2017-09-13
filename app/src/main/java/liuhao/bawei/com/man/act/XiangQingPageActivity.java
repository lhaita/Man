package liuhao.bawei.com.man.act;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import android.widget.TextView;
import android.widget.Toast;


import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.adapter.MyPhotoFragment;
import liuhao.bawei.com.man.adapter.RecyclerviewTwoAdapter;
import liuhao.bawei.com.man.bean.BaseBean;
import liuhao.bawei.com.man.bean.XiangQingBean;
import liuhao.bawei.com.man.fragment.Photofragment;
import liuhao.bawei.com.man.net.HttpXutils;
import liuhao.bawei.com.man.net.netClickListenr;
import liuhao.bawei.com.man.untils.JIEkou;

public class XiangQingPageActivity extends AppCompatActivity {

    private HttpXutils httpXutils = HttpXutils.getHttpXutils(this);

    private TextView tvllsname;
    private TextView tvllsgj;
    private TextView tvllsmoney;
    private TextView tvxiaoliang;
    private ImageView image;
    private String url;
    private ViewPager vip;
    private List<Fragment>    frags  = new ArrayList<>();
    private RelativeLayout relativeLayout;
    private RecyclerView rec;
    private LinearLayout linearLayout;
    private LinearLayout ll;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing_page);

        Intent intent = getIntent();
        String gu_id = intent.getStringExtra("gu_id");
        url = JIEkou.xq + "&goods_id=" + gu_id;

        initview();

        loadData();




    }

    private void loadData() {

        httpXutils.get(url, XiangQingBean.class, new netClickListenr() {

            private MyPhotoFragment m;

            @Override
            public void onsuccess(BaseBean baseBean) {
                XiangQingBean baseBean1 = (XiangQingBean) baseBean;
                final  XiangQingBean.DatasBean.GoodsInfoBean goods_info = baseBean1.getDatas().getGoods_info();
                tvllsname.setText(goods_info.getGoods_name());
                tvllsgj.setText(goods_info.getGoods_jingle());
                tvllsmoney.setText("￥"+goods_info.getGoods_price());
                tvxiaoliang.setText("销量:" + goods_info.getGoods_salenum() + "件");
                //初始化viewpager的数据
                final   List<String> spec_image = baseBean1.getDatas().getSpec_image();
//                lists.addAll(spec_image);

                for (int i = 0 ; i < spec_image.size() ; i ++){

                    Photofragment photo = new Photofragment();
                    Bundle b = new Bundle();
                    b.putString("url",spec_image.get(i));
                    photo.setArguments(b);
                    frags.add(photo);

                }

                m = new MyPhotoFragment(getSupportFragmentManager(),XiangQingPageActivity.this,frags);
                m.notifyDataSetChanged();
                vip.setAdapter(m);
                final List<XiangQingBean.DatasBean.GoodsCommendListBean> goods_commend_list = baseBean1.getDatas().getGoods_commend_list();
                rec.setLayoutManager(new LinearLayoutManager(XiangQingPageActivity.this,LinearLayoutManager.VERTICAL,false));
                rec.addItemDecoration(new DividerItemDecoration(XiangQingPageActivity.this,DividerItemDecoration.VERTICAL));
                RecyclerviewTwoAdapter twoAdapter = new RecyclerviewTwoAdapter(XiangQingPageActivity.this,goods_commend_list);
                rec.setAdapter(twoAdapter);
                twoAdapter.setOnItemClickLisen(new RecyclerviewTwoAdapter.OnItemClickLisen() {
                    @Override
                    public void OnItemClick(int position) {


                        Intent intent = new Intent(XiangQingPageActivity.this,XiangQingPageActivity.class);
                        intent.putExtra("gu_id",goods_commend_list.get(position).getGoods_id());
                        startActivity(intent);
                        overridePendingTransition(R.anim.right, R.anim.left);

                    }
                });
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        initPop(goods_info,spec_image);
                    }
                });
                ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        initPop(goods_info,spec_image);
                    }
                });
            }
        });

    }
    //初始化PopWindow
    public void initPop(XiangQingBean.DatasBean.GoodsInfoBean goods_info,List<String> spec_image){
        View view1 = View.inflate(XiangQingPageActivity.this,R.layout.popwindow_layout,null);
        PopupWindow window = new PopupWindow(view1,
                WindowManager.LayoutParams.MATCH_PARENT,
                350);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);
        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(view1,
                Gravity.BOTTOM, 0, 0);


        ImageView image = view1.findViewById(R.id.recyclerimg);
        TextView tename = view1.findViewById(R.id.recyclername);
        TextView temoney = view1.findViewById(R.id.recyclermoney);
        TextView num = view1.findViewById(R.id.recyclersalenum);
        final Button jia = view1.findViewById(R.id.jia);
        final Button jian = view1.findViewById(R.id.jian);
        final EditText jisuan = view1.findViewById(R.id.jisuan);
        Button btngoumai = view1.findViewById(R.id.shopcar);
        //点击将该页面的详情信息保存数据库
        btngoumai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num =  jisuan.getText().toString();

                int integer = Integer.valueOf(num);
                if(integer>=100){
                    //吐司事件
                    Toast.makeText(XiangQingPageActivity.this, "购买数量不能大于100件!",Toast.LENGTH_SHORT).show();
                    jisuan.setText("100");

                }{
                    integer= integer+1;
                    jisuan.setText(integer+"");
                }

            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num =  jisuan.getText().toString();
                if(num!=null&!num.equals("")){

                    int integer = Integer.valueOf(num);
                    integer = integer-1;
                    jisuan.setText(integer+"");
                    if(integer<=1){
                        //吐司事件
                        Toast.makeText(XiangQingPageActivity.this, "没有了!",Toast.LENGTH_SHORT).show();
                        jisuan.setText("1");

                    }
                }else {

                    //吐司事件
                    Toast.makeText(XiangQingPageActivity.this, "输入不能为空",Toast.LENGTH_SHORT).show();

                }

            }
        });
        num.setText("库存 : "+goods_info.getGoods_storage());
        temoney.setText("￥"+goods_info.getGoods_price());
        tename.setText(goods_info.getGoods_name());
        String s = spec_image.get(0);
        String[] split = s.split(JIEkou.yip);
        StringBuffer sb = new StringBuffer();
        sb.append(JIEkou.ip);
        for (String b : split){
            sb.append(b);
        }
        ImageLoader.getInstance().displayImage(sb.toString(),image);


    }
    //初始化UI
    private void initview() {

        tvllsname = (TextView) findViewById(R.id.tvllsname);
        tvllsgj = (TextView) findViewById(R.id.tvllsgj);
        tvllsmoney = (TextView) findViewById(R.id.tvllsmoney);
        tvxiaoliang = (TextView) findViewById(R.id.tvxiaoliang);
        image = (ImageView) findViewById(R.id.normal_code);
        relativeLayout = (RelativeLayout) findViewById(R.id.rlrl);
        rec = (RecyclerView) findViewById(R.id.lv);
        vip = (ViewPager) findViewById(R.id.vp);
        linearLayout = (LinearLayout) findViewById(R.id.linadd);
        ll = (LinearLayout) findViewById(R.id.linlijigoumai);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(XiangQingPageActivity.this,XiangQingLieBiaoMainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent i = new Intent(XiangQingPageActivity.this,WebMainActivity.class);
                startActivity(i);
            }
        });




    }
}
