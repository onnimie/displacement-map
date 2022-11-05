import javax.swing.ImageIcon
import scala.swing.Label

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import java.awt.Image

import java.awt.Graphics2D
import swing.Panel

import swing.Dimension

import scala.math.pow

object TextureOps:


    def copyImage(source: BufferedImage): BufferedImage =
        val clone: BufferedImage = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        val g: Graphics2D = clone.createGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        clone
    end copyImage


    def makeGray(img: BufferedImage): Unit = 

        for x <- 0 until img.getWidth() do
            for y <- 0 until img.getHeight() do

                val rgb: Int = img.getRGB(x, y)
                val r: Int = (rgb >> 16) & 0xFF
                val g: Int = (rgb >> 8) & 0xFF
                val b: Int = (rgb & 0xFF)

                // Normalize and gamma correct:
                val rr: Double = pow(r / 255.0, 2.2)
                val gg: Double = pow(g / 255.0, 2.2)
                val bb: Double = pow(b / 255.0, 2.2)

                // Calculate luminance:
                val lum: Double = 0.2126 * rr + 0.7152 * gg + 0.0722 * bb

                // Gamma compand and rescale to byte range:
                val grayLevel: Int = (255.0 * pow(lum, 1.0 / 2.2)).toInt
                val gray: Int = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
                img.setRGB(x, y, gray)
    end makeGray

    def makeGrayCopy(img: BufferedImage): BufferedImage = 
        val ret = this.copyImage(img)
        this.makeGray(ret)
        ret
    end makeGrayCopy