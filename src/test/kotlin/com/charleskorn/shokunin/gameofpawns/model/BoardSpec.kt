package com.charleskorn.shokunin.gameofpawns.model

import ch.tutteli.atrium.api.cc.en_GB.all
import ch.tutteli.atrium.api.cc.en_GB.hasSize
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.verbs.assert
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object BoardSpec : Spek({
    describe("a chess board") {
        describe("an empty board") {
            val board = Board()

            it("should have eight ranks") {
                assert(board.ranks).hasSize(8)
            }

            it("should have eight files") {
                assert(board.ranks).all { hasSize(8) }
            }

            it("should not have pieces in any square") {
                assert(board.ranks).all { all { toBe(EmptySquare) } }
            }
        }

        describe("a board with a single piece placed") {
            val board = Board().withPiece(rank = 2, file = 4, player = Player.White, piece = Piece.Rook)

            it("should have eight ranks") {
                assert(board.ranks).hasSize(8)
            }

            it("should have eight files") {
                assert(board.ranks).all { hasSize(8) }
            }

            it("should have the piece in the expected location") {
                assert(board.ranks[2][4]).toBe(OccupiedSquare(Player.White, Piece.Rook))
            }

            it("should not have pieces in any other square") {
                assert(board.ranks.slice((0..1) + (3..7))).all { all { toBe(EmptySquare) } }
                assert(board.ranks[2].slice((0..3) + (5..7))).all { toBe(EmptySquare) }
            }
        }
    }
})
