package com.charleskorn.shokunin.gameofpawns

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.entry
import ch.tutteli.atrium.api.cc.en_GB.exactly
import ch.tutteli.atrium.api.cc.en_GB.inAnyOrder
import ch.tutteli.atrium.api.cc.en_GB.none
import ch.tutteli.atrium.api.cc.en_GB.notOrAtMost
import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.verbs.assert
import com.charleskorn.shokunin.gameofpawns.model.Board
import com.charleskorn.shokunin.gameofpawns.model.Coordinates
import com.charleskorn.shokunin.gameofpawns.model.OccupiedSquare
import com.charleskorn.shokunin.gameofpawns.model.Piece
import com.charleskorn.shokunin.gameofpawns.model.Player
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.util.Random

object BoardGeneratorSpec : Spek({
    describe("a board generator") {
        val seedGenerator = Random()

        repeat(1000) {
            val seed = seedGenerator.nextLong()

            describe("given the seed $seed") {
                val generator = BoardGenerator(Random(seed))
                val board = generator.generate()
                val occupiedSquares = board.ranks
                        .flatten()
                        .filterIsInstance<OccupiedSquare>()

                it("generates a board with a white king") {
                    assert(occupiedSquares).containsSingle {
                        property(OccupiedSquare::player).toBe(Player.White)
                        property(OccupiedSquare::piece).toBe(Piece.King)
                    }
                }

                it("generates a board with a black king") {
                    assert(occupiedSquares).containsSingle {
                        property(OccupiedSquare::player).toBe(Player.Black)
                        property(OccupiedSquare::piece).toBe(Piece.King)
                    }
                }

                it("generates a board where the white king and black king are not adjacent") {
                    val whiteKing = board.coordinatesOfKing(Player.White)
                    val blackKing = board.coordinatesOfKing(Player.Black)

                    assert(whiteKing.isAdjacentTo(blackKing)).toBe(false)
                }

                it("generates a board where there are no white pawns in the promotion square (eighth rank)") {
                    val squaresInEighthRank = board.ranks.last()

                    assert(squaresInEighthRank).none { toBe(OccupiedSquare(Player.White, Piece.Pawn)) }
                }

                it("generates a board where there are no black pawns in the promotion square (first rank)") {
                    val squaresInFirstRank = board.ranks.first()

                    assert(squaresInFirstRank).none { toBe(OccupiedSquare(Player.Black, Piece.Pawn)) }
                }

                mapOf(
                        Piece.King to 1,
                        Piece.Queen to 1,
                        Piece.Rook to 2,
                        Piece.Bishop to 2,
                        Piece.Knight to 2,
                        Piece.Pawn to 8
                ).forEach { piece, maximumAllowedPerColour ->
                    val pluralisedDescription = if (maximumAllowedPerColour == 1) piece.name.toLowerCase() else "${piece.name.toLowerCase()}s"

                    it("generates a board where there is no more than $maximumAllowedPerColour white $pluralisedDescription") {
                        assert(occupiedSquares).contains.inAnyOrder.notOrAtMost(maximumAllowedPerColour).entry { toBe(OccupiedSquare(Player.White, piece)) }
                    }

                    it("generates a board where there is no more than $maximumAllowedPerColour black $pluralisedDescription") {
                        assert(occupiedSquares).contains.inAnyOrder.notOrAtMost(maximumAllowedPerColour).entry { toBe(OccupiedSquare(Player.Black, piece)) }
                    }
                }
            }
        }
    }
})

private fun <E : Any, T : Iterable<E>> Assert<T>.containsSingle(assertionCreator: Assert<E>.() -> Unit) = this.contains.inAnyOrder.exactly(1).entry(assertionCreator)

private fun Board.coordinatesOfKing(player: Player): Coordinates {
    this.ranks.forEachIndexed { rankIndex, rank ->
        rank.forEachIndexed { fileIndex, square ->
            if (square == OccupiedSquare(player, Piece.King)) {
                return Coordinates(rankIndex, fileIndex)
            }
        }
    }

    throw IllegalArgumentException("Could not find a king for player $player.")
}
