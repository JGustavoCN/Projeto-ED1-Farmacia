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
	@if exist "target\PharmaStation.exe" copy /Y "target\PharmaStation.exe" "dist\PharmaStation.exe" >nul
	@copy /Y "target\pharmastation-1.0-SNAPSHOT-launcher.jar" "dist\pharmastation.jar" >nul
	@copy /Y "iniciar.bat" "dist\iniciar.bat" >nul
	@copy /Y "assets\logo.png" "dist\logo.png" >nul
	@if exist "assets\logo.ico" copy /Y "assets\logo.ico" "dist\logo.ico" >nul
else
	@mkdir -p dist
	@[ -f target/PharmaStation.exe ] && cp target/PharmaStation.exe dist/PharmaStation.exe || true
	@cp target/pharmastation-1.0-SNAPSHOT-launcher.jar dist/pharmastation.jar
	@cp iniciar.bat dist/iniciar.bat
	@cp assets/logo.png dist/logo.png
	@[ -f assets/logo.ico ] && cp assets/logo.ico dist/logo.ico || true
endif
	@echo [OK] Pacote de distribuicao gerado com sucesso em dist/!
	@echo   - dist/PharmaStation.exe  (Executavel nativo Windows com icone oficial)
	@echo   - dist/pharmastation.jar  (JAR executavel universal multiplataforma)
	@echo   - dist/iniciar.bat        (Launcher Windows de contingencia)
	@echo   - dist/logo.ico / logo.png (Identidade visual oficial)
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
