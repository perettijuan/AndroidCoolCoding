package com.irregular.shapes.ui;

import com.irregular.shapes.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class ExampleContainerScreen extends Activity {


    public static enum ShapeTechnique {
        BITMAP_SHADER;
    }

    public static final String KEY_TECHNIQUE = "use_technique_key";

    private ImageView ivImageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_container_screen);
        ivImageContainer = (ImageView) findViewById(R.id.iv_image_container);
        ShapeTechnique technique = (ShapeTechnique) getIntent().getSerializableExtra(KEY_TECHNIQUE);
        processImageUsingTechnique(technique);
    }

    private void processImageUsingTechnique(ShapeTechnique technique) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.doug_avatar_small);
        Bitmap processed = null;
        switch (technique) {
        case BITMAP_SHADER:
            processed = ImageProcessor.processImageUsingBitmapShader(bmp);
            break;
        }
        if (processed != null) {
            ivImageContainer.setImageBitmap(processed);
        }
    }
}
