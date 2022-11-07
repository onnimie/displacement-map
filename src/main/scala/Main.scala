import scala.swing.*
import java.awt.Color

import javax.swing.Timer

import java.awt.event.ActionEvent
import scala.swing.Swing.ActionListener

object App extends SimpleSwingApplication:
    val top = MainWindow()

class MainWindow() extends MainFrame:

    title = "window title c:"

    val test_image = Image("./images/redsand.png")
    val test_image_width = test_image.bufferedImage.getWidth()
    val test_image_height = test_image.bufferedImage.getHeight()

    var copy_image = TextureOps.copyImage(test_image.bufferedImage)
    //TextureOps.makeGray(copy_image)
    val displacementMap = DisplacementMap(test_image_width, test_image_height)
    
    displacementMap.makeIntoGradient()
    

    val contentsPanel = new FlowPanel:

        background = Color.darkGray
        preferredSize = new Dimension(test_image_width*3, test_image_height)

        override protected def paintComponent(g: Graphics2D): Unit =

            super.paintComponent(g)

            g.drawImage(test_image.bufferedImage, 0, 0, null)
            
            g.drawImage(displacementMap.getBufferedImage, test_image_width, 0, null)

            g.drawImage(copy_image, test_image_width * 2, 0, null)
        
        end paintComponent

        //contents += test_image.imageLabel // this would work as well as the g.drawImage with bufferedImage

    contents = contentsPanel
 
    val timerListener = ActionListener( (e: ActionEvent) => {
        displacementMap.scrollBy(1)
        copy_image = TextureOps.displace(test_image.bufferedImage, displacementMap)
        repaint()
    })

    val timer = Timer(10, timerListener)
    timer.start()

end MainWindow