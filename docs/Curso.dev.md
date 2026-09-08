**FUNDAÇÃO FINALIZADA agora é fazer o dia 36 em diante**

## **1\. O "Extrator de Valor" (Web Scraper Institucional)**

Em vez de esperar que os alunos postem tudo, o TabIFS pode ser proativo.

* **A ideia:** Criar um microserviço em **Go** (aproveitando seu interesse na linguagem) que monitora o site oficial do IFS e o SIGAA.  
* **O que faz:** Sempre que sair um edital de estágio, uma nota sobre o Restaurante Universitário ou mudança no calendário, o bot cria automaticamente um "Tab" com o resumo e o link direto.  
* **Valor:** O aluno não precisa "caçar" a notícia no site oficial confuso; ela aparece limpa no TabIFS.

**📊 Diagnóstico: Cobertura do Arquivo vs. Aulas (Dia 1 ao 13\)**

O seu arquivo **pula o início técnico (Dias 1-4)**, cobre muito bem o **miolo (Dias 5-10)** e deixa passar a **teoria técnica de DNS e Domínios (Dias 11-12)**, embora cubra a filosofia final do Dia 13\.

| Dia | Status no Arquivo | Observação |
| :---- | :---- | :---- |
| **Dia 1** | ❌ **Ausente** | Nada sobre Pista Rápida/Lenta ou estrutura. |
| **Dia 2** | ❌ **Ausente** | Faltam configurações de GitHub, Codespaces e Ambiente. |
| **Dia 3** | ❌ **Ausente** | Falta a "Fundação" e a "Primeira Parede". |
| **Dia 4** | ❌ **Ausente** | Falta a explicação técnica de Protocolos e localhost. |
| **Dia 5** | ✅ **Completo** | Git Interno, Log, Commit, Amend 1. |
| **Dia 6** | ✅ **Completo** | Push e Force Push 2. |
| **Dia 7** | ✅ **Completo** | Client/Server, Deploy e Vercel 3. |
| **Dia 8** | ✅ **Completo** | Orgânico vs Impressora 3D 4. |
| **Dia 9** | ✅ **Completo** | Gestão, Milestones e Issues 5. |
| **Dia 10** | ✅ **Completo** | EditorConfig, Prettier e Padronização 6. |
| **Dia 11** | ⚠️ **Parcial** | Tem dicas de escolha de domínio 7, mas **falta a teoria técnica de DNS** (\#F043). |
| **Dia 12** | ❌ **Ausente** | Faltam os passos técnicos de registrar domínio e configurar Nameservers. |
| **Dia 13** | ⚠️ **Parcial** | Cobre SLA e Status Page 8, mas falta o fechamento da Milestone 0 (Página em Construção). |

### **O que é um "Slug"?**

Em termos simples, o **slug** é a parte de uma URL (endereço web) que identifica uma página ou recurso específico de forma legível para humanos e motores de busca.

No contexto do GitHub, o slug é o **nome do seu repositório** como ele aparece na barra de endereços.

**Exemplo:** Se o seu usuário for `seu-usuario` e você criar um projeto chamado `meu-projeto-legal`:

* **URL Completa:** `https://github.com/seu-usuario/meu-projeto-legal`  
* **Slug:** `meu-projeto-legal`

**Características de um bom slug:**

* **Apenas letras minúsculas** (para evitar confusão em servidores sensíveis a maiúsculas/minúsculas).  
* **Hífens (`-`) ao invés de espaços** (espaços quebram URLs ou viram `%20`).  
* **Curto e descritivo.**

Baseado nos arquivos que você enviou do projeto **dataprofiler**, a resposta direta é: **Não, você não está usando Next.js neste projeto.**

Você está usando uma arquitetura diferente, separada em duas partes:

1. **Frontend:** Feito com **React** rodando sobre o **Vite**.  
2. **Backend:** Feito com a linguagem **Go (Golang)**.

Abaixo, explico cada tecnologia que você perguntou e como elas se conectam (ou diferem) do que é ensinado no curso do TabNews.

---

### **1\. O que você está usando no dataprofiler? (Vite vs. Next.js)**

No seu projeto dataprofiler, você está usando o **Vite**.

* O que é o Vite?  
  É uma ferramenta de construção (build tool) extremamente rápida. Ele pega o seu código JavaScript/React e o prepara para rodar no navegador. Ele foca em criar SPAs (Single Page Applications).  
  * *Analogia:* Imagine que o Vite é uma esteira de fábrica ultra-rápida que empacota seu código para ser entregue ao navegador.  
* Diferença para o Next.js:  
  O Next.js é um Framework completo. Ele traz roteamento, renderização no servidor (SSR), otimização de imagem e backend embutido. O Vite é mais "leve" e foca apenas em fazer o frontend React rodar rápido.  
  * No curso do TabNews, o Filipe usa Next.js porque o TabNews precisa de SEO (aparecer no Google), e o Next.js é ótimo nisso.  
  * O seu dataprofiler é uma ferramenta interna (um dashboard), então usar **Vite \+ React** é uma escolha técnica excelente e mais simples.

---

### **2\. O glossário das tecnologias (Explicado Simples)**

Aqui está como todas essas peças se encaixam, usando o JavaScript como base:

#### **JavaScript (A Língua)**

É a linguagem de programação. Tudo o que acontece no frontend (navegador) e nas ferramentas de build (Node) é escrito nessa língua.

#### **Node.js (O Motor)**

O JavaScript foi criado para rodar apenas dentro do navegador (Chrome, Firefox). O Node.js é um ambiente que permite rodar JavaScript **fora** do navegador, no seu computador ou servidor.

* **No seu projeto:** Você usa o Node.js apenas para rodar as ferramentas de desenvolvimento (como o Vite e o NPM para instalar pacotes). O seu backend real (a lógica de processar o CSV) **não** é Node.js, é **Go**.

#### **React (O Arquiteto / A Biblioteca)**

O React é uma biblioteca JavaScript para criar interfaces.

* Em vez de escrever HTML puro e ficar manipulando-o manualmente, você cria **Componentes** (como o seu FileUploader ou DataReport).  
* O React gerencia o "estado" da aplicação (ex: saber se o arquivo está carregando ou se deu erro).

#### **React-DOM (O Pedreiro / O Renderizador)**

Você perguntou especificamente sobre isso. O **React** (a biblioteca acima) é apenas lógica abstrata; ele vive na memória criando uma árvore virtual de elementos.

* O **React-DOM** é a biblioteca que pega essa lógica abstrata do React e **realmente coloca** os elementos na tela do navegador (no DOM \- Document Object Model).  
* Sem o react-dom, o React saberia *o que* desenhar, mas não saberia *como* falar com o navegador para desenhar.

---

### **Resumo da sua Stack no dataprofiler**

| Tecnologia | Função no seu Projeto | Comparação com TabNews (Curso) |
| :---- | :---- | :---- |
| **JavaScript** | Linguagem do Frontend | Igual. |
| **React** | Cria a interface visual | Igual. |
| **Vite** | Roda o servidor local e gera o build | O curso usa **Next.js** (que faz isso \+ backend \+ rotas). |
| **Go (Golang)** | Backend (API que processa o CSV) | O curso usa **Node.js** (dentro do Next.js) para o backend. |
| **Vercel** | Onde você *pode* hospedar | Igual (Vercel hospeda tanto Vite quanto Next.js). |

### **Conclusão**

Você está no caminho certo. Usar **React \+ Vite** no frontend e **Go** no backend é uma combinação muito poderosa e profissional ("Fullstack Moderno").

Para o seu repositório de estudo do TabNews (tabnews-rebuilt), aí sim você deve usar **Next.js**, pois a ideia lá é replicar a arquitetura específica ensinada no curso, que depende das funcionalidades de servidor do Next.js.

## **DIA 5-6 📚 Revisão: Ciclo de Vida e Comandos Essenciais do Git**

### **1\. Fases dos Arquivos no Git**

Entender os "estados" de um arquivo é o primeiro passo para dominar o versionamento.

* **Untracked (Não rastreado):** O arquivo existe no seu computador (no diretório de trabalho), mas o Git ainda não "olha" para ele. Ele não será incluído no próximo commit a menos que você peça (git add).  
* **Modified (Modificado):** O arquivo já é rastreado pelo Git e sofreu alterações desde o último commit, mas essas alterações ainda **não** foram marcadas para serem salvas.  
* **Staged (Preparado):** Você selecionou as alterações do arquivo modificado e as colocou na "área de preparação" (Staging Area). Elas estão prontas para entrar no próximo "pacote" (commit).  
* **Committed (Comitado/Consolidado):** As alterações foram gravadas com segurança no banco de dados do Git local.

### **2\. Dicionário de Comandos**

Lista rápida para referência diária.

* git status: O "GPS" do Git. Mostra em qual estado (fase) seus arquivos estão agora (modificados, staged ou untracked).  
* git log: Mostra o histórico de commits (quem fez, quando e a mensagem).  
* git log \--stat: Além do histórico, mostra estatísticas resumidas de quais arquivos foram alterados e quantas linhas mudaram em cada commit.  
* git diff: Mostra **o que** mudou no conteúdo dos arquivos (a diferença exata entre o que está no seu diretório e o que está no último commit ou no stage).  
* git branch: Lista as ramificações (branches) do projeto. O asterisco \* indica em qual você está.  
* git commit: Pega tudo que está em **Staged** e cria um novo ponto na história (snapshot).  
* git commit \--amend: Permite corrigir o **último** commit feito (mudar a mensagem ou adicionar arquivos esquecidos) sem criar um novo commit separado. Ele *substitui* o anterior.

---

### **3\. Estudo de Caso: git commit \--amend após git push**

O Cenário:

Você fez um commit, enviou para o GitHub (git push), mas depois percebeu que queria mudar algo e usou git commit \--amend. Ao tentar enviar de novo (git push), recebeu o erro: \! \[rejected\] main \-\> main (non-fast-forward).

**O que aconteceu?**

1. Quando você faz um \--amend, o Git não "edita" o commit antigo; ele **cria um novo commit** (com um novo código hash/ID) e apaga o anterior localmente.  
2. O GitHub, no entanto, ainda tem o commit **antigo**.  
3. Ao tentar o push, o GitHub diz: *"Ei, o seu histórico local não combina com o meu. Você tem um commit diferente no lugar daquele que eu já tenho."*

A Solução: Force Push

Para resolver isso, você precisa "forçar" o GitHub a aceitar a sua nova versão da história, sobrescrevendo a antiga.

Comandos para usar:

Bash

git push \-f  
\# ou a versão por extenso:  
git push \--force

### **4\. Regra de Ouro: Quando usar (e não usar) git push \-f**

O \--force é uma ferramenta poderosa e perigosa, pois reescreve a história.

✅ **Quando USAR:**

* **Trabalho Pessoal:** Você está trabalhando sozinho em uma feature ou projeto (como neste *tabnews-rebuilt* agora) e ninguém mais baixou seu código recentemente.  
* **Branch de Pull Request:** Você está trabalhando em um branch secundário (ex: feature/nova-pagina) e precisou corrigir o último commit antes de a equipe revisar.

❌ **Quando NÃO USAR:**

* **Branches Compartilhados (ex: main ou master em equipe):** Se outra pessoa já tiver baixado (pull) o projeto, ao usar force, você quebrará o histórico dela. O trabalho dos seus colegas ficará dessincronizado, causando grandes dores de cabeça.

Resumo da atitude Clean Code:

Só reescreva o histórico (amend/force) se você tiver certeza de que é o único consumindo aquele branch naquele momento. Se estiver em dúvida, é mais seguro fazer um novo commit com a correção.

## **DIA 7📚 Revisão: Evolução do Deploy, Vercel e as Muralhas de Valor**

### **1\. A Evolução da Hospedagem e Deploy**

Para entender onde estamos (Vercel), precisamos lembrar de onde viemos. A história do deploy mostra como a responsabilidade saiu do "humano" e foi para o "robô".

* **O Passado (A "Idade da Pedra"):**  
  * **Como era:** Você tinha um computador (servidor) alugado. Para colocar o site no ar, você arrastava arquivos manualmente (via FTP) do seu computador para o servidor.  
  * **O Problema:** "Na minha máquina funciona". O ambiente do seu computador era diferente do servidor. Se você esquecesse um arquivo, o site quebrava. Era um processo manual, lento e perigoso.  
* **O Presente (A Era do "Robô" / CI/CD):**  
  * **A Mudança:** Agora, tratamos a infraestrutura como código.  
  * **O Fluxo Moderno:**  
    1. **O Repositório (A Fonte da Verdade):** Seu código no GitHub é a versão oficial.  
    2. **O Gatilho:** Quando você faz um `git push`, você não está jogando arquivos no servidor. Você está apenas avisando: "Ei, atualizei a receita do bolo".  
    3. **O Robô da Vercel (CI/CD):** A Vercel tem um "robô" monitorando seu GitHub. Assim que ele vê o novo código, ele "baixa" o repositório em um computador limpo da Vercel.  
    4. **Build & Deploy:** O robô segue as instruções (`npm install`, `npm run build`) para construir o site. Se der erro, ele avisa. Se der certo, ele coloca no ar automaticamente.

### **2\. Como a Vercel Funciona**

A Vercel não apenas hospeda; ela cria um fluxo de trabalho baseado no Git.

* **Integração com GitHub:** Ao conectar o repositório do projeto `tabnews-rebuilt`, a Vercel ganha permissão para ler seus commits.  
* **Deploy de Produção:** Quando você altera a branch `main`, a Vercel atualiza o domínio principal (seu site oficial).  
* **Deploy de Preview (Imutabilidade):**  
  * Cada commit gera uma **URL única e permanente**.  
  * Isso significa que você pode visitar a versão do site de 3 meses atrás exatamente como ela era.  
  * Isso evita o medo de "quebrar a produção", pois você pode testar a URL de preview antes de aceitar a mudança na `main`.

### **3\. O Conceito das "Muralhas" (Tecnologia vs. Negócio)**

Este é um conceito vital sobre foco e cegueira profissional. Existem duas grandes muralhas que podem bloquear sua visão:

* **A Muralha da Tecnologia:**  
  * É quando o desenvolvedor foca tanto na configuração do servidor, no Docker, no Linux, na otimização prematura, que esquece **para quê** o software serve.  
  * **Sintoma:** O código é perfeito, a infraestrutura é complexa, mas ninguém usa ou o produto não resolve o problema do usuário. A pessoa não vê o negócio porque a tecnologia está na frente.  
* **A Muralha do Negócio:**  
  * É quando se foca apenas em vender, marketing e funcionalidades, ignorando a qualidade técnica.  
  * **Sintoma:** O produto vende muito, mas é lento, cheio de bugs e difícil de manter (dívida técnica).  
* **O Papel da Vercel nas Muralhas:**  
  * A Vercel tenta tornar a **Muralha da Tecnologia invisível** (especificamente a parte de Infraestrutura/DevOps).  
  * Ao automatizar o deploy (o "robô"), você gasta menos tempo configurando servidores e mais tempo criando valor para o usuário (focando no Negócio/Produto) e na qualidade do código (Clean Code).

### **4\. O Que Foi Feito (Passo a Passo Prático)**

Nesta etapa do projeto, realizamos a conexão entre as pontas:

1. **Criação de Conta:** Acesso à plataforma Vercel.  
2. **Importação do Git:** Selecionamos o repositório `tabnews-rebuilt`.  
3. **Configuração Zero:** A Vercel detectou automaticamente que é um projeto Next.js e configurou o comando de build.  
4. **Primeiro Deploy:** O código saiu do GitHub, passou pelo "robô" de build da Vercel e recebeu uma URL pública (`.vercel.app`).

## **DIA 8📚 Revisão: Psicologia do Desenvolvedor e Metodologias de Crescimento**

### **1\. O Cérebro e a Distorção da Realidade**

Nosso cérebro não é um gravador fiel da realidade; ele é um editor de vídeo que faz cortes e edições em tempo real.

* **O Input e a Mutação:** O cérebro recebe dados (input), mas altera a percepção (mutação) para proteger o ego ou evitar dor.  
* **A Realidade Dolorosa:** Programar é lidar com frustração constante (erros, bugs, coisas que não funcionam). A realidade de que "o código está ruim" ou "eu não sei resolver isso" gera dor.  
* **A Distorção como Defesa:** Para suportar essa dor, muitas vezes distorcemos a realidade:  
  * *"Isso é apenas um detalhe, depois eu arrumo"* (ignorando a complexidade real).  
  * *"Na minha máquina funciona"* (negando o ambiente de produção).  
  * **Lição:** Um bom programador luta contra essa distorção. Ele encara o erro de frente, lê o log de erro cru e aceita que o código precisa de refatoração, sem levar para o lado pessoal.

### **2\. A Curva da Empolgação: Começar vs. Terminar**

O cemitério do GitHub está cheio de projetos iniciados e não terminados.

* **O Início (Lua de Mel):** No começo, tudo é novidade. A dopamina está alta. Configuramos o projeto, instalamos bibliotecas e sentimos que somos gênios.  
* **O Vale da Desilusão:** Logo surgem os problemas "chatos" (configuração de deploy, tratamento de erros, CSS quebrado). A empolgação some.  
* **O Filtro:** É nessa fase que a maioria desiste. A diferença entre o amador e o profissional é que o profissional continua trabalhando mesmo sem a empolgação inicial. A **disciplina** substitui a motivação.

### **3\. Metáfora de Desenvolvimento: Impressora 3D vs. Modelo Orgânico**

Essa é a chave para entender como construir o **tabnews-rebuilt** e qualquer software robusto.

#### **❌ O Modelo "Impressora 3D" (Errado para Software)**

* **Como funciona:** Uma impressora 3D constrói camada por camada, da base até o topo. Se a impressão falhar na metade (ex: faltou luz), o objeto inteiro muitas vezes vai para o lixo.  
* **No Software:** Tentar planejar tudo antes, escrever todo o código de uma vez (Backend \+ Frontend \+ Banco) e só tentar rodar no final.  
* **O Risco:** Você passa meses programando e, quando clica em "rodar", nada funciona. O sistema não tem vida até o último segundo.

#### **✅ O Modelo "Orgânico" (Correto para Software)**

* **Como funciona:** Pense em um embrião ou uma semente.  
  * No estágio inicial, ele é minúsculo, mas já é **completo** e está **vivo**. Tem células, metabolismo e funciona.  
  * Ele cresce e se complexifica: desenvolve membros, órgãos, etc.  
* **No Software (Iterativo/Incremental):**  
  * Você cria o "embrião": Um arquivo `index.js` simples que retorna "Olá".  
  * Você coloca ele no mundo (Deploy na Vercel). **Ele já está vivo.**  
  * Depois, você adiciona um braço (uma página nova). O sistema continua vivo.  
  * Depois, adiciona o cérebro (banco de dados).  
* **Vantagem:** O sistema está sempre funcionando. Se der erro ao adicionar o "braço", você sabe exatamente onde o problema está, porque o resto estava saudável antes.

---

### **Resumo Prático para o Projeto**

Ao fazer o deploy do **tabnews-rebuilt** na Vercel logo no início (apenas com a página inicial), nós adotamos o **Modelo Orgânico**. O site já existe, já tem endereço e já é acessível. Agora, vamos apenas fazê-lo crescer, commit por commit.

## **DIA 9📚 Revisão: Metodologia de Gestão, Produtividade e Psicologia do Trabalho**

### **1\. O Mecanismo de Saldo (Engajamento)**

Para decidir se vamos fazer uma tarefa ou procrastinar, nosso cérebro faz um cálculo automático de "Saldo":

* **A Regra:** O esforço necessário versus a recompensa percebida.  
* **O Objetivo:** Fazer **muito com pouco** (alto impacto, baixo esforço).  
* **O Erro Comum:** Fazer pouco com muito (muito planejamento, burocracia excessiva, pouco código entregue).  
* **Conclusão:** Quando se planeja excessivamente, sobra pouca energia para programar. O ideal é manter o saldo positivo para garantir o engajamento contínuo.

### **2\. Os 4 Níveis de Organização de Tarefas**

Toda ferramenta de gestão tem um "Preço" a ser pago: o **Custo de Produção** (energia para registrar a tarefa) e o **Tempo de Aquecimento** (tempo para entender o que precisa ser feito).

* **Nível 1 \- Lembrete Individual:**  
  * *Ex:* Um post-it ou nota pessoal.  
  * *Custo:* Baixíssimo.  
  * *Aquecimento:* Imediato.  
* **Nível 2 \- Lembrete em Grupo (Kanban Simples):**  
  * *Ex:* Quadro "A Fazer / Fazendo / Feito".  
  * *Foco:* Marcar progresso visualmente.  
  * *Contexto:* Raso (apenas o título da tarefa).  
* **Nível 3 \- Expansão de Conhecimento (GitHub Issues/Trello):**  
  * *Foco:* Discutir **o que** e **como** deve ser feito.  
  * *Custo:* Aumenta o custo de produção (escrever detalhes) e aquecimento (ler tudo antes de começar), mas gera alinhamento técnico.  
* **Nível 4 \- Geração de Métricas (Jira/Enterprise):**  
  * *Foco:* Mensurar produtividade, burndown charts, velocidade.  
  * *Risco:* Gera muita carga (reuniões, preenchimento de campos). Pode deixar o "Saldo" negativo, cansando a pessoa antes mesmo de ela começar a trabalhar.  
  * *Perigo dos Incentivos:* Se a métrica foca apenas em "fechar tarefas", o desenvolvedor pode focar em barreiras técnicas irrelevantes em vez de resolver barreiras de negócio. A mensuração vira o fim, não o meio.

### **3\. Estratégia de Execução: Milestones e Issues**

Como organizar o caos usando o GitHub.

* **A Metáfora da Pedra:**  
  * **Milestone (Marco):** É a Pedra Grande (O Objetivo Macro). *Ex: "Lançar a Home Page".*  
  * **Issue (Tarefa):** É a Pedrinha ou o Pó. *Ex: "Criar o botão de login".*  
* **Inception Issue:** A tarefa inicial para discutir o escopo.  
* **Milestone 0 (Em construção):**  
  * Foque apenas na pedra atual. Não perca tempo criando tarefas para problemas que vão acontecer daqui a 6 meses.  
  * Se o seu planejamento de longo prazo nunca muda, ou você é vidente, ou viaja no tempo, ou (mais provável) **não está encostando na realidade**.  
  * **Dividir para Conquistar:** Quebrar a pedra grande cria rachaduras imprevisíveis. Aceite que o plano vai mudar conforme você executa.

### **4\. Hackeando o Cérebro: A Estratégia da Dopamina**

Como vencer a procrastinação usando a química do cérebro.

* **A Armadilha do "Tudo ou Nada":** Se a tarefa for "Construir o Site Inteiro", o cérebro vê um custo gigantesco e escolhe "Nada" (procrastinação), pois o saldo é negativo.  
* **Desapontar o Cérebro (no bom sentido):**  
  * O segredo é quebrar a tarefa até ela virar "pó".  
  * *Ex:* Em vez de "Fazer a Home", a tarefa é "Criar um arquivo index.js".  
  * A tarefa deve ser tão ridiculamente pequena e simples que o seu cérebro pensa: *"Só isso? Isso é fácil demais"*. Isso remove o medo e a resistência.  
* **A Estratégia do Judô (Dopamina):** Use a própria necessidade de recompensa do cérebro a seu favor.  
  * **Estágio 1 \- Início:** A expectativa de que algo bom vai acontecer (motivação para começar).  
  * **Estágio 2 \- Progresso:** Feedback visual de que você está avançando (checkboxes sendo marcados mantém a vontade de continuar).  
  * **Estágio 3 \- Conclusão:** A carga final de dopamina ao terminar (sensação de realização).

### **5\. Realidade vs. Planejamento**

* **Conexão na Tomada:** Quando o projeto vai para a realidade (produção/código real), ele está "ligado na tomada". Ele tem vida própria.  
* A realidade não se importa com o seu gráfico do Excel ou seu diagrama. Se houver divergência, a realidade sempre ganha.  
* Portanto: Planeje o suficiente para começar, mas coloque a "mão na massa" rápido para receber o feedback real do sistema.

## **DIA 10 📚 Revisão: Padronização de Código e Ferramentas de Estilo**

### **1\. Filosofia: "Assinatura" e Progresso**

Antes de configurar as ferramentas, é preciso entender o *porquê*.

* **Removendo a Assinatura Pessoal:**  
  * **O Problema:** Sem regras, cada programador escreve de um jeito (um usa aspas simples, outro duplas; um usa tab, outro espaço). Isso deixa uma "assinatura" pessoal no código.  
  * **O Objetivo:** O código deve parecer ter sido escrito por uma única entidade (o Time/Projeto), e não por várias pessoas diferentes. Quando você lê o arquivo, não deve saber *quem* escreveu, apenas que está bem escrito e padronizado.  
  * **Benefício:** Facilita a leitura e a manutenção por qualquer membro da equipe.  
* **Foco e Progresso Intermediário:**  
  * Marcar o progresso intermediário (pequenas vitórias) é crucial para manter o cérebro engajado.  
  * **A Regra do Foco:** Quanto maior o foco na tarefa atual e imediata, menor a probabilidade de desistir. Ferramentas de padronização automática (como o Prettier) ajudam nisso, pois tiram a carga cognitiva de ter que *pensar* na formatação, permitindo que você foque apenas na *lógica*.

### **2\. EditorConfig (`.editorconfig`)**

O primeiro nível de padronização acontece no próprio editor de texto (VS Code, Vim, Sublime, etc).

* **O que é:** Um arquivo simples que "ensina" o seu editor como ele deve se comportar neste projeto específico.  
* **Para que serve:** Garante que, independentemente do sistema operacional (Windows/Mac/Linux) ou do editor que a pessoa use, regras básicas sejam respeitadas, como:  
  * Tamanho da indentação (ex: 2 espaços).  
  * Quebra de linha (LF vs CRLF).  
  * Charset (UTF-8).  
* **Como usar:** Basta criar um arquivo chamado `.editorconfig` na raiz do projeto. Muitos editores já leem isso nativamente; o VS Code precisa de uma extensão chamada "EditorConfig for VS Code".

### **3\. Prettier**

Enquanto o EditorConfig configura o editor, o Prettier reescreve o código para garantir a formatação perfeita.

* **O que é:** Um formatador de código opinativo. Ele remove o debate de "como deve ser formatado" e simplesmente formata.

**Instalação:** Deve ser instalado como uma dependência de desenvolvimento, pois só é usado enquanto programamos, não na produção.  
Bash  
npm install prettier \-D

* *(O `-D` significa `--save-dev`)*  
* **Extensão do VS Code:** Para facilitar, instalamos a extensão "Prettier \- Code formatter" no VS Code e configuramos para "Format on Save" (Formatar ao salvar). Assim, o arquivo é corrigido automaticamente toda vez que você salva (`Ctrl + S`).

### **4\. Ignorando Arquivos (`.prettierignore`)**

Nem tudo deve ser formatado pelo Prettier.

* **Comportamento Padrão:** O Prettier já é inteligente o suficiente para ignorar pastas de sistema como `.git`, `.svn`, `.hg` e a pesada `node_modules`.  
* **Arquivo `.prettierignore`:** Funciona exatamente como o `.gitignore`. Você cria este arquivo para dizer ao Prettier quais outros arquivos ou pastas ele **não** deve tocar.  
  * *Exemplo:* Arquivos de build (`.next/`, `dist/`) ou arquivos públicos estáticos que não queremos alterar.

## 

## **DIA 13 📚 Revisão: Desbloqueio Criativo, Propósito e Confiabilidade (SLA)**

### **1\. Teoria McDonald's (Como destravar um time)**

Muitas vezes, a busca pela "ideia perfeita" paralisa o início do trabalho (o medo da página em branco).

* **O Conceito:** Quando ninguém sabe onde almoçar, sugira "McDonald's".  
  * Ninguém quer ir ao McDonald's, mas a sugestão "ruim" ou "absurda" quebra o gelo.  
  * Imediatamente alguém dirá: *"McDonald's não, vamos naquele italiano\!"*.  
* **Aplicação no Código:**  
  * Se você ou o time estão travados em como arquitetar uma solução complexa, sugira a solução mais simples e "feia" possível.  
  * Isso remove a pressão da perfeição e inicia o debate para chegar à solução correta. O objetivo é sair da inércia.

### **2\. A Bússola do Projeto: Propósito e Usuário**

Para que o projeto não se perca, é essencial sintetizar a missão em poucas frases. Isso serve como um "filtro" para decisões futuras.

* **O Manifesto:** Deve responder a 3 pontos para qualquer pessoa nova que entre no projeto (ou para você mesmo no futuro):  
  * **O que deve ser feito:** (A missão técnica/produto).  
  * **O que deve ser protegido:** (Os valores inegociáveis, ex: Performance, Clean Code).  
  * **O que deve ser repetido:** (A cultura e os rituais que mantêm o padrão).  
* **Ação Histórica:**  
  * Escreva isso no `README.md` ou na página inicial.  
  * Faça o **commit** e o **deploy**.  
  * **Tire um Print:** Guarde esse momento. No futuro, ver como tudo começou gera um valor nostálgico e prova a evolução. É a materialização da "Pedra Fundamental".

### **3\. A Ilusão do 100% Uptime e o SLA**

Na infraestrutura real, a perfeição não existe.

* **100% Uptime:** É praticamente impossível. Manutenções, falhas físicas, erros humanos e leis da física impedem que um sistema fique online para sempre sem interrupção.  
* **SLA (Service Level Agreement):** É o "contrato" de disponibilidade.  
  * O padrão de mercado geralmente é **99.9%** (os "três noves").  
  * Isso permite que o sistema fique fora do ar algumas horas por ano para manutenção ou falhas, sem quebra de contrato.  
  * Quanto mais "noves" (99.999%), exponencialmente mais caro e complexo é manter o sistema.

### **4\. Status Pages e a Mentira da Ferramenta**

Como saber se o problema é no seu código ou na nuvem (Vercel/AWS)?

* **Status Pages:** Páginas dedicadas onde as empresas reportam a saúde de seus serviços (ex: `vercel-status.com`, `health.aws.amazon.com`).  
* **A Lição de 2017 (AWS S3 Outage):**  
  * Em 2017, o serviço S3 da AWS caiu, derrubando metade da internet.  
  * Porém, a **Status Page da AWS mostrava tudo verde (funcionando)**.  
  * **Por que?** A própria página de status dependia do S3 para armazenar seus ícones e dados. Quando o S3 caiu, a página de status perdeu a capacidade de avisar que o S3 caiu.  
* **Conclusão Sênior:** Tenha alguém no time monitorando essas páginas, mas **não confie cegamente nelas**. Tenha seus próprios logs e alertas independentes.

## 

## **DIA 14 📚 Revisão: Estratégia de Desenvolvimento, PoC vs MVP e Arquitetura Inicial**

### **1\. A Característica Suprema: Modificabilidade**

Antes de qualquer linha de código, definimos a regra de ouro:

* **O Princípio:** A característica mais importante de um software não é ser rápido ou bonito, é ser **modificável**.  
* **O Motivo:** É a potência que o time tem de alterar o software a curto, médio e longo prazo. Se o software trava a mudança, ele morre.  
* **A Equação:** Para ter um software modificável, você precisa equilibrar as variáveis do mundo real (tempo, equipe, custo) com a complexidade técnica.

### **2\. PoC vs. MVP: A Analogia do Navio**

Como navegar no desconhecido sem afundar o projeto.

#### **O Cenário (O Navio Perdido)**

Imagine que você tem um navio gigantesco e pesado (seu projeto final), mas perdeu a bússola e o mapa. Você não sabe onde está o porto (a solução que o mercado quer).

#### **❌ A Abordagem Arriscada (Opção 1\)**

* Navegar com o navio inteiro para uma direção aleatória esperando encontrar terra.  
* **Risco:** O custo de manobra é alto. Se errar o caminho, você gasta todo o combustível (dinheiro/tempo) e a tripulação morre. É assim que muitos projetos falham na largada: tentando construir o "programa perfeito" sem saber se alguém quer.

#### **✅ A Abordagem Estratégica (Opção 2 \- PoC)**

* Lançar pequenos botes (barquinhos) com duas pessoas para explorar direções diferentes.  
* **PoC (Proof of Concept / Prova de Conceito):**  
  * **O que é:** O "barquinho". É descartável, barato e rápido.  
  * **Objetivo:** Descobrir a **direção**. Validar se uma ideia técnica ou de negócio para de pé.  
  * **Mentalidade:** O fracasso na PoC é valioso. Descobrir que "por ali não dá" economiza milhões, pois impede que você leve o navio gigante para o lugar errado.

#### **🚀 O MVP (Minimum Viable Product)**

* Depois que o barquinho encontrou a terra firme, você move o navio.  
* **O que é:** É o **Mínimo Produto Viável**.  
* **Estratégia:** Fazer o mínimo possível, mas de forma **excelente** e **focada**. Sem ruído.  
* **O Salto Consciente:** Diferente da PoC (que é rascunho), o MVP é produto real. Você pula com os dois pés, mas sabendo onde vai cair. É melhor entregar uma única funcionalidade que resolva 100% do problema de alguém do que 50 funcionalidades que fazem sombra umas nas outras e confundem o usuário.

### **3\. Complexidade: Simples vs. Sofisticado**

O dilema do **Overengineering** (Excesso de Engenharia).

* **A Verdade:** É difícil se arrepender de ter feito algo simples e depois evoluir. É frustrante (e caro) ter feito algo complexo e ter que simplificar.  
* **O Perigo da Complexidade:** Algo que nasce complexo tende a permanecer complexo. Seus tentáculos se amarram no sistema, exigindo "luta" para implementar qualquer novidade.  
* **O Equilíbrio Sênior:**  
  * **Simples bem feito:** Não é código amador. É resolver o problema sem trazer a complexidade desnecessária do mundo para dentro do código.  
  * **Decisão de Tecnologia:** Escolher a linguagem ou framework depende da maturidade da empresa, facilidade de contratação e comunidade, não apenas "hype".

### **4\. Milestone 1: A Fundação**

O objetivo desta etapa não é entregar valor ao usuário final ainda, mas preparar o terreno (o Navio) para que ele seja **modificável**.

**Checklist da Milestone 1:**

* \[ \] Proposta de Arquitetura e Pastas  
* \[ \] Testes Automatizados  
* \[ \] Banco de Dados Local  
* \[ \] Migrations  
* \[ \] Continuous Integration (CI)  
* \[ \] Linter de Código e Commits  
* \[ \] Configuração de Banco (Homologação e Produção)

### **5\. Arquitetura vs. Organização de Pastas**

Muitos confundem, mas são coisas distintas.

* **Arquitetura:** É conceitual. Define os limites, responsabilidades e como os componentes conversam (ex: MVC). Você pode ter uma arquitetura MVC dentro de um único arquivo.  
* **Organização de Pastas:** É físico. Onde os arquivos são salvos.  
* **Decisão do Projeto:** Deixaremos o framework (Next.js) nos ajudar, mas definiremos limites claros para não ficarmos reféns dele.

#### **Proposta de Estrutura de Pastas**

Esta estrutura separa claramente o que é responsabilidade do Framework, do Negócio e da Infraestrutura.

Plaintext  
📦 root  
 ┣ 📂 pages        (Interface/Rotas \- Onde o Next.js atua)  
 ┃ ┗ 📜 index.js  
 ┣ 📂 models       (Regras de Negócio \- O coração do sistema)  
 ┃ ┣ 📜 user.js  
 ┃ ┣ 📜 content.js  
 ┃ ┗ 📜 password.js  
 ┣ 📂 infra        (O que sustenta o sistema \- Banco, Scripts)  
 ┃ ┣ 📜 database.js  
 ┃ ┣ 📂 migrations  
 ┃ ┗ 📂 provisioning  
 ┃ ┃ ┣ 📂 staging  
 ┃ ┃ ┗ 📂 production  
 ┗ 📂 tests        (Garantia de qualidade e segurança para refatorar)

![][image1]

## **DIA 15 📚 Revisão: Testes Automatizados, Jest e Cultura de Qualidade**

### **1\. A Importância: Mantendo o Software "Modificável"**

Como vimos, a característica mais valiosa de um software é ser **modificável**.

* **O Problema do Crescimento:** Conforme o projeto cresce, testar tudo manualmente (clicar em botões, preencher formulários) torna-se impossível.  
* **O Medo de Mexer:** Sem testes, você desenvolve medo de alterar um código antigo porque não sabe se vai quebrar algo em um "canto" escondido do sistema. Isso mata a modificabilidade.  
* **A Solução:** Testes automatizados garantem que o que funcionava ontem continua funcionando hoje. Eles dão a **segurança** necessária para refatorar e evoluir o código constantemente.

### **2\. O Que é um Test Runner (Jest)**

Test Runners são programas que executam o código que você escreveu para verificar se ele se comporta como esperado.

* **Fluxo de Trabalho:**  
  1. O Test Runner executa o teste.  
  2. Compara o resultado obtido com o resultado esperado.  
  3. Gera um **Report** (Relatório).  
* **Uso no CI/CD:** Esse relatório é o sinal verde ou vermelho para o deploy. Se o teste falhar, o código não vai para produção.  
* **Modo Watch:** Durante o desenvolvimento, usamos o modo de "observação" (\--watch). O Jest fica assistindo seus arquivos; a cada Ctrl+S, ele roda os testes afetados instantaneamente, dando feedback imediato.

**Instalação (Versão Específica):**

Para garantir compatibilidade neste estágio do projeto, usamos:

Bash

npm install \--save-dev jest@29.6.2

### **3\. Anatomia de um Teste e a Cultura do Erro**

* **A Estrutura Básica:** Todo teste é uma comparação entre um **Valor Gerado Dinamicamente** (pelo seu código) e um **Valor Esperado** (Hardcoded/Fixo).  
  * *Se (Gerado \=== Esperado):* ✅ Passou.  
  * *Se (Gerado \!== Esperado):* ❌ Falhou.  
* **Cultura de Erro (Antifragilidade):**  
  * Ver erros vermelhos na tela não deve ser assustador. O ambiente de desenvolvimento **deve** ser um lugar onde errar é barato e seguro.  
  * É melhor "tomar o susto" agora, no seu computador local, do que o erro explodir na cara do usuário em produção.  
  * Um bom ambiente de dev é resiliente: ele aguenta falhas e se recupera rápido. Não tenha medo de entrar em "ruas sem saída" enquanto explora o código.

### **4\. Desafios Técnicos: CommonJS vs ES Modules**

No ecossistema JavaScript, existem duas formas principais de importar arquivos. Entender isso é vital para lidar com código legado e moderno.

* **ES Modules (ESM):** É o padrão moderno (import / export). É o futuro.  
* **CommonJS (CJS):** É o padrão antigo do Node.js (require / module.exports).  
* **A Decisão do Projeto:** O Jest (na configuração padrão desta versão) funciona nativamente com CommonJS.  
  * Para simular o ambiente real de muitas empresas e aprender a lidar com incompatibilidades, começaremos usando o require (método antigo).  
  * Futuramente, implementaremos um processo de **Transpiling** (conversão de código), aprendendo como modernizar uma base de código antiga para a nova.

### **5\. Limitações e Metodologias (TDD)**

* **A Verdade sobre Testes:** Um teste não prova que o software está livre de bugs. Ele apenas prova que, **para aquele cenário específico que você escreveu**, o código funciona.  
* **Documentação Viva:** Testes automatizados são a melhor documentação possível, pois eles mostram como o código realmente funciona na prática e nunca ficam desatualizados (senão o teste quebra).  
* **TDD (Test Driven Development):**  
  * **Método Tradicional:** Escreve o código $\\to$ Escreve o teste.  
  * **Método TDD (Profissional):**  
    1. **Red:** Escreve o teste primeiro (ele vai falhar, pois a funcionalidade não existe).  
    2. **Green:** Escreve o código mínimo necessário para o teste passar.  
    3. **Refactor:** Melhora o código com a segurança de que o teste garante o funcionamento.

## 

## **DIA 16 📚 Revisão: Estratégia de Testes, Design de API e Desmistificando o HTTP**

### **1\. Estratégias de Testes: Pirâmide vs. Troféu**

O maior mistério não é ter peças funcionando isoladamente, mas fazer com que elas se encaixem.

* **A Pirâmide de Testes (Modelo Clássico):**  
  * **Base (Unitários):** A maior parte dos testes. São rápidos e baratos. Testam funções isoladas.  
  * **Meio (Integração):** Testam a comunicação entre módulos (ex: API \+ Banco).  
  * **Topo (E2E/UI):** Testam a interface completa. Lentos, caros e frágeis.  
  * *Crítica:* Ter 100% de cobertura unitária não garante que o sistema funcione quando montado.  
* **O Troféu de Testes / Favo de Mel (Modelo Moderno/API First):**  
  * Focada em empresas como Spotify ou produtos API First (Pagar.me, TabNews).  
  * **A Mudança:** O foco sai da base (Unitários) e vai para o meio (**Integração**).  
  * **Por quê?** O teste de integração oferece o maior retorno sobre o investimento (ROI). Ele garante que o sistema funciona (confiança) sem ser tão lento ou frágil quanto um teste de interface gráfica (E2E).  
  * *Lema:* "Escreva testes. Não muitos. Principalmente de integração." (Guillermo Rauch / Kent C. Dodds).

### **2\. O Que é uma API e a Natureza das Interfaces**

Tudo com o que interagimos possui uma interface (abstração).

* **O Conceito:** Os botões do micro-ondas são uma interface para não precisarmos lidar com a física das ondas eletromagnéticas (implementação).  
* **A Fronteira:** Interfaces definem bordas claras. Sem elas, temos "código macarronico" (onde não se sabe onde começa um módulo e termina outro).  
* **Tipos de Interface:**  
  * **GUI (Graphical User Interface):** Feita para **Humanos** (botões, cores). É visual, muda com frequência e é difícil de automatizar.  
  * **API (Application Programming Interface):** Feita para **Máquinas/Programas**. É estruturada, previsível e baseada em texto/dados.  
  * **Conexão:** Ambas podem usar o mesmo protocolo (HTTP) por baixo dos panos. A diferença é quem consome.

### **3\. Desmistificando a "Magia": HTTP e cURL**

Para ter autoridade técnica, precisamos eliminar a palavra "magia" do nosso vocabulário e entender o que acontece "nos bastidores".

* **A Ferramenta da Verdade (curl \-v):**  
  * Navegadores escondem detalhes. O terminal mostra a verdade.  
  * \* (Asterisco): O que o cURL fez internamente (ex: handshake TLS).  
  * \> (Maior que): O cabeçalho da **Requisição** (o que você enviou).  
  * \< (Menor que): O cabeçalho da **Resposta** (o que o servidor devolveu).  
  * *Lição:* As informações estão lá, só precisamos olhar (modo verbose).  
* **O Caso do Redirecionamento na Vercel (IP vs Host):**  
  * **O Fenômeno:** Se você acessar o IP direto de um deploy da Vercel, recebe um erro ou redirecionamento (308), não o site.  
  * **A Explicação Técnica:** A Vercel usa hospedagem compartilhada. Um único IP serve milhares de sites.  
  * **O Segredo:** O servidor precisa do cabeçalho Host na requisição para saber *qual* site entregar. Se você acessa pelo IP, o navegador não manda o nome do site, e o servidor fica perdido. Não é magia, é protocolo HTTP.

### **4\. Versionamento de APIs e Contratos**

Ao contrário de interfaces visuais que o usuário humano se adapta visualmente, APIs precisam de contratos rígidos para não quebrar integrações.

* **Tipos de Mudanças:**  
  * **Breaking Change (Quebra de contrato):** Você removeu um campo ou mudou o tipo de dado (ex: de string para int). O código de quem consome vai quebrar.  
  * **Non-breaking Change (Retrocompatível):** Você *adicionou* um campo novo. O sistema antigo deve continuar funcionando.  
  * **Backward Compatibility:** A capacidade do sistema novo entender e respeitar as regras antigas.  
* **Responsabilidades:**  
  * **Consumidor:** Deve criar códigos resilientes.  
    * *Exemplo de Erro:* Fazer um script que quebra se a API retornar um campo extra que ele não esperava. Isso é culpa do consumidor. O código deve ignorar o desconhecido, não travar.  
  * **Fornecedor:** Deve avisar sobre mudanças drásticas.  
    * *Estratégias:* URI Versioning (/v1/status, /v2/status) ou Header Versioning.

DIA 17 \#\# 📚 Revisão: História da Infraestrutura, Decisões de Banco de Dados e Docker

\#\#\# 1\. Filosofia: "Future-Proof" e a História

Para tomar decisões técnicas sólidas, muitas vezes precisamos olhar para o passado.

\* \*\*Avançar olhando para trás:\*\* A tecnologia é cíclica. Entender como as coisas eram feitas "no dia 0" nos protege de cair em \*hypes\* (ondas de empolgação) passageiros.  
\* \*\*Future-Proof (À prova de futuro):\*\* Buscamos conhecimentos que não expirem em 6 meses. SQL, Protocolo HTTP e Linux são exemplos de conhecimentos perenes.

\#\#\# 2\. A Escolha do Banco de Dados (DBMS & Querying)

Ao escolher a camada de dados, tomamos três decisões fundamentais:

\* \*\*O SGBD (Sistema de Gerenciamento de Banco de Dados):\*\*  
\* É o software servidor. Exemplos: PostgreSQL, SQL Server, MySQL.  
\* \*Decisão:\* PostgreSQL (Open source, robusto, padrão de mercado).

\* \*\*A Interface de Comunicação (Querying):\*\*  
\* \*\*ORM (Object Relational Mapping):\*\* Abstrai o SQL, permitindo usar código da linguagem (JS/Python) para buscar dados.  
\* \*\*Driver Nativo / Query Builder:\*\* Mais próximo do SQL puro.  
\* \*Decisão:\* Usar o \*\*\`pg\` (node-postgres)\*\*.  
\* \*Por que não ORM agora?\* Para aprender SQL de verdade. ORMs escondem a complexidade (e o poder) das \*queries\*, como as \*\*CTEs (Common Table Expressions)\*\* para buscas recursivas.

\* \*\*Versionamento (Migrations):\*\*  
\* Precisamos de um histórico das mudanças no esquema do banco.  
\* \*Ferramenta:\* \*\*\`node-pg-migrate\`\*\*. Garante segurança e reprodutibilidade ao alterar tabelas.

\#\#\# 3\. A Evolução da Virtualização: De VMs a Containers

Como saímos de "maquinas pesadas" para "processos isolados".

\* \*\*O Passado (Vagrant/VMs):\*\*  
\* Para garantir que "funciona na minha máquina e na sua", usávamos máquinas virtuais completas.  
\* \*Problema:\* Cada VM rodava um Sistema Operacional (OS) inteiro. Consumo absurdo de RAM e CPU.

\* \*\*A Revolução (Linux 2013+):\*\*  
\* Descobriu-se como combinar \*\*Namespaces\*\* (isolamento de visão) e \*\*Cgroups\*\* (controle de recursos) do Linux.  
\* \*\*PID Namespace:\*\* Permite criar uma árvore de processos isolada. O processo acha que é o PID 1 (o chefe), mas é apenas um filho no OS hospedeiro.

\* \*\*O Resultado (Docker):\*\*  
\* Não cria um sistema novo. Apenas isola processos dentro do \*mesmo\* Kernel. Leveza extrema.

\#\#\# 4\. Docker na Prática: Conceitos e Arquivos

\* \*\*YAML (\`compose.yml\`):\*\*  
\* \*Significado:\* "YAML Ain't Markup Language" (YAML não é linguagem de marcação).  
\* É uma linguagem de \*\*serialização de dados\*\* (configuração).  
\* É um \*superset\* de JSON (todo JSON é um YAML válido, mas o YAML é mais limpo visualmente).  
\* \*Nome do arquivo:\* O padrão moderno aceita apenas \`compose.yml\` (sem o prefixo \`docker-\`).

\* \*\*Imagem vs. Container:\*\*  
\* \*\*Imagem:\*\* É a "classe" ou o "molde". O código estático e imutável.  
\* \*\*Container:\*\* É a "instância" ou o "objeto". É a imagem em execução (viva).

\* \*\*Versões Alpine:\*\*  
\* Imagens baseadas no Alpine Linux. Extremamente leves (5mb), focadas em rodar com o mínimo de hardware possível.

\#\#\# 5\. Dicionário de Comandos e Operações (Unix Way)

\#\#\#\# Monitoramento e Status

\* \`docker ps\`: Lista os containers ativos. (Vem do \`ps\` do Unix \- \*Process Status\*).  
\* \*\*Exit Codes:\*\*  
\* \`0\`: Sucesso.  
\* \`\> 0\` (1 a 255): Erro.  
\* \*CI/CD:\* O Continuous Integration olha para esse número. Se for diferente de 0, ele bloqueia o deploy.  
\* \*Graceful Shutdown:\* O objetivo é que o processo termine suas tarefas e saia "graciosamente" (código 0), sem ser morto abruptamente.

\#\#\#\# Cliente PostgreSQL (\`psql\`)

O \`psql\` é o terminal interativo oficial do Postgres.

\`\`\`bash  
\# Conectar  
psql \--host=localhost \--username=postgres \--port=5432

\# Comandos internos  
\\q  \# (Quit) Para sair  
\\l  \# Listar bancos  
\\dt \# Listar tabelas

\`\`\`

\#\#\#\# Docker Compose (Gerenciamento)

\* \`docker compose up\`: Sobe os serviços travando o terminal (mostra logs).  
\* \`docker compose up \-d\`: (\*\*Detached\*\*). Sobe em background (segundo plano), liberando o terminal.  
\* \`docker compose up \-d \--force-recreate\`: Força o Docker a matar o container atual e criar um novo do zero (útil se você mudou configurações no YAML).  
\* \`docker compose \-f infra/compose.yml up\`: A flag \`-f\` (file) diz onde está o arquivo se ele não estiver na pasta atual.

## **DIA 18 📚 Revisão: Segurança, Arquitetura Stateless, Neurociência e Produtividade**

### **1\. Atualizações, Segurança e a Rede de Proteção (Testes)**

Manter dependências atualizadas é vital, mas perigoso.

* **O Risco das Atualizações:** Até mesmo atualizações de segurança (patches) podem quebrar seu código. Uma mudança em uma biblioteca pode alterar comportamentos sutis que derrubam a aplicação.  
* **A Função dos Testes Automatizados:**  
  * Eles são o "detetor de fumaça". Se você atualizar uma lib e os testes de integração passarem, você tem confiança para seguir. Se falharem, você sabe exatamente o que quebrou.  
* **Jest Watch Mode:**  
  * npm run test:watch: Por padrão (dependendo da config do git), o Jest roda testes apenas em arquivos **modificados** desde o último commit. É rápido e focado.  
  * \--watchAll: Força o Jest a rodar **todos** os testes do projeto, independentemente do que foi alterado. Útil para garantir que uma mudança global não quebrou algo não relacionado.

### **2\. Neuroplasticidade: Como o Cérebro Aprende Código**

A neurociência explica por que existe uma diferença brutal entre "Copiar e Colar" e "Digitar".

* **O Conceito:** O cérebro não é fixo; ele se molda (plástico) a novas situações. Cada nova informação cria ou reforça conexões neuronais.  
* **A Regra do Aprendizado:**  
  * **Código Novo (Desconhecido):** **Digitar caractere por caractere.** Isso força o cérebro a processar a sintaxe, a lógica e a estrutura, abrindo uma nova "trilha" na mente.  
  * **Código Conhecido (Repetitivo):** **Copiar e Colar.** Se a trilha já existe, não gaste energia cognitiva recriando-a. Poupe tempo.

### **3\. Arquitetura de Serviços: Camadas e Stateless**

Para escalar uma aplicação, precisamos entender onde o "Estado" (dados/memória) vive.

* **As 3 Camadas de um Serviço:**  
  * **Interface:** A porta de entrada (API/Frontend).  
  * **Aplicação:** A lógica de negócio (Regras).  
  * **Persistência:** Onde o dado é salvo (Banco de Dados).  
* **Stateful vs. Stateless:**  
  * **Stateful (Com Estado):** O servidor guarda informações do usuário na própria memória/hardware. Se o servidor reiniciar, perde-se tudo. É difícil de escalar (criar réplicas).  
  * **Stateless (Sem Estado):** O servidor é "efêmero". Ele processa a requisição e esquece. O estado é jogado para fora (Banco de Dados ou Cache externo).  
  * **Vantagem:** Se a aplicação é Stateless (ignorante), você pode ter 1 ou 1000 servidores rodando o mesmo código. As **Variáveis de Ambiente** são a chave para configurar essas réplicas sem mudar o código.

### **4\. Domínio das Variáveis de Ambiente (.env)**

As *Environment Variables* desacoplam a configuração do código.

* **Flexibilidade Extrema:**  
  * O código não deve saber se está mandando email via *Mailgun* ou *Mailcatcher*. Ele apenas lê EMAIL\_PROVIDER\_URL das variáveis. Isso permite trocar serviços inteiros mudando apenas uma linha de texto na configuração.  
  * **Configurações Complexas:** É possível passar um JSON inteiro em uma única variável de ambiente (ex: regras de Rate Limit) e fazer o JSON.parse dentro da aplicação.  
* **Implementação:**  
  * **Node.js:** Acesso nativo via process.env.NOME\_DA\_VARIAVEL.  
  * **Dotenv:** Biblioteca (npm install dotenv) que lê um arquivo .env e o injeta no process.env durante o desenvolvimento.  
  * **Docker Compose:** Usamos a diretiva env\_file: ./path/to/.env no compose.yml para injetar as variáveis nos containers.  
* **Segurança no Terminal (O Truque do Espaço):**  
  * Ao rodar POSTGRES\_PASSWORD=senha123 npm run dev, a senha fica salva no histórico do terminal (history).  
  * **A Solução:** Adicione um **espaço** antes do comando.  
    * \<espaço\>POSTGRES\_PASSWORD=senha123 npm run dev  
    * A maioria dos shells (Bash/Zsh) é configurada para não gravar no histórico comandos que começam com espaço em branco.

### **5\. Docker: O Limite do force-recreate**

Um detalhe técnico importante sobre Bancos de Dados em Docker.

* Se você mudar a senha ou nome do banco no .env e rodar docker compose up \-d \--force-recreate, **o banco muitas vezes não muda a senha**.  
* **Por quê?** O script de inicialização do Postgres (initdb) só roda quando o **Volume** (o disco virtual) é criado pela primeira vez. Se o volume já existe, ele ignora as novas variáveis de ambiente.  
* **Solução:** É necessário derrubar o container **e** o volume: docker compose down \-v (Cuidado: isso apaga os dados).

### **6\. Produtividade: O Poder do Multi-Cursor (Ctrl \+ D)**

Não edite código repetitivo linha por linha.

* **A Técnica:** Selecione uma palavra (âncora) e pressione Ctrl \+ D (CMD \+ D no Mac).  
* **O Resultado:** O VS Code cria um novo cursor na próxima ocorrência idêntica dessa palavra.  
* **Uso:** Você pode renomear variáveis, alterar propriedades ou formatar múltiplas linhas simultaneamente. É como ter vários mouses trabalhando ao mesmo tempo.

## 

## **DIA 19 📚 Revisão: Segurança de Dados, Imports Absolutos e Filosofia de Verificação**

### **1\. O Dilema do .env e Segurança no Git**

A gestão de variáveis de ambiente é a primeira linha de defesa da segurança da aplicação.

* **Pode dar push no .env?**  
  * **Regra de Ouro:** Nunca envie **segredos** (senhas, chaves de API privadas, chaves de criptografia) para o repositório público.  
  * **A Exceção (Defaults):** Arquivos como .env.example ou .env.development podem ser comitados **SE E SOMENTE SE** contiverem apenas configurações públicas ou valores padrão inofensivos para o ambiente de desenvolvimento local.  
* **Precedência de Variáveis:**  
  * É crucial entender quem manda em quem.  
  * **Hierarquia:** Configurações do Ambiente (Vercel/Sistema) \> .env.local \> .env.development \> .env.  
  * Isso garante que a produção (Vercel) nunca leia acidentalmente uma configuração de teste.  
* **Gestão de Crise (Vazamento de Senha):**  
  * Se uma senha foi comitada, ela está comprometida. **O Github é um sistema distribuído**; a informação já foi replicada.  
  * **Passo 1:** **Rotacionar a credencial.** Mude a senha ou revogue a chave imediatamente.  
  * **Passo 2:** Limpar o histórico. Ferramentas como **BFG Repo-Cleaner** ou **git-filter-repo** reescrevem a história do Git para remover o arquivo.  
  * **Consequência:** Isso altera o *hash* de todos os commits subsequentes, exigindo um git push \--force e sincronização com todos do time.

### **2\. Imports Absolutos vs. Relativos**

Como organizar a casa e melhorar a leitura do código.

* **O Problema (Hell dos Pontinhos):**  
  * Imports relativos (../../../../infra/database.js) são frágeis. Se você mover o arquivo de lugar, o import quebra. Além disso, é difícil saber onde a raiz está visualmente.  
* **A Solução (Absolute Imports):**  
  * Usar a raiz do projeto como base: import database from "infra/database.js".  
  * Melhora o **Intellisense** (auto-complete) do VS Code.  
* **Configuração (jsconfig.json):**  
  * Para o VS Code entender isso em projetos JavaScript, criamos o arquivo na raiz.  
  * **Por que compilerOptions?** O VS Code usa o serviço do TypeScript por baixo dos panos para entender JavaScript. Por isso, usamos a mesma estrutura do tsconfig.json.  
* JSON

{  
  "compilerOptions": {  
    "baseUrl": "."  
  }  
}

*   
  * baseUrl: ".": Define que a pasta atual (raiz onde o arquivo está) é o ponto de partida para os imports.

### **3\. Filosofia de Teste: O Caso da "Caneta de Tensão"**

Uma lição valiosa sobre garantir resultados e evitar "Falsos Negativos".

* **A História:** Uma pessoa tomou choque porque a caneta de tensão indicou "sem energia". O problema não era a energia, mas a pessoa não saber usar a ferramenta (não fechou o circuito com o dedo).  
* **Aplicação no Código:**  
  * Antes de aplicar uma solução (como criar o jsconfig.json), **teste o cenário sem ele**.  
  * Verifique: "O Next.js já faz isso nativamente? O import absoluto funciona sem eu fazer nada?"  
  * Se você não testar o "antes", pode adicionar configurações inúteis ("fantasmas") que aumentam a complexidade sem trazer valor.  
  * **Conclusão Sênior:** Só adicione código ou configuração se você provou que o sistema falha sem ele. Isso evita inchar o projeto com lixo técnico.

### **4\. Produtividade: Fuzzy Search no VS Code**

Navegar no código deve ser tão rápido quanto pensar.

* **Busca Difusa (Ctrl \+ P):**  
  * Não precisa digitar o caminho exato. Digite letras soltas que compõem o nome do arquivo.  
  * Ex: dbs pode encontrar infra/database.js.  
* **Navegação por Símbolos (@):**  
  * Dentro da busca, use @ para filtrar o conteúdo do arquivo.  
  * **Combo Master:** pk@sc  
    * pk: Acha o package.json.  
    * @sc: Vai direto para a linha dos scripts dentro do arquivo.  
  * Isso economiza segundos preciosos de rolagem e leitura visual.

## **DIA 20 📚 Revisão: Construção de API, Arquitetura, Segurança e Filosofia Sênior**

### **1\. O Endpoint /status e Padrões de API**

O objetivo do endpoint /status é expor a "saúde" do sistema (usado em *Status Pages*).

* **A Estrutura de Dados:**  
  * **updated\_at**: A data e hora exata da verificação.  
  * **Dependências:** Informações vitais como versão do Node.js, versão do Banco de Dados (Postgres), latência, conexões máximas permitidas e conexões abertas no momento.  
* **Formatação de Datas:**  
  * Date.now(): Retorna um *Unix Timestamp* (milissegundos desde 01/01/1970). Não é legível para humanos.  
  * new Date().toISOString(): Retorna o padrão **ISO 8601** (ex: 2026-02-13T02:44:34Z). O "Z" significa *Zulu Time Zone* (Tempo Universal Coordenado \- UTC/Fuso zero).  
* **Convenção de Nomes (JSON):**  
  * No JavaScript, usamos camelCase (ex: updatedAt).  
  * Em APIs REST, o consenso de mercado é usar **snake\_case** nas chaves do JSON (ex: updated\_at). Precisamos converter isso na hora de responder a requisição.

### **2\. Arquitetura: O Trampolim do MVC**

Como estruturar o código sem cair no excesso de engenharia.

* **A Teoria (MVC):**  
  * **Controller (Controlador):** O maestro. Ele recebe o pedido, orquestra as ferramentas e devolve a resposta. Não deve ter regras de negócio complexas.  
  * **Model (Modelo):** Onde os dados são gerados e as regras de negócio vivem.  
  * **View (Visão):** A interface final (no nosso caso de API, o próprio JSON devolvido).  
* **A Prática (O Caminho Sênior):**  
  * Podemos começar fazendo **tudo dentro do Controller**.  
  * *Por quê?* Para evitar abstração prematura. Criar um Model antes de precisar reutilizar aquele código apenas gera mais arquivos para manter.  
  * *A Evolução:* O Controller será nosso trampolim. Assim que sentirmos "dor" (necessidade de reaproveitar a lógica em outro lugar), refatoramos e extraímos o código para um Model, usando os testes automatizados para garantir que nada quebrou na mudança.

### **3\. Filosofia de Testes Automatizados**

O equilíbrio entre garantia e custo de manutenção.

* **O Custo da Garantia Perfeita:** Quanto mais restrito é o seu teste (ex: checar tipo, tamanho, formato exato de cada variável), mais caro e frágil ele fica. Tentar prever 100% das falhas é uma ilusão que paralisa o projeto.  
* **A Postura Sênior:** Siga em frente. Crie os testes essenciais e deixe que o uso real (clientes) revele os casos extremos (*edge cases*). Quando o bug acontecer, você adiciona um novo teste para cobri-lo.  
* **A Exceção (Testes Estritos para Segurança):**  
  * Se for um JSON que **não pode** vazar dados (ex: retorno de dados de usuário, onde a senha jamais pode aparecer), o teste deve ser **estrito**.  
  * O teste deve falhar se *qualquer* chave extra não documentada aparecer no JSON. Isso garante que, se alguém no futuro adicionar um campo sensível por engano, o CI barra o deploy.

### **4\. Banco de Dados, Segurança e SQL Injection**

Como consultar o Postgres com segurança.

* **O Retorno Padrão:** Toda consulta no Postgres (seja SELECT, UPDATE ou SHOW) sempre retorna um formato de linhas (rows), ou seja, um Array/Lista.  
* **Views de Status:** O Postgres tem tabelas especiais para ler a saúde dele:  
  * pg\_stat\_database: Estatísticas gerais.  
  * pg\_stat\_activity: O "ao vivo" (mostra o que está rodando exatamente agora).  
* **SQL Injection (Injeção de SQL):** Ocorre quando usamos Template Strings do JS para concatenar dados do usuário direto na *Query*.  
  * **3 Tipos de Queries:** 1\) Sem parâmetros (Segura). 2\) Parâmetros Fixos/Hardcoded (Segura). 3\) Parâmetros Dinâmicos (Vulnerável).  
* **O Perigo da Sanitização Manual:**  
  * Tentar limpar variáveis criando regras manuais (Regex, Replace) é amador e perigoso.  
  * *O Caso "ALTER do Chão":* Se você bloqueia a palavra ALTER, um usuário da cidade de "Alter do Chão" não consegue se cadastrar. Na Inglaterra, empresas já registraram nomes com códigos de DROP TABLE para testar os sistemas do governo.  
  * *A Solução:* Usar as **Queries Parametrizadas** (fornecidas pela biblioteca do banco), onde o próprio *driver* separa o que é código do que é texto de forma 100% segura.

### **5\. Prevenção de Vazamento de Conexões (Try-Catch)**

Um erro silencioso que derruba servidores.

* **O Problema:** Você abre uma conexão com o banco. Ocorre um erro no meio da consulta (Exceção). O código para a execução e **não fecha a conexão**.  
* **O Vazamento:** Se isso acontecer várias vezes, o banco atinge o limite máximo de conexões e o sistema inteiro cai.  
* **A Solução:** Envolver a comunicação com o banco em um bloco try / catch / finally. Independentemente de a consulta dar certo (try) ou explodir um erro (catch), a conexão **deve** ser fechada no bloco finally (ou tratada corretamente no catch para garantir o fechamento).

### **6\. Dica de Produtividade**

* **Layout Personalizado:** Não use a configuração padrão do seu editor de código (VS Code) se ela não serve para você. Ajuste os painéis, atalhos e temas para criar o *seu* ambiente de foco. A organização externa reflete na clareza do código.

## **DIA 21 📚 Revisão: Infraestrutura, Segurança, Debugging e Estratégia de Mercado**

### **1\. Infraestrutura, Nuvem e Flexibilidade**

A forma como lidamos com a infraestrutura dita a velocidade e a segurança do projeto.

* **O Banco de Dados como "Commodity":**  
  * Uma *commodity* é uma mercadoria básica que é quase a mesma, não importa quem venda (como água ou arroz). Hoje, bancos de dados (como o PostgreSQL) são *commodities*. Não importa se você compra da AWS, DigitalOcean ou Neon; o que importa é a flexibilidade do seu código de se conectar a qualquer um deles.  
* **Vendor Lock-in (Aprisionamento Tecnológico):**  
  * É quando seu código fica tão dependente das ferramentas exclusivas de uma única empresa (ex: AWS) que mudar de provedor se torna impossível ou caríssimo.  
  * **A Solução:** Garantir que o código funcione em vários provedores (como testar no Neon, DigitalOcean e localmente) prova que sua arquitetura é flexível e livre de *Vendor Lock-in*.  
* **ClickOps vs. IaC (Infrastructure as Code):**  
  * **ClickOps:** Configurar servidores e bancos clicando em botões no painel do provedor. É visual, mas é frágil, difícil de replicar e propenso a erros humanos.  
  * **IaC:** Escrever a infraestrutura em código (ex: Terraform, arquivos Docker). É auditável, versionável e replicável.

### **2\. O Medo da Produção e os Testes de Limites**

A produção não deve ser uma caixa preta assustadora. Você faz parte dela.

* **A Metáfora do Carro Parado (Deploys):**  
  * Ficar muito tempo sem fazer *deploy* em produção é como deixar um carro parado na garagem por meses. Quando você tentar ligar, a bateria vai falhar, o óleo vai estar grosso e tudo vai dar problema. **Deploys devem ser frequentes e pequenos.**  
* **Testando os Limites (O Caso do Postgres):**  
  * Não tenha medo de quebrar o ambiente local. Simule o pior cenário: escreva um código que esgote o limite máximo de conexões do banco de dados propositalmente.  
  * **O Segredo do Postgres:** Quando todas as conexões se esgotam, o Postgres inteligentemente reserva um pequeno número de conexões apenas para **superusuários** (admin). Assim, você não fica "trancado do lado de fora" da própria casa e consegue entrar para matar os processos travados. Se você testar isso antes, não entrará em pânico quando acontecer na vida real.

### **3\. Debugging Profissional: Como resolver problemas**

Não se conserta o que não se entende.

* **Regra nº 1: Replique o Erro.**  
  * Antes de tentar corrigir um erro (principalmente os de produção), você precisa ser capaz de replicá-lo no seu ambiente local. Isso dá a **confiança** de que você realmente encontrou a raiz do problema.  
* **Isolando o Problema (Consistente vs. Intermitente):**  
  * **Erro Consistente:** Acontece toda vez que você clica no botão. É fácil de isolar e corrigir.  
  * **Erro Intermitente:** Acontece de vez em quando. É um pesadelo. Provavelmente não há um ponto único de falha, ou depende de concorrência de dados.  
  * **A Arma (Logs):** Para erros intermitentes, aproxime sua imaginação da realidade espalhando logs de aplicação e de servidor. Registre o estado dos dados antes e depois do erro para ter um "filme" do que aconteceu.  
* **Ferramenta de Limpeza (git restore):**  
  * Durante o *debugging*, você vai bagunçar o código. O comando git restore \<arquivo\> (ou git restore . para tudo) é o botão de pânico que desfaz todas as alterações não comitadas, voltando o arquivo exatamente como estava no último commit.

### **4\. Segurança Paranoica e Criptografia**

Estratégias para proteger você e seus usuários.

* **Estratégia de E-mail (O Pulo do Gato):**  
  * Nunca use seu e-mail raiz (seu.nome@gmail.com) para criar contas em serviços críticos como AWS ou Bancos de Dados.  
  * **Plus Addressing:** O Gmail permite adicionar um \+ e qualquer palavra. Ex: seu.nome+aws@gmail.com. Isso cria um "sub-email" dinâmico que cai na sua caixa de entrada principal.  
  * **O Truque do Ponto:** O Gmail ignora pontos. s.e.u.nome@gmail.com cai na mesma caixa.  
  * **A Vantagem:** Se houver um vazamento de dados, você saberá exatamente de onde vazou. Além disso, evita ataques de força bruta, pois o invasor não sabe qual é a variação do seu e-mail. *(Nota: Alguns sites antigos rejeitam o \+, então teste primeiro ou use o truque do ponto nestes casos).*  
* **A Senha "Temperada" (Bitwarden):**  
  * Usar gerenciadores de senha é ótimo, mas se alguém invadir seu gerenciador, você perde tudo.  
  * **A Paranoia Saudável:** Salve uma senha complexa no gerenciador (ex: 123@abc), mas na hora de colar no site, adicione mentalmente um prefixo e um sufixo que só você sabe (ex: A+123@abc+Z). Assim, mesmo se o gerenciador vazar, a senha armazenada lá é inútil.  
* **Criptografia e Certificados (O Básico da Web Segura):**  
  * **Chaves Assimétricas:** Um par de chaves (Pública e Privada). O que a pública tranca, só a privada destranca. (Seguro, mas lento).  
  * **Chave de Sessão (Simétrica):** Uma chave única gerada na hora para aquela conexão específica. (Mais rápido, usado após o "aperto de mão" inicial).  
  * **CA (Certificate Authority):** É o "cartório" da internet. Ele atesta que a chave pública do TabNews realmente pertence ao TabNews.  
  * **Self-signed Certificate:** Um certificado assinado por você mesmo. Criptografa os dados, mas o navegador avisa que "não confia", pois não foi validado por um "cartório" (CA). Útil apenas para desenvolvimento local.  
  * **PKI:** A infraestrutura e regras que mantêm todo esse sistema de chaves e cartórios funcionando.

### **5\. O Ecossistema Node: NODE\_ENV**

* O NODE\_ENV é uma variável de ambiente clássica e fundamental no ecossistema JavaScript.  
* **O que faz:** Ela informa ao seu código e aos *frameworks* (como React, Next.js, Express) em qual ambiente o sistema está rodando (geralmente development, production ou test).  
* **Por que é crucial:** Em production, os frameworks se otimizam automaticamente (removem logs, escondem rastros de erros críticos dos usuários, ativam caches). Em development, eles mostram mensagens de erro detalhadas e ativam o *hot-reload*.

### **6\. Estratégia de Mercado (Marketing de Conteúdo)**

Como gigantes da tecnologia cresceram ensinando.

* **O Modelo HubSpot & DigitalOcean:**  
  * Em vez de fazer propaganda direta do produto, crie **documentação e conteúdo educativo** de alta qualidade sobre os problemas que seu público tem (overlap).  
  * *Exemplo HubSpot:* Ensinou o mundo o que era SEO e como capturar *Leads* (quando ninguém sabia fazer isso). No final do artigo, a conclusão lógica era: "Dá muito trabalho fazer na mão, compre a nossa ferramenta que faz isso para você".  
  * *Exemplo DigitalOcean:* Dominou o Google com os melhores tutoriais de "Como instalar Docker no Linux" ou "Como configurar um servidor Nginx". O desenvolvedor entrava para aprender de graça e acabava alugando a *Droplet* (máquina virtual da DO) pela facilidade oferecida. Empacotaram a complexidade em um produto acessível.

## **DIA 22 📚 Revisão: Database Migrations, Controle de Estado e Gestão de Complexidade**

### **1\. O que são Migrations? (O Git do Banco de Dados)**

Desenvolver um projeto sem usar *Migrations* no banco de dados é exatamente como escrever código sem usar versionamento (Git).

* **O Problema:** Sem *migrations*, se dois desenvolvedores mudarem a estrutura de uma tabela, ou se você precisar colocar o sistema em um novo servidor, ninguém saberá qual é o esquema (schema) correto e atualizado do banco.  
* **A Solução:** *Database Schema Migrations* são o histórico de versão da estrutura do seu banco de dados. Elas garantem que o banco de dados seja uniforme, previsível e recriável em qualquer ambiente (Desenvolvimento, Homologação ou Produção).

### **2\. A Anatomia do Sistema de Migração**

Um sistema robusto de migração é composto por duas partes vitais:

* **1\. Os Arquivos de Migração (O Histórico):**  
  * São arquivos físicos gerados no projeto.  
  * **Identificação:** Eles usam números crescentes ou *Unix Timestamps* no nome do arquivo (ex: 169123456789\_cria\_tabela\_usuarios.js) para garantir a ordem exata de execução.  
  * **O Conteúdo (Diff):** Cada arquivo não contém o banco inteiro, mas apenas o *diff* (a diferença/alteração) entre o estado anterior e o novo estado. Eles funcionam como blocos de Lego empilhados.  
* **2\. O Framework de Migração (O Motor):**  
  * É a ferramenta que lê os arquivos e aplica no banco.  
  * **Controle de Estado:** O framework cria automaticamente uma tabela própria escondida dentro do seu banco de dados (ex: pgmigrations). Essa tabela anota quais arquivos já foram rodados.  
  * **Garantia de Concorrência:** Se duas pessoas (ou dois servidores no CI/CD) tentarem rodar as migrações ao mesmo tempo, o framework verifica a tabela de controle, compara com os arquivos e garante que a migração seja executada **apenas uma vez**.

### **3\. A Regra de Ouro: O Ciclo Up e Down**

Para que o sistema de *migrations* funcione, a disciplina do time deve ser militar.

> **🚫 Proibido Alterações Manuais:** Nunca, sob nenhuma circunstância, altere a estrutura do banco de dados manualmente via terminal (psql) ou interface gráfica (DBeaver, pgAdmin). Toda mudança **deve** nascer de um arquivo de migração.

Todo arquivo de migração possui duas funções obrigatórias:

* **Comando Up (Avançar):** Aplica a alteração nova (Ex: CREATE TABLE usuarios).  
* **Comando Down (Desfazer):** Reverte exatamente o que o up fez (Ex: DROP TABLE usuarios). Isso é a sua rota de fuga caso algo dê errado em produção.

### **4\. Estratégia de Execução: CLI vs. Endpoint**

Existem duas formas principais de disparar o framework para rodar as *migrations*:

* **Via CLI (Linha de Comando):** Rodando um comando no terminal (ex: npm run migrate:up). Muito comum durante o desenvolvimento local.  
* **Via Endpoint (API):** Em sistemas modernos de *API First* (como o nosso projeto), é uma prática avançada expor um endpoint seguro para rodar as migrações. Isso facilita o deploy automatizado (Continuous Integration), permitindo que um robô dispare a atualização do banco através de uma requisição HTTP.

### **5\. Filosofia Sênior: Complexidade e Trade-offs**

#### **A Pressão da Complexidade**

* Lute sempre contra a complexidade interna.  
* **A Metáfora do Submarino:** Imagine que seu sistema é um submarino. A complexidade do mundo real (regras de negócio, usuários, falhas de rede) é a pressão da água do lado de fora. Se o seu submarino for simples, coeso e denso por dentro, ele suporta a pressão. Se ele já for caótico e complexo por dentro, quando a pressão externa bater, as duas complexidades entrarão em conflito e o sistema **implodirá**.  
* *Nota sobre Estabilidade:* Um sistema pode ser "Feature Complete" (todas as funcionalidades prontas) e "Estável", mas **nunca** será 100% livre de bugs. Os bugs apenas migram para os *Edge Cases* (casos extremos e raros).

#### **O Trade-off da Ferramenta Específica**

* Ao escolhermos um framework de migração focado 100% no PostgreSQL (o node-pg-migrate), tomamos uma decisão arquitetural consciente.  
* **O Trade-off (Troca):** Perdemos a flexibilidade genérica (a ilusão de que "um dia mudaremos para MySQL"), mas **ganhamos poder absoluto**.  
* Não cedemos ao medo do futuro. Ao remover a abstração genérica, reduzimos o atrito e podemos usar as capacidades mais avançadas e específicas do PostgreSQL diretamente nas nossas migrações.

## **DIA 23📚 Revisão: Mentalidade, Migrations via API e Estratégias de Teste**

### **1\. A Mentalidade Sênior: O Código não é de Pedra**

O medo de alterar o código existente é o maior inimigo da evolução de um software.

* **A Ilusão da Imutabilidade:** Código não é entalhado em pedra. É puramente digital e, por definição, feito para ser modificado.  
* **O Erro do Sênior Protetor:** Muitas vezes, desenvolvedores mais experientes tentam impedir que os mais novos trilhem certos caminhos por já saberem que vai dar errado. No entanto, privar o outro da experiência do erro é privá-lo do aprendizado real.  
* **O Perfil Ideal:** Um bom desenvolvedor não cede ao "peso" do que já existe. Ele tem a coragem e a capacidade técnica de refatorar e mudar as coisas, entendendo que o sistema é um organismo vivo.

### **2\. Migrations via API (Programática)**

Em um sistema *API First*, o controle das migrações também pode (e deve) ser exposto como um serviço.

* **Dry Run vs. Live Run:**  
  * **GET (Dry Run):** Funciona como uma simulação. Ele consulta o banco, verifica os arquivos e retorna o que *seria* executado, mas não altera nada.  
  * **POST (Live Run):** É a execução real. Ele aplica as migrações pendentes no banco de dados.  
* **Paridade entre CLI e API:**  
  * Ao usar a biblioteca de migrações via código (API Programática), precisamos ter certeza de que ela se comporta igual à Linha de Comando (CLI).  
  * *Exemplo Prático:* A CLI salva o histórico em uma tabela padrão. Na API, se não configurarmos explicitamente, ela pode salvar em uma tabela undefined. Temos que passar os mesmos parâmetros para garantir o funcionamento idêntico.  
* **Compatibilidade de Sistemas Operacionais (Paths):**  
  * O Windows usa barras invertidas (\\) para pastas, enquanto o Linux/Mac (e a Vercel) usam barras normais (/).  
  * Para evitar bugs de diretório, **nunca** escreva caminhos fixos (ex: "pastas/migrations"). Use ferramentas do Node.js (como o path.join()) para que o sistema operacional resolva a barra correta de forma flexível.

### **3\. O Mito do Rollback (Por que não usar o "Down")**

Historicamente, aprendemos a criar scripts de up (fazer) e down (desfazer). Na prática moderna, o down é quase um anti-padrão.

* **Os 4 Motivos para abandonar o Rollback:**  
  * **São Raros:** Você quase nunca precisa voltar o banco para uma versão anterior.  
  * **São Destrutivos:** Desfazer uma migração geralmente significa apagar tabelas ou colunas (DROP). Se você fizer isso, perderá todos os dados reais que os usuários gravaram ali desde o deploy.  
  * **Baixa Qualidade:** Como ninguém quer usar, os scripts de down são escritos sem vontade, de forma ruim e raramente recebem a devida atenção.  
  * **Não são testados:** Dificilmente alguém testa o cenário de falha catastrófica voltando a versão do banco em produção.  
* **A Filosofia "Roll Forward" (Avançar para Corrigir):**  
  * Como defende a Stack Overflow (uma das maiores arquiteturas do mundo): *"Why roll back when you can roll forward?"* (Por que reverter quando você pode avançar?).  
  * Se uma migração quebrou algo, não rode o script de down. Crie uma **nova** migração de up corrigindo o problema. Mova-se sempre para frente.

### **4\. Testes de Banco de Dados: O Problema do Estado**

Testar rotas que alteram o banco de dados (como a rota de migrações) gera testes **intermitentes** (às vezes passam, às vezes falham).

* **O Problema:** Se o teste A roda as migrações (Live Run), quando o teste B for rodar a simulação (Dry Run), o banco já estará atualizado e o teste B falhará por não encontrar migrações pendentes.  
* **As 3 Abordagens de Resolução:**  
  * **Tear Down (Limpar tudo):** Antes de cada teste, o banco é completamente apagado e recriado (Estado Vazio). Deixa o teste previsível e isolado.  
  * **Sem Tear Down (Acumulativo):** Deixa a "sujeira" acumular. Reflete melhor o caos da produção, mas é terrível para debugar (abordagem de Cal Paterson).  
  * **Transactions (Transações):** Roda o teste dentro de uma transação SQL e, no final, dá um ROLLBACK. É rápido e limpo, mas complexo de configurar em testes de integração ponta a ponta.  
* **Nossa Escolha (Trade-off):**  
  * Optamos pela **Opção 1 (Limpar o Banco)**.  
  * **O Custo:** O framework de testes (Jest) roda testes em paralelo (ao mesmo tempo) por padrão. Para apagarmos o banco sem que um teste interfira no outro, teremos que forçar o Jest a rodar de forma **Serial** (um após o outro). O teste ficará mais lento, mas ganhamos 100% de confiança e previsibilidade.

### **5\. Dica de Produtividade no Terminal (Jest Regex)**

Conforme o projeto cresce, rodar todos os testes demora muito.

* Você pode filtrar quais testes o Jest deve rodar usando Regex (Expressões Regulares) direto no terminal:  
* Bash

npm run test:watch \-- migrations

*   
*   
* Os dois traços \-- avisam ao npm que a palavra a seguir (migrations) não é um comando do npm, mas sim um argumento a ser repassado para o Jest. Ele executará apenas os arquivos de teste que tenham "migrations" no nome ou no caminho.

## **DIA 24 📚 Revisão: Visão de Negócio, Conflito de Módulos (CJS vs ESM) e Maturidade em Testes**

### **1\. Filosofia: As Duas Muralhas e o Ego do Programador**

Antes de resolvermos qualquer problema técnico complexo, precisamos lembrar *por que* estamos resolvendo.

* **A Visão por Cima do Muro:** Um desenvolvedor sênior não fica preso apenas na "Muralha Técnica" (o código) nem apenas na "Muralha de Negócio" (as vendas). Ele se posiciona acima de ambas, entendendo que a tecnologia é apenas uma ferramenta para viabilizar o negócio.  
* **O Risco do Ego:** Enfrentar desafios técnicos difíceis (como configurar o Jest para rodar com o Next.js) não deve servir para massagear o ego do programador ou provar que ele é "inteligente". Todo esforço técnico deve ter um objetivo claro de negócio por trás (neste caso, garantir a estabilidade do produto através de testes).

### **2\. O Conflito de Infraestrutura: Next.js vs. Jest**

Por que o código funcionava no servidor web, mas dava erro ao rodar o teste?

* **A Causa Raiz:** Você tem duas "infraestruturas" rodando no seu projeto.  
  * O **Next.js** orquestra o servidor web (ele entende o JavaScript moderno automaticamente).  
  * O **Jest** orquestra os testes (ele roda direto no Node.js "cru", que por padrão usa o modelo antigo).  
* **A Batalha dos Módulos (CJS vs ESM):** O erro ocorreu porque o Node.js tentou ler um arquivo moderno (import) usando as regras antigas (require).

#### **Tabela Comparativa: Módulos no JavaScript**

| Característica | CommonJS (CJS) \- O Legado | ECMAScript Modules (ESM) \- O Moderno |
| :---- | :---- | :---- |
| **Sintaxe** | const db \= require("db.js"); / module.exports | import db from "db.js"; / export default |
| **Ambiente Nativo** | Node.js (Servidores antigos) | Navegadores e Node.js Moderno |
| **Carregamento** | **Síncrono:** Lê um arquivo por vez, parando a execução. | **Assíncrono:** Lê a árvore de arquivos de forma eficiente e estática. |
| **Performance** | Difícil de otimizar. | Permite **Tree Shaking** (o compilador "balança a árvore" e derruba/remove o código morto que não está sendo usado, deixando o sistema mais leve). |

### **3\. A Solução: Emprestando o Poder do Next.js para o Jest**

Para resolver a incompatibilidade, usamos o pacote next/jest para dizer ao Jest: *"Por favor, recompile meus arquivos de teste usando as mesmas regras modernas que o Next.js usa"*.

* **O Problema do .env:** O pacote next/jest tem uma regra própria: em ambiente de teste, ele não carrega o .env.development automaticamente (ele procura por um .env.test).  
* **A Intervenção Manual:** Para forçar o Jest a se conectar ao nosso banco de dados de desenvolvimento local, injetamos o dotenv diretamente no topo do jest.config.js.

JavaScript

// jest.config.js  
const dotenv \= require("dotenv");  
// Forçamos a leitura das variáveis de desenvolvimento  
dotenv.config({ path: "./.env.development" });

const nextJest \= require("next/jest");

const createJestConfig \= nextJest({  
  dir: "./",  
});

const jestConfig \= createJestConfig({  
  moduleDirectories: \["node\_modules", "\<rootDir\>/"\],  
});

module.exports \= jestConfig;

* **Forçando a Fila (\--runInBand):** Como estamos testando o Banco de Dados, se o Jest rodar os testes em paralelo, um teste vai apagar a tabela enquanto o outro tenta ler. O argumento \--runInBand no terminal resolve isso, forçando o Jest a rodar os testes de forma **Serial** (um de cada vez).

### **4\. JavaScript Avançado: O *Spread Operator* (...)**

No endpoint de migrações, você aplicou um padrão de projeto muito elegante usando os "três pontinhos" do JavaScript moderno.

* **O Padrão (Configuração Padrão vs. Sobrescrita):**  
  * Criamos um objeto defaultMigrationOptions com tudo o que é comum (URL do banco, pasta, tabela e o dryRun: true por segurança).  
  * No GET, apenas repassamos o padrão.  
  * No POST, usamos o *Spread Operator* (...defaultMigrationOptions). Ele "espalha" (copia) todas as propriedades do padrão para dentro de um novo objeto, e na linha de baixo nós **sobrescrevemos** apenas o que precisamos mudar (dryRun: false).  
  * *Benefício:* Evita repetição de código (DRY \- *Don't Repeat Yourself*) e diminui a chance de errar o nome de um parâmetro.

### **5\. Maturidade Profissional: A Cultura do Teste Oculto**

Uma das maiores lições de sobrevivência na carreira de TI.

* **O Mito da "Alteração Simples":** *"Confiar que a modificação é simples não é garantia de que ela não quebrou nada"*. A mente humana falha, o código é interligado e sistemas complexos têm efeitos colaterais invisíveis. O teste automatizado é a sua memória mecânica.  
* **Projetos sem Teste (Falta de Maturidade):** A menos que seja um script de curtíssima duração, não ter testes é um sinal vermelho de maturidade técnica do projeto.  
* **A Estratégia "Peça desculpas, não peça permissão":**  
  * Se a empresa proíbe ou acha que testes são "perda de tempo", **faça-os localmente na sua máquina mesmo assim**.  
  * O teste é, antes de tudo, uma ferramenta de **gerenciamento do seu próprio risco**. Ele evita que você seja o responsável por derrubar a produção.  
  * Quando a empresa perceber que você é o único desenvolvedor que nunca quebra o sistema ao subir uma nova *feature*, o valor do seu método ficará evidente. Você será visto como alguém que entrega com qualidade e previsibilidade.

## **DIA 25 📚 Revisão: Postura Profissional, Bottom Line e a Armadilha da Tecnologia**

### **1\. O Dilema: Caminho Automático vs. Caminho Manual**

Com a ascensão das Inteligências Artificiais, o mercado de trabalho mudou drasticamente. Existem duas rotas:

* **O Caminho Automático (O "Proxy Burro"):**  
  * É a cultura do Copiar e Colar (seja do StackOverflow antigamente ou do ChatGPT hoje).  
  * **O Perigo:** Ao escolher o caminho de menor resistência, você se torna apenas um repassador de informações (um *proxy*). A inteligência está na ferramenta, o problema está no projeto, e você está no meio sem agregar valor real.  
  * **A Consequência:** Se você não entende o que cola, **você não cria raízes**. Quando o "vento" da tecnologia mudar (um novo framework surgir), você será derrubado e substituído.  
* **O Caminho Manual (A Criação de Raízes):**  
  * É escolher digitar, debugar e entender linha por linha, mesmo quando a IA poderia fazer em 2 segundos.  
  * **O Radicalismo:** A etimologia da palavra *Radical* vem de *Raiz*. Ser um programador radical é ir até a raiz do problema. A tecnologia perde a validade rapidamente, mas a **postura investigativa** cria uma fundação inabalável para o resto da sua carreira.

### **2\. A Armadilha da "Muralha Técnica" (Procrastinação Oculta)**

Muitos programadores se escondem atrás do código por medo de enfrentar a realidade do mercado.

* **A Metáfora da Comida:** Se você passa horas cozinhando pratos complexos, mas ninguém come, há um problema grave. Na programação, construir um software com arquitetura perfeita que não resolve o problema de ninguém é o mesmo desperdício.  
* **O Refúgio Técnico:**  
  * Programar é apaixonante e nós temos controle total sobre o ambiente técnico (diferente do mercado de negócios, que é incontrolável).  
  * **A Procrastinação:** Refatorar código indefinidamente, adicionar ferramentas "revolucionárias" ou atualizar versões apenas para resolver conflitos inventados são formas de **adiar a dor**.  
  * **O Medo:** É o medo de lançar a funcionalidade (*Deploy*), colocá-la na frente do cliente e descobrir que a sua ideia era ruim, inútil ou não funciona. O desenvolvedor fica rodando em círculos na "Muralha Técnica" para não ter que enfrentar a "Muralha de Negócio".

### **3\. A Chave do Sucesso (E de Virar Sócio): O *Bottom Line***

Como deixar de ser apenas "o cara do TI" e se tornar uma peça insubstituível (e muito bem paga) na empresa.

* **O que é o Bottom Line?**  
  * Traduzido do mundo financeiro, é a "Última Linha" do DRE (Demonstração do Resultado do Exercício).  
  * É o **Lucro Líquido**. Depois que a empresa pagou todos os salários, servidores e impostos, o que sobra é o *Bottom Line*.  
* **A Regra de Ouro:**  
  *Quem consegue influenciar o Bottom Line positivamente, ganha o jogo.*  
* **Como Hackear a Muralha de Negócios:**  
  * Em vez de discutir se vamos usar React ou Vue.js, faça perguntas de negócios aos donos ou líderes:  
    1. *"Qual dos nossos produtos tem a maior influência no lucro líquido hoje?"*  
    2. *"Qual custo de infraestrutura/operação mais penaliza o nosso resultado final hoje?"*  
  * **A Magia:** Ao fazer isso, o dono da empresa percebe que você não está ali apenas para bater ponto. Você está preocupado com o dinheiro dele. Quando um técnico consegue usar suas habilidades de programação para reduzir um custo de servidor em 40% ou criar uma automação que aumenta a conversão de vendas, **um salário normal não satisfaz mais essa relação**. É assim que convites para sociedade ou cargos de CTO surgem.

### **4\. O Farol na Escuridão**

Você não precisa ser o Elon Musk dos negócios.

* A maioria dos programadores e até gerentes intermediários não sabe o que afeta o lucro da própria empresa.  
* O mercado é desesperado por desenvolvedores que consigam olhar "por cima do muro" e conectar código a dinheiro/valor real.  
* Se você tiver apenas uma **noção básica** do negócio e usar a tecnologia a favor disso, você será um farol no meio da escuridão. O destaque é automático.

## **DIA 26 📚 Revisão: A Anatomia das Branches, Ambientes e Revisão de Código**

### **1\. Os 3 Níveis de Compreensão do Git**

Como o Git realmente gerencia as versões?

* **Nível 1 (A Ilusão da Cópia Física):**  
  * *O que parece ser:* Cada vez que você cria uma branch, o Git duplica a pasta do projeto inteira.  
  * *A Realidade:* Se fosse assim, mudar de branch em um projeto de 10GB levaria minutos copiando arquivos. No Git, a troca é instantânea. Logo, essa teoria está errada.  
* **Nível 2 (A Ilusão da Linha do Tempo Duplicada):**  
  * *O que parece ser:* O Git reaproveita os arquivos antigos (Blobs) e, ao criar uma branch, ele duplica a linha do tempo dali para frente.  
  * *A Realidade:* O Git não duplica a história. Ele é muito mais inteligente e econômico que isso.  
* **Nível 3 (A Realidade: O Git é um Gerenciador de Ponteiros):**  
  * *O Conceito:* Uma **Branch** nada mais é do que um **apelido** (alias) para um **Hash de Commit** específico. É um arquivo de texto minúsculo (40 bytes) que contém apenas o endereço do commit.  
  * **O Mecanismo (HEAD):**  
    * **HEAD:** É o ponteiro que diz "Onde eu estou agora?". Ele aponta para a Branch.  
    * **Branch:** Aponta para o último Commit daquela linha.  
    * **Commit:** Aponta para a árvore de arquivos (Blobs).  
  * **A Prova Real:**  
    * cat .git/HEAD: Mostra ref: refs/heads/nome-da-branch.  
    * cat .git/refs/heads/nome-da-branch: Mostra o Hash do commit.  
  * *Conclusão:* Trocar de branch (checkout/switch) é apenas o Git movendo o ponteiro HEAD de um lugar para outro e atualizando os arquivos na sua pasta de trabalho. Por isso é instantâneo.

### **2\. Comandos Essenciais de Navegação**

* **Ver onde estou:** git branch (procurar o asterisco \*) ou git status.  
* **Criar:** git branch \<nome-da-nova-branch\>.  
* **Trocar (Mover a HEAD):**  
  * git checkout \<nome\>: Comando antigo (serve para muitas coisas, o que confunde).  
  * git switch \<nome\>: Comando moderno (focado apenas em trocar de branch).  
* **Conectar com o GitHub (Upstream):**  
  * Quando criamos uma branch local, o GitHub não sabe que ela existe. O Git não assume que o nome remoto deve ser igual ao local.  
  * **Comando:** git push \--set-upstream origin \<nome-da-branch\>. Isso cria o "trilho" ligando sua branch local à nuvem.

### **3\. Ambientes: Homologação (Staging)**

* **Etimologia:** Vem do grego *homologeo* (concordar/alinhar).  
* **O Objetivo:** É o ambiente de **acordo**. É onde o time e o cliente concordam que "está pronto".  
* **A Regra de Ouro:** O ambiente de homologação deve ser o espelho mais fiel possível da **Produção**. Mesma infraestrutura, mesmas versões de banco, mesmas configurações (ainda que com menos potência/tamanho). É aqui que pegamos bugs que só existem na infraestrutura real e que não aparecem no localhost.

### **4\. Dica de Ferramenta: cURL \+ Python**

Como testar APIs e visualizar JSON no terminal de forma legível.

Bash

curl \-s \-L \-X GET \-H "x-vercel-protection-bypass: SEU\_TOKEN" https://sua-url.com/api | python3 \-m json.tool

* curl: Client URL (ferramenta de transferência de dados).  
* \-s: Silent (não mostra barra de progresso).  
* \-L: Follow Redirects (se a página redirecionar, ele segue).  
* \-X GET: Define o verbo HTTP.  
* | (Pipe): Pega a saída do comando anterior e joga no próximo.  
* python3 \-m json.tool: Usa o módulo nativo do Python para formatar (pretty-print) o JSON bagunçado que volta da API.

### **5\. Filosofia: A Lei de Linus e o "Você do Futuro"**

* **A Citação:** *"Given enough eyeballs, all bugs are shallow"* (Dados olhos suficientes, todos os erros são superficiais) — Eric S. Raymond (A Catedral e o Bazar).  
* **Revisão Pessoal:**  
  * Se você não está confiante, espere um dia.  
  * O "Você de Amanhã" é biologicamente e mentalmente diferente do "Você de Hoje". O descanso reseta o contexto do cérebro.  
  * Ao ler seu próprio código no dia seguinte, você o lê como se fosse outra pessoa (mais um par de olhos), tornando óbvios os bugs que ontem eram invisíveis.

## **DIA 27 📚 Revisão: Perigos com Branches, Resgate de Commits e Fast-Forward Merge**

### **1\. O Perigo Oculto: Deletando Branches por Acidente**

O autocompletar do terminal ou a área de transferência podem nos trair, fazendo com que a gente delete a branch errada.

* **A Trava de Segurança (\-d minúsculo):**  
  * Comando: git branch \-d \<nome-da-branch\>  
  * O Git é seu amigo. Se você tentar deletar uma branch que **ainda não teve seu código mesclado (merged)** em outra, ele vai bloquear a ação e avisar.  
  * *Atenção aos Remotos:* Se você já fez o push dessa branch para o GitHub, o Git local permite deletar a branch local mesmo sem merge, pois ele sabe que o código está seguro na cópia remota (refs/remotes/origin/nome-da-branch).  
* **O Botão de Pânico (\-D maiúsculo):**  
  * Comando: git branch \-D \<nome-da-branch\>  
  * Isso força a deleção. Diz ao Git: *"Eu sei o que estou fazendo, apague mesmo sem ter feito merge"*. É aqui que os acidentes acontecem.

### **2\. Detached HEAD e Commits Pendurados (Dangling)**

O que acontece quando você deleta a única branch que apontava para um commit?

* **O Estado Dangling (Pendurado):**  
  * Lembre-se: uma branch é apenas um adesivo colado em um commit. Se você arranca o adesivo (deleta a branch), o commit continua existindo lá no fundo do Git, mas agora ele está "pendurado" no vazio, sem nome. Nenhuma branch leva até ele naturalmente.  
* **Detached HEAD (Cabeça Desanexada):**  
  * Se você souber o Hash (o código) desse commit perdido e fizer um git checkout \<hash\>, o Git avisa que você está em *Detached HEAD*.  
  * Isso significa que o seu ponteiro HEAD não está apontando para uma branch com nome (como main), mas sim diretamente para um commit solto.  
* **O Lixeiro do Git (Garbage Collector \- GC):**  
  * Deixar commits pendurados é perigoso a longo prazo. O Git possui um sistema de limpeza (Garbage Collector) que roda de tempos em tempos. Se um commit ficar pendurado (sem branch apontando para ele) por muito tempo (geralmente algumas semanas, como os 14 dias citados), o Git assume que é lixo e o apaga definitivamente para economizar espaço.

### **3\. O Resgate: Como recuperar um Commit Perdido**

Se você deletou a branch errada e não lembra o Hash do commit, não entre em pânico.

* **O Histórico Secreto (git reflog):**  
  * O git log mostra a história da sua branch atual. Se a branch sumiu, o log não ajuda.  
  * O git reflog é o diário de bordo do seu ponteiro HEAD. Ele anota **todos** os seus movimentos, mesmo entre branches deletadas. Ali você encontrará o Hash do seu commit "perdido".  
* **Colando o Adesivo Novamente:**  
  * Para salvar o commit do lixeiro (Garbage Collector), basta colar um novo adesivo (criar uma branch) apontando para ele.  
  * **Comando Direto:** git checkout \-b \<nova-branch\> \<hash-do-commit-recuperado\>  
  * Pronto\! Seu trabalho voltou à vida como se nada tivesse acontecido.

### **4\. O Merge de Avanço Rápido (Fast-Forward)**

Fazer o merge é juntar o trabalho de duas linhas do tempo. O *Fast-Forward* é o tipo mais simples e limpo.

* **A Regra de Ouro (Target vs. Source):**  
  * O posicionamento do seu HEAD é fundamental.  
  * **Target (Alvo):** A branch que *vai receber* o código (geralmente a main). Você **deve** estar nela antes de começar.  
  * **Source (Fonte):** A branch que *tem* o código novo.  
* **Como Fazer:**  
  * Mova-se para o Alvo: git switch main  
  * Puxe a Fonte: git merge \<branch-fonte\>  
* **Por que "Avanço Rápido"?**  
  * Se a main não sofreu nenhuma alteração enquanto você trabalhava na sua branch, o Git não precisa criar um commit de junção complexo. Ele simplesmente pega o adesivo da main e o desliza rapidamente (fast-forward) para a frente, colando-o no mesmo commit onde a sua branch fonte está.  
* **Dica Visual:** Use o comando git log \--graph para ver uma representação visual (em texto) de como as linhas do tempo estão se conectando.

### **5\. Bônus: Monitoramento de API no Terminal**

Uma dica valiosa de quem trabalha com sistemas *API First*.

Bash

watch \-n 1 "curl \-s https://tabifs.vercel.app/api/v1/status | jq"

* watch \-n 1: Executa o comando que vem a seguir a cada 1 segundo, atualizando a tela do terminal como um painel ao vivo.  
* curl \-s: Busca os dados da URL silenciosamente.  
* jq: Uma ferramenta de terminal incrível que colore e formata o JSON recebido, deixando-o perfeito para leitura humana. (Precisa ser instalada no sistema operacional).

## **DIA 28 Muito interessante📚 Revisão: A História do Desenvolvimento, Ágil, CI/CD e Estratégias de Branching**

### **1\. A Evolução do Trabalho: Do Waterfall ao Ágil**

Como a indústria de software saiu de um modelo engessado para tentar ser dinâmica.

* **O Modelo Waterfall (Cascata):**  
  * **Como funcionava:** Um fluxo estritamente linear e sequencial: *Requisitos $\\to$ Projeto $\\to$ Dev (Implementação) $\\to$ Validação $\\to$ Ops (Implantação).*  
  * **O Problema:** Os desenvolvedores ficavam esmagados no meio do processo. O ciclo de *feedback* era gigantesco (às vezes 1 ano para o cliente ver o software). As implantações eram grandes "bolões" de código ("Big Bang Deploys"), o que gerava quebras catastróficas e brigas intermináveis entre a equipe de Dev (que queria lançar) e a equipe de Ops (que queria estabilidade).  
* **O Manifesto Ágil (2001):**  
  * Uma revolta contra o Waterfall. 17 especialistas definiram que o foco deveria ser em: **Indivíduos e interações** (mais que processos/ferramentas) e **Software funcionando** (mais que documentação).  
  * **O Pragmatismo (Dave Thomas):** A agilidade real cabe em um slide: 1\) Descubra onde você está. 2\) Dê um pequeno passo. 3\) Avalie. 4\) Repita.  
  * **A Regra de Ouro do Design:** Ao se deparar com duas opções, escolha aquela que torne as *mudanças futuras mais fáceis*. Um bom design é o que aceita ser modificado.

### **2\. A Corrupção do Ágil e a Infantilização da Área**

Como um movimento revolucionário se tornou um produto de prateleira.

* **O Dogmatismo e a Comercialização:** O mercado transformou a "cultura ágil" em um pacote de vendas. As empresas começaram a focar estritamente no lado esquerdo do manifesto (*Processos e Ferramentas* como Jira, Scrum, Kanban), esquecendo completamente dos *Indivíduos*, porque melhorar pessoas não é escalável e é difícil de vender.  
* **A Infantilização do Ambiente de Trabalho:** Houve uma época em que o mercado confundiu "cultura legal" com "recreação" (piscinas de bolinhas, videogames). O resultado foi contraintuitivo: as pessoas ficaram **mais tristes e inseguras**.  
* **A Verdadeira Satisfação:** A felicidade no trabalho não vem da recreação, vem de se sentir **útil**. Times que focam em produtividade e em entregar impacto real nas mãos do cliente são, de longe, os mais felizes e seguros profissionalmente.

### **3\. O Movimento DevOps e a Automação (CI/CD)**

A resposta técnica para curar a dor entre Desenvolvimento e Operações.

* **O Objetivo Central:** Reduzir o **Ciclo de Feedback**. Fazer o código sair da máquina do programador e chegar na mão do cliente em dias, ou horas, de forma segura.  
* **Continuous Integration (CI \- Integração Contínua):**  
  * Automação de Builds (compilar/transpilar) e de Validação (Testes Automatizados).  
  * Garante que o código de um desenvolvedor funciona e pode ser integrado ao projeto principal com segurança.  
* **Continuous Delivery (CD \- Entrega Contínua):**  
  * O código está pronto e empacotado para a produção. O deploy é feito com um único "clique" manual de aprovação.  
* **Continuous Deployment (CD \- Implantação Contínua):**  
  * Automação extrema. Se o CI passar (testes e build), o código vai direto para a produção sem intervenção humana (como o "robozinho da Vercel").  
* **O Caso Stone (O Perigo do Processo Manual):** Uma infraestrutura gigante ficou horas fora do ar porque um humano esqueceu de desmarcar uma "caixinha" manual na hora do deploy. Na nossa área, não automatizar fluxos críticos para evitar erro humano não é apenas descuido, **é negligência**.

### **4\. A Filosofia da Integração Contínua e a Entropia**

O erro comum de achar que "CI" é uma máquina na nuvem (como o GitHub Actions).

* **CI é uma AÇÃO:** Integração Contínua é a ação de unir o código dos desenvolvedores.  
* **A Força da Entropia:** Na física, entropia é a medida da desordem. No código, quando você cria uma branch e se afasta da main, a sua entropia (suas mudanças) e a entropia da empresa (mudanças dos outros devs) começam a se afastar.  
* **O Segredo:** Quanto mais tempo você demora para fazer o Merge, maior a entropia, e pior será o conflito. O objetivo da Integração Contínua real é fazer merges pequenos e frequentes para domar essa entropia coletiva.

### **5\. As 3 Grandes Estratégias de Branching (Git Workflows)**

O Git não liga para nomes (main, master, develop são só etiquetas). Como nos organizamos então?

#### **1\. Trunk-Based Development (O modo "Hardcore/Big Tech")**

* **Como funciona:** Todos comitam direto na branch principal (trunk/main) várias vezes ao dia. Não existem branches de vida longa.  
* **Como não quebrar a produção?**  
  * *Feature Flags:* Tomadas de "liga/desliga" no código. O código vai pra produção quebrado/incompleto, mas escondido do usuário final.  
  * *Branch by Abstraction:* Criar camadas de abstração temporárias para refatorar sistemas grandes aos poucos, sem travar o time.  
* **Vantagem:** Domina a entropia. É o modelo do Google, Meta, Netflix.

#### **2\. Feature Branch (GitHub Flow)**

* **Como funciona:** Cria-se uma branch para cada nova feature/bugfix. Quando pronta, abre-se um *Pull Request* (PR) para revisão, e depois é feito o merge na main. A main deve estar sempre pronta para deploy.  
* **Vantagem:** Excelente para open-source e revisão de código.  
* **Risco:** Se a revisão demorar, as features envelhecem, a entropia aumenta e o time trava.

#### **3\. Gitflow (O Modelo Legado)**

* **Como funciona:** Complexo e burocrático. Possui branches específicas (main, develop, feature/\*, release/\*, hotfix/\*). Ninguém comita na main. A main só recebe código via releases versionados.  
* **A Verdade Atual:** O próprio criador do Gitflow (Vincent Driessen) admitiu 10 anos depois que este fluxo **não é recomendado para aplicações Web/SaaS** que têm entrega contínua. Ele serve apenas para softwares que mantêm múltiplas versões rodando simultaneamente (ex: aplicativos desktop antigos, pacotes npm mantidos em versões v1, v2, v3).

### **6\. A Decisão do Projeto (TabNews/FinTab)**

* **O Fluxo Escolhido:** **Feature Branch (GitHub Flow)**.  
* **O Motivo:** É o padrão esperado na comunidade open-source. Facilita o recebimento de contribuições.  
* **A Mentalidade (O Pulo do Gato):** Usaremos o Feature Branch, mas com o *espírito* do Trunk-Based. Criaremos branches de **vida curta** (*short-lived branches*), dando pequenos passos e integrando rapidamente para manter a entropia sob controle.

## **DIA 29 📚 Revisão: Estabilização de Ambiente, DX, Race Conditions e Dicas de Terminal**

### **1\. O Padrão Ouro do DX (Experiência do Desenvolvedor)**

A melhor coisa para a produtividade de um time é conseguir rodar o sistema inteiro com um único comando (ex: npm run dev). Isso tira a carga mental do programador de ter que lembrar a ordem exata das coisas.

**Nosso Checklist de Profissionalização:**

* \[ \] Estabilizar Ambiente Local (Subir banco, rodar migrations, ligar servidor)  
* \[ \] Estabilizar Testes Locais (Garantir que não existam falsos negativos)  
* \[ \] Estabilizar CI (Continuous Integration)  
* \[ \] Refatorações Gerais

### **2\. O Perigo da "Condição de Corrida" (Race Condition)**

Quando automatizamos a subida do ambiente, podemos esbarrar em um problema clássico da computação.

* **O que é:** Uma Condição de Corrida (Race Condition) ocorre quando dois ou mais processos competem por recursos de forma desorganizada, e o resultado final depende de quem "chega primeiro".  
* **O Exemplo do Banco de Dados:** Se criarmos um script que liga o Banco de Dados e, ao mesmo tempo, roda as *Migrations*, o script de migração pode chegar no banco antes dele estar totalmente ligado e pronto para receber conexões. O sistema quebra porque a ordem (sincronia) não foi respeitada. Precisamos de um **orquestrador** para garantir que as migrações só rodem *depois* que o banco estiver 100% de pé.

### **3\. Anatomia do Terminal e Sistemas Operacionais**

Entender como os sistemas se comunicam por baixo dos panos nos dá muito poder para debugar (encontrar erros).

* **Saídas Padrão (Streams):**  
  * **stdout (Standard Output):** É o canal por onde o programa envia as mensagens normais e resultados de sucesso.  
  * **stderr (Standard Error):** É o canal exclusivo para mensagens de erro.  
  * *Por que separar?* Isso permite que outros programas (ou nós mesmos) possamos filtrar apenas os erros de um log gigante ou redirecionar os erros para um arquivo de texto específico.  
* **A Regra do Maiúsculo/Minúsculo \[y/N\]:**  
  * Quando um CLI (Interface de Linha de Comando) te faz uma pergunta, como no Docker: Are you sure you want to continue? \[y/N\].  
  * A letra **Maiúscula** (neste caso, o N) indica qual é o **comportamento padrão**. Se você apenas apertar Enter sem digitar nada, o sistema executará a ação da letra maiúscula (Não).  
* **Exit Codes e Interrupções (SIGINT):**  
  * Já vimos que Exit Code 0 é sucesso e \> 0 é erro.  
  * **Exit Code 130:** Significa que o programa foi encerrado por um **SIGINT** (Signal Interrupt \- Sinal de Interrupção). É exatamente o que acontece quando você aperta Ctrl \+ C no terminal para parar um servidor.  
  * **Dica:** Para ver o código de saída do último programa que rodou no terminal Unix (Linux/Mac), digite echo $?.

### **4\. Estabilizando Testes: Confiabilidade Acima de Tudo**

Testes automatizados existem para nos dar paz de espírito. Se eles geram ansiedade, algo está errado.

* **A Bateria de Testes não pode ter "Flakiness" (Intermitência):** Se um teste passa de manhã, mas falha à tarde sem que ninguém tenha mexido no código, a equipe perde a confiança nele. Testes intermitentes são inúteis e perigosos.  
* **Conheça as Configurações Padrão:**  
  * O nosso *Test Runner* (Jest) tem uma regra padrão: se um teste demorar mais de **5 segundos**, ele automaticamente aborta e marca como "Falhou" (Timeout).  
  * *O Problema:* Se o seu computador estiver um pouco lento naquele dia, ou se o servidor demorar um pouco mais para subir as conexões no início do teste, o Jest vai dar um "Falso Negativo" cortando o teste no meio. Saber disso nos permite aumentar esse *timeout* na configuração para evitar essas quebras injustas.  
* **Orquestração Concorrente nos Testes:** Como o nosso servidor de desenvolvimento não roda em segundo plano (modo *detached*), precisamos rodar o servidor e o Jest ao mesmo tempo (de forma concorrente), mas orquestrando para que o Jest só comece a disparar as requisições *depois* que o servidor avisar que está escutando a porta.

### **5\. Segurança de Dependências (Typosquatting)**

* Na hora de instalar pacotes de terceiros, **nunca tente adivinhar o nome digitando de cabeça**.  
* *Typosquatting* é um ataque hacker onde pessoas maliciosas criam pacotes com nomes quase idênticos aos famosos (ex: exprees em vez de express). Se você digitar errado, instala um vírus no seu projeto.  
* **A Regra:** Copie o comando de instalação diretamente do site oficial do NPM (npmjs.com).

## **DIA 30 📚 Revisão: Locus de Controle, Decisões Baseadas em Dados e a Arte de Entregar**

### **1\. Locus de Controle: A Coragem de Assumir a Responsabilidade**

O espaço que você ocupa em uma empresa é diretamente proporcional à quantidade de responsabilidade que você consegue carregar.

* **O Conceito (Locus de Controle):**  
  * **Externo:** Pessoas fracas culpam o sistema, o prazo, o chefe ou o colega ("Tirar o corpo fora"). Elas são vítimas da situação.  
  * **Interno:** Pessoas fortes e "The Bigger Person" (A pessoa maior/mais madura) entendem que o controle está em suas mãos. Elas assumem o erro e a resolução ("Enfiar o pé na jaca" e resolver).  
* **O Efeito na Carreira:** Quando você chama a responsabilidade para si, a empresa naturalmente te dá mais espaço, autonomia e liderança.  
* **O Alerta (O Limite do Herói):** Cuidado para não dar o passo maior que a perna. Assumir o controle não significa abraçar o mundo inteiro sozinho. Se você diluir seu foco em dezenas de problemas, não resolverá nenhum com excelência e acabará em *Burnout*.

### **2\. Profissionalismo Baseado em Dados (O Fim do "Achismo")**

No desenvolvimento de software maduro, a autoridade não vem do tempo de empresa, vem dos números.

* **O Perigo do "Aceitador de Sugestões":** Um profissional júnior ou um "proxy burro" escuta uma dica de um sênior, copia, cola e diz: *"Pronto, funcionou"*.  
* **A Postura Sênior (Mensuração):** Você só aceita uma mudança se puder provar que ela melhorou o sistema.  
  * *A Prática:* Antes de mudar, meça o estado atual. Aplique a mudança. Meça novamente.  
  * *O Exemplo Real:* Comprovar uma redução de **29%** no tempo de execução dos testes. Isso é um dado concreto que justifica o tempo gasto na tarefa.  
* **O Risco da Complexidade Inútil:** Sem mensurar, você corre o risco de aceitar uma ideia que "parece muito massa" e moderna, mas que no contexto real da sua aplicação não traz ganho de performance nenhum. O resultado? Você apenas inflamou o sistema com complexidade desnecessária e dificultou a manutenção futura.

### **3\. A Arte de Entregar (Evitando o Código "Refém")**

O perfeccionismo técnico é, muitas vezes, apenas uma máscara para a procrastinação.

* **O Código na Máquina Local tem Valor Zero:** Deixar um trabalho parado na sua máquina por dias, ajustando pequenos detalhes técnicos, não resolve o problema de nenhum cliente.  
* **Saber a Hora de Parar:** Sempre existirão melhorias a serem feitas e refatorações possíveis. O profissional maduro sabe o momento exato de dizer: *"Isso aqui está bom o suficiente para gerar valor agora"*.  
* **Avançar para Aprender:** Faça o *commit*, abra o *Pull Request* e coloque em produção. As melhorias futuras podem e devem ser feitas em iterações seguintes, baseadas no uso real.

## **DIA 31 📚 Revisão: Continuous Integration, GitHub Actions, Linting e Status Pages**

### **1\. A Anatomia do GitHub Actions (O CI Gratuito e Poderoso)**

Para o CI funcionar, o seu código precisa rodar de forma estável em uma máquina remota ("Runner") que simule o ambiente de produção (como o Ubuntu/Linux).

O GitHub Actions orquestra isso através de arquivos YAML. A hierarquia funciona assim:

* **Workflow:** O arquivo principal que define o fluxo de trabalho inteiro.  
* **Event (Gatilho):** O que faz o fluxo rodar? (Ex: Alguém abriu um Pull Request, ou fez um Push na branch main).  
* **Jobs:** Os trabalhos que precisam ser feitos. Um workflow pode ter um ou vários jobs rodando em paralelo.  
* **Runner:** A máquina remota que vai executar o Job (ex: ubuntu-latest).  
* **Steps (Passos):** A lista de comandos executados dentro da máquina (Ex: Baixar o código, instalar dependências, rodar os testes).  
* **Marketplace (Ações Prontas):** Não precisamos reinventar a roda. O GitHub fornece *Actions* prontas, como o actions/checkout, que serve para o Runner fazer o download do seu código antes de testá-lo.

### **2\. O Segredo da Constância: npm ci vs npm install**

Rodar os testes em uma máquina remota exige previsibilidade absoluta.

* **npm install (Para Humanos):** Ele instala as dependências, mas tem a liberdade de atualizar versões secundárias de pacotes (e reescrever o package-lock.json). Isso é perigoso em um CI, pois uma atualização invisível pode quebrar seu teste de surpresa.  
* **npm ci (Para Máquinas/CI):** Significa *Clean Install*. Ele ignora o package.json e lê estritamente o package-lock.json. Ele congela as versões, garantindo que a máquina do GitHub Actions baixe **exatamente** os mesmos arquivos que você usou na sua máquina local.

### **3\. Testando o "Caminho Triste" e Regras de Proteção**

O trabalho de um profissional não é apenas torcer para dar certo, é planejar o que acontece quando dá errado.

* **O Teste do Caos:** Forçar um erro no teste local e enviar para o GitHub. Descobrimos que o GitHub, por padrão, permite que um Pull Request com testes falhos seja mesclado (Merge) na main. Isso é um desastre.  
* **GitHub Rulesets (Regras de Proteção):** Para consertar isso, criamos proteções na branch main:  
* Bloqueamos commits diretos na main.  
* Exigimos a criação de um Pull Request.  
* Exigimos que os "Checks" (os testes do GitHub Actions) passem com o *Status Code 0* antes do botão de Merge ser liberado.

### **4\. Linting (Análise Estática): Protegendo a Sanidade do Código**

Conforme o projeto cresce, o código tende a "endoidar". Os Linters são robôs que inspecionam o código em busca de erros e despadronizações.

* **Pré-formatadores (EditorConfig):** Agem enquanto você digita. São suaves. Garantem o básico, como o tipo de espaçamento (Tabs vs Spaces) e codificação de texto.  
* **Pós-formatadores (Agressivos, rodam ao salvar o arquivo):**  
* **Prettier (Style Linting):** Focado apenas na estética. Ele reorganiza quebras de linha, aspas simples vs duplas e espaçamentos. Não muda a lógica do código.  
* **ESLint (Quality Linting):** Focado na qualidade e no comportamento. Ele te impede de usar variáveis não declaradas, aponta lógicas perigosas e previne bugs silenciosos antes mesmo de você rodar o código.

### **5\. Sobrevivência DevOps: Status Pages e o Truque do Amend**

Saber lidar com fatores externos economiza horas de estresse.

* **GitHub Status (githubstatus.com):** Antes de achar que você quebrou o seu CI e passar horas tentando debugar o YAML, verifique se o problema não é na própria infraestrutura do GitHub.  
* **O Truque para Retriggar o CI:** Se o GitHub Actions travar por instabilidade deles, você precisa fazer o CI rodar de novo. Mas se você não alterou nenhum arquivo, não consegue fazer um git push. A saída sênior é alterar o Hash do último commit mudando apenas a data dele:  
* Comando: git commit \--amend \--date=now  
* Depois: git push \--force (para forçar a sobreposição do commit re-datado).

### **6\. A Muralha Técnica vs. Negócios**

* Quando pessoas focadas apenas em negócios definem fluxos sem entender as limitações técnicas, elas criam becos sem saída para os programadores.  
* O nosso papel como profissionais é dominar ferramentas como CI, Linting e Testes justamente para criar uma "Muralha Técnica" tão robusta e automatizada que possamos focar energia em resolver os problemas reais de negócio, em vez de ficar caçando bugs de formatação de código.

## **DIA 32 📚 Revisão: A Arte do Commit, Comunicação e o Valor do Negócio**

### **1\. O Alívio da Pressão: Negócio \> Código**

A primeira regra para não ter "medo de comitar" é entender o verdadeiro valor do seu trabalho.

* **O Peso do Commit:** O commit gera ansiedade porque não é apenas código; é um registro público (com seu nome) das suas decisões, sujeito a julgamento.  
* **A Regra de Ouro (Muralha de Negócio vs. Muralha Técnica):** Se você tiver que escolher onde tirar nota 10, escolha a Muralha de Negócio.  
  * Um código tecnicamente perfeito (nota 10\) em uma empresa falida não serve para nada.  
  * O *Product-Market Fit* (fazer um produto que as pessoas amam e pagam por ele) é o que traz dinheiro. O dinheiro compra tempo e liberdade para, depois, você refatorar e deixar a Muralha Técnica nota 10\. Nunca escale a tecnologia antes de validar o negócio.  
* **O Pragmatismo:** Em projetos solo, você pode comitar apenas com "x" na mensagem (como o criador do NomadList faz). Mas em equipes ou Open Source, o commit é uma **ferramenta de comunicação**. O cálculo mental deve ser sempre: *"O tempo que eu gasto escrevendo essa mensagem poupa mais tempo do meu time no futuro?"* Se o saldo for positivo, faça.

### **2\. Escopo do Commit: Onde começa e onde termina?**

A dúvida clássica: Quantos arquivos ou alterações devo colocar em um único commit?

* **A Regra do Kernel do Linux:** "Separe cada mudança lógica em um commit (patch) separado."  
* **O que é uma Mudança Lógica?**  
  * Refatorar a performance de uma query é uma mudança lógica.  
  * Corrigir um erro ortográfico na Home é outra.  
  * *Não misture as duas no mesmo commit.* Isso dificulta a revisão (Code Review) e o rastreamento de bugs.  
* **O Hack Reverso (A Prova de Fogo):**  
  * Para saber se seu escopo está certo, pergunte-se: *"Se eu reverter (git revert) este commit inteiro agora, eu ficarei satisfeito com o resultado?"*  
  * Se ao reverter o commit para consertar a query quebrada, você acabar desfazendo a correção ortográfica da Home (que estava funcionando bem), significa que você agrupou coisas demais. O escopo estava errado.  
* **Efeitos Colaterais (A Exceção):** Se a sua mudança exige alterar outros arquivos para que o sistema continue funcionando (ex: mudar o nome de uma função exige mudar todos os lugares que a chamam), **tudo isso deve ir no mesmo commit**. Um commit não pode quebrar o sistema. Ele deve parar em pé sozinho, com início, meio e fim.  
* **Testes:** O código e o teste que o valida são uma única mudança lógica. Devem ir no mesmo commit.

### **3\. A Semântica da Mensagem: O Tempo Verbal Imperativo**

Por que profissionais não escrevem commits no passado ("Ajustei", "Adicionei")?

* **A Ilusão do Diário:** O Git não é o seu diário pessoal ("Querido diário, hoje eu ajustei um bug"). Ele é um manual de instruções para o projeto.  
* **O Padrão do Git:** O próprio Git usa o imperativo quando gera mensagens automáticas: Merge branch, Revert commit.  
* **A Mudança de Perspectiva (O Revisor):** Quando alguém revisa seu código em um Pull Request, a pergunta que ela faz não é *"O que você fez ontem?"*, mas sim: **"O que esse commit FAZ com o meu projeto se eu aceitá-lo agora?"**  
  * ❌ *"Ajustei o bug de login"* (Foco no passado/em você).  
  * ✅ *"Ajusta o bug de login"* (Foco na ação/no projeto).  
* **A Analogia das Camadas do Photoshop:** Pense no commit como um filtro. Você não nomeia um filtro como "Adicionei brilho", você o nomeia como "Adiciona brilho", pois é isso que a camada *faz* com a imagem final toda vez que é ativada.

### **4\. Idioma e Padronização (Conventional Commits)**

* **Português vs. Inglês:** \* Se o objetivo principal é comunicação fluida dentro de um time 100% brasileiro, o português é infinitamente mais eficiente e exato.  
  * Se o objetivo é Open Source, alcance global, ou treinar seu inglês para vagas internacionais, use o Inglês. Além disso, as ferramentas automatizadas de CI/CD funcionam melhor em inglês.  
* **Conventional Commits:** É o padrão da indústria (feat:, fix:, chore:). Ele transforma as mensagens de commit em algo legível por máquinas, permitindo automatizar a geração de *Changelogs* (notas de atualização) e o versionamento do sistema (SemVer).

### **5\. A Cirurgia do Git: Cherry-Pick**

Como pegar apenas o tempero certo para a sua sopa.

* **O Problema:** Você está em uma branch gigante e incompleta, mas fez um commit excelente lá no meio que conserta um bug urgente na produção (main). Você não pode mesclar a branch inteira, pois ela não está pronta.  
* **A Solução (git cherry-pick \<hash\>):** \* Você vai para a main.  
  * Roda o comando com o Hash do commit específico.  
  * O Git "pinça" (cherry-pick) exatamente aquela mudança lógica e a copia para a main, ignorando todo o resto da branch suja.  
  * *Nota:* Por isso a regra do "escopo do commit" é tão importante. Se você tivesse misturado a correção do bug com a feature incompleta no mesmo commit, o cherry-pick seria impossível.

## **DIA 33📚 Revisão: Padrões de Commit, Git Rebase, Hooks e Husky**

### **1\. A Filosofia do Aprendizado: Criando Trilhas no Cérebro**

Antes das ferramentas, a postura.

* **Enfrentando o Desconhecido:** Quando o terminal explode com erros amarelos e vermelhos (como num conflito de Rebase), o instinto inicial é abortar (git rebase \--abort), apagar a branch e recriar.  
* **A Postura Sênior:** Fugir do erro impede o aprendizado. Você só cria uma nova "trilha neural" se passar pelo caminho difícil várias vezes. Entender e resolver um conflito de Rebase constrói uma confiança inabalável de que você consegue consertar qualquer desastre no repositório.

### **2\. Conventional Commits (O Padrão da Indústria)**

Usar um padrão elimina o atrito de decidir "como" escrever uma mensagem, focando apenas no "o que" foi feito.

**Os Principais Tipos (Baseado no Angular):**

* feat: Uma nova funcionalidade (feature).  
* fix: Uma correção de bug.  
* docs: Alterações exclusivas em documentação.  
* test: Adição ou correção de testes faltantes/errados.  
* ci: Alterações nos arquivos e scripts de integração contínua.  
* build: Alterações no sistema de build ou dependências externas (npm).

> ⚠️ **O Dilema Feat vs. Test:** Se você criou uma nova feature e escreveu os testes para ela, **tudo deve ir no mesmo commit com o tipo feat**. O tipo test deve ser usado *apenas* quando você está corrigindo ou adicionando testes isolados para códigos que já existiam antes.

### **3\. Git Hooks e a Solução do Husky**

Os "Ganchos" do Git permitem executar scripts automaticamente em momentos específicos (ex: antes de comitar).

* **O Problema dos Hooks Nativos:** Os hooks nativos vivem na pasta oculta .git/hooks. Por segurança, essa pasta **não é versionada** (não sobe para o GitHub). Portanto, você não consegue compartilhar regras de validação facilmente com a sua equipe.  
* **A Solução (Husky):** O Husky é uma biblioteca que resolve esse problema de forma elegante.  
  * Ele altera a configuração interna do Git (core.hooksPath) para ler os hooks de uma pasta normal chamada .husky na raiz do projeto, que **pode** ser versionada.  
  * **Automação (prepare):** Ao colocar o script prepare: "husky" no package.json, o NPM garante que sempre que um novo desenvolvedor rodar npm install no projeto, os hooks do Husky serão configurados automaticamente na máquina dele.  
* **O Botão de Fuga (\--no-verify):** Se por algum motivo crítico você precisar pular a validação dos hooks, pode forçar o commit usando: git commit \-m "msg" \--no-verify (ou apenas \-n).

### **4\. Ferramentas de Produtividade e Qualidade**

* **Commitlint:** É o fiscal. Ele roda dentro do hook commit-msg e bloqueia o commit se você tentar usar um padrão fora do Conventional Commits.  
* **Commitizen:** É o assistente interativo. Executando npm run commit (após configurado), ele abre uma interface no terminal que te faz perguntas (Qual o tipo? Qual o escopo? Tem breaking change?) e monta a mensagem perfeita para você.

### **5\. O Monstro Domado: git rebase e Resolução de Conflitos**

O Rebase é a arte de reescrever a história alterando a base de onde seus commits partiram.

* **A Situação:** Sua branch (A) foi criada a partir de uma main antiga. A main atualizada agora tem o arquivo X alterado. Sua branch A também alterou o arquivo X. Ao tentar fazer o Merge/Rebase, o Git trava com um **CONFLITO**.  
* **Como resolver (O caso do package-lock.json):**  
  * Como o package-lock.json é um arquivo gerado por máquina, tentar resolver as marcações (\<\<\<\< HEAD) manualmente é um erro terrível.  
  * Apele para a limpeza: Delete o arquivo package-lock.json gerado pelo conflito.  
  * Rode npm install novamente. O NPM lerá o package.json atualizado e gerará um lock limpo e novo.  
  * Adicione a solução: git add package-lock.json.  
  * Continue o processo: git rebase \--continue.  
* **Git Rebase Interativo (\-i):**  
  * Comando: git rebase \-i HEAD\~2 (Pega a base atual e abre os últimos 2 commits para edição).  
  * O arquivo git-rebase-todo é aberto. Você pode mudar a palavra pick para reword (ou r) ao lado do commit que deseja alterar.  
  * O Git pausará o processo nesse commit e permitirá que você reescreva a mensagem.  
* **A Consequência (git push \-f):** Fazer Rebase reescreve a história e altera os Hashes (IDs) dos commits. Se você já havia enviado (pushed) essa branch para o GitHub antes de fazer o Rebase, terá que forçar a sobreposição usando git push \-f (Force Push). Use com extrema cautela se outras pessoas estiverem trabalhando na mesma branch.


**DIA 34 📚 Revisão: Manutenção, Versionamento Semântico (SemVer) e Políticas de Licença**

### **1\. Segurança e npm audit (A Síndrome do "Menino Lobo")**

O comando npm audit verifica se as dependências do seu projeto possuem vulnerabilidades conhecidas. No entanto, usá-lo sem critério no CI/CD pode paralisar o desenvolvimento.

* **O Problema do Excesso de Alerta:** Dan Abramov (co-criador do Redux/React) explica que o npm audit é "Broken by Design" (Quebrado por design). Ele emite dezenas de alertas de vulnerabilidade que, na prática, são inexploráveis. Ex: Uma falha de injeção de código em um formatador de texto (Prettier) que roda apenas no computador local do desenvolvedor. Se o hacker já está no seu computador local, você tem problemas muito maiores do que o Prettier.  
* **A Estratégia Sênior para o CI:**  
  * Nunca quebre o CI por vulnerabilidades em ferramentas de desenvolvimento (devDependencies).  
  * **Comando Otimizado:** npm audit \--omit=dev \--audit-level=critical  
  * *O que faz:* Ignora pacotes de desenvolvimento e só avisa se houver uma falha nível "Crítico" rodando no código final (Produção). Se esse comando retornar erro, aí sim a equipe deve parar tudo e resolver.

### **2\. A Guerra das Licenças: A Lição do Facebook (Meta) e React**

Entender de código não basta; é preciso entender o impacto legal do que você insere no projeto.

* **O Contexto:** O React nasceu com a licença *Apache 2.0* (permissiva \+ concessão de patentes). Você podia usar o React e, se ele tivesse uma patente do Facebook embutida, você não seria processado por isso.  
* **A Mudança Polêmica (2014):** O Facebook trocou para *BSD \+ Patents*. Uma cláusula dizia: *"Você pode usar o React, mas se você processar o Facebook por qualquer motivo, você perde o direito de usar o React e nossos outros softwares"*.  
* **O Efeito FUD (Fear, Uncertainty, Doubt):** O medo se instalou. Concorrentes usaram isso como arma. A *Apache Foundation* baniu o uso de softwares com essa licença. Grandes projetos (como o WordPress) anunciaram que iriam reescrever seus sistemas inteiros só para arrancar o React.  
* **A Resolução:** Sob extrema pressão da comunidade e ameaça de abandono em massa, o Facebook cedeu, deletou a cláusula de patentes e mudou o React, Jest e outros para a **Licença MIT** (a mais permissiva de todas).  
* **A Lição:** Licenças restritivas matam a adoção orgânica de projetos Open Source. Para o TabNews, escolheu-se a **MIT** justamente para remover o atrito e incentivar a contribuição livre.

### **3\. A Nuance da "ASP Loophole" (AGPL vs. GPLv3)**

* **GPLv3:** Permite o uso gratuito, mas diz que, se você distribuir o software, deve abrir o seu código-fonte modificado.  
* **ASP Loophole (A Brecha do SaaS):** Empresas usavam o código GPLv3 nos seus servidores e vendiam o acesso via navegador (SaaS). Como não "distribuíam o binário/arquivo" para o usuário final, elas tecnicamente não precisavam abrir seu próprio código.  
* **AGPL (Affero GPL):** Criada para fechar essa brecha. Ela diz: *"Se o usuário interage com o software modificado pela rede (internet), você é obrigado a disponibilizar o código-fonte dele"*.

### **4\. Semantic Versioning (SemVer) e NPM**

A convenção universal para nomear versões de software e gerenciar risco nas atualizações.

* **A Estrutura (Major.Minor.Patch):** Ex: Versão 1.2.3  
  * **Patch (Correção \- Último número \- Ex: 3 para 4):** Apenas correções de bugs. 100% seguro atualizar. Nenhuma quebra.  
  * **Minor (Adição \- Número do meio \- Ex: 2 para 3):** Novas funcionalidades adicionadas, mas tudo que existia antes continua funcionando igual (*Backwards Compatible*). Seguro atualizar.  
  * **Major (Quebra \- Primeiro número \- Ex: 1 para 2):** *Breaking Change*. A interface mudou, funções foram removidas ou alteradas. Atualizar vai quebrar sua aplicação. Exige leitura da documentação e refatoração do seu código.  
* **O Fator Humano:** O SemVer é uma convenção feita por humanos. Humanos erram. Um desenvolvedor pode lançar um *Patch* que, sem querer, contém uma *Breaking Change*. Nunca confie cegamente; tenha testes automatizados.  
* **Símbolos do NPM (package.json):**  
  * 1.2.3: Trava o NPM para baixar exatamente a versão 1.2.3.  
  * \~1.2.3 (Til): Permite que o NPM instale **apenas atualizações de Patch** (Ex: vai até o 1.2.9, mas nunca o 1.3.0).  
  * ^1.2.3 (Circunflexo): Permite que o NPM instale **atualizações de Patch e Minor** (Ex: vai até o 1.9.9, mas nunca o 2.0.0). É o padrão do NPM.

### **5\. Boas Práticas de Refatoração e Manutenção**

* **O Processo (De Baixo para Cima):** Ao atualizar dependências em um projeto antigo, não atualize tudo de uma vez. Atualize primeiro os *Patches*, rode os testes. Depois atualize os *Minors*, rode os testes. Por último, os *Majors* (um a um), adaptando o código.  
* **A Arte do Commit na Atualização:**  
  1. Faça o commit da atualização da Dependência A. Os testes passaram?  
  2. Atualize a Dependência B. Os testes passaram? Use o git commit \--amend (ou rebase interativo) para juntar com o commit anterior, mantendo um histórico limpo de "Atualização de Dependências Bem-Sucedidas".

### **6\. Peer Dependencies e Resolução de Conflitos (ERESOLVE)**

* **O Erro ERESOLVE:** Ocorre quando o NPM não sabe qual versão de um pacote instalar, pois duas bibliotecas diferentes no seu projeto exigem versões conflitantes da mesma dependência compartilhada (*Peer Dependency*).  
* **A Estratégia de Resolução (A Opção Nuclear):**  
  * Se você não consegue resolver o conflito de árvore de dependências, a tática "reiniciar o roteador" do Node é:  
  * rm \-rf node\_modules package-lock.json  
  * npm cache clean \--force  
  * npm install  
  * *Cuidado:* Isso vai forçar o NPM a recalcular a árvore do zero e instalar as versões mais recentes permitidas pelos circunflexos (^). Sem testes automatizados, essa manobra é um tiro no escuro.

## **DIA 35 📚 Revisão: O Efeito Estilingue, BDD vs TDD e o Falso Positivo do CI**

### **1\. Psicologia do Desenvolvedor: O Efeito "Estilingue Infinito"**

Em projetos pessoais, a falta de prazos rígidos pode ser o seu maior inimigo.

* **O Conceito:** Você puxa o elástico do estilingue cada vez mais para trás (lapidando o código, refatorando, adicionando ferramentas), tentando ter 100% de certeza de que vai acertar o alvo. O problema? Enquanto você não soltar a pedra, você nunca vai errar. Mas também nunca vai acertar.  
* **A Procrastinação Disfarçada:** Ficar refatorando infinitamente é uma forma confortável de evitar o confronto com a realidade (o cliente/usuário).  
* **A Solução:** Em estágios iniciais de um projeto, a **experimentação rápida** vale muito mais do que a precisão lenta. O código é volátil. Entregue, erre rápido e ajuste.

### **2\. Utilitários do NPM: pre e post Scripts**

O NPM possui uma mecânica oculta e poderosa para orquestrar comandos no package.json.

* Se você tem um script chamado test, você pode criar automaticamente scripts chamados pretest e posttest.  
* **Como funciona:**  
  * Ao rodar npm run test, o NPM procura primeiro se existe o pretest. Se existir, ele roda.  
  * Depois, roda o test principal.  
  * Se o test terminar com sucesso (**Exit Code 0**), ele roda o posttest. Se o comando principal falhar, o post é ignorado.

### **3\. Paradigmas de Qualidade: TDD, BDD e Gherkin**

Como não deixar seus testes isolados apenas para os programadores.

* **TDD (Test Driven Development):** Guiado por testes. O risco é que o desenvolvedor crie testes "viciados" apenas em detalhes técnicos, esquecendo o valor real para o negócio.  
* **BDD (Behavior Driven Development):** Guiado por comportamento. Foca no que o sistema *deve fazer* na visão do usuário, unindo o time técnico, o time de Negócios e o de Qualidade (QA).  
* **O Padrão Gherkin (A Linguagem Universal):**  
  * Uma sintaxe que lê como uma história, facilitando a comunicação.  
  * **Given (Dado):** O estado inicial. *(Dado que o usuário é anônimo)*  
  * **When (Quando):** A ação. *(Quando ele faz um POST no /migrations)*  
  * **Then (Então):** O resultado esperado. *(Então ele recebe um array vazio)*

### **4\. A Filosofia Sênior: Contexto vs. Afirmação**

Um dos maiores erros em testes automatizados é a duplicação de informações, gerando o **descasamento**.

* **O Perigo da Duplicação:** Se você escreve no nome do teste: "Deve retornar o status 200", e no código você escreve expect(status).toBe(200), você tem a mesma informação em dois lugares (Linguagem Natural e Código). Se a API mudar para 201, você atualiza o código, mas esquece de mudar o texto. O teste passa, mas a documentação mente. Em um dia de pressão, isso causa pânico.  
* **A Solução (A Regra do TabNews/FinTab):**  
  * **O Texto (Describe/It) é apenas o CONTEXTO:** Descreve a situação e a ação. Ex: "Usuário anônimo acessando o endpoint de migrations". O texto não promete nenhum valor.  
  * **O Código (Expect) é a única AFIRMAÇÃO:** Os valores (200, 201, array vazio) vivem exclusivamente dentro do código. Assim, existe apenas uma **única fonte de verdade**, eliminando o risco de descasamento.

### **5\. O Maior Aprendizado: O Falso Positivo no CI e o Ambiente Serverless**

A prova definitiva de que o "Funciona na minha máquina" é uma armadilha.

* **O Cenário:** O GitHub Actions rodou a nova atualização do node-pg-migrate. Tudo passou perfeitamente (no ambiente Ubuntu). Mas quando a Vercel subiu para homologação, o endpoint /migrations retornou Erro 500 dizendo que a pasta não existia.  
* **Por que o CI mentiu?**  
  * O CI (Ubuntu) é um Sistema Operacional completo. O disco está lá, as pastas estão lá. O path.join navega fisicamente pelas pastas.  
  * A Vercel usa **Serverless (Lambdas)**. Antes de subir o código, a Vercel usa um "empacotador" (Bundler) que tenta ler o seu código para adivinhar quais pastas ele precisa levar para a nuvem. Com a atualização da biblioteca, o bundler da Vercel se perdeu com o path.join e simplesmente **não empacotou** a pasta de migrações.  
* **A Correção (path.resolve):** Trocar de join para resolve gerou um caminho absoluto claro e determinístico que o empacotador da Vercel conseguiu entender e rastrear, forçando-o a levar os arquivos .js de migração para dentro da Lambda.

### **6\. Cirurgia Avançada no Git: rebase \-i (Edit e Reword)**

Como consertar o passado com precisão cirúrgica a pedido de um "Sênior".

* Usando o git rebase \-i \<hash\_base\>, abrimos a máquina do tempo.  
* **reword (r):** Usamos no último commit apenas para alterar a mensagem de "clean" para "clear", sem tocar no código.  
* **edit (e):** Usamos no commit do meio da história. O Git pausou a linha do tempo. Abrimos o arquivo de teste, trocamos a palavra running por retrieving, rodamos git commit \--amend \--no-edit (para salvar sem mudar a mensagem) e depois git rebase \--continue.  
* O resultado foi um histórico limpo e perfeitamente lapidado, empurrado com git push \-f.

---

### **🎉 Fim da Milestone 1: A Fundação**

Você superou os desafios de Banco de Dados, Migrations, Testes Automatizados de Integração, Git avançado e orquestração de CI/CD. A base de concreto do seu "arranha-céu" está pronta, sólida e profissional.

[image1]: <data:image/png;base64,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>