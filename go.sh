#!/usr/bin/env bash

set -euo pipefail

./gradlew --quiet installDist
./build/install/game-of-pawns/bin/game-of-pawns $@
