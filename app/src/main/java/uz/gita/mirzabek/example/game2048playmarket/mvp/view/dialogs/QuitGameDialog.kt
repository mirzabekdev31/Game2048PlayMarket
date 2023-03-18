package uz.gita.mirzabek.example.game2048playmarket.mvp.view.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import uz.gita.mirzabek.example.game2048playmarket.databinding.DialogQuitGameBinding

class QuitGameDialog (ctx: Context) : Dialog(ctx) {
    private var listener: (() -> Unit)? = null
    private lateinit var binding: DialogQuitGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogQuitGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNo.setOnClickListener {
            dismiss()
        }
        binding.btnYes.setOnClickListener {
            listener?.invoke()
        }
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun setListener(block: () -> Unit) {
        listener = block
    }

}