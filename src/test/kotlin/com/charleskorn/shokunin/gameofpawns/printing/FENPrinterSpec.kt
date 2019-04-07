package com.charleskorn.shokunin.gameofpawns.printing

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.verbs.assert
import com.charleskorn.shokunin.gameofpawns.model.Board
import com.charleskorn.shokunin.gameofpawns.model.Piece
import com.charleskorn.shokunin.gameofpawns.model.Player
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object FENPrinterSpec : Spek({
    describe("a FEN printer") {
        val printer = FENPrinter()

        describe("given an empty board") {
            val board = Board()
            val printed = printer.print(board)

            it("prints the board in the expected format") {
                assert(printed).toBe("8/8/8/8/8/8/8/8 w - - 0 1")
            }
        }

        describe("given a board with a single piece on it at the beginning of a rank") {
            val board = Board().withPiece(0, 0, Player.White, Piece.Rook)
            val printed = printer.print(board)

            it("prints the board in the expected format") {
                assert(printed).toBe("8/8/8/8/8/8/8/R7 w - - 0 1")
            }
        }

        describe("given a board with a single piece on it at the end of a rank") {
            val board = Board().withPiece(0, 7, Player.White, Piece.Rook)
            val printed = printer.print(board)

            it("prints the board in the expected format") {
                assert(printed).toBe("8/8/8/8/8/8/8/7R w - - 0 1")
            }
        }

        describe("given a board with a single piece on it in the middle of a rank") {
            val board = Board().withPiece(0, 5, Player.White, Piece.Rook)
            val printed = printer.print(board)

            it("prints the board in the expected format") {
                assert(printed).toBe("8/8/8/8/8/8/8/5R2 w - - 0 1")
            }
        }

        describe("given a board with two consecutive pieces for the same player") {
            val board = Board()
                    .withPiece(0, 5, Player.White, Piece.Rook)
                    .withPiece(0, 6, Player.White, Piece.Rook)

            val printed = printer.print(board)

            it("prints the board in the expected format") {
                assert(printed).toBe("8/8/8/8/8/8/8/5RR1 w - - 0 1")
            }
        }

        describe("given a board with each of the different pieces for each player") {
            val board = Board()
                    .withPiece(0, 0, Player.White, Piece.Pawn)
                    .withPiece(0, 1, Player.White, Piece.Knight)
                    .withPiece(0, 2, Player.White, Piece.Bishop)
                    .withPiece(0, 3, Player.White, Piece.Rook)
                    .withPiece(0, 4, Player.White, Piece.Queen)
                    .withPiece(0, 5, Player.White, Piece.King)
                    .withPiece(1, 0, Player.Black, Piece.Pawn)
                    .withPiece(1, 1, Player.Black, Piece.Knight)
                    .withPiece(1, 2, Player.Black, Piece.Bishop)
                    .withPiece(1, 3, Player.Black, Piece.Rook)
                    .withPiece(1, 4, Player.Black, Piece.Queen)
                    .withPiece(1, 5, Player.Black, Piece.King)

            val printed = printer.print(board)

            it("prints the board in the expected format") {
                assert(printed).toBe("8/8/8/8/8/8/pnbrqk2/PNBRQK2 w - - 0 1")
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

            it("prints the board in the expected format, matching the sample given") {
                assert(printed).toBe("3Q2b1/8/R2b2r1/4q2K/4P3/1BkP4/1n6/3N4 w - - 0 1")
            }
        }
    }
})
