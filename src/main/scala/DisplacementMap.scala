
import java.awt.image.BufferedImage
import scala.collection.mutable.ArrayBuffer

import scala.math.round


class DisplacementMap(val width: Int, val height: Int, var maxHorizontalDisplacement: Int = 30, var maxVerticalDisplacement: Int = 30):
  
    //val valueRange: Range = Range(0, 256)

    private val grid = Array.fill[Int](width, height)(255) //init grid with 0's

    def getRaw(x: Int, y: Int): Int = grid(x)(y)

    // value between -1 and 1 (corresponding for values between 0 and 255)
    def getFactor(x: Int, y: Int): Float = 
        val ret = (getRaw(x, y) - 128).toFloat / 128.0f
        assert(ret <= 1f)
        assert(ret >= -1f)
        ret

    def getDisplacementForX(x: Int, y: Int): Int = round(getFactor(x, y) * maxHorizontalDisplacement)
    def getDisplacementForY(x: Int, y: Int): Int = round(getFactor(x, y) * maxVerticalDisplacement)


    // TODO: create some functions for editing the grid of a displacement map

end DisplacementMap