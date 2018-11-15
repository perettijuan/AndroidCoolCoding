package com.irregular.shapes.ui;

import com.irregular.shapes.R;
import com.irregular.shapes.ui.ExampleContainerScreen.Shape;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeScreen extends Activity implements OnClickListener {


    private Button btnUsingBitmapShader;
    private Button btnChatBubble;
    private Button btnDrawOval;
    private Button btnDrawOvalRotated;
    private Button btnHeart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        btnUsingBitmapShader = (Button) findViewById(R.id.btn_rounded_corners);
        btnUsingBitmapShader.setOnClickListener(this);
        btnChatBubble = (Button) findViewById(R.id.btn_chat_bubble);
        btnChatBubble.setOnClickListener(this);
        btnDrawOval = (Button) findViewById(R.id.btn_draw_oval);
        btnDrawOval.setOnClickListener(this);
        btnDrawOvalRotated = (Button) findViewById(R.id.btn_draw_oval_rotated);
        btnDrawOvalRotated.setOnClickListener(this);
        btnHeart = (Button) findViewById(R.id.btn_draw_heart);
        btnHeart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Shape technique = null;
        switch (id) {
        case R.id.btn_rounded_corners:
            technique = Shape.ROUNDED_CORNERS;
            break;
        case R.id.btn_chat_bubble:
            technique = Shape.CHAT_BUBBLE;
            break;
        case R.id.btn_draw_oval:
            technique = Shape.DRAW_OVAL;
            break;
        case R.id.btn_draw_oval_rotated:
            technique = Shape.DRAW_OVAL_ROTATED;
            break;
        case R.id.btn_draw_heart:
            technique = Shape.HEART;
            break;
        }

        Intent intent = new Intent(this, ExampleContainerScreen.class);
        intent.putExtra(ExampleContainerScreen.KEY_TECHNIQUE, technique);
        startActivity(intent);
    }

}
