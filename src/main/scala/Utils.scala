package com.jamesrthompson.games

object Utils {

  def addPair(p1: Pos, p2: Pos) = Pos(p1.x + p2.x, p1.y + p2.y)
  
  def multPair(n: Int, p:Pos) = Pos(n * p.x, n * p.y)
  
  def insideBoard(p: Pos) = outsideBoard(p) match {
    case true => false
    case false => true
  }
  
  def outsideBoard(p: Pos) = p.x < 0 || p.y < 0 || p.x > 7 || p.y > 7 
}