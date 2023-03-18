package uz.gita.mirzabek.example.game2048playmarket.data

interface AppPre {
    fun getBestScore(): Int

    fun setBestScore(score: Int)

    fun clearData()

    fun getSound(): Boolean

    fun setSound(isSound: Boolean)

    fun getSoundEffect(): Boolean

    fun setSoundEffect(isSoundEffect: Boolean)

    fun setCurrentScore(score: Int)

    fun setPos(array: Array<Array<Int>>)

    fun getCurrentScore():Int

    fun getCurrentPos():Array<Array<Int>>

    fun getNewGame():Boolean

    fun setNewGame(isNewGame:Boolean)
}