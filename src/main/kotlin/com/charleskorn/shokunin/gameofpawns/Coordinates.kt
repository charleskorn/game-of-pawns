package com.charleskorn.shokunin.gameofpawns

data class Coordinates(val rank: Int, val file: Int) {
    fun isAdjacentTo(other: Coordinates): Boolean = Math.abs(rank - other.rank) <= 1 && Math.abs(file - other.file) <= 1
}
