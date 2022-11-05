import scala.swing.*
import java.awt.Color

object App extends SimpleSwingApplication:
    val top = MainWindow()

class MainWindow() extends MainFrame:

    title = "window title c:"

    val test_image = Image("./images/redsand.png")

    val contentsPanel = new FlowPanel:

        background = Color.darkGray
        preferredSize = new Dimension(1200, 600)

        override protected def paintComponent(g: Graphics2D): Unit =

            super.paintComponent(g)

            g.setColor(Color.red)
            g.drawRect(100,100,100,100)

            g.drawImage(test_image.bufferedImage, 600, 200, null)
        
        end paintComponent

        //contents += test_image.imageLabel // this would work as well as the g.drawImage with bufferedImage

    contents = contentsPanel
 


end MainWindow