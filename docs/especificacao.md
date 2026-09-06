# 📋 Especificação e Planejamento Técnico — PharmaStation

Documento original de requisitos acadêmicos, decisões de arquitetura e referências de design do projeto **PharmaStation**.

---

## 🎯 1. Contexto Acadêmico & Requisitos do Laboratório

O projeto foi desenvolvido para a disciplina de **Estruturas de Dados I (ED1)** no **Instituto Federal de Sergipe (IFS)**.

### 📌 Objetivo
Implementar um sistema de gerenciamento e controle de estoque de medicamentos fundamentado no uso de **Estruturas de Dados Dinâmicas Lineares próprias** (listas simplesmente encadeadas com manipulação de nós de memória), sendo vedado o uso de classes prontas da biblioteca padrão (`java.util.List`, `ArrayList`, etc.) para os repositórios do domínio.

---

### 📦 Atributos Obrigatórios do Produto
Cada registro de medicamento no estoque deve conter:
1. **Código:** Identificador numérico único do medicamento.
2. **Descrição:** Detalhamento da posologia, forma farmacêutica ou uso terapêutico.
3. **Marca:** Fabricante ou laboratório farmacêutico.
4. **Valor de Entrada:** Preço de custo / compra junto ao fornecedor.
5. **Valor de Saída:** Preço final de venda ao consumidor.
6. **Quantidade em Estoque:** Volume físico disponível para dispensação.

---

### ⚙️ Funcionalidades Obrigatórias

| # | Operação | Descrição |
| :-: | :--- | :--- |
| **1** | **Adicionar Produto** | Inserção de novo registro com validação de duplicidade de código (`ProdutoJaExisteException`). |
| **2** | **Mostrar Produtos** | Listagem tabular dos medicamentos cadastrados no estoque. |
| **3** | **Ordenar por Nome** | Ordenação alfabética dinâmica utilizando algoritmo sobre a lista encadeada própria via `Comparator`. |
| **4** | **Repor Estoque** | Entrada de mercadoria com incremento na quantidade física. |
| **5** | **Vender Produto** | Saída de mercadoria com validação rígida de saldo suficiente (`ProdutoNaoPodeSerVendidoException`). |
| **6** | **Alteração de Preço em Lote** | Aplicação de reajuste de valores em múltiplos produtos selecionados simultaneamente. |
| **7** | **Relatório de Vendas** | Demonstrativo de saídas com quantidades, valores unitários e arrecadação total. |
| **8** | **Relatório de Estoque** | Posição de inventário com apuração de custos totais, projeção de receita e margem de lucro. |

---

## 🏗️ 2. Arquitetura e Padrões de Projeto

A aplicação foi estruturada seguindo o padrão **MVC (Model-View-Controller)** com camada intermediária de **Repository** e persistência desacoplada:

```
[ Usuário ]
    ↕
[  View  ] ── (Eventos / Formulários) 
    ↕
[ Controller ] (Singleton — Regras de negócio e mediação)
    ↕
[ Repository ] (Singleton — Manipulação da Lista<E> em memória)
    ↕
[ Persistence ] (Serialização em disco .dat via ObjectOutputStream)
```

### 🧩 Responsabilidade dos Pacotes

* **`br.edu.ifs.farmacia.model`:**  
  Entidades de domínio puras (`Produto`, `Venda`). Encapsulam dados, validações básicas e regras de cálculo de negócio (como `isVendivel()`).
* **`br.edu.ifs.farmacia.model.login`:**  
  Modelagem de segurança e autenticação com polimorfismo (`Usuario` como classe base abstrata, `Administrador` e `Funcionario`).
* **`br.edu.ifs.farmacia.controller`:**  
  Controladores estruturados com o padrão **Singleton** (`ProdutoController`, `VendaController`, `LoginController`, `MainController`). Recebem os comandos da interface, validam dados e orquestram chamadas aos repositórios.
* **`br.edu.ifs.farmacia.repository`:**  
  Mantém os dados ativos em memória em instâncias de coleções personalizadas (`Lista<Produto>`, `Lista<Venda>`). Expõe métodos de busca por código/ID, ordenação e filtros.
* **`br.edu.ifs.farmacia.persistence`:**  
  Camada de I/O encarregada de serializar (`ObjectOutputStream`) e desserializar (`ObjectInputStream`) o estado dos repositórios em arquivos binários (`.dat`).
* **`br.edu.ifs.farmacia.util`:**  
  O núcleo de Estruturas de Dados do projeto:
  - `No<E>`: Nó genérico com encadeamento de ponteiro.
  - `Lista<E>`: Lista dinâmica com operações de inserção, remoção e ordenação por seleção in-place.
  - `Fila<E>` e `Pilha<E>`: Estruturas FIFO e LIFO.
  - Exceções personalizadas de domínio.
* **`br.edu.ifs.farmacia.view` & `view.component`:**  
  Telas e formulários modulares desacoplados (`PanelProdutos`, `PanelCreateProduto`, `PanelProductPriceEditor`, `PanelVenda`, `PanelCover`, `PanelLogin`).
* **`br.edu.ifs.farmacia.report`:**  
  Gerenciador do motor de relatórios **JasperReports 7.0**, compilando dinamicamente os templates `.jrxml` e preenchendo parâmetros com dados dos repositórios.

---

## 🎨 3. Referências de Design & UI/UX

A estética do projeto foi construída para romper com o visual arcaico das aplicações Swing tradicionais:

1. **Conceito de Login Deslizante (Dribbble):**  
   A mecânica de transição animada da tela de autenticação foi inspirada no conceito [Diprella Login no Dribbble](https://dribbble.com/shots/5311359-Diprella-Login), implementada com o **TimingFramework** e cálculo de fração de tamanho entre o painel de capa (`PanelCover`) e o painel de campos (`PanelLogin`).
2. **Gerenciador de Layout (MigLayout):**  
   Uso do [MigLayout](https://devdoc.net/javamisc/miglayout/whitepaper.html) para alcançar layouts fluidos e responsivos sem as limitações dos layouts padrão da AWT/Swing.
3. **Design System & FlatLaf:**  
   - Suporte a temas Claro e Escuro (`FlatMacDarkLaf` e `FlatIntelliJLaf`) com troca suave de tema em tempo real.
   - Cantos arredondados (`arc: 25`), tipografia moderna (famílias Roboto e Inter) e ícones vetoriais SVG (`FlatSVGIcon`).
4. **Modais e Notificações Flutuantes:**  
   Integração das bibliotecas modernas do Raven (`swing-glasspane-popup`, `modal-dialog` e `swing-toast-notifications`), substituindo `JOptionPane` por toasts e caixas modais integradas ao design.
