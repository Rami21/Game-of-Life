import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Application
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage
import kotlin.system.exitProcess

class Main : Application() {
    override fun start(stage: Stage) {
        val root = VBox()
        val model = Model()
        val toolbar = ToolBarView(model)
        val gridview = Grid(model)
        val status = StatusView(model)
        model.addView(toolbar)
        model.addView(gridview)
        model.addView(status)
        root.children.addAll(toolbar, gridview, status)
        val scene = Scene(root)
        model.timeline.cycleCount = Animation.INDEFINITE
        model.timeline.play()
        stage.scene = scene
        stage.width = 1050.0
        stage.height = 800.0
        stage.isResizable = false
        scene.root.style = "-fx-font-family: 'helvetica'"
        stage.title = "Conway's Game of Life (r3abdulr)"
        stage.show()
        stage.setOnCloseRequest { exitProcess(0) }
    }
}