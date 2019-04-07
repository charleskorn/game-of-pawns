package com.charleskorn.shokunin.gameofpawns

class FENPrinter : BoardPrinter {
    override fun print(board: Board): String {
        val ranks = board.ranks
                .reversed()
                .map { printRank(it) }
                .joinToString("/")

        return ranks + " w - - 0 1"
    }

    private fun printRank(rank: List<BoardSquare>): String = rank
            .fold("") { acc, square ->
                when (square) {
                    is OccupiedSquare -> acc + printOccupiedSquare(square)
                    is EmptySquare -> printEmptySquare(acc)
                }
            }

    private fun printOccupiedSquare(square: OccupiedSquare): String = when (square.player) {
        Player.White -> square.piece.abbreviation.toUpperCase()
        Player.Black -> square.piece.abbreviation.toLowerCase()
    }

    private fun printEmptySquare(acc: String): String = when {
        acc.isEmpty() || !acc.last().isDigit() -> acc + "1"
        else -> {
            val unchanged = acc.dropLast(1)
            val lastEmptyCount = acc.takeLast(1).toInt()
            val newEmptyCount = lastEmptyCount + 1

            unchanged + newEmptyCount
        }
    }
}
