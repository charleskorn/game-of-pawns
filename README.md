# game-of-pawns

[![Build Status](https://travis-ci.com/charleskorn/game-of-pawns.svg?token=fGxKTw5HkWk6PQtSDkK5&branch=master)](https://travis-ci.com/charleskorn/game-of-pawns)

The April TW Shokunin challenge: generate a semi-realistic random chess board according to some rules.

## Requirements

* JDK 1.8 or later

## Usage

For a FEN-formatted chess board:

`./go.sh --fen`

For a plain text chess board:

`./go.sh --grid`

`go.sh` will automatically download any missing dependencies and compile the application if required.
