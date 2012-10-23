package com.jamesrthompson.games

object GamePlay extends App {

  implicit def charToPos(s: String) : Pos = Pos(matchChar(s(0)), matchChar(s(1)))

  def matchChar(c: Char) : Int = c.toLowerCase match {
    case 'a' => 0
    case 'b' => 1
    case 'c' => 2
    case 'd' => 3
    case 'e' => 4
    case 'f' => 5
    case 'g' => 6
    case 'h' => 7
    case '1' => 0
    case '2' => 1
    case '3' => 2
    case '4' => 3
    case '5' => 4
    case '6' => 5
    case '7' => 6
    case '8' => 7
  }
  
  lazy val initialBoardStr =  List( "rnbqkbnr",
                                    "pppppppp",
                                    "........",
                                    "........",
                                    "........",
                                    "........",
                                    "PPPPPPPP",
                                    "RNBQKBNR")

  lazy val mateBoard1 =       List( "...q..k.",
                                    "....b.p.",
                                    "....p...",
                                    "...pP..Q",
                                    "...P.r..",
                                    "..P....B",
                                    "..P..P.P",
                                    "......K.")

    lazy val mateBoard2 =     List( "........",
                                    ".R...k..",
                                    "R.......",
                                    "........",
                                    "........",
                                    "........",
                                    "........",
                                    ".....K..")

  // Pick input board list of rows(strings) and a starting color (White, or Black)
  // val board = BoardBuilder.makeBoard(mateBoard1, White)
  // val board = BoardBuilder.makeBoard(mateBoard2, Black) 
  val board = BoardBuilder.makeBoard(initialBoardStr, White)

  val start = System.nanoTime

  Minimax.gameComp(board) // Run the game until a player wins!

  println("\nTook " + ((System.nanoTime - start) / 1e9).toString + " s")

}