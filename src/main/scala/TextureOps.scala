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
import java.awt.Color
import java.awt.Graphics2D

object TextureOps:


    def copyImage(source: BufferedImage): BufferedImage =
        val clone: BufferedImage = new BufferedImage(source.getWidth(), source.getHeight(), source.getType())
        val g: Graphics2D = clone.createGraphics()
        g.drawImage(source, 0, 0, null)
        g.dispose()
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
                val gray: Int = (grayLevel << 16) + (grayLevel << 8) + grayLevel
                img.setRGB(x, y, gray)
    end makeGray

    def makeGrayCopy(img: BufferedImage): BufferedImage = 
        val ret = this.copyImage(img)
        this.makeGray(ret)
        ret
    end makeGrayCopy


    // displace an image with the given displacementMap
    def displace(img: BufferedImage, displacementMap: DisplacementMap): BufferedImage = 
        val w = img.getWidth()
        val h = img.getHeight()
        
        val g: Graphics2D = img.createGraphics()

        val copy: BufferedImage = new BufferedImage(w, h, img.getType())
        val g_copy: Graphics2D = copy.createGraphics()
        for i <- 0 until w do
            for j <- 0 until h do
                val rgba: Int = img.getRGB(i, j)

                val blue = rgba & 0xff;
                val green = (rgba & 0xff00) >> 8
                val red = (rgba & 0xff0000) >> 16
                val alpha = (rgba & 0xff000000) >>> 24

                val newX = i + displacementMap.getDisplacementForX(i, j)
                val newY = j + displacementMap.getDisplacementForY(i, j)
                //println(s"old: ($i,$j), new: ($newX,$newY)")

                g_copy.setColor(Color(red, green, blue, alpha))
                //g.setColor(Color.black)
                //g.drawRect(i, j, 1, 1) //these are for testing
                g_copy.drawRect(newX, newY, 1, 1)

        //g.drawImage(copy, 0, 0, null)
        g.dispose()
        g_copy.dispose()
        copy

end TextureOps