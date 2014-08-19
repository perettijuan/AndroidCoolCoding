package com.irregular.shapes.ui;

import com.irregular.shapes.R;
import com.irregular.shapes.ui.ExampleContainerScreen.ShapeTechnique;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeScreen extends Activity implements OnClickListener {


    private Button btnUsingBitmapShader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        btnUsingBitmapShader = (Button) findViewById(R.id.btn_bitmap_shader);
        btnUsingBitmapShader.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        ShapeTechnique technique = null;
        switch (id) {
        case R.id.btn_bitmap_shader:
            technique = ShapeTechnique.BITMAP_SHADER;
            break;
        }

        Intent intent = new Intent(this, ExampleContainerScreen.class);
        intent.putExtra(ExampleContainerScreen.KEY_TECHNIQUE, technique);
        startActivity(intent);
    }

}
