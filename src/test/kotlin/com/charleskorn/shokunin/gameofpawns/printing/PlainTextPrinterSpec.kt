package com.charleskorn.shokunin.gameofpawns.printing

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.verbs.assert
import com.charleskorn.shokunin.gameofpawns.model.Board
import com.charleskorn.shokunin.gameofpawns.model.Piece
import com.charleskorn.shokunin.gameofpawns.model.Player
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object PlainTextPrinterSpec : Spek({
    describe("a plain text printer") {
        val printer = PlainTextPrinter()

        describe("given an empty board") {
            val board = Board()
            val printed = printer.print(board)

            it("prints the board as an 8x8 grid of dots") {
                assert(printed).toBe("""
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                """.trimIndent())
            }
        }

        describe("given a board with a single white piece on it") {
            val board = Board().withPiece(0, 0, Player.White, Piece.Rook)
            val printed = printer.print(board)

            it("prints the board as an 8x8 grid of dots with the piece in the correct location") {
                assert(printed).toBe("""
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    R  .  .  .  .  .  .  .
                """.trimIndent())
            }
        }

        describe("given a board with a single black piece on it") {
            val board = Board().withPiece(0, 0, Player.Black, Piece.Rook)
            val printed = printer.print(board)

            it("prints the board as an 8x8 grid of dots with the piece in the correct location") {
                assert(printed).toBe("""
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    r  .  .  .  .  .  .  .
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

            it("prints the board as an 8x8 grid of dots with each of the pieces in the correct location") {
                assert(printed).toBe("""
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    P  N  B  R  Q  K  .  .
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

            it("prints the board as an 8x8 grid of dots with each of the pieces in the correct location") {
                assert(printed).toBe("""
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    .  .  .  .  .  .  .  .
                    p  n  b  r  q  k  .  .
                """.trimIndent())
            }
        }

        describe("given a board matching the sample given") {
            val board = Board()
                    .withPiece(7, 3, Player.White, Piece.Queen)
                    .withPiece(7, 6, Player.Black, Piece.Bishop)
                    .withPiece(5, 0, Player.White, Piece.Rook)
                    .withPiece(5, 3, Player.Black, Piece.Bishop)
                    .withPiece(5, 6, Player.Black, Piece.Rook)
                    .withPiece(4, 4, Player.Black, Piece.Queen)
                    .withPiece(4, 7, Player.White, Piece.King)
                    .withPiece(3, 4, Player.White, Piece.Pawn)
                    .withPiece(2, 1, Player.White, Piece.Bishop)
                    .withPiece(2, 2, Player.Black, Piece.King)
                    .withPiece(2, 3, Player.White, Piece.Pawn)
                    .withPiece(1, 1, Player.Black, Piece.Knight)
                    .withPiece(0, 3, Player.White, Piece.Knight)

            val printed = printer.print(board)

            it("prints the board as an 8x8 grid matching the sample given") {
                assert(printed).toBe("""
                    .  .  .  Q  .  .  b  .
                    .  .  .  .  .  .  .  .
                    R  .  .  b  .  .  r  .
                    .  .  .  .  q  .  .  K
                    .  .  .  .  P  .  .  .
                    .  B  k  P  .  .  .  .
                    .  n  .  .  .  .  .  .
                    .  .  .  N  .  .  .  .
                """.trimIndent())
            }
        }
    }
})
