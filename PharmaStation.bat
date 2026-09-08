@echo off
title PharmaStation - Gestao Farmaceutica
chcp 65001 >nul

:: ========================================================
::  PharmaStation • Launcher Executável Windows
:: ========================================================

:: 1. Verificar presenca do Java
where java >nul 2>nul
if %errorlevel% neq 0 (
    echo [ERRO] O Java nao foi encontrado no seu sistema.
    echo Por favor, instale o Java 21 (LTS) ou superior para executar o PharmaStation.
    echo Download: https://adoptium.net/temurin/releases/?version=21
    echo.
    pause
    exit /b 1
)

:: 2. Identificar o arquivo JAR executavel
if exist "%~dp0pharmastation.jar" (
    set "JAR_PATH=%~dp0pharmastation.jar"
) else if exist "%~dp0dist\pharmastation.jar" (
    set "JAR_PATH=%~dp0dist\pharmastation.jar"
) else if exist "%~dp0target\pharmastation-1.0-SNAPSHOT-launcher.jar" (
    set "JAR_PATH=%~dp0target\pharmastation-1.0-SNAPSHOT-launcher.jar"
) else (
    echo [ERRO] O executavel pharmastation.jar nao foi encontrado.
    echo Execute 'make release' ou 'make package' para compilar o executavel.
    echo.
    pause
    exit /b 1
)

:: 3. Executar em modo desktop (javaw) sem prender a janela do console
start "" javaw -jar "%JAR_PATH%"
exit /b 0
