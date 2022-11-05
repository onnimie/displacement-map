import javax.swing.ImageIcon
import scala.swing.Label

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import java.awt.Graphics2D
import swing.Panel

import swing.Dimension

class Image(path: String):

  val imageLabel: Label = new Label:
    preferredSize = new Dimension(400,400)
    icon = new ImageIcon(path)


  lazy val bufferedImage: BufferedImage = ImageIO.read(new File(path))
    // implementation for panel
  lazy val imagePanel: Panel = new Panel:

    override def paintComponent(g: Graphics2D): Unit = 
        g.drawImage(bufferedImage, 0, 0, null)

  end imagePanel



end Image

