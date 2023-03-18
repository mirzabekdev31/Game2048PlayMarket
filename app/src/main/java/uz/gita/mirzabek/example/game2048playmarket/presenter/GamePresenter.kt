package uz.gita.mirzabek.example.game2048playmarket.presenter

import uz.gita.mirzabek.example.game2048playmarket.models.contracts.GameContract

class GamePresenter(
    private val view: GameContract.View, private val repository: GameContract.Repository
) : GameContract.Presenter {

    init {
        view.setCurrentScore(repository.getCurrentScore())
        view.setSound(repository.getSound(), repository.getSoundEffect())
    }

    override fun initView() {
        view.setBestScore(repository.getBestScore())
        view.setChangePosition(repository.getChangePosition())
        val matrix = repository.getMatrix()
        view.changeState(matrix)
        if (checkWin(matrix)) {
            view.showWinnerDialog()
        }
    }

    override fun onLeft() {
        //   Log.d("TTT","${repository.canMove()}")
        if (repository.onLeft()) {
            initView()
        } else {
            if (!checkOver(repository.getMatrix())) view.gameOver()
        }
        if (!checkOver(repository.getMatrix())) {
            view.gameOver()
            return
        }
    }

    override fun onRight() {
        //  Log.d("TTT","${repository.canMove()}")
        if (repository.onRight()) {
            initView()
        } else {
            if (!checkOver(repository.getMatrix())) view.gameOver()
        }
        if (!checkOver(repository.getMatrix())) {
            view.gameOver()
            return
        }
    }

    override fun onUp() {
        // Log.d("TTT","${repository.canMove()}")
        if (repository.onUp()) {

            initView()
        } else {
            if (!checkOver(repository.getMatrix())) view.gameOver()
        }
        if (!checkOver(repository.getMatrix())) {
            view.gameOver()
            return
        }
    }

    override fun onDown() {
        //  Log.d("TTT","${repository.canMove()}")
        if (repository.onDown()) {
            initView()
        } else {
            if (!checkOver(repository.getMatrix())) view.gameOver()
        }
        if (!checkOver(repository.getMatrix())) {
            view.gameOver()
            return
        }
    }

    override fun gameOver(score: Int) {
        repository.setBestScore(score)
    }

    override fun saveData(currentScore: Int) {
        repository.saveData(currentScore)
    }

    override fun quitGameDialog() {
        view.quitGameDialog()
    }

    override fun refresh() {
        repository.refresh()
        view.setCurrentScore(0)
        view.changeState(repository.getMatrix())
    }

    override fun newGame() {
        repository.newGame()
    }

    private fun checkWin(array: Array<Array<Int>>): Boolean {

        for (i in array.indices) {
            for (j in array[i].indices) {
                if (array[i][j] == 2048) return true
            }
        }

        return false
    }

    private fun chack() {

    }

    private fun checkOver(array: Array<Array<Int>>): Boolean {

        for (i in array.indices) {
            for (j in 0..3) {
                if (array[i][j] == 0) return true
            }
        }

        for (i in array.indices) {
            for (j in 0..2) {
                if (array[i][j] == array[i][j + 1]) return true
            }
        }

        for (i in 0..3) {
            for (j in 0..2) {
                if (array[j][i] == array[j + 1][i]) return true
            }
        }
        return false
    }
}