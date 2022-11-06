
import java.awt.image.BufferedImage
import scala.collection.mutable.ArrayBuffer
import java.awt.Graphics2D
import java.awt.Color

import scala.math.round


class DisplacementMap(val width: Int, val height: Int, var maxHorizontalDisplacement: Int = 0, var maxVerticalDisplacement: Int = 30):
  
    //val valueRange: Range = Range(0, 256)

    private val grid = Array.fill[Int](width, height)(0) //init grid with 0's

    def getRaw(x: Int, y: Int): Int = grid(x)(y)

    // value between -1 and 1 (corresponding for values between 0 and 255)
    def getFactor(x: Int, y: Int): Float = 
        val ret = (getRaw(x, y) - 128).toFloat / 128.0f
        if ret > 1.0f || ret < -1.0f then
            println("FAILFAIL: ret = " + ret)
            assert(false)
        ret

    def getDisplacementForX(x: Int, y: Int): Int = round(getFactor(x, y) * maxHorizontalDisplacement)
    def getDisplacementForY(x: Int, y: Int): Int = round(getFactor(x, y) * maxVerticalDisplacement)

    def getBufferedImage: BufferedImage =
        val img: BufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val g: Graphics2D = img.createGraphics()
        for i <- 0 until width do
            for j <- 0 until height do
                val value = getRaw(i, j)
                val color = Color(value, value, value)
                g.setColor(color)
                g.drawRect(i, j, 1, 1)
        g.dispose()
        img



    // TODO: create some functions for editing the grid of a displacement map

    def makeIntoGradient(): Unit = 
        val step_x = 255.0f / width
        val step_y = 255.0f / height

        for i <- 0 until width do
            for j <- 0 until height do
                grid(i)(j) = round(step_x * i)
                //grid(i)(j) = round(step_y * j)
    


end DisplacementMap