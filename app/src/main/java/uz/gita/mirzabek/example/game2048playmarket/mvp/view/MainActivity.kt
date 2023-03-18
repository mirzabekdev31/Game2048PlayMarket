package uz.gita.mirzabek.example.game2048playmarket.mvp.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.get
import uz.gita.mirzabek.example.game2048playmarket.R
import uz.gita.mirzabek.example.game2048playmarket.data.AppPref
import uz.gita.mirzabek.example.game2048playmarket.data.SideEnum
import uz.gita.mirzabek.example.game2048playmarket.databinding.ActivityMainBinding
import uz.gita.mirzabek.example.game2048playmarket.domain.AppRepository
import uz.gita.mirzabek.example.game2048playmarket.models.GameRepository
import uz.gita.mirzabek.example.game2048playmarket.models.contracts.GameContract
import uz.gita.mirzabek.example.game2048playmarket.mvp.view.dialogs.GameOverDialog
import uz.gita.mirzabek.example.game2048playmarket.mvp.view.dialogs.QuitGameDialog
import uz.gita.mirzabek.example.game2048playmarket.mvp.view.dialogs.WinDialog
import uz.gita.mirzabek.example.game2048playmarket.presenter.GamePresenter
import uz.gita.mirzabek.example.game2048playmarket.util.MyTouchListener

class MainActivity : AppCompatActivity(),GameContract.View{
    private val elements = ArrayList<TextView>(16)
    //private val repository = AppRepository(this)
    private val colorArr = HashMap<Int, Int>()
//    private val pref=AppPref(this)
//    private lateinit var score: TextView
//    private lateinit var bestScore:TextView
    private lateinit var binding: ActivityMainBinding
    private lateinit var textList: ArrayList<TextView>
    private lateinit var presenter: GameContract.Presenter
    private lateinit var changePosition: List<Pair<Int, Int>>
    private var score = 0
    private var first = true
    private var bestScore = 0
    private var gameOver = false
    private var isSoundEffect: Boolean = false
    //private lateinit var effect: ButtonClickEffect

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = GamePresenter(this, GameRepository(this))
        initView()
        presenter.initView()
        findViewById<ImageView>(R.id.backBtn).setOnClickListener {
            if (!gameOver) {
                quitGameDialog()
            } else {
                finish()
            }
        }

//        loadViews()
//        loadData()
//        describeMatrix()
//        score = findViewById<TextView>(R.id.score)
//        bestScore=findViewById(R.id.BestScore)
    }

//    private fun loadData() {
//        colorArr.put(0, R.drawable.bg_item_0)
//        colorArr.put(2, R.drawable.bg_item_2)
//        colorArr.put(4, R.drawable.bg_item_4)
//        colorArr.put(8, R.drawable.bg_item_8)
//        colorArr.put(16, R.drawable.bg_item_16)
//        colorArr.put(32, R.drawable.bg_item_32)
//        colorArr.put(64, R.drawable.bg_item_64)
//        colorArr.put(128, R.drawable.bg_item_128)
//        colorArr.put(256, R.drawable.bg_item_256)
//        colorArr.put(512, R.drawable.bg_item_512)
//        colorArr.put(1024, R.drawable.bg_item_1024)
//        colorArr.put(2048, R.drawable.bg_item_2048)
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    private fun loadViews() {
//        val mainContainer = findViewById<LinearLayout>(R.id.mainView)
//        val myTouchListener = MyTouchListener(this)
//        myTouchListener.setDetectSideListener {
//            when (it) {
//                SideEnum.RIGHT -> {
//                    repository.actionToRight()
//                    describeMatrix()
//                }
//                SideEnum.LEFT -> {
//                    repository.actionToLeft()
//                    describeMatrix()
//                }
//                SideEnum.UP -> {
//                    repository.actionToUP()
//                    describeMatrix()
//                }
//                SideEnum.DOWN -> {
//                    repository.actionToDown()
//                    describeMatrix()
//                }
//            }
//            score.text = repository.getScore().toString()
//            pref.score=repository.getScore().toString()
//            bestScore.text=pref.score
//        }
//        mainContainer.setOnTouchListener(myTouchListener)
//
//        for (i in 0 until mainContainer.childCount) {
//            val linear = mainContainer[i] as LinearLayout
//            for (j in 0 until linear.childCount) {
//                elements.add(linear[j] as TextView)
//            }
//        }
//
//        val reloadButton = findViewById<AppCompatButton>(R.id.buttonReload)
//        reloadButton.setOnClickListener {
//            repository.replay()
//            describeMatrix()
//            score.setText("0")
//            repository.setScore(0)
//        }
//    }



//    private fun describeMatrix() {
//        val matrix = repository.getMatrix()
//        for (i in 0 until 4) {
//            for (j in 0 until 4) {
//                elements[i * 4 + j].setBackgroundResource(colorArr[matrix[i][j]]!!)
//                if (matrix[i][j] != 0) elements[i * 4 + j].text = "${matrix[i][j]}"
//                else elements[i * 4 + j].text = ""
//            }
//        }
//    }

//    override fun onBackPressed() {
//        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
//        val customLayout = layoutInflater.inflate(R.layout.menyu_chiqish, null)
//        val yes1 = customLayout.findViewById<TextView>(R.id.btnPositive)
//        yes1.setOnClickListener { b: View? -> finish() }
//        val no1 = customLayout.findViewById<TextView>(R.id.btnNegative)
//        no1.setOnClickListener { b: View? -> builder.hide() }
//        builder.setView(customLayout)
//        builder.setCancelable(false)
//        builder.show()
//    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        textList = ArrayList()
        binding.apply {
            for (i in 0 until mainView.childCount) {
                val group = mainView.getChildAt(i) as LinearLayout
                for (j in 0 until group.childCount) {
                    val view = group.getChildAt(j) as TextView
                    textList.add(view)
                }
            }

            val detector = MovementDetector(this@MainActivity)

            detector.setOnMovementListener {
                when (it) {
                    Movement.LEFT -> presenter.onLeft()
                    Movement.DOWN -> presenter.onDown()
                    Movement.UP -> presenter.onUp()
                    else -> presenter.onRight()
                }
            }
            mainView.setOnTouchListener(detector)
        }

        binding.buttonReload.setOnClickListener {
                    presenter.refresh()
        }

    }

    override fun setBestScore(score: Int) {
        binding.BestScore.text = "$score"
        bestScore = score
    }

    override fun setCurrentScore(currentScore: Int) {
        binding.score.text = "$currentScore"
        score = currentScore
    }

    override fun changeState(array: Array<Array<Int>>) {
        if (isSoundEffect && !first) {
        }
        first = false
        //val animation = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        for (i in array.indices) {
            for (j in array[i].indices) {
                val textView = textList[i * array.size + j]
                val value = array[i][j]
                textView.text = if (array[i][j] == 0) {
                    textView.setBackgroundResource(R.drawable.background_item)
                    ""
                } else {
                    if (containsView(i, j)) {
                        score += value
                        //textView.startAnimation(animation)
                    }
                    if (value > 100) {
                        textView.textSize = 24f
                    } else if (value > 1000) {
                        textView.textSize = 22f
                    }
                    textView.setBackgroundResource(Colors.list[value.degreeOfTwo()])
                    "$value"
                }
            }
        }
        binding.score.text = "$score"
        if (score > bestScore) {
            bestScore = score
            binding.BestScore.text = "$bestScore"
        }
    }

    private fun containsView(i: Int, j: Int): Boolean {
        for (k in changePosition) {
            if (i == k.first && j == k.second) return true
        }
        return false
    }

    override fun setChangePosition(changePosition: List<Pair<Int, Int>>) {
        this.changePosition = changePosition
    }

    override fun gameOver() {
        gameOver = true
        val dialog = GameOverDialog(this)
        dialog.show()
        dialog.setFinishListener {
            dialog.dismiss()
            presenter.newGame()
            finish()
        }
        dialog.setRetryListener {
            gameOver = false
            dialog.dismiss()
            presenter.refresh()
        }
    }

    override fun quitGameDialog() {
        val dialog = QuitGameDialog(this)
        dialog.setListener {
            dialog.dismiss()
            finish()
        }
        dialog.show()
    }

    override fun setSound(isSound: Boolean, isSoundEffect: Boolean) {

        if (isSound) {
        }
        this.isSoundEffect = isSoundEffect

    }

    override fun showWinnerDialog() {
        val dialog = WinDialog(this,score)
        dialog.show()
        dialog.setDismissListener {
            dialog.dismiss()
            presenter.newGame()
            finish()
        }
        dialog.setRetryClickListener {
            dialog.dismiss()
            presenter.refresh()
        }

    }

    override fun onPause() {
        presenter.gameOver(bestScore)
        if (!gameOver) {
            presenter.saveData(score)
        }
        super.onPause()
    }

    override fun onBackPressed() {
        if (!gameOver) {
            quitGameDialog()
        } else {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}


fun Int.degreeOfTwo(): Int {
    var value = this
    var deg = 0
    while (value>1){
        value/=2
        deg++
    }
    return deg
}