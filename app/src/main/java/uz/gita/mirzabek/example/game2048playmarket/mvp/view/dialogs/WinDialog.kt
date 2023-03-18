package uz.gita.mirzabek.example.game2048playmarket.mvp.view.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import uz.gita.mirzabek.example.game2048playmarket.databinding.DialogWinnerBinding

class WinDialog (ctx: Context, private val score: Int) : Dialog(ctx) {
    private lateinit var binding: DialogWinnerBinding
    private var retry: (() -> Unit)? = null
    private var dismissListener: (() -> Unit)? = null
    // private val player = MediaPlayer.create(ctx, R.raw.winner)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogWinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //   player.start()
        binding.tvResult.text = "$score"
        binding.apply {
            btnNo.setOnClickListener {
                dismissListener?.invoke()
            }
            btnYes.setOnClickListener {
                retry?.invoke()
            }
        }
    }

    fun setRetryClickListener(t: () -> Unit) {
        retry = t
    }

    fun setDismissListener(v: () -> Unit) {
        dismissListener = v
    }
}