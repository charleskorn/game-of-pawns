package com.charleskorn.shokunin.gameofpawns.printing

import com.charleskorn.shokunin.gameofpawns.model.Board
import com.charleskorn.shokunin.gameofpawns.model.BoardSquare
import com.charleskorn.shokunin.gameofpawns.model.EmptySquare
import com.charleskorn.shokunin.gameofpawns.model.OccupiedSquare
import com.charleskorn.shokunin.gameofpawns.model.Player

class PlainTextPrinter : BoardPrinter {
    override fun print(board: Board): String {
        return board.ranks.reversed().joinToString("\n") { rank ->
            rank.joinToString("  ") { printSquare(it) }
        }
    }

    private fun printSquare(square: BoardSquare): String {
        return when (square) {
            is EmptySquare -> "."
            is OccupiedSquare -> printOccupiedSquare(square)
        }
    }

    private fun printOccupiedSquare(square: OccupiedSquare): String {
        val abbreviation = square.piece.abbreviation

        return when (square.player) {
            Player.White -> abbreviation.toUpperCase()
            Player.Black -> abbreviation.toLowerCase()
        }
    }
}
