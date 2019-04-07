package com.charleskorn.shokunin.gameofpawns

import ch.tutteli.atrium.api.cc.en_GB.atLeast
import ch.tutteli.atrium.api.cc.en_GB.atMost
import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.containsRegex
import ch.tutteli.atrium.api.cc.en_GB.exactly
import ch.tutteli.atrium.api.cc.en_GB.notToBe
import ch.tutteli.atrium.api.cc.en_GB.regex
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.verbs.assert
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.ByteArrayOutputStream
import java.io.PrintStream

object AppSpec : Spek({
    describe("the application") {
        mapOf(
                "given no arguments" to arrayOf(),
                "given the single argument '--fen'" to arrayOf("--fen")
        ).forEach { description, args ->
            describe(description) {
                val output = ByteArrayOutputStream()
                val error = ByteArrayOutputStream()

                val exitCode = runApplication(args, PrintStream(output), PrintStream(error))

                it("exits with a zero exit code") {
                    assert(exitCode).toBe(0)
                }

                it("prints a FEN-formatted board to stdout") {
                    assert(output.toString()).containsRegex("""^([0-9pnbrqkPNBRQK]+/){7}[0-9pnbrqkPNBRQK]+ w - - 0 1$""")
                }

                it("prints nothing to stderr") {
                    assert(error.toString()).toBe("")
                }
            }
        }

        describe("given the single argument '--grid'") {
            val args = arrayOf("--grid")
            val output = ByteArrayOutputStream()
            val error = ByteArrayOutputStream()

            val exitCode = runApplication(args, PrintStream(output), PrintStream(error))

            it("exits with a zero exit code") {
                assert(exitCode).toBe(0)
            }

            it("prints a text-formatted board to stdout") {
                assert(output.toString()).containsRegex("""^(([pnbrqkPNBRQK.]\s\s){7}[pnbrqkPNBRQK.]\n){8}$""")
            }

            it("prints nothing to stderr") {
                assert(error.toString()).toBe("")
            }
        }

        describe("given the single argument '--pretty'") {
            val args = arrayOf("--pretty")
            val output = ByteArrayOutputStream()
            val error = ByteArrayOutputStream()

            val exitCode = runApplication(args, PrintStream(output), PrintStream(error))

            it("exits with a zero exit code") {
                assert(exitCode).toBe(0)
            }

            it("prints a pretty-printed board to stdout") {
                assert(output.toString()).contains.exactly(64).regex("""[\u2654-\u265F]\s|\s\s""")
            }

            it("prints nothing to stderr") {
                assert(error.toString()).toBe("")
            }
        }

        mapOf(
                "given another argument" to arrayOf("--something"),
                "given multiple arguments" to arrayOf("--fen", "--grid")
        ).forEach { description, args ->
            describe(description) {
                val output = ByteArrayOutputStream()
                val error = ByteArrayOutputStream()

                val exitCode = runApplication(args, PrintStream(output), PrintStream(error))

                it("exits with a zero exit code") {
                    assert(exitCode).notToBe(0)
                }

                it("prints nothing to stdout") {
                    assert(output.toString()).toBe("")
                }

                it("prints nothing to stderr") {
                    assert(error.toString()).toBe("Error: this application takes at most one argument, either --grid, --fen or --pretty.\n")
                }
            }
        }
    }
})
