package net.simplifiedcoding.simplegame;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class GameActivity extends AppCompatActivity {
    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout game = new FrameLayout(this);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        gameView = new GameView(this, size.x, size.y);
        game.addView(gameView);
        Button booster = new Button(this);
        booster.setText("Boost!");
        booster.setTextColor(Color.RED);
        booster.setHeight(260);
        booster.setWidth(175);
        booster.setX(1650.0f);
        booster.setY(960.0f);
        booster.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    gameView.speedBooster = 10;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    gameView.speedBooster = 0;
                }
                return true;
            }
        });
        game.addView(booster, 260, 145);
        setContentView(game);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}
