package liuhao.bawei.com.man.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.adapter.RecyclerviewAdapter;
import liuhao.bawei.com.man.bean.BaseBean;
import liuhao.bawei.com.man.bean.LieBiaoBean;
import liuhao.bawei.com.man.net.HttpXutils;
import liuhao.bawei.com.man.net.netClickListenr;
import liuhao.bawei.com.man.untils.JIEkou;

public class XiangQingLieBiaoMainActivity extends AppCompatActivity {

    private ImageView image;
    private boolean flag  = true ;
    private RecyclerView recyclerView;
    private HttpXutils httpXutils;
    private String liebiaourl;
    private RecyclerviewAdapter adapter ;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing_main);

        initview();
        loadData();

    }

    private void loadData() {

          httpXutils.get(liebiaourl, LieBiaoBean.class, new netClickListenr() {
              @Override
              public void onsuccess(BaseBean baseBean) {
                  LieBiaoBean baseBean1 = (LieBiaoBean) baseBean;

                  final List<LieBiaoBean.DatasBean.GoodsListBean> goods_list = baseBean1.getDatas().getGoods_list();

                  adapter = new RecyclerviewAdapter(XiangQingLieBiaoMainActivity.this,goods_list);

                  recyclerView.setAdapter(adapter);

                  adapter.setOnItemClickLisen(new RecyclerviewAdapter.OnItemClickLisen() {
                      @Override
                      public void OnItemClick(int position) {


                          Intent intent = new Intent(XiangQingLieBiaoMainActivity.this,XiangQingPageActivity.class);
                          intent.putExtra("gu_id",goods_list.get(position).getGoods_id());
                          startActivity(intent);
                          overridePendingTransition(R.anim.right, R.anim.left);

                      }
                  });

              }
          });

    }

    private void initview() {

        image = (ImageView) findViewById(R.id.qiehuan);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerone);
        imageView = (ImageView) findViewById(R.id.huichuan);
        httpXutils = HttpXutils.getHttpXutils(this);
        liebiaourl = JIEkou.liebiao;
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    image.setBackgroundResource(R.mipmap.ic_goods_list_ver);
                    flag = false ;
                }else {
                    image.setBackgroundResource(R.mipmap.ic_goods_list_hor);
                    flag = true ;
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });
    }
}
