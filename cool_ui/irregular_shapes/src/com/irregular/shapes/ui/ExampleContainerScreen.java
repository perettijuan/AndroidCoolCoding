package com.irregular.shapes.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.irregular.shapes.R;

public class ExampleContainerScreen extends Activity {


    public static enum Shape {
        ROUNDED_CORNERS,
        CHAT_BUBBLE,
        DRAW_OVAL,
        DRAW_OVAL_ROTATED,
        HEART;
    }

    public static final String KEY_TECHNIQUE = "use_technique_key";

    private ImageView ivImageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_container_screen);
        ivImageContainer = (ImageView) findViewById(R.id.iv_image_container);
        Shape technique = (Shape) getIntent().getSerializableExtra(KEY_TECHNIQUE);
        processImageUsingTechnique(technique);
        setTitle(technique.toString());
    }

    private void processImageUsingTechnique(Shape technique) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.betty);
        Bitmap processed = null;
        switch (technique) {
        case ROUNDED_CORNERS:
            processed = ImageProcessor.getRoundedCornersImage(bmp);
            break;
        case CHAT_BUBBLE:
            processed = ImageProcessor.createChatBubble(bmp);
            break;
        case DRAW_OVAL:
            processed = ImageProcessor.drawOval(bmp);
            break;
        case DRAW_OVAL_ROTATED:
            processed = ImageProcessor.drawOvalRotated(bmp);
            break;
        case HEART:
            processed = ImageProcessor.drawHeart(bmp);
            break;
        }
        if (processed != null) {
            ivImageContainer.setImageBitmap(processed);
        }
        bmp.recycle();
    }
}
