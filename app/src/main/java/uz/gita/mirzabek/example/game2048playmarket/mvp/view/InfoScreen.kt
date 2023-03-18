package uz.gita.mirzabek.example.game2048playmarket.mvp.view
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import uz.gita.mirzabek.example.game2048playmarket.R

class InfoScreen :AppCompatActivity(R.layout.info_screen){
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_screen)
        findViewById<ImageView>(R.id.backBTN).setOnClickListener {
            finish()
        }
    }
}