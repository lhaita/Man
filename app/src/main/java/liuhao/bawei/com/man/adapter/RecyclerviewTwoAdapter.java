package liuhao.bawei.com.man.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.bean.LieBiaoBean;
import liuhao.bawei.com.man.bean.XiangQingBean;
import liuhao.bawei.com.man.untils.JIEkou;

/**
 * Created by 15218 on 2017/9/7.
 */
public class RecyclerviewTwoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context ;
    private List<XiangQingBean.DatasBean.GoodsCommendListBean> listBeen = new ArrayList<>();
    private OnItemClickLisen onItemClickLisen ;
    public void setOnItemClickLisen(OnItemClickLisen onItemClickLisen){
           this.onItemClickLisen = onItemClickLisen ;
    }
    public interface OnItemClickLisen{
        public void OnItemClick(int position);
    };

    public RecyclerviewTwoAdapter(Context context, List<XiangQingBean.DatasBean.GoodsCommendListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewItemHolder myViewItemHolder =
                new MyViewItemHolder(LayoutInflater.from(context).inflate(R.layout.recyclertwo,parent,false));
        return myViewItemHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                     if(holder instanceof  MyViewItemHolder){

                         MyViewItemHolder myViewItemHolder = (MyViewItemHolder) holder;
                         String goods_image_url = listBeen.get(position).getGoods_image_url();
                         String[] split = goods_image_url.split(JIEkou.yip);
                         StringBuffer sb = new StringBuffer();
                         sb.append(JIEkou.ip);
                         for (String a : split){
                             sb.append(a);
                         }
                         ImageLoader.getInstance().displayImage(sb.toString(),myViewItemHolder.i);
                         myViewItemHolder.name.setText(listBeen.get(position).getGoods_name());
                         myViewItemHolder.money.setText("￥"+listBeen.get(position).getGoods_promotion_price());
                         myViewItemHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                  if(onItemClickLisen != null){
                                      onItemClickLisen.OnItemClick(position);
                                  }
                             }
                         });
                     }
    }

    @Override
    public int getItemCount() {
        return listBeen.size();
    }

    class MyViewItemHolder extends RecyclerView.ViewHolder{

        private  ImageView i;
        private  TextView name;
        private  TextView money;
        private RelativeLayout linearLayout;

        public MyViewItemHolder(View itemView) {
            super(itemView);

            i =   itemView.findViewById(R.id.recyclerimg);
            name = itemView.findViewById(R.id.recyclername);
            money = itemView.findViewById(R.id.recyclermoney);
            linearLayout = itemView.findViewById(R.id.recyclerll);

        }
    }

}
