package uz.gita.mirzabek.example.game2048playmarket.mvp.view

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class MovementDetector (context: Context) : View.OnTouchListener {
    private var listener: ((Movement) -> Unit)? = null
    private val MOVE_REQUIRED = 100
    private val gesture = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            if (abs(e1.x - e2.x) > abs(e1.y - e2.y)) {
                if (abs(e1.x - e2.x) < MOVE_REQUIRED) return false
                listener?.invoke(if (e2.x > e1.x) Movement.RIGHT else Movement.LEFT)
            } else {
                if (abs(e1.y - e2.y) < MOVE_REQUIRED) return false
                listener?.invoke(if (e2.y > e1.y) Movement.DOWN else Movement.UP)
            }
            return true
        }
    })

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        gesture.onTouchEvent(p1)
        return true
    }

    fun setOnMovementListener(block: (Movement) -> Unit) {
        listener = block
    }
}