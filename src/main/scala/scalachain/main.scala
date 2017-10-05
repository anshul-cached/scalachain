/**
  * Created by yankee on 5/10/17.
  */
package scalachain

import java.util.Calendar

object main {

  def main(args: Array[String]) : Unit = {
    println(calculateHash(0,"abc","date",Map("A"->1l)))
  }

  def calculateHash(index:Long, previousHash:String, timestamp: String, data:Map[String,Long]) : String = {
    def sha256Hash(text: String) : String = String.format("%064x", new java.math.BigInteger(1, java.security.MessageDigest.getInstance("SHA-256").digest(text.getBytes("UTF-8"))))
    sha256Hash(index+previousHash+timestamp+data)
  }

  def generateNextBlock = {
    val previousBlock = getLatestBlock()
    val nextIndex = previousBlock.index+1
    val nextTimestamp = Calendar.getInstance().getTime().toString


  }

}