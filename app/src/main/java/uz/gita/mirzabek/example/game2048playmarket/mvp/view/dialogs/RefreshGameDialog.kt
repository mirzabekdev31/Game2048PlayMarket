package uz.gita.mirzabek.example.game2048playmarket.mvp.view.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import uz.gita.mirzabek.example.game2048playmarket.databinding.DialogRefreshGameBinding

class RefreshGameDialog (ctx: Context) : Dialog(ctx) {
    private lateinit var binding: DialogRefreshGameBinding
    private var retryListener: (() -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogRefreshGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
        binding.apply {
            btnNo.setOnClickListener { dismiss() }
            btnYes.setOnClickListener { retryListener?.invoke() }
        }
    }

    fun setRefreshListener(t: () -> Unit) {
        retryListener = t
    }

}