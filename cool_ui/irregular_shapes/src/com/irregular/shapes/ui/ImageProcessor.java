package com.irregular.shapes.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;

public class ImageProcessor {


    private static final float RADIUS_FACTOR = 8.0f;
    private static final int TRIANGLE_WIDTH = 120;
    private static final int TRIANGLE_HEIGHT = 100;
    private static final int TRIANGLE_OFFSET = 300;

    /**
     * Processes a {@link Bitmap} using a {@link BitmapShader}
     */
    public static Bitmap getRoundedCornersImage(Bitmap bitmap) {
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

    /**
     * This technique uses the rounded corners image technique, but adding it a
     * triangle using a {@link Path} object.
     */
    public static Bitmap createChatBubble(Bitmap bitmap) {
        Bitmap bmp;

        bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

        float radius = Math.min(bitmap.getWidth(), bitmap.getHeight()) / RADIUS_FACTOR;
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        RectF rect = new RectF(TRIANGLE_WIDTH, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawRoundRect(rect, radius, radius, paint);

        Path triangle = new Path();
        /*
         * use moveTo() to move the current point to the starting position. In
         * this case we want to move to the point of the triangle at the left
         * edge of the Canvas, and down a little from the top. This is the
         * outermost point of the speech bubble triangle
         */
        triangle.moveTo(0, TRIANGLE_OFFSET);
        /*
         * use lineTo() to draw a line from the current position (which we set
         * in the moveTo()) to the right edge of the margin area, sloping
         * upwards
         */
        triangle.lineTo(TRIANGLE_WIDTH, TRIANGLE_OFFSET - (TRIANGLE_HEIGHT / 2));
        /*
         * use a second lineTo() to draw a second line from the current position
         * (the lineTo() drew a line, but did not alter the current position).
         * This time we want the line to slope downwards, in a mirror image of
         * the first line
         */
        triangle.lineTo(TRIANGLE_WIDTH, TRIANGLE_OFFSET + (TRIANGLE_HEIGHT / 2));

        /*
         * we want to draw the final line to be the final edge of the triangle.
         * The obvious way would be to use a moveTo() to move to one of the
         * endpoints of the previous two lines, and then do a lineTo to draw a
         * line to the endpoint of the other line. However, there’s an easier
         * way. If we call close() on the path it will automatically draw a line
         * to close the two open ends of the current path, and this will draw
         * precisely the line that we need
         */
        triangle.close();
        canvas.drawPath(triangle, paint);

        return bmp;
    }

    /**
     * Draws an oval image vertically centered
     */
    public static Bitmap drawOval(Bitmap bitmap) {

        Bitmap bmp;

        bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        float width = bitmap.getWidth();
        float height = bitmap.getHeight();

        Path oval = new Path();
        RectF ovalRect = new RectF(width / 8, 0, width - (width / 8), height);
        oval.addOval(ovalRect, Path.Direction.CW);
        canvas.drawPath(oval, paint);

        return bmp;
    }

    /**
     * Draws an oval image rotated in 30 degrees
     */
    public static Bitmap drawOvalRotated(Bitmap bitmap) {
        Bitmap bmp;

        bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        float width = bitmap.getWidth();
        float height = bitmap.getHeight();

        Path oval = new Path();
        Matrix matrix = new Matrix();
        RectF ovalRect = new RectF(width / 8, 0, width - (width / 8), height);

        oval.addOval(ovalRect, Path.Direction.CW);
        matrix.postRotate(30, width / 2, height / 2);
        oval.transform(matrix, oval);

        canvas.drawPath(oval, paint);

        return bmp;
    }

    /**
     * Draws a heart by combining the previous techniques
     */
    public static Bitmap drawHeart(Bitmap bitmap) {
        Bitmap bmp;

        bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        float width = bitmap.getWidth();
        float height = bitmap.getHeight();

        Path oval = new Path();
        Matrix matrix = new Matrix();
        Region region = new Region();
        RectF ovalRect = new RectF(width / 8, 0, width - (width / 8), height);

        oval.addOval(ovalRect, Path.Direction.CW);
        matrix.postRotate(30, width / 2, height / 2);
        oval.transform(matrix, oval);
        region.setPath(oval, new Region((int) width / 2, 0, (int) width, (int) height));
        canvas.drawPath(region.getBoundaryPath(), paint);

        matrix.reset();
        oval.reset();
        oval.addOval(ovalRect, Path.Direction.CW);
        matrix.postRotate(-30, width / 2, height / 2);
        oval.transform(matrix, oval);
        region.setPath(oval, new Region(0, 0, (int) width / 2, (int) height));
        canvas.drawPath(region.getBoundaryPath(), paint);

        return bmp;
    }

}
