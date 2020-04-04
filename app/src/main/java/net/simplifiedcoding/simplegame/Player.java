package net.simplifiedcoding.simplegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Player {
    private Rect detectCollision;
    private Bitmap bitmap;

    private int x;
    private int y;

    private int lift = 0;
    private boolean lifting;

    private int maxY;
    private int minY;

    public Player(Context context, int screenX, int screenY) {
        x = 75;
        y = 50;
        lift = 1;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        maxY = screenY - bitmap.getHeight();
        minY = 0;
        lifting = false;

        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }
    public void setLifting() {
        lifting = true;
    }
    public void stopLifting() {
        lifting = false;
    }
    public void update() {
        if (lifting) {
            lift = 10;
        } else {
            lift = -10;
        }

        y -= lift;

        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }
    public Rect getDetectCollision() {
        return detectCollision;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
