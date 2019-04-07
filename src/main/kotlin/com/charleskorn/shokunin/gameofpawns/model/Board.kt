package com.charleskorn.shokunin.gameofpawns.model

class Board private constructor(val ranks: List<List<BoardSquare>>) {
    constructor() : this(List(8) {
        List(8) { EmptySquare }
    })

    fun withPiece(rank: Int, file: Int, player: Player, piece: Piece): Board {
        val newSquare = OccupiedSquare(player, piece)
        val newRank = ranks[rank].replaceAt(file, newSquare)
        val newRanks = ranks.replaceAt(rank, newRank)

        return Board(newRanks)
    }

    private fun <T> List<T>.replaceAt(index: Int, newValue: T): List<T> {
        return this.mapIndexed { i, existingValue ->
            if (i == index) {
                newValue
            } else {
                existingValue
            }
        }
    }
}
