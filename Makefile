ifeq ($(OS),Windows_NT)
    MVNW = mvnw.cmd
else
    MVNW = ./mvnw
endif

.PHONY: help build run package clean test

help:
	@echo ========================================================
	@echo               PharmaStation - Comandos Make
	@echo ========================================================
	@echo   make build    - Compila as classes do projeto
	@echo   make run      - Inicia a aplicacao desktop
	@echo   make package  - Gera o JAR executavel (Fat JAR) em target/
	@echo   make clean    - Limpa artefatos de build (target/)
	@echo   make test     - Executa testes do projeto
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
