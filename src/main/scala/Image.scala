import javax.swing.ImageIcon
import scala.swing.Label

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import java.awt.Graphics2D
import swing.Panel

class Image(path: String):

  val imageLabel: Label = new Label:
    icon = new ImageIcon(path)


    // implementation for panel
  lazy val imagePanel: Panel = new Panel:

    val bufferedImage: BufferedImage = ImageIO.read(File(path))

    override def paintComponent(g: Graphics2D): Unit = 
        g.drawImage(bufferedImage, 0, 0, null)

  end imagePanel
  


end Image

