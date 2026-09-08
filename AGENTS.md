# Diretrizes para Agentes • PharmaStation (AGENTS.md)

Este documento contém os padrões essenciais de engenharia, arquitetura e validação que qualquer agente ou desenvolvedor deve seguir ao trabalhar no **PharmaStation**.

---

## 📌 1. Visão Geral do Projeto
* **Domínio:** Sistema Desktop para Gestão Farmacêutica, Controle de Estoque e Ponto de Venda (PDV).
* **Tecnologias:** Java 21 (LTS), Apache Maven, Java Swing, FlatLaf (Dark/Light), TimingFramework, MigLayout e JasperReports.
* **Contexto Acadêmico (ED1 - IFS):** O projeto implementa estruturas de dados fundamentais do zero (`Lista`, `Fila`, `Pilha`, `No`). **Nunca substitua essas estruturas por coleções prontas da JVM (`java.util.ArrayList`, etc.) nos repositórios.**

---

## 🛡️ 2. Regra de Ouro: Validação Contínua (`make check`)
Antes de considerar qualquer tarefa finalizada, submeter código ou declarar prontidão, **é obrigatório executar e obter sucesso no comando:**

```bash
make check
```

O pipeline do `make check` executa sequencialmente:
1. `mvnw clean compile` — Compilação limpa do zero.
2. `mvnw test` — Bateria de testes automatizados.
3. `mvnw package -DskipTests` — Empacotamento completo do Fat JAR executável (`target/pharmastation-1.0-SNAPSHOT-launcher.jar`).

> [!IMPORTANT]
> Se o `make check` falhar, a tarefa **NÃO** está concluída. Corrija o problema antes de qualquer entrega.

---

## ⚡ 3. Comandos Essenciais de Desenvolvimento

| Comando | Descrição |
| :--- | :--- |
| `make check` | Validação completa de integridade (clean, build, test, package) |
| `make run` | Compila e inicia a aplicação desktop na área de trabalho |
| `make build` | Compila as classes do projeto (`mvnw compile`) |
| `make package` | Gera o executável final Fat JAR em `target/` |
| `make release` | Prepara o pacote de distribuição pronto em `dist/` |
| `make clean` | Limpa a pasta `target/`, `dist/` e artefatos de compilação |
| `make help` | Exibe o menu com todos os comandos disponíveis |

---

## 📦 4. Dependências e Portabilidade do Build
1. **Maven Wrapper (`mvnw` / `mvnw.cmd`):** Sempre utilize o wrapper nos scripts e chamadas para garantir que o projeto rode em qualquer sistema sem exigir Maven instalado globalmente.
2. **Repositório In-Project (`repo/`):**
   * Bibliotecas customizadas da UI do Raven (`modal-dialog:1.1.0`, `swing-glasspane-popup:1.5.1`, `swing-toast-notifications:1.0.3`) residem na pasta `repo/` estruturada no padrão Maven local.
   * **NUNCA use `<scope>system</scope>` com `<systemPath>`**, pois o `exec-maven-plugin` descarta dependências de sistema no runtime (`mvn exec:java`), gerando `ClassNotFoundException`.
3. **Política Estrita de Atualização de Bibliotecas:**
   * **Não atualize versões de dependências no meio de refatorações de código.**
   * Upgrades de libs (como migrar `modal-dialog` para a versão 2.6.2 do Maven Central) devem ser planejados isoladamente e executados **apenas depois** que todas as fases anteriores estiverem totalmente estáveis e validadas.

---

## 💾 5. Persistência de Dados & Arquivos
1. **Isolamento em `./data/`:**
   * Objetos serializados (`.dat`) de execução devem ser salvos na pasta `./data/` na raiz de trabalho, devidamente ignorada no `.gitignore`.
   * Os arquivos em `src/main/resources/serialized_objects/` atuam apenas como seeds iniciais de fábrica.
2. **Proteção de Código:** Nunca salve dados ou arquivos temporários dentro da pasta `src/`.

---

## 📖 6. Documentação Sempre Atualizada
* Sempre que um comando for criado/modificado no `Makefile`, nos scripts (`iniciar.bat` / `iniciar.sh`) ou houver mudança de arquitetura, o [README.md](file:///c:/Users/joseg/Documents/NetBeansProjects/Projeto-ED1-Farmacia/README.md) **deve ser atualizado imediatamente**.
* O README deve manter instruções simples de 1 clique no topo para facilitar a vida do usuário.

---

## 🚀 7. Esteira de CI/CD & Releases (GitHub Actions)
1. **Pipeline de CI (`.github/workflows/ci.yml`):**
   * Disparado em todo `push` e `pull_request` para a branch `main`.
   * Valida compilação limpa com linter rigoroso (`-Xlint:all`), bateria de testes e geração do Fat JAR.
2. **Pipeline de Releases (`.github/workflows/release.yml`):**
   * Disparado ao criar e empurrar uma tag semântica (ex: `git tag v1.0.0; git push origin v1.0.0`) ou via `workflow_dispatch`.
   * Publica automaticamente o executável universal `pharmastation.jar` e o pacote portátil Windows `pharmastation-windows-x64.zip` na aba **Releases** do GitHub.

---

## 📐 8. Padronização Universal de Ambiente (`.editorconfig`)
* O arquivo `.editorconfig` na raiz do repositório garante consistência de indentação (4 espaços para Java e XML, tabs para Makefile), finais de linha (`LF`) e codificação UTF-8 sem BOM em qualquer editor (VS Code, NetBeans, IntelliJ, etc.).

---

## 🌐 9. Idioma e Comunicação
* Todo diálogo, explicação de código, sugestão ou relatório com o usuário deve ser feito **única e exclusivamente em Português do Brasil (pt-BR)**.
