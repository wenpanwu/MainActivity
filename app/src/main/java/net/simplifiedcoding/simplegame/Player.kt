package net.simplifiedcoding.simplegame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Player(context: Context, screenX: Int, screenY: Int) {
    @JvmField
    val detectCollision: Rect
    @JvmField
    val bitmap: Bitmap
    @JvmField
    val x = 75
    var y = 50
        private set
    private var lift = 0
    private var lifting: Boolean
    private val maxY: Int
    private val minY: Int
    fun setLifting() {
        lifting = true
    }

    fun stopLifting() {
        lifting = false
    }

    fun update() {
        lift = if (lifting) {
            10
        } else {
            -10
        }
        y -= lift
        if (y < minY) {
            y = minY
        }
        if (y > maxY) {
            y = maxY
        }
        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }

    init {
        lift = 1
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)
        maxY = screenY - bitmap.height
        minY = 0
        lifting = false
        detectCollision = Rect(x, y, bitmap.width, bitmap.height)
    }
}