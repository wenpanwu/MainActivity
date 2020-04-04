package net.simplifiedcoding.simplegame

import java.util.*

class Star(private val maxX: Int, private val maxY: Int) {
    var x: Int
        private set
    var y: Int
        private set
    private var speed: Int
    fun update(playerSpeed: Int) {
        x -= playerSpeed
        x -= speed
        if (x < 0) {
            x = maxX
            val generator = Random()
            y = generator.nextInt(maxY)
            speed = generator.nextInt(15)
        }
    }

    val starWidth: Float
        get() {
            val minX = 1.0f
            val maxX = 4.0f
            val rand = Random()
            return rand.nextFloat() * (maxX - minX) + minX
        }

    init {
        val generator = Random()
        speed = generator.nextInt(10)
        x = generator.nextInt(maxX)
        y = generator.nextInt(maxY)
    }
}