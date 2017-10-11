/**
  * Created by yankee on 5/10/17.
  */
package scalachain

import java.util.Calendar

import scala.collection.mutable.ListBuffer

object main {

  val blockchain: ListBuffer[Block] = ListBuffer[Block]()

  def main(args: Array[String]) : Unit = {

    //val blockchain: ListBuffer[Block] = ListBuffer[Block]()
    //println(calculateHash(0,"abc","date",Map("A"->1l)))
    blockchain += getGenesisBlock
    print("Genesis:"+ blockchain)
  }

  def calculateHash(index:Long, previousHash:String, timestamp: String, data:Map[String,Long]) : String = {
    def sha256Hash(text: String) : String = String.format("%064x", new java.math.BigInteger(1, java.security.MessageDigest.getInstance("SHA-256").digest(text.getBytes("UTF-8"))))
    sha256Hash(index+previousHash+timestamp+data)
  }

  def generateNextBlock = {
    val previousBlock = getLatestBlock()
    val nextIndex = previousBlock.index+1
    val nextTimestamp = Calendar.getInstance().getTime().toString
    val nextHash = calculateHash(nextIndex,previousBlock.hash,nextTimestamp,blockData)
    new Block(nextIndex,previousBlock.hash,nextTimestamp,blockData,nextHash)
  }

  def getGenesisBlock = {
    new Block(0,"0","2017-10-05 10:10:10",Map("Genesis Block"->0),"9f3b823888954db0e6d16791f153d3741879d0cf56b99c0712f91c8e8e2836a2")
  }

  def isValidNewBlock(newBlock:Block, previousBlock:Block):Boolean = {
    if (previousBlock.index+1 != newBlock.index) {
      println("Invalid Index")
      return false
    } else if (previousBlock.hash != newBlock.previousHash){
      println("Invalid PreviousHash")
      return false
    } else if (calculateHashForBlock(newBlock) != newBlock.hash) {
      println("Invalid hash: "+ calculateHashForBlock(newBlock)+' '+newBlock.hash)
      return false
    }
    true
  }

  def replaceChain(newBlocks:ListBuffer[Block]) = {
    if(isValidChain(newBlocks) && newBlocks.length > blockchain.length) {
      println("Received blockchain is valid. Replacing current blockchain with received blockchain")
      val newBlockchain = newBlocks.clone()
    } else {
      println("Received blockchain invalid")
    }
  }

  //Incomplete
  def isValidChain(blockchain: ListBuffer[Block]):Boolean = {
    blockchain.forall()

  }

  def calculateHashForBlock(block:Block) : String = {
    calculateHash(block.index,block.previousHash,block.timestamp,block.data)
  }

  def addBlock(newBlock:Block) = {
    if(isValidNewBlock(newBlock,getLatestBlock())) {
      blockchain.append(newBlock)
    }
  }
  

}