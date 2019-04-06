package com.charleskorn.shokunin.gameofpawns

sealed class BoardSquare
object EmptySquare : BoardSquare()
data class OccupiedSquare(val player: Player, val piece: Piece) : BoardSquare()
