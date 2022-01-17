import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.layout.Border
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox

class StatusView(private val model: Model): BorderPane(), IView {
    private val status = Label()
    private val frames = Label()
    private var frameCount = 0

    init {
        this.padding = Insets(6.0)
        this.left = status
        this.right = frames
        frames.text = "Frame ${frameCount.toString()}"
    }

    fun updateStatusBar(str: String) {
        status.text = str
    }

    override fun updateView(x: Int, y: Int) {
        val currShape = model.returnSelectedShape()
        if (currShape != "NONE" && x > -1 && y > -1) {
            status.text = "Created $currShape at $y, $x"
        }
        if (currShape == "CLEAR") status.text = "Board Cleared"
        if (model.firstShapePlaced) {
            frameCount++
            frames.text = "Frame ${frameCount.toString()}"
        }
    }
}