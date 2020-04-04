package net.simplifiedcoding.simplegame

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.util.*

@SuppressLint("ViewConstructor")
class GameView(context: Context?, screenX: Int, screenY: Int) : SurfaceView(context), Runnable {
    @Volatile
    var playing = false
    private var gameThread: Thread? = null
    private val player: Player
    private val paint: Paint
    private val scorePaint: Paint
    private val surfaceHolder: SurfaceHolder
    private val enemies: Array<Enemy?>
    private val enemyCount = 3
    private var canvas: Canvas? = null
    private val stars = ArrayList<Star>()
    private val boom: Boom
    var speedBooster = 0
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        when (motionEvent.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_UP -> player.stopLifting()
            MotionEvent.ACTION_DOWN -> player.setLifting()
        }
        return true
    }

    override fun run() {
        while (playing) {
            update()
            draw()
            control()
        }
    }

    private fun update() {
        player.update()
        boom.x = -250
        boom.y = -250
        for (s in stars) {
            s.update(-5 + speedBooster)
        }
        for (i in 0 until enemyCount) {
            enemies[i]!!.update(-5 + speedBooster)
            if (Rect.intersects(player.detectCollision, enemies[i]!!.detectCollision)) {
                boom.x = enemies[i]!!.x
                boom.y = enemies[i]!!.y
                enemies[i]!!.x = -200
            }
        }
    }

    private fun draw() {
        if (surfaceHolder.surface.isValid) {
            canvas = surfaceHolder.lockCanvas()
            canvas?.drawColor(Color.BLACK)
            paint.color = Color.WHITE
            for (s in stars) {
                paint.strokeWidth = s.starWidth
                canvas?.drawPoint(s.x.toFloat(), s.y.toFloat(), paint)
            }
            canvas?.drawBitmap(player.bitmap,
                    player.x.toFloat(),
                    player.y.toFloat(),
                    paint)
            for (i in 0 until enemyCount) {
                canvas?.drawBitmap(
                        enemies[i]!!.bitmap,
                        enemies[i]!!.x.toFloat(),
                        enemies[i]!!.y.toFloat(),
                        paint
                )
            }
            canvas?.drawBitmap(
                    boom.bitmap,
                    boom.x.toFloat(),
                    boom.y.toFloat(),
                    paint
            )
            canvas?.drawText("SCORE: ", 10f, 30f, scorePaint)
            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    private fun control() {
        try {
            Thread.sleep(17)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun pause() {
        playing = false
        try {
            gameThread!!.join()
        } catch (e: InterruptedException) {
        }
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread!!.start()
    }

    init {
        player = Player(context!!, screenY)
        surfaceHolder = holder
        paint = Paint()
        scorePaint = Paint()
        scorePaint.color = Color.WHITE
        scorePaint.textSize = 36f
        val starNums = 100
        for (i in 0 until starNums) {
            val s = Star(screenX, screenY)
            stars.add(s)
        }
        enemies = arrayOfNulls(enemyCount)
        for (i in 0 until enemyCount) {
            enemies[i] = Enemy(context, screenX, screenY)
        }
        boom = Boom(context)
    }
}