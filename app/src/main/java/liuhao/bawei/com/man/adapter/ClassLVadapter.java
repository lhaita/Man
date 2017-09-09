package liuhao.bawei.com.man.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.bean.BaseBean;
import liuhao.bawei.com.man.bean.EXLVgroupbean;
import liuhao.bawei.com.man.bean.FenLeiBean;
import liuhao.bawei.com.man.net.HttpXutils;
import liuhao.bawei.com.man.net.netClickListenr;
import liuhao.bawei.com.man.untils.JIEkou;

/**
 * Created by 15218 on 2017/9/2.
 */
public class ClassLVadapter extends BaseAdapter {


     public int index ;
     Context context ;
         List<FenLeiBean.DatasBean.ClassListBean> list ;
         int layout ;


    public ClassLVadapter(Context context, List<FenLeiBean.DatasBean.ClassListBean> list, int layout){

          this.context = context;
          this.list = list;
          this.layout = layout;

      }

         @Override
         public int getCount() {
             return list!=null?list.size():0;
         }

         @Override
         public FenLeiBean.DatasBean.ClassListBean getItem(int i) {
             return list.get(i);
         }

         @Override
         public long getItemId(int i) {
             return i;
         }

         @Override
         public View getView(int i, View view, ViewGroup viewGroup) {

             this.index = i ;
             String yip = JIEkou.yip;
             String ip = JIEkou.ip;
             ViewHolder holder = null;
             if(view==null) {
                 view = View.inflate(context, layout, null);
                 holder = new ViewHolder(view);
                 //查找ID操作

                 view.setTag(holder);
             }else {

                 holder = (ViewHolder) view.getTag();

             }
             //赋值操作
             holder.t.setText(list.get(i).getGc_name());
             String image = list.get(i).getImage();
                 String[] split = image.split(yip);
                 StringBuffer sb = new StringBuffer();
                 sb.append(ip);
                 for (String s : split){
                     sb.append(s);
                 }
                 String str = sb.toString();

                 ImageLoader.getInstance().displayImage(str,holder.i);

             return view;
         }

         class ViewHolder{
          //重复性的控件
             ImageView i ;
             TextView t ;
               public ViewHolder(View v){

                   i = (ImageView) v.findViewById(R.id.class_image);
                   t = (TextView) v.findViewById(R.id.class_tv);

               }
         }
//    public void setMySelection(int index) {
//        this.index = index;
//        notifyDataSetChanged();
//    }
//    public interface getIndex{
//
//         public int index();
//
//    };

}
