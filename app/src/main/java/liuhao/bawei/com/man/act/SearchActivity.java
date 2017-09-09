package liuhao.bawei.com.man.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import liuhao.bawei.com.man.R;
import liuhao.bawei.com.man.fragment.HomeFragment;

public class SearchActivity extends AppCompatActivity {

    private ImageView image_normal_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        image_normal_code = (ImageView) findViewById(R.id.normal_code);

        image_normal_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = getIntent();

                finish();
                overridePendingTransition(R.anim.leftone, R.anim.rightone);

            }
        });
    }
}
