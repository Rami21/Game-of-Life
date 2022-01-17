import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.ActionEvent
import javafx.util.Duration
import java.util.*

class Model() {
    private val width = 75
    private val height = 50
    var firstShapePlaced = false
    var drawMode = false

    var board = Array(height) { BooleanArray(width) }
    init {
        for (i in 0..49) {
            for (j in 0..74) {
                board[i][j] = false
            }
        }
    }

    private val views = ArrayList<IView>()

    var shapeSelected = "NONE"

    fun returnSelectedShape(): String {
        return shapeSelected
    }

    fun addView(view: IView) {
        views.add(view)
    }

    fun removeView(view: IView) {
        views.remove(view)
    }

    fun notifyView(x: Int = -1, y: Int = -1) {
        for (view in views) {
            view.updateView(x, y)
        }
    }

    // sets the current selected shape
    fun changeShape(shape: String) {
        shapeSelected = shape
        println("Shape selected is now $shapeSelected")
    }

    fun placeShape(x: Int, y: Int) {
        if (shapeSelected == "BLINKER") {
            board[x][y] = true
            board[x][y+1] = true
            board[x][y+2] = true

        } else if (shapeSelected == "BLOCK") {
            board[x][y] = true
            board[x][y+1] = true
            board[x+1][y] = true
            board[x+1][y+1] = true
        } else if (shapeSelected == "BEEHIVE") {
            board[x][y] = true
            board[x][y+1] = true
            board[x+1][y-1] = true
            board[x+1][y+2] = true
            board[x+2][y+1] = true
            board[x+2][y] = true
        } else if (shapeSelected == "TOAD") {
            board[x][y] = true
            board[x][y+1] = true
            board[x][y+2] = true
            board[x+1][y-1] = true
            board[x+1][y] = true
            board[x+1][y+1] = true
        } else if (shapeSelected == "GLIDER") {
            board[x][y] = true
            board[x+1][y+1] = true
            board[x+1][y+2] = true
            board[x][y+2] = true
            board[x-1][y+2] = true
        }
        firstShapePlaced = true
        notifyView(x, y)
    }

    fun copyboard(): Array<BooleanArray> {
        val newArr = Array(50) { BooleanArray(75) }
        for (i in 1..49) {
            for (j in 1..74) {
                newArr[i][j] = board[i][j]
            }
        }
        return newArr
    }

    fun countNeighbours(x: Int, y: Int): Int {
        var neighbours = 0

        for (i in -1..1) {
            for (j in -1..1) {
                if (board[x + i][y + j]) neighbours++
            }
        }
        if (board[x][y]) neighbours--
        return neighbours
    }

    fun gameOfLife() {
        val new = copyboard()
        for (i in 1..48) {
            for (j in 1..73) {
                var neighbours = countNeighbours(i, j)
                if (board[i][j] && neighbours < 2) new[i][j] = false
                else if (board[i][j] && neighbours > 3) new[i][j] = false
                else if (!board[i][j] && neighbours == 3) new[i][j] = true
                else new[i][j] = board[i][j]
            }
        }
        board = new
        notifyView()
    }

//    var tm: Unit = Timer().scheduleAtFixedRate(object : TimerTask() {
//        override fun run() {
//            animation()
//        }
//    },2000,1000)

    val timeline = Timeline(KeyFrame(Duration.seconds(0.75), { ev: ActionEvent? ->
        gameOfLife()
    }))

    fun clearGrid() {
        for (i in 1..49) {
            for (j in 1..74) {
                board[i][j] = false
            }
        }
        notifyView()
    }
}

