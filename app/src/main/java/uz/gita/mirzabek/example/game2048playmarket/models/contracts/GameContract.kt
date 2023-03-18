package uz.gita.mirzabek.example.game2048playmarket.models.contracts

interface GameContract {
    interface View {

        fun initView()

        fun setBestScore(score: Int)

        fun setCurrentScore(currentScore: Int)

        fun changeState(array: Array<Array<Int>>)

        fun setChangePosition(changePosition: List<Pair<Int, Int>>)

        fun gameOver()

        fun quitGameDialog()

        fun setSound(isSound: Boolean, isSoundEffect: Boolean)

        fun showWinnerDialog()

    }

    interface Presenter {

        fun initView()

        fun onLeft()

        fun onRight()

        fun onUp()

        fun onDown()

        fun gameOver(score: Int)

        fun saveData(currentScore: Int)

        fun quitGameDialog()

        fun refresh()

        fun newGame()


    }

    interface Repository {

        fun getBestScore(): Int

        fun setBestScore(score: Int)

        fun getSound(): Boolean

        fun getSoundEffect(): Boolean

        fun onLeft():Boolean

        fun onRight():Boolean

        fun onUp():Boolean

        fun onDown():Boolean

        fun findEmptySpaces(): ArrayList<Int>

        fun getMatrix(): Array<Array<Int>>

        fun getCurrentScore(): Int

        fun getChangePosition(): List<Pair<Int, Int>>

        fun saveData(currentScore: Int)

        fun refresh()

        fun newGame()
    }
}