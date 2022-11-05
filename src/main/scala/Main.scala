import scala.swing.*
import java.awt.Color

object App extends SimpleSwingApplication:
    val runner = MainWindow

object MainWindow extends MainFrame:

    title = "window title c:"

    contents = new Panel:
        
        background = Color.darkGray
        preferredSize = new Dimension(300, 300)

        override protected def paintComponent(g: Graphics2D): Unit =

            g.setColor(Color.red)
            g.drawRect(100,100,100,100)
        
        end paintComponent

end MainWindow