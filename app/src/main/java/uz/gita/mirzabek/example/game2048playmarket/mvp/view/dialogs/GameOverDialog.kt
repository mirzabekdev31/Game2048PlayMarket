package uz.gita.mirzabek.example.game2048playmarket.mvp.view.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import uz.gita.mirzabek.example.game2048playmarket.databinding.DialogGameOverBinding

class GameOverDialog (ctx: Context) : Dialog(ctx) {
    private lateinit var binding: DialogGameOverBinding
    // private val player = MediaPlayer.create(ctx, R.raw.over)
    private var retryListener: (() -> Unit)? = null
    private var finishListener: (() -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        // player.start()
        binding = DialogGameOverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
        binding.apply {
            btnNo.setOnClickListener { finishListener?.invoke() }
            btnYes.setOnClickListener { retryListener?.invoke() }
        }
    }

    fun setRetryListener(t: () -> Unit) {
        retryListener = t
    }

    fun setFinishListener(t: () -> Unit) {
        finishListener = t
    }
}