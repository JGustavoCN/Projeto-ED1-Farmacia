<div align="center">

  <img src="assets/logo-nome.png" alt="PharmaStation Logo" width="260"/>

# PharmaStation

### Sistema Desktop para Gestão Farmacêutica, Controle de Estoque e PDV

  [![Java](https://img.shields.io/badge/Java-21-orange.svg?logo=openjdk&logoColor=white)](https://openjdk.org/)
  [![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36.svg?logo=apache-maven&logoColor=white)](https://maven.apache.org/)
  [![UI](https://img.shields.io/badge/UI-Java%20Swing%20%7C%20FlatLaf-blue.svg)](https://www.formdev.com/flatlaf/)
  [![Reports](https://img.shields.io/badge/Reports-JasperReports%207.0-red.svg)](https://community.jaspersoft.com/)
  [![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

  <p align="center">
    Um estudo de caso prático que une <b>Estruturas de Dados Fundamentais (ED1)</b> construídas do zero a uma <b>Interface Gráfica Moderna</b> com animações, temas dinâmicos e emissão de relatórios gerenciais em PDF.
  </p>

</div>

---

## 📌 Sobre o Projeto

O **PharmaStation** foi concebido como um estudo de caso prático para a disciplina de **Estruturas de Dados I (ED1)** no **Instituto Federal de Sergipe (IFS)**.

Diferente de sistemas acadêmicos convencionais que utilizam apenas telas cinzas e coleções nativas prontas (`java.util.ArrayList`), o **PharmaStation** foi desenvolvido com dois objetivos centrais:

1. **Rigor Algorítmico:** Implementar as estruturas de dados de base (**Lista Encadeada**, **Fila** e **Pilha**) manualmente com manipulação de nós de memória.
2. **Experiência de Usuário de Alto Padrão (UI/UX):** Provar que aplicações desktop em **Java Swing** podem ser elegantes, fluidas e modernas através da integração de **FlatLaf**, animações com **TimingFramework**, componentes customizados e **MigLayout**.

---

## 🚀 Funcionalidades Principais

### 💊 Gestão de Medicamentos & Catálogo

- **Cadastro Completo:** Registro de código, nome, marca, descrição detalhada, preço de entrada (custo), preço de saída (venda) e quantidade em estoque.
- **Edição em Lote:** Seleção de múltiplos medicamentos na tabela para reajuste de preços simultâneo.
- **Busca e Ordenação Dinâmica:** Ordenação alfabética em tempo real via algoritmo próprio sobre a lista encadeada.

### 🛒 Ponto de Venda (PDV)

- **Venda Rápida:** Seleção direta dos itens e quantidade a ser vendida.
- **Integridade de Estoque:** Validação automática com bloqueio de vendas caso a quantidade solicitada exceda a disponível (`ProdutoNaoPodeSerVendidoException`).
- **Cálculo Financeiro:** Atualização em tempo real de subtotais e margem de lucro por operação.

### 📑 Emissão de Relatórios Gerenciais (JasperReports)

- **Relatório de Posição de Estoque:** Demonstrativo completo com volume de produtos, custos totais, projeção de faturamento e lucro estimado.
- **Relatório Analítico de Vendas:** Histórico consolidado de saídas com quantidades vendidas e total financeiro realizado.
- Visualização e exportação nativa em **PDF**.

### 🎨 Experiência Visual & Temas

- **Dark / Light Mode:** Alternância suave entre temas escuros (`FlatMacDarkLaf`) e claros (`FlatIntelliJLaf`) com transição animada e *debounce*.
- **Tela de Login Deslizante:** Animação com `TimingFramework` dividindo o painel de boas-vindas e credenciais.
- **Toasts & Modais:** Notificações flutuantes e janelas modais via `GlassPane` (Raven UI), sem travar a interface com diálogos modais antiquados.
- **Botões com Efeito Ripple:** Renderização vetorial customizada em `Graphics2D` com efeito de clique Material Design.

---

## 🧠 Estruturas de Dados Implementadas do Zero (ED1)

O núcleo do projeto não utiliza `java.util.List` para a lógica de negócio dos repositórios. Toda a manipulação em memória é sustentada por classes próprias:

| Estrutura | Classe | Destaques de Implementação |
| :--- | :--- | :--- |
| **Nó Genérico** | [`No<E>`](src/main/java/br/edu/ifs/farmacia/util/No.java) | Armazena o elemento genérico e o ponteiro para o próximo nó. |
| **Lista Encadeada** | [`Lista<E>`](src/main/java/br/edu/ifs/farmacia/util/Lista.java) | Inserção no início/fim/índice, remoção, busca e método `ordenar(Comparator<E>)` utilizando algoritmo de seleção sobre nós encadeados. |
| **Fila** | [`Fila<E>`](src/main/java/br/edu/ifs/farmacia/util/Fila.java) | Operações `enfileirar` e `desenfileirar` (FIFO) com controle de ponteiro inicial e final. |
| **Pilha** | [`Pilha<E>`](src/main/java/br/edu/ifs/farmacia/util/Pilha.java) | Operações `empilhar` e `desempilhar` (LIFO) com verificação de vazia e topo. |

---

## 🏗️ Arquitetura do Software

A aplicação adota uma organização em camadas inspirada no padrão **MVC (Model-View-Controller)** com abstração de **Repository**:

```
br.edu.ifs.farmacia/
├── controller/       # Mediação entre as telas e a regra de negócio (ProdutoController, VendaController, LoginController)
├── model/            # Entidades do domínio (Produto, Venda)
│   └── login/        # Hierarquia e polimorfismo de acessos (Usuario, Administrador, Funcionario)
├── repository/       # Gerenciamento dos dados em memória via instâncias de Lista<E> (Singleton)
├── persistence/      # Camada de serialização de objetos (arquivos .dat)
├── report/           # Compilação e preenchimento de relatórios via JasperReports
├── util/             # Estruturas de dados próprias (Lista, Fila, Pilha, No) e Exceções do negócio
└── view/             # Formulários principais (LoginForm, MainForm)
    ├── component/    # Painéis reutilizáveis (PanelProdutos, PanelVenda, PanelCreateProduto, PanelCover)
    └── swing/        # Componentes visuais estilizados (Button com ripple, MyTextField, switches)
```

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** [Java 21 (LTS)](https://openjdk.org/)
- **Build Tool:** [Apache Maven](https://maven.apache.org/)
- **GUI Toolkit:** Java Swing
- **Look and Feel & Ícones:** [FlatLaf 3.5.1](https://www.formdev.com/flatlaf/), FlatLaf IntelliJ Themes, FlatSVGIcon
- **Animações:** [TimingFramework 1.0](https://java.net/)
- **Gerenciador de Layout:** [MigLayout 11.4](http://www.miglayout.com/)
- **Componentes de Janela:** Raven Swing GlassPane Popup, Modal Dialog, Toast Notifications
- **Motor de Relatórios:** [JasperReports 7.0.0](https://community.jaspersoft.com/) & Groovy

---

## 💻 Como Executar o Projeto

### Pré-requisitos

- **Java JDK 21** instalado e configurado nas variáveis de ambiente.

- **Apache Maven 3.8+** instalado (ou utilize o suporte integrado da sua IDE).
- **Git** instalado.

### Passo a Passo

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/SEU_USUARIO/pharmastation.git
   cd pharmastation
   ```

2. **Compile o projeto com o Maven:**

   ```bash
   mvn clean package
   ```

3. **Execute a aplicação:**

   ```bash
   mvn exec:java
   ```

   *(Ou execute diretamente o arquivo principal `br.edu.ifs.farmacia.view.LoginForm` pela sua IDE).*

### 🔑 Credenciais de Acesso Padrão

- **Usuário:** `admin`

- **Senha:** `admin`

---

## 👨‍💻 Autor

Desenvolvido por **José Gustavo**  
Estudante de Tecnologia / Ciência da Computação — **IFS (Instituto Federal de Sergipe)**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](www.linkedin.com/in/jgustavocn)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/JGustavoCN)

---

## 📄 Licença

Este projeto está sob a licença [MIT](LICENSE) — sinta-se à vontade para utilizar como referência de estudos.
