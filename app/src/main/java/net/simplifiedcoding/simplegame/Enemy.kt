package net.simplifiedcoding.simplegame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import java.util.*

class Enemy(context: Context, screenX: Int, screenY: Int) {
    @JvmField
    val bitmap: Bitmap
    @JvmField
    var x: Int
    var y: Int
        private set
    var speed = 1
        private set
    private val maxX: Int
    private val minX: Int
    private val maxY: Int
    private val minY: Int
    @JvmField
    val detectCollision: Rect
    fun update(playerSpeed: Int) {
        x -= playerSpeed
        x -= speed
        if (x < minX - bitmap.width) {
            val generator = Random()
            speed = generator.nextInt(10) + 10
            x = maxX
            y = generator.nextInt(maxY) - bitmap.height
        }
        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }

    init {
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.alien)
        maxX = screenX
        maxY = screenY
        minX = 0
        minY = 0
        val generator = Random()
        speed = generator.nextInt(6) + 10
        x = screenX
        y = generator.nextInt(maxY) - bitmap.height
        detectCollision = Rect(x, y, bitmap.width, bitmap.height)
    }
}