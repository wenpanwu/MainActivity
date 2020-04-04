package net.simplifiedcoding.simplegame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Boom(context: Context) {
    var bitmap: Bitmap
    var x: Int
    var y: Int

    init {
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.blast_small)
        x = -250
        y = -250
    }
}