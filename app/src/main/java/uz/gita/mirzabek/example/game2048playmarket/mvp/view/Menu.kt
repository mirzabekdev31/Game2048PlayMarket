package uz.gita.mirzabek.example.game2048playmarket.mvp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import uz.gita.mirzabek.example.game2048playmarket.R

class Menu : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        findViewById<ImageView>(R.id.Play).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            YoYo.with(Techniques.FadeInUp).duration(300).playOn(findViewById<ImageView>(R.id.Play))
            startActivity(intent)
        }
        findViewById<ConstraintLayout>(R.id.Info).setOnClickListener {
            val intent=Intent(this,InfoScreen::class.java)
            startActivity(intent)
        }
        findViewById<ConstraintLayout>(R.id.Share).setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = "Here is the share content body"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
    }
}