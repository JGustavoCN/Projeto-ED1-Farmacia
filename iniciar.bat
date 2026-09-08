@echo off
title PharmaStation - Sistema de Farmacia
cd /d "%~dp0"

echo ========================================================
echo        Iniciando PharmaStation (Desktop Swing)
echo ========================================================

REM Se o executavel ou JAR nao existirem, compila e gera tudo via Maven Wrapper
if not exist "target\PharmaStation.exe" if not exist "target\pharmastation-1.0-SNAPSHOT-launcher.jar" (
    echo Compilando e gerando executavel...
    call mvnw.cmd package -DskipTests
)

if exist "target\PharmaStation.exe" (
    start "" "target\PharmaStation.exe"
) else (
    set "JAVA_CMD=javaw"
    where javaw >nul 2>nul || set "JAVA_CMD=java"
    start "" "%JAVA_CMD%" -jar "target\pharmastation-1.0-SNAPSHOT-launcher.jar"
)

echo.
echo Aplicativo iniciado com sucesso na area de trabalho!
