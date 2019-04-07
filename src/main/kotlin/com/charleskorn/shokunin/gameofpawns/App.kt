package com.charleskorn.shokunin.gameofpawns

import com.charleskorn.shokunin.gameofpawns.printing.BoardPrinter
import com.charleskorn.shokunin.gameofpawns.printing.FENPrinter
import com.charleskorn.shokunin.gameofpawns.printing.PlainTextPrinter
import java.io.PrintStream
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    exitProcess(runApplication(args, System.out, System.err))
}

fun runApplication(args: Array<String>, out: PrintStream, err: PrintStream): Int {
    if (args.size > 1) {
        return showArgsErrorAndExit(err)
    }

    when (args.firstOrNull()) {
        "--grid" -> printRandomBoard(PlainTextPrinter(), out)
        "--fen", null -> printRandomBoard(FENPrinter(), out)
        else -> return showArgsErrorAndExit(err)
    }

    return 0
}

private fun showArgsErrorAndExit(err: PrintStream): Int {
    err.println("Error: this application takes at most one argument, either --grid or --fen.")

    return -1
}

private fun printRandomBoard(printer: BoardPrinter, out: PrintStream) {
    val generator = BoardGenerator()
    val board = generator.generate()

    out.println(printer.print(board))
}
