@echo off
title PharmaStation - Sistema de Farmacia
cd /d "%~dp0"

echo ========================================================
echo        Iniciando PharmaStation (Desktop Swing)
echo ========================================================

REM Se o JAR empacotado nao existir, compila e gera com o Maven Wrapper
if not exist "target\pharmastation-1.0-SNAPSHOT-launcher.jar" (
    echo Compilando e gerando pacote executavel...
    call mvnw.cmd package -DskipTests
)

REM Define o executavel do Java (preferindo javaw para interface grafica)
set "JAVA_CMD=javaw"
where javaw >nul 2>nul
if %errorlevel% neq 0 (
    set "JAVA_CMD=java"
)

start "" "%JAVA_CMD%" -jar "target\pharmastation-1.0-SNAPSHOT-launcher.jar"

echo.
echo Aplicativo iniciado com sucesso na area de trabalho!
