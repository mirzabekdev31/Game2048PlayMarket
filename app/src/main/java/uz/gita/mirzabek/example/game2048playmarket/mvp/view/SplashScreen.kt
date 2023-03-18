package uz.gita.mirzabek.example.game2048playmarket.mvp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.gita.mirzabek.example.game2048playmarket.R

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep((3 * 1000).toLong())
                    val i = Intent(baseContext, Menu::class.java)
                    startActivity(i)
                    finish()
                } catch (e: Exception) {

                }
            }
        }
        background.start()
    }
}