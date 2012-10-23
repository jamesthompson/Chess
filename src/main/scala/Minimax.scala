package com.jamesrthompson.games

case class GameTree(state: Board, tree: List[GameTree])

object Minimax {

	lazy val depth = 3
	lazy val threshold = 900
	lazy val greaterThan = (i1: Int, i2: Int) => i1 > i2
	lazy val lessThan = (i1: Int, i2: Int) => i1 < i2 

	def evalBoard(b: Board) = b.evalBoard

	def genGameTree(maxDepth: Int, board: Board) : GameTree = {
		maxDepth match {
			case 0 => GameTree(board, List[GameTree]())
			case _ => {
				val fs = finalState(board)
				if(fs) GameTree(board, List[GameTree]()) else {
					GameTree(board, board.nextBoardStates.par.map(ns => genGameTree(maxDepth - 1, ns)).toList)
				}
			}
		}	
	}

	def play(gameTree: GameTree) : Int = {
		if(gameTree.tree.isEmpty) evalBoard(gameTree.state) else {
			gameTree.state.state match {
				case White => gameTree.tree.map(play).max
				case Black => gameTree.tree.map(play).min
			}
		}
	}

	def doMove(board: Board) : Board = {
		val gt = genGameTree(depth, board)
		gt.tree.isEmpty match {
			case true => gt.state
			case false => {
				val compFunc = board.state match {
					case White => greaterThan
					case Black => lessThan
				}
				val list = gt.tree.par.map(x => (play(x), x.state)).toList
				findBest(gt.state.state, compFunc, list)._2
			}
		}
	}

	def findBest(color: PieceColor, compare: (Int, Int) => Boolean, list: List[(Int, Board)]) : (Int, Board) = {
		if(list.length == 1) list(0) else {
			winningState(color, list(0)._2) match {
				case true	=> list(0)
				case false => {
					val fb2 = findBest(color, compare, list.tail)
					if(compare(list(0)._1, fb2._1)) list(0) else fb2
				}
			}
		}
	}

	def winningState(color: PieceColor, board: Board) : Boolean = {
		val st = evalBoard(board)
		color match {
			case White => st > threshold
			case Black => st < -threshold
		}
	}

	def gameComp(board: Board) : List[Either[Board, String]] = {
		board.showBoard
		val sw = evalBoard(board)
		def getNextCol(board: Board) : Board = {
    	board.changeState
    	board
  	}
		if(sw > threshold) { 
			println("\n\nWhite wins!\n")
			List(Right("White wins!")) 
		} else if(sw < -threshold) { 
				println("\n\nBlack wins!\n")
				List(Right("Black wins!")) 
			} else Left(board) :: gameComp(doMove(getNextCol(board)))
	}

	def finalState(board: Board) = {
		val sw = evalBoard(board)
		if(sw > threshold || sw < -threshold) true else false
	}

}