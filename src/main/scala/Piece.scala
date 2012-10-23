package com.jamesrthompson.games

abstract class PieceColor
  case object White extends PieceColor 
  case object Black extends PieceColor

abstract class PieceType
  case object Pawn extends PieceType
  case object Knight extends PieceType
  case object Bishop extends PieceType
  case object Rook extends PieceType
  case object Queen extends PieceType
  case object King extends PieceType

case class Pos(x: Int, y: Int) {
  override def toString = "x = " + x.toString + ", y = " + y.toString
}

class Piece(val pcolor: PieceColor, val ptype: PieceType, var pos: Pos) {

	lazy val straight = List[Pos](Pos(0,1), Pos(0,-1), Pos(1,0), Pos(-1,0))
	lazy val diagonal = List[Pos](Pos(1,1), Pos(-1,-1), Pos(1,-1), Pos(-1,1))
	lazy val infinity = 1000

	lazy val moves = ptype match {
		case Pawn 	=> List[Pos]()
    case Knight => List[Pos](Pos(2,1), Pos(1,2), Pos(2,-1), Pos(-1,2), Pos(1,-2), Pos(-2,1), Pos(-2,-1), Pos(-1,-2))
    case Bishop => diagonal
    case Rook 	=> straight
    case Queen 	=> straight ++ diagonal
    case King 	=> straight ++ diagonal
	}

  lazy val pieceScore = ptype match {
		case Pawn 	=> 1
		case Rook 	=> 5
		case Knight => 3
		case Bishop => 3
		case Queen	=> 9
		case King		=> infinity
	}

	lazy val direction = pcolor match {
		case White 	=> -1
		case Black 	=> 1
	}

  def moveTo(newPos: Pos) {
		pos = newPos
		()
	}

	def possibleMoveLocations : List[Pos] = moves.map(m => Utils.addPair(this.pos, m)).view.filter(Utils.insideBoard).toList

	def printType = println(ptype)
	def printColor = println(pcolor)
	def printpos = println(pos)
	def allInformation : String = "Piece Type : " + ptype + ", color : " + pcolor + ", pos : " + pos

	override def toString = ptype match {
    case King => getColor("K")
    case Knight => getColor("N")
    case _ => getColor()
  }

  private def getColor(t: String = ptype.toString) = {
    pcolor match {
      case White => " " + t.toUpperCase.substring(0,1) + " "
      case Black => " " + t.toLowerCase.substring(0,1) + " "
    }
  }
}