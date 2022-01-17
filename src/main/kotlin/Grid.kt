import javafx.collections.ObservableList
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle


class Grid(private val model: Model): IView, GridPane() {
    init {
        this.isGridLinesVisible = true
        this.hgap = 1.2
        this.vgap = 1.2
        for (i in 0..49) {
            for (j in 0..74) {
                val rect = Rectangle(13.0, 13.0)
                rect.fill = Color.WHITE
                rect.setOnMouseClicked {
                    model.placeShape(i, j)
                    if (model.drawMode) {
                        if (rect.fill == Color.WHITE) {
                            rect.fill = Color.BLACK
                            model.board[i][j] = true
                        } else {
                            rect.fill = Color.WHITE
                            model.board[i][j] = false
                        }
                    }
                }
                this.add(rect, j, i)
            }
        }
    }

    fun getRect(i : Int, j: Int): Rectangle {
        val children = this.getManagedChildren<Rectangle>()
        var result = children[0]
        for (child in children) {
            if (child.properties["gridpane-row"] == i && child.properties["gridpane-column"] == j) {
                result = child
                break
            }
        }
        return result
    }

    override fun updateView(x: Int, y: Int) {
        for (i in 0..49) {
            for (j in 0..74) {
                if (model.board[i][j]) getRect(i, j).fill = Color.BLACK
                if (!model.board[i][j]) getRect(i, j).fill = Color.WHITE
            }
        }
    }
}