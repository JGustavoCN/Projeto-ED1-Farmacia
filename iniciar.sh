#!/usr/bin/env bash
set -e

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd "$DIR"

echo "========================================================"
echo "       Iniciando PharmaStation (Desktop Swing)"
echo "========================================================"

if [ ! -f "target/pharmastation-1.0-SNAPSHOT-launcher.jar" ]; then
    echo "Compilando e gerando pacote executavel via Maven Wrapper..."
    ./mvnw package -DskipTests
fi

java -jar "target/pharmastation-1.0-SNAPSHOT-launcher.jar" &
echo "Aplicativo disparado com sucesso!"
