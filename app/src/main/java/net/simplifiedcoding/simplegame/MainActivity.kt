package net.simplifiedcoding.simplegame

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageButton

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var buttonPlay: ImageButton? = null
    private var buttonScore: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        buttonPlay = findViewById<View>(R.id.buttonPlay) as ImageButton
        buttonPlay!!.setOnClickListener(this)
        buttonScore = findViewById<View>(R.id.buttonScore) as ImageButton
        buttonScore!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        startActivity(Intent(this, GameActivity::class.java))
    }
}