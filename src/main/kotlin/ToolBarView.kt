import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.Button
import javafx.scene.control.Separator
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView


class ToolBarView(private val model: Model): ToolBar(), IView {
    init {
        val block = Button("Block")
        val blockIcon= ImageView(Image("block.png"))
        blockIcon.fitHeight = 20.0
        blockIcon.isPreserveRatio = true
        block.graphic = blockIcon

        block.setOnAction {
            model.changeShape("BLOCK")
        }

        val beehive = Button("Beehive")
        val beehiveIcon= ImageView(Image("beehive.png"))
        beehiveIcon.fitHeight = 20.0
        beehiveIcon.isPreserveRatio = true
        beehive.graphic = beehiveIcon

        beehive.setOnAction {
            model.changeShape("BEEHIVE")
        }

        val blinker = Button("Blinker")
        val blinkerIcon= ImageView(Image("blinker.png"))
        blinkerIcon.fitHeight = 20.0
        blinkerIcon.isPreserveRatio = true
        blinker.graphic = blinkerIcon

        blinker.setOnAction {
            model.changeShape("BLINKER")
        }

        val toad = Button("Toad")
        val toadIcon= ImageView(Image("toad.png"))
        toadIcon.fitHeight = 20.0
        toadIcon.isPreserveRatio = true
        toad.graphic = toadIcon

        toad.setOnAction {
            model.changeShape("TOAD")
        }

        val glider = Button("Glider")
        val gliderIcon= ImageView(Image("glider.png"))
        gliderIcon.fitHeight = 20.0
        gliderIcon.isPreserveRatio = true
        glider.graphic = gliderIcon

        glider.setOnAction {
            model.changeShape("GLIDER")
        }

        val clear = Button("Clear")
        val clearIcon = ImageView(Image("clear.png"))
        clearIcon.fitHeight = 20.0
        clearIcon.isPreserveRatio = true
        clear.graphic = clearIcon

        clear.setOnAction {
            model.changeShape("CLEAR")
            model.clearGrid()
        }

        val done = Button("Done")
        val doneIcon = ImageView(Image("tick.png"))
        doneIcon.fitHeight = 20.0
        doneIcon.isPreserveRatio = true
        done.graphic = doneIcon

        //val cancel = Button("Cancel")
        done.isManaged = false
        //cancel.isManaged = false
        done.isVisible = false
        //cancel.isVisible = false

        val draw = Button("Draw")
        val drawIcon =  ImageView(Image("draw.png"))
        drawIcon.fitHeight = 20.0
        drawIcon.isPreserveRatio = true
        draw.graphic = drawIcon
        //var drawMode = false
        draw.setOnAction {
            model.shapeSelected = "NONE"
            draw.isVisible = false
            draw.isManaged = false
            done.isManaged = true
            //cancel.isManaged = true
            done.isVisible = true
            model.drawMode = true
            //cancel.isVisible = true
            val alert = Alert(AlertType.INFORMATION)
            alert.title = "Drawing Mode"
            alert.headerText = "You have entered drawing mode!"
            alert.contentText = "Draw the desired shape by clicking on the grid then press done when you are finished."
            alert.dialogPane.style = "-fx-font-family: 'helvetica'"
            model.timeline.stop()
            alert.showAndWait()
        }

        done.setOnAction {
            done.isManaged = false
            //cancel.isManaged = false
            done.isVisible = false
            //cancel.isVisible = false
            draw.isVisible = true
            draw.isManaged = true
            model.drawMode = false
            model.timeline.play()
        }

//        cancel.setOnAction {
//            done.isManaged = false
//            cancel.isManaged = false
//            done.isVisible = false
//            cancel.isVisible = false
//            draw.isVisible = true
//            draw.isManaged = true
//        }

        this.items.addAll(block, beehive, blinker, toad, glider, Separator(), Separator(), draw, done, clear)
    }
    override fun updateView(x: Int, y: Int) {
    }
}