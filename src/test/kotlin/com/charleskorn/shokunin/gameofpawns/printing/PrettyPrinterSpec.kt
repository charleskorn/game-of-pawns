package com.charleskorn.shokunin.gameofpawns.printing

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.verbs.assert
import com.charleskorn.shokunin.gameofpawns.model.Board
import com.charleskorn.shokunin.gameofpawns.model.Piece
import com.charleskorn.shokunin.gameofpawns.model.Player
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object PrettyPrinterSpec : Spek({
    describe("a console printer") {
        val printer = PrettyPrinter()
        val ESC = "\u001B"
        val whiteBackground = "${ESC}[48;2;255;255;255m"
        val whiteForeground = "${ESC}[38;2;160;160;160m"
        val blackBackground = "${ESC}[48;2;85;85;85m"
        val blackForeground = "${ESC}[38;2;0;0;0m"
        val reset = "${ESC}[0m"

        val twoSpaces = "  "
        val oneSpace = " "
        val whiteKing = "$whiteForeground\u2654"
        val whiteQueen = "$whiteForeground\u2655"
        val whiteRook = "$whiteForeground\u2656"
        val whiteBishop = "$whiteForeground\u2657"
        val whiteKnight = "$whiteForeground\u2658"
        val whitePawn = "$whiteForeground\u2659"
        val blackKing = "$blackForeground\u265A"
        val blackQueen = "$blackForeground\u265B"
        val blackRook = "$blackForeground\u265C"
        val blackBishop = "$blackForeground\u265D"
        val blackKnight = "$blackForeground\u265E"
        val blackPawn = "$blackForeground\u265F"

        describe("given an empty board") {
            val board = Board()
            val printed = printer.print(board)

            it("prints the board as an 8x8 grid") {
                assert(printed).toBe("""
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                """.trimIndent())
            }
        }

        describe("given a board with a single white piece on it") {
            val board = Board().withPiece(0, 0, Player.White, Piece.Rook)
            val printed = printer.print(board)

            it("prints the board as an 8x8 grid with the piece in the correct location") {
                assert(printed).toBe("""
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$whiteRook$oneSpace$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                """.trimIndent())
            }
        }

        describe("given a board with a single black piece on it") {
            val board = Board().withPiece(0, 0, Player.Black, Piece.Rook)
            val printed = printer.print(board)

            it("prints the board as an 8x8 grid with the piece in the correct location") {
                assert(printed).toBe("""
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$blackRook$oneSpace$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                """.trimIndent())
            }
        }

        describe("given a board with each of the white pieces on it") {
            val board = Board()
                    .withPiece(0, 0, Player.White, Piece.Pawn)
                    .withPiece(0, 1, Player.White, Piece.Knight)
                    .withPiece(0, 2, Player.White, Piece.Bishop)
                    .withPiece(0, 3, Player.White, Piece.Rook)
                    .withPiece(0, 4, Player.White, Piece.Queen)
                    .withPiece(0, 5, Player.White, Piece.King)

            val printed = printer.print(board)

            it("prints the board as an 8x8 grid with each of the pieces in the correct location") {
                assert(printed).toBe("""
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$whitePawn$oneSpace$whiteBackground$whiteKnight$oneSpace$blackBackground$whiteBishop$oneSpace$whiteBackground$whiteRook$oneSpace$blackBackground$whiteQueen$oneSpace$whiteBackground$whiteKing$oneSpace$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                """.trimIndent())
            }
        }

        describe("given a board with each of the black pieces on it") {
            val board = Board()
                    .withPiece(0, 0, Player.Black, Piece.Pawn)
                    .withPiece(0, 1, Player.Black, Piece.Knight)
                    .withPiece(0, 2, Player.Black, Piece.Bishop)
                    .withPiece(0, 3, Player.Black, Piece.Rook)
                    .withPiece(0, 4, Player.Black, Piece.Queen)
                    .withPiece(0, 5, Player.Black, Piece.King)

            val printed = printer.print(board)

            it("prints the board as an 8x8 grid with each of the pieces in the correct location") {
                assert(printed).toBe("""
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                    $whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$whiteBackground$twoSpaces$blackBackground$twoSpaces$reset
                    $blackBackground$blackPawn$oneSpace$whiteBackground$blackKnight$oneSpace$blackBackground$blackBishop$oneSpace$whiteBackground$blackRook$oneSpace$blackBackground$blackQueen$oneSpace$whiteBackground$blackKing$oneSpace$blackBackground$twoSpaces$whiteBackground$twoSpaces$reset
                """.trimIndent())
            }
        }
    }
})
