package uz.gita.mirzabek.example.game2048playmarket.models

import android.content.Context
import android.util.Log
import uz.gita.mirzabek.example.game2048playmarket.data.AppPref
import uz.gita.mirzabek.example.game2048playmarket.models.contracts.GameContract

class GameRepository(ctx:Context) :GameContract.Repository{
    private val sharedPreferences= AppPref(ctx)
    private var changePosition = ArrayList<Pair<Int, Int>>()
    private val minNumber = 2
    var canMove=false
    var count1=0;
    var count2=0;
    var count3=0;
    var count4=0;
    private var currentScore:Int = 0


    private var matrix = arrayOf(
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 0, 0, 0),
        arrayOf(0, 0, 0, 0)
    )

    init {
        currentScore = sharedPreferences.getCurrentScore()
        if (!sharedPreferences.getNewGame()) {
            matrix = sharedPreferences.getCurrentPos()
        } else {

            addElement()
            //addElement()
        }
    }


    private fun addElement() {
        val list = ArrayList<Pair<Int, Int>>()
        for (i in 0 until 4) {
            for (j in 0 until matrix[i].size) {
                if (matrix[i][j] == 0) list.add(Pair(i, j))
            }
        }
        list.shuffle()
        if (list.size == 0) return
        val element = list[0]
        matrix[element.first][element.second] = 2
    }

    override fun getBestScore(): Int = sharedPreferences.getBestScore()

    override fun setBestScore(score: Int) = sharedPreferences.setBestScore(score)

    override fun getSound(): Boolean = sharedPreferences.getSound()

    override fun getSoundEffect(): Boolean = sharedPreferences.getSoundEffect()

    override fun onLeft() :Boolean{
        count1++
        count2=0
        count3=0
        count4=0
        changePosition = ArrayList()
        canMove = false
        var canAdd = false
        for (i in matrix.indices) {
            var state = true
            val amounts: ArrayList<Int> = arrayListOf()
            for (j in 0 until matrix[i].size) {

                if (matrix[i][j] == 0) continue

                if (amounts.isEmpty()) {
                    amounts.add(matrix[i][j])


                } else {
                    if (amounts.last() == matrix[i][j] && state) {
                        state = false
                        amounts[amounts.lastIndex] = matrix[i][j] * 2
                        canMove=true
                        // score+=matrix[i][j] * 2
                    } else {
                        state = true
                        amounts.add(matrix[i][j])
                    }
                }

                matrix[i][j] = 0

            }

            for (j in 0 until amounts.size) {
                if (matrix[i][j] != amounts[j]) {
                    canAdd = true
                }
                matrix[i][j] = amounts[j]
            }
        }
        if (canMove || count1<=2) addElement()
        return true
    }

    override fun onRight() :Boolean{
        canMove = false
        count1=0
        count2++
        count3=0
        count4=0
        // count2++
        var canAdd = false
        for (i in matrix.indices) {
            var state = true
            val amounts: ArrayList<Int> = arrayListOf()
            for (j in 0 until matrix[i].size) {
                if (matrix[matrix.size - 1 - i][matrix.size - 1 - j] == 0) continue;

                if (amounts.isEmpty()) {
                    amounts.add(matrix[matrix.size - 1 - i][matrix.size - 1 - j])
                } else {
                    if (amounts.last() == matrix[matrix.size - 1 - i][matrix.size - 1 - j] && state) {
                        state = false
                        amounts[amounts.lastIndex] =
                            matrix[matrix.size - 1 - i][matrix.size - 1 - j] * 2
                        canMove=true
                        //score+=matrix[matrix.size - 1 - i][matrix.size - 1 - j] * 2
                    } else {
                        state = true
                        amounts.add(matrix[matrix.size - 1 - i][matrix.size - 1 - j])
                    }
                }
                matrix[matrix.size - 1 - i][matrix.size - 1 - j] = 0
            }

            for (j in 0 until amounts.size) {
                if (matrix[matrix.size - 1 - i][matrix.size - 1 - j] != amounts[j]) {
                    canAdd = true
                }
                matrix[matrix.size - 1 - i][matrix.size - 1 - j] = amounts[j]

            }
        }
        if (canMove || count2<=2) addElement()
        return true
    }

    override fun onUp() :Boolean{
        canMove = false
        count1=0
        count2=0
        count3++
        count4=0
        var canAdd = false
        for (i in matrix.indices) {
            var state = true
            val amounts: ArrayList<Int> = arrayListOf()
            for (j in 0 until matrix[i].size) {
                if (matrix[j][i] == 0) continue;

                if (amounts.isEmpty()) {
                    amounts.add(matrix[j][i])

                } else {
                    if (amounts.last() == matrix[j][i] && state) {
                        state = false
                        amounts[amounts.lastIndex] = matrix[j][i] * 2
                        canMove=true
                        //     score+=matrix[j][i] * 2
                    } else {
                        state = true
                        amounts.add(matrix[j][i])
                    }
                }
                matrix[j][i] = 0
            }

            for (j in 0 until amounts.size) {
                if (matrix[j][i] != amounts[j]) {
                    canAdd = true
                }
                matrix[j][i] = amounts[j]
            }
        }
        if (canMove || count3<=2) addElement()

        return true
    }

    override fun onDown() :Boolean{
        canMove = false
        count1=0
        count2=0
        count3=0
        count4++
        changePosition = ArrayList()
        var canAdd = false
        for (i in matrix.indices) {
            var state = true
            val amounts: ArrayList<Int> = arrayListOf()
            for (j in 0 until matrix[i].size) {
                if (matrix[matrix.size - 1 - j][matrix.size - 1 - i] == 0) continue

                if (amounts.isEmpty()) {
                    amounts.add(matrix[matrix.size - 1 - j][matrix.size - 1 - i])

                } else {
                    if (amounts.last() == matrix[matrix.size - 1 - j][matrix.size - 1 - i] && state) {
                        state = false
                        amounts[amounts.lastIndex] =
                            matrix[matrix.size - 1 - j][matrix.size - 1 - i] * 2
                        canMove=true
                        // score+=matrix[matrix.size - 1 - j][matrix.size - 1 - i] * 2

                    } else {
                        state = true
                        amounts.add(matrix[matrix.size - 1 - j][matrix.size - 1 - i])

                    }
                }
                matrix[matrix.size - 1 - j][matrix.size - 1 - i] = 0
            }

            for (j in 0 until amounts.size) {
                if (matrix[matrix.size - 1 - j][matrix.size - 1 - i] != amounts[j]) {
                    Log.d(
                        "TTT",
                        "${matrix[matrix.size - 1 - j][matrix.size - 1 - i]}   ${amounts[j]}"
                    )
                    canAdd = true
                }
                matrix[matrix.size - 1 - j][matrix.size - 1 - i] = amounts[j]
            }
        }
        if (canMove || count4<=2) addElement()
        return true
    }

    override fun findEmptySpaces(): ArrayList<Int> {
        val list = ArrayList<Int>()
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == 0) {
                    list.add(i * matrix.size + j)
                }
            }
        }
        return list
    }

    override fun getMatrix() = matrix

    override fun getCurrentScore(): Int = sharedPreferences.getCurrentScore()

    override fun getChangePosition(): List<Pair<Int, Int>> = changePosition

    override fun saveData(currentScore: Int) {
        sharedPreferences.setPos(matrix)
        sharedPreferences.setCurrentScore(currentScore)
        sharedPreferences.setNewGame(false)
    }

    override fun refresh() {
        matrix = arrayOf(
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0)
        )
        sharedPreferences.setNewGame(false)
        addElement()
        addElement()
    }

  /*  override fun canMove(): Boolean {
        return canMove
    }*/

    override fun newGame() {
        sharedPreferences.setNewGame(true)
    }
}