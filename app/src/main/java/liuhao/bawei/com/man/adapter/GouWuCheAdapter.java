package liuhao.bawei.com.man.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.bean.User;

/**
 * Created by 15218 on 2017/9/13.
 */
public class GouWuCheAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context ;
    private List<User> users ;

    public GouWuCheAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context,R.layout.gouwuche_layout,null);
        MyItemViewHolder1 myItemViewHolder = new MyItemViewHolder1(v);
        return myItemViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof  MyItemViewHolder1){
            ((MyItemViewHolder1) holder).textname.setText(users.get(position).getName());
            final float f = Float.parseFloat(users.get(position).getPrice());
            int i = Integer.parseInt(users.get(position).getNum());
            float ji = i * f;
            float fff = Math.round(ji*10)/10;
            ((MyItemViewHolder1) holder).textprice.setText("￥"+users.get(position).getPrice());
            ((MyItemViewHolder1) holder).textnum.setText("×"+users.get(position).getNum());
            ((MyItemViewHolder1) holder).shopname.setText(users.get(position).getShopname());
            ((MyItemViewHolder1) holder).zongjie.setText("共"+i+"件商品,合计"+fff+"元");
            ImageLoader.getInstance().displayImage(users.get(position).getImage_url(),((MyItemViewHolder1) holder).image);
            ((MyItemViewHolder1) holder).jisuan.setText(users.get(position).getNum());
            ((MyItemViewHolder1) holder).jia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String num =  ((MyItemViewHolder1) holder).jisuan.getText().toString();

                    int integer = Integer.valueOf(num);
                    if(integer>=999){
                        //吐司事件
                        Toast.makeText(context, "购买数量不能大于999件!",Toast.LENGTH_SHORT).show();
                        ((MyItemViewHolder1) holder).jisuan.setText("999");
                    }{
                        integer= integer+1;
                        ((MyItemViewHolder1) holder).jisuan.setText(integer+"");
                        ((MyItemViewHolder1) holder).textnum.setText("×"+integer);
                        float v = integer * f;
                        float fff = Math.round(v*10)/10;
                        ((MyItemViewHolder1) holder).zongjie.setText("共"+integer+"件商品,合计"+fff+"元");
                    }

                }
            });
            ((MyItemViewHolder1) holder).jian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String num =  ((MyItemViewHolder1) holder).jisuan.getText().toString();
                    if(num!=null&!num.equals("")){
                        int integer = Integer.valueOf(num);
                        integer = integer-1;
                        if(integer<=0){
                            integer = integer+1;
                            ((MyItemViewHolder1) holder).jisuan.setText("1");
                        }
                        ((MyItemViewHolder1) holder).jisuan.setText(integer+"");
                        ((MyItemViewHolder1) holder).textnum.setText("×"+integer);
                        float v = integer * f;
                        float fff = Math.round(v*10)/10;
                        ((MyItemViewHolder1) holder).zongjie.setText("共"+integer+"件商品,合计"+fff+"元");

                    }else {
                        //吐司事件
                        Toast.makeText(context, "输入不能为空",Toast.LENGTH_SHORT).show();
                    }

                }
            });
            ((MyItemViewHolder1) holder).shopdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);



                    builder.create().show();
                     users.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    class MyItemViewHolder1 extends RecyclerView.ViewHolder{

        private  ImageView image;
        private  TextView textname;
        private  TextView textprice;
        private  TextView textnum;
        private  TextView shopname;
        private  TextView zongjie;
        private  Button jia;
        private  Button jian;
        private  EditText jisuan;
        private final Button shopdelete;

        public MyItemViewHolder1(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.recyclerimg);
            textname = itemView.findViewById(R.id.recyclername);
            textprice = itemView.findViewById(R.id.recyclermoney);
            textnum = itemView.findViewById(R.id.recyclersalenum);
            shopname = itemView.findViewById(R.id.shopname);
            zongjie = itemView.findViewById(R.id.zongjie);
            jia = itemView.findViewById(R.id.jia);
            jian= itemView.findViewById(R.id.jian);
            jisuan = itemView.findViewById(R.id.jisuan);
            shopdelete = itemView.findViewById(R.id.shopdelete);
        }
    }
}
