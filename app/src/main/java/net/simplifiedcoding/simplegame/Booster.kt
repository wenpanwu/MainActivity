package net.simplifiedcoding.simplegame

class Booster {
    private var boosting = false
    fun setBoosting() {
        boosting = true
    }

    fun stopBoosting() {
        boosting = false
    }

    fun update() {
        /* if (boosting) {
            speed += 2;
        } else {
            speed -=5;
        }

        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }
        y -= speed + GRAVITY;

        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight(); */
    }

}