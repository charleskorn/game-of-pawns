package com.charleskorn.shokunin.gameofpawns.printing

import com.charleskorn.shokunin.gameofpawns.model.Board
import com.charleskorn.shokunin.gameofpawns.model.BoardSquare
import com.charleskorn.shokunin.gameofpawns.model.EmptySquare
import com.charleskorn.shokunin.gameofpawns.model.OccupiedSquare
import com.charleskorn.shokunin.gameofpawns.model.Piece
import com.charleskorn.shokunin.gameofpawns.model.Player

class PrettyPrinter : BoardPrinter {
    private val ESC = "\u001B"
    private val whiteBackground = "${ESC}[48;2;255;255;255m"
    private val whiteForeground = "${ESC}[38;2;160;160;160m"
    private val blackBackground = "${ESC}[48;2;85;85;85m"
    private val blackForeground = "${ESC}[38;2;0;0;0m"
    private val reset = "${ESC}[0m"

    override fun print(board: Board): String {
        var index = 0

        return board.ranks.reversed().joinToString("\n") { rank ->
            index++

            rank.joinToString("") { printSquare(it, index++) } + reset
        }
    }

    private fun printSquare(square: BoardSquare, index: Int): String {
        val display = squareMap[square]

        if (index % 2 == 0) {
            return "$blackBackground$display"
        } else {
            return "$whiteBackground$display"
        }
    }

    private val squareMap = mapOf(
            EmptySquare to "  ",
            OccupiedSquare(Player.White, Piece.King) to "$whiteForeground\u2654 ",
            OccupiedSquare(Player.White, Piece.Queen) to "$whiteForeground\u2655 ",
            OccupiedSquare(Player.White, Piece.Rook) to "$whiteForeground\u2656 ",
            OccupiedSquare(Player.White, Piece.Bishop) to "$whiteForeground\u2657 ",
            OccupiedSquare(Player.White, Piece.Knight) to "$whiteForeground\u2658 ",
            OccupiedSquare(Player.White, Piece.Pawn) to "$whiteForeground\u2659 ",
            OccupiedSquare(Player.Black, Piece.King) to "$blackForeground\u265A ",
            OccupiedSquare(Player.Black, Piece.Queen) to "$blackForeground\u265B ",
            OccupiedSquare(Player.Black, Piece.Rook) to "$blackForeground\u265C ",
            OccupiedSquare(Player.Black, Piece.Bishop) to "$blackForeground\u265D ",
            OccupiedSquare(Player.Black, Piece.Knight) to "$blackForeground\u265E ",
            OccupiedSquare(Player.Black, Piece.Pawn) to "$blackForeground\u265F "
    )
}
