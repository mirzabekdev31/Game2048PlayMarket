package uz.gita.mirzabek.example.game2048playmarket.data

import android.content.Context
import android.content.SharedPreferences

class AppPref(ctx:Context) :AppPre {
    private val sharedPreferences: SharedPreferences =
        ctx.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)

    private val editor = sharedPreferences.edit()

    override fun getBestScore(): Int = sharedPreferences.getInt(BEST_SCORE, 0)

    override fun setBestScore(score: Int) {
        editor.putInt(BEST_SCORE, score)
        editor.apply()
    }

    override fun clearData() {
        editor.clear()
        editor.apply()
    }

    override fun getSound(): Boolean = sharedPreferences.getBoolean(SOUND, false)

    override fun setSound(isSound: Boolean) {
        editor.putBoolean(SOUND, isSound)
        editor.apply()
    }

    override fun getSoundEffect(): Boolean = sharedPreferences.getBoolean(SOUND_EFFECT, false)

    override fun setSoundEffect(isSoundEffect: Boolean) {
        editor.putBoolean(SOUND_EFFECT, isSoundEffect)
        editor.apply()
    }

    override fun setCurrentScore(score: Int) {
        editor.putInt(CURRENT_SCORE, score)
        editor.apply()
    }

    override fun setPos(array: Array<Array<Int>>) {
        val builder = StringBuilder()
        for (i in array.indices) {
            for (j in array[i].indices) {
                builder.append("${array[i][j]}#")
            }
        }
        val toString = builder.substring(0, builder.lastIndex).toString()
        editor.putString(CURRENT_POS, toString)
        editor.apply()
    }

    override fun getCurrentScore(): Int = sharedPreferences.getInt(CURRENT_SCORE, 0)

    override fun getCurrentPos(): Array<Array<Int>> {
        val list = arrayOf(
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0)
        )
        val newList =
            sharedPreferences.getString(CURRENT_POS, "0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0")!!.split("#")
        for (i in newList.indices) {
            var x = i / 4
            var y = i % 4
            list[x][y] = newList[i].toInt()
        }
        return list
    }

    override fun getNewGame(): Boolean = sharedPreferences.getBoolean(NEW_GAME, true)

    override fun setNewGame(isNewGame: Boolean) {
        editor.putBoolean(NEW_GAME, isNewGame)
        editor.apply()
    }

    companion object {
        const val SHARED_NAME = "app_data"
        const val BEST_SCORE = "score"
        const val SOUND = "sound"
        const val SOUND_EFFECT = "sound_effect"
        const val CURRENT_SCORE = "current_score"
        const val CURRENT_POS = "current_pos"
        const val NEW_GAME = "new_game"
    }

}