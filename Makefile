ifeq ($(OS),Windows_NT)
    MVNW = mvnw.cmd
else
    MVNW = ./mvnw
endif

.PHONY: help build run package clean test check

help:
	@echo ========================================================
	@echo               PharmaStation - Comandos Make
	@echo ========================================================
	@echo   make check    - Executa bateria completa de validacao (clean, build, test, package)
	@echo   make build    - Compila as classes do projeto
	@echo   make run      - Inicia a aplicacao desktop
	@echo   make package  - Gera o JAR executavel (Fat JAR) em target/
	@echo   make test     - Executa testes do projeto
	@echo   make clean    - Limpa artefatos de build (target/)
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

clean:
	$(MVNW) clean

test:
	$(MVNW) test
