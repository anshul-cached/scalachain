
package scalachain

import scala.annotation.tailrec

case class Block(index:Long, previousHash:String, timestamp: Long, data:Map[String,Long], hash:String)


object Block{
  def apply()=List(GenesisBlock)


  def validateBlock(newBlock:Block,previousBlock:Block) ={
    previousBlock.index + 1 == newBlock.index &&
      previousBlock.hash == newBlock.previousHash &&
      HashCalculator(newBlock.index,newBlock.previousHash,newBlock.timestamp,newBlock.data) == newBlock.hash
  }


  @tailrec
  def validateChain( chain: List[Block] ): Boolean = chain match {
    case singleBlock :: Nil if singleBlock == GenesisBlock => true
    case head :: mid :: tail if validateBlock(head, mid) => validateChain(mid :: tail)
    case _ => false
  }

}
















