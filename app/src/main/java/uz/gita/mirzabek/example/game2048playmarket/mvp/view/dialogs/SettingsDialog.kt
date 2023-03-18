package uz.gita.mirzabek.example.game2048playmarket.mvp.view.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import uz.gita.mirzabek.example.game2048playmarket.data.AppPref
import uz.gita.mirzabek.example.game2048playmarket.databinding.DialogSettingsBinding

class SettingsDialog (
    ctx: Context, ) : Dialog(ctx) {
    private lateinit var binding: DialogSettingsBinding
    private val sharedPreferences= AppPref(ctx)
    private var changeListener: ((Boolean, Boolean) -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvHighScore.text =sharedPreferences.getBestScore().toString()
        binding.btnOk.setOnClickListener {
            dismiss()
        }
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun setChangeListener(t: (Boolean, Boolean) -> Unit) {
        changeListener = t
    }

}