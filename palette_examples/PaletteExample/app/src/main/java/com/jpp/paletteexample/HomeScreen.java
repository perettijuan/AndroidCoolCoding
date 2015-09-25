package com.jpp.paletteexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;


public class HomeScreen extends ActionBarActivity {

    private ImageView ivPalette;
    private ListView lvPalette;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        ivPalette = (ImageView) findViewById(R.id.iv_palette);
        lvPalette = (ListView) findViewById(R.id.lv_palette);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_pack250_text);
        extractColors(bmp);
        ivPalette.setImageBitmap(bmp);
    }


    private void extractColors(Bitmap bmp) {
        Palette.generateAsync(bmp, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                extractColorsFromPalette(palette);
            }
        });
    }


    private void extractColorsFromPalette(Palette palette) {
        ArrayList<PaletteColor> data = new ArrayList<>();
        int vibrant = palette.getVibrantColor(0x000000);
        PaletteColor color = new PaletteColor("vibrant", vibrant);
        data.add(color);
        int vibrantLight = palette.getLightVibrantColor(0x000000);
        color = new PaletteColor("vibrantLight", vibrantLight);
        data.add(color);
        int vibrantDark = palette.getDarkVibrantColor(0x000000);
        color = new PaletteColor("vibrantDark", vibrantDark);
        data.add(color);
        int muted = palette.getMutedColor(0x000000);
        color = new PaletteColor("muted", muted);
        data.add(color);
        int mutedLight = palette.getLightMutedColor(0x000000);
        color = new PaletteColor("mutedLight", mutedLight);
        data.add(color);
        int mutedDark = palette.getDarkMutedColor(0x000000);
        color = new PaletteColor("mutedDark", mutedDark);
        data.add(color);

        PaletteListAdapter adapter = new PaletteListAdapter(data, getApplicationContext());
        lvPalette.setAdapter(adapter);

    }
}
