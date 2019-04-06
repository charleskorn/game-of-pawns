package com.charleskorn.shokunin.gameofpawns

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
