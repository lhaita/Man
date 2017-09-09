package liuhao.bawei.com.man.untils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by 15218 on 2017/9/5.
 */
public class CustomGridView extends GridView {
    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
