# game-of-pawns

[![Build Status](https://travis-ci.com/charleskorn/game-of-pawns.svg?token=fGxKTw5HkWk6PQtSDkK5&branch=master)](https://travis-ci.com/charleskorn/game-of-pawns)

The April TW Shokunin challenge: generate a semi-realistic random chess board according to some rules:

* there is one and only one king of each color;
* the kings must not be placed on adjacent squares;
* there can not be any pawn in the promotion square (no white pawn in the eighth rank, and no black pawn in the first rank);
* including the kings, up to 32 pieces of either color can be placed. There is no requirement for material balance between sides, but the picking of pieces should comply with what is found in a regular chess set (e.g., 8 pawns/colour, 1 queen/colour, etc)
* (FEN requirement only) it is white's turn, both sides have lost castling rights and there is no possibility for en passant (the FEN should thus end in w - - 0 1).

## Requirements

* JDK 1.8 or later

## Usage

For a FEN-formatted chess board, run `./go.sh --fen`. This produces output like:

```
4K3/6N1/2n1P3/P7/2p3k1/3R4/2P5/2P5 w - - 0 1
```

For a plain text chess board, run `./go.sh --grid`. This produces output like:

```
.  .  .  .  .  .  .  .
.  .  .  .  .  .  .  .
.  .  .  k  .  .  .  K
N  .  .  .  .  .  n  .
.  .  .  r  .  .  .  .
.  N  .  .  n  .  .  .
P  .  r  .  .  .  .  .
.  .  .  .  .  P  R  .
```

For a pretty-printed chess board, run `./go.sh --pretty`. This produces output like the following on a supported terminal:

![Pretty print example](docs/pretty.png)

`go.sh` will automatically download any missing dependencies and compile the application if required.
