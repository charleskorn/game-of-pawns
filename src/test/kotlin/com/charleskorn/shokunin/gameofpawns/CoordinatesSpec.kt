package com.charleskorn.shokunin.gameofpawns

import ch.tutteli.atrium.api.cc.en_GB.toBe
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import ch.tutteli.atrium.verbs.assert

object CoordinatesSpec : Spek({
    describe("a set of coordinates") {
        val coordinates = Coordinates(3, 3)

        mapOf(
                "diagonally to the top left of it" to Coordinates(2, 2),
                "to the left of it" to Coordinates(3, 2),
                "diagonally to the bottom left of it" to Coordinates(4, 2),
                "above it" to Coordinates(2, 3),
                "below it" to Coordinates(4, 3),
                "diagonally to the top right of it" to Coordinates(2, 4),
                "to the right of it" to Coordinates(3, 4),
                "diagonally to the bottom right of it" to Coordinates(4, 4)
        ).forEach { description, other ->
            it("is adjacent to the square $description") {
                assert(coordinates.isAdjacentTo(other)).toBe(true)
            }
        }

        mapOf(
                "two squares diagonally to the top left of it" to Coordinates(1, 1),
                "two squares to the left of it" to Coordinates(3, 1),
                "two squares diagonally to the bottom left of it" to Coordinates(5, 1),
                "two squares above it" to Coordinates(1, 3),
                "two squares below it" to Coordinates(5, 3),
                "two squares diagonally to the top right of it" to Coordinates(1, 5),
                "two squares to the right of it" to Coordinates(3, 5),
                "two squares diagonally to the bottom right of it" to Coordinates(5, 5)
        ).forEach { description, other ->
            it("is not adjacent to the square $description") {
                assert(coordinates.isAdjacentTo(other)).toBe(false)
            }
        }
    }
})
