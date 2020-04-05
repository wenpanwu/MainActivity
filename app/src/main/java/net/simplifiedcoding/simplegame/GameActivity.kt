package net.simplifiedcoding.simplegame

import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.widget.Button
import android.widget.FrameLayout

class GameActivity : AppCompatActivity() {
    private var gameView: GameView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val game = FrameLayout(this)
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        gameView = GameView(this, size.x, size.y)
        game.addView(gameView)
        val booster = Button(this)
        booster.text = "Boost!"
        booster.setTextColor(Color.RED)
        booster.height = 260
        booster.width = 175
        booster.x = 905.0f
        booster.y = 530.0f
        booster.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                gameView!!.speedBooster = 10
            } else if (event.action == MotionEvent.ACTION_UP) {
                gameView!!.speedBooster = 0
            }
            true
        }
        game.addView(booster, 260, 145)
        setContentView(game)
    }

    override fun onPause() {
        super.onPause()
        gameView!!.pause()
    }

    override fun onResume() {
        super.onResume()
        gameView!!.resume()
    }
}