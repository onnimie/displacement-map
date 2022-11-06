
import java.awt.image.BufferedImage
import scala.collection.mutable.ArrayBuffer




class DisplacementMap(val width: Int, val height: Int):
  
    //val valueRange: Range = Range(0, 256)

    val grid = Array.fill[Int](width, height)(0) //init grid with 0's

    def getRaw(x: Int, y: Int): Int = grid(x)(y)

    // value between -1 and 1 (corresponding for values between 0 and 255)
    def getFactor(x: Int, y: Int): Double = (getRaw(x, y) - 128).toDouble / 128.0

    // TODO: create some functions for editing the grid of a displacement map

end DisplacementMap