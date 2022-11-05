import scala.swing.*
import java.awt.Color

object App extends SimpleSwingApplication:
    val top = MainWindow()

class MainWindow() extends MainFrame:

    title = "window title c:"

    contents = new Panel:

        background = Color.darkGray
        preferredSize = new Dimension(300, 300)

        override protected def paintComponent(g: Graphics2D): Unit =

            super.paintComponent(g)
            
            g.setColor(Color.red)
            g.drawRect(100,100,100,100)
        
        end paintComponent

end MainWindow