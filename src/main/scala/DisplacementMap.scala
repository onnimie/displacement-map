
import java.awt.image.BufferedImage
import scala.collection.mutable.ArrayBuffer
import java.awt.Graphics2D
import java.awt.Color

import scala.math.round
import scala.math.{max, min}


class DisplacementMap(val width: Int, val height: Int, var maxHorizontalDisplacement: Int = 1, var maxVerticalDisplacement: Int = 0):
  
    //val valueRange: Range = Range(0, 256)

    private val grid = Array.fill[Int](width, height)(0) //init grid with 0's
    private val helper_grid = Array.fill[Int](width, height)(0)

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

    private def copyHelperToGrid(): Unit =
        for i <- 0 until width do
            for j <- 0 until height do
                grid(i)(j) = helper_grid(i)(j)
                helper_grid(i)(j) = 0

    // TODO: create some functions for editing the grid of a displacement map

    def makeIntoGradient(): Unit = 
        val step_x = 255.0f / width * 2


        for i <- 0 until width do
            for j <- 0 until height do
                if i > width/2 then
                    grid(i)(j) = min(255, max(0, round(255.0f - step_x * (i-width/2))))
                else
                    grid(i)(j) = max(0, min(255, round(step_x * i)))

    end makeIntoGradient

    def scrollBy(step: Int): Unit = 
        for i <- 0 until width do
            for j <- 0 until height do
                val value = grid(i)(j)
                val newX = (i + step) % width
                //val newY = (j + step) % height
                helper_grid(newX)(j) = value
        copyHelperToGrid()
    end scrollBy


end DisplacementMap