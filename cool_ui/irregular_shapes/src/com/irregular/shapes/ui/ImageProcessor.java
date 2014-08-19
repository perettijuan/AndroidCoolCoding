package com.irregular.shapes.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class ImageProcessor {
	
	private static final float RADIUS_FACTOR = 5;

	/**
	 * Processes a {@link Bitmap} using a {@link BitmapShader}
	 */
	public static Bitmap processImageUsingBitmapShader(Bitmap bitmap) {
		Bitmap bmp;

		bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
				Bitmap.Config.ARGB_8888);
		BitmapShader shader = new BitmapShader(bitmap,
				BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

		float radius = Math.min(bitmap.getWidth(), bitmap.getHeight())
				/ RADIUS_FACTOR;
		Canvas canvas = new Canvas(bmp);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setShader(shader);

		RectF rect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
		canvas.drawRoundRect(rect, radius, radius, paint);

		return bmp;
	}

}
