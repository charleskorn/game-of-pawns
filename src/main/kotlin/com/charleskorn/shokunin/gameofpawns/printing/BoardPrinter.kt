package com.charleskorn.shokunin.gameofpawns.printing

import com.charleskorn.shokunin.gameofpawns.model.Board

interface BoardPrinter {
    fun print(board: Board): String
}
