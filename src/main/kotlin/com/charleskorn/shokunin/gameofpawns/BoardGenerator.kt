package com.charleskorn.shokunin.gameofpawns

import com.charleskorn.shokunin.gameofpawns.model.Board
import com.charleskorn.shokunin.gameofpawns.model.Coordinates
import com.charleskorn.shokunin.gameofpawns.model.OccupiedSquare
import com.charleskorn.shokunin.gameofpawns.model.Piece
import com.charleskorn.shokunin.gameofpawns.model.Player
import java.util.Random

class BoardGenerator(private val random: Random = Random()) {
    private val rules = listOf(
            PieceGenerationRule(Piece.King, 1, minimumRequired = 1, acceptableCoordinates = ::acceptableCoordinatesForKing),
            PieceGenerationRule(Piece.Queen, 1),
            PieceGenerationRule(Piece.Rook, 2),
            PieceGenerationRule(Piece.Bishop, 2),
            PieceGenerationRule(Piece.Knight, 2),
            PieceGenerationRule(Piece.Pawn, 8, acceptableCoordinates = ::acceptableCoordinatesForPawn)
    )

    fun generate(): Board {
        val usedCoordinates = mutableSetOf<Coordinates>()

        return rules.fold(Board()) { board, rule -> addPiecesToBoard(board, rule, usedCoordinates) }
    }

    private fun addPiecesToBoard(startingBoard: Board, rule: PieceGenerationRule, usedCoordinates: MutableSet<Coordinates>): Board = Player.values()
            .fold(startingBoard) { board, player ->
                addPiecesToBoardForPlayer(board, rule, player, usedCoordinates)
            }

    private fun addPiecesToBoardForPlayer(startingBoard: Board, rule: PieceGenerationRule, player: Player, usedCoordinates: MutableSet<Coordinates>): Board {
        val count = rule.minimumRequired + random.nextInt(rule.maximumAllowed - rule.minimumRequired + 1)

        return (1..count)
                .fold(startingBoard) { board, _ ->
                    val coordinates = nextRandomCoordinates { proposedCoordinates -> proposedCoordinates !in usedCoordinates && rule.acceptableCoordinates(proposedCoordinates, player, board) }
                    usedCoordinates += coordinates

                    board.withPiece(coordinates.rank, coordinates.file, player, rule.piece)
                }
    }

    private fun nextRandomCoordinates(): Coordinates = Coordinates(random.nextInt(8), random.nextInt(8))

    private fun nextRandomCoordinates(predicate: (Coordinates) -> Boolean): Coordinates {
        while (true) {
            val coordinates = nextRandomCoordinates()

            if (predicate(coordinates)) {
                return coordinates
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun acceptableCoordinatesForKing(coordinates: Coordinates, player: Player, board: Board): Boolean {
        board.ranks.forEachIndexed { rankIndex, rank ->
            rank.forEachIndexed { fileIndex, square ->
                if (square is OccupiedSquare && square.piece == Piece.King && coordinates.isAdjacentTo(Coordinates(rankIndex, fileIndex))) {
                    return false
                }
            }
        }

        return true
    }

    @Suppress("UNUSED_PARAMETER")
    private fun acceptableCoordinatesForPawn(coordinates: Coordinates, player: Player, board: Board): Boolean = when (player) {
        Player.White -> coordinates.rank != 7
        Player.Black -> coordinates.rank != 0
    }

    private data class PieceGenerationRule(val piece: Piece, val maximumAllowed: Int, val minimumRequired: Int = 0, val acceptableCoordinates: AcceptableCoordinatesPredicate = { _, _, _ -> true })
}

private typealias AcceptableCoordinatesPredicate = (Coordinates, Player, Board) -> Boolean
