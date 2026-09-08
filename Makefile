ifeq ($(OS),Windows_NT)
    MVNW = mvnw.cmd
else
    MVNW = ./mvnw
endif

.PHONY: help build run package release clean test check

help:
	@echo ========================================================
	@echo               PharmaStation - Comandos Make
	@echo ========================================================
	@echo   make check    - Executa bateria completa de validacao (clean, build, test, package)
	@echo   make build    - Compila as classes do projeto
	@echo   make run      - Inicia a aplicacao desktop
	@echo   make package  - Gera o JAR executavel (Fat JAR) em target/
	@echo   make release  - Prepara o pacote de distribuicao pronto em dist/
	@echo   make test     - Executa testes do projeto
	@echo   make clean    - Limpa artefatos de build (target/ e dist/)
	@echo ========================================================

check:
	@echo ========================================================
	@echo [1/3] Validando compilacao limpa...
	@echo ========================================================
	$(MVNW) clean compile
	@echo ========================================================
	@echo [2/3] Executando testes...
	@echo ========================================================
	$(MVNW) test
	@echo ========================================================
	@echo [3/3] Validando empacotamento do executavel (Fat JAR)...
	@echo ========================================================
	$(MVNW) package -DskipTests
	@echo ========================================================
	@echo   [OK] PROJETO VALIDADO E INTEGRO! TUDO FUNCIONANDO.
	@echo ========================================================

build:
	$(MVNW) compile

run:
	$(MVNW) compile exec:java

package:
	$(MVNW) package -DskipTests

release: package
	@echo ========================================================
	@echo [Release] Preparando pacote de distribuicao em dist/...
	@echo ========================================================
ifeq ($(OS),Windows_NT)
	@if not exist "dist" mkdir "dist"
	@copy /Y "target\pharmastation-1.0-SNAPSHOT-launcher.jar" "dist\pharmastation.jar" >nul
	@copy /Y "PharmaStation.bat" "dist\PharmaStation.bat" >nul
	@copy /Y "assets\logo.png" "dist\logo.png" >nul
else
	@mkdir -p dist
	@cp target/pharmastation-1.0-SNAPSHOT-launcher.jar dist/pharmastation.jar
	@cp PharmaStation.bat dist/PharmaStation.bat
	@cp assets/logo.png dist/logo.png
endif
	@echo [OK] Pacote gerado com sucesso!
	@echo   - dist/pharmastation.jar  (JAR executavel universal)
	@echo   - dist/PharmaStation.bat  (Launcher Windows 1-clique)
	@echo   - dist/logo.png           (Icone oficial)
	@echo ========================================================

clean:
	$(MVNW) clean
ifeq ($(OS),Windows_NT)
	@if exist "dist" rmdir /s /q "dist"
else
	@rm -rf dist
endif

test:
	$(MVNW) test
