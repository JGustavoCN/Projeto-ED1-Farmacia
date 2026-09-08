# Diretrizes de Engenharia e Workflow • PharmaStation

Esta regra define os padrões obrigatórios de execução, validação e documentação para o projeto PharmaStation.

## 1. Validação Obrigatória Contínua (`make check`)
- Antes de declarar qualquer tarefa concluída, submeter código para revisão ou finalizar uma fase, **SEMPRE execute e garanta sucesso no comando:**
  ```bash
  make check
  ```
- O `make check` valida a cadeia completa:
  1. `mvnw clean compile` (compilação limpa do zero).
  2. `mvnw test` (execução dos testes unitários).
  3. `mvnw package -DskipTests` (construção e empacotamento completo do Fat JAR executável).
- Nenhuma entrega é considerada pronta sem que o `make check` passe sem erros.

## 2. Atualização Contínua do README.md
- Sempre que um novo comando do `Makefile`, script de automação (`iniciar.bat` / `iniciar.sh`), requisito de ambiente ou funcionalidade for criado/alterado, o arquivo `README.md` **DEVE ser atualizado imediatamente**.
- O README é o cartão de visitas e o manual operacional do projeto: deve conter instruções de 1 linha e manter os comandos rápidos visíveis no topo.

## 3. Política de Atualização de Dependências
- **NÃO atualize dependências de terceiros no meio de refatorações ou correções de negócio.**
- Atualizações de versão de bibliotecas (ex: migração para `modal-dialog:2.6.2` do Maven Central) devem ser planejadas e executadas de forma isolada, **estritamente após** todas as funcionalidades existentes estarem validadas e em pleno funcionamento.

## 4. Portabilidade e Gerenciamento de JARs
- Dependências locais ou customizadas devem ser integradas via repositório in-project local (`repo/`) configurado no `pom.xml` (`file://${project.basedir}/repo`).
- **NUNCA utilize `<scope>system</scope>`** para bibliotecas de runtime da aplicação, pois o plugin `exec:java` (`make run`) descarta dependências de sistema do classpath de execução.
- Sempre prefira o Maven Wrapper (`mvnw` / `mvnw.cmd`) em scripts e Makefiles para garantir reprodutibilidade em qualquer sistema operacional.

## 5. Isolamento de Escopo por Fases
- Respeite o roadmap de fases do projeto:
  - **Fase 1:** Fundação & DX (apenas infraestrutura e build, sem tocar em lógica Java).
  - **Fase 2:** Saneamento de Persistência (isolamento dos arquivos `.dat` em `./data/`).
  - **Fase 3:** Refatoração de Código & UX.
  - **Fase 4:** Atualização de Dependências (Raven modal-dialog 2.6.2).
  - **Fase 5:** Documentação de Portfólio.
- Não misture objetivos de fases distintas em um mesmo commit ou pull request.
