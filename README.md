### Descrição de Funcionamento do Sistema.

### Interface `Pagavel`

A interface Pagavel define um contrato para classes que representam itens que podem ser pagos. Ela possui um único método, pagar(double valor), este método é responsável por marcar um item como pago e possivelmente processar o valor pago, a interface é implementada pela classe Despesa e suas subclasses, garantindo que todas as despesas possam ser pagas e que o sistema trate o pagamento de maneira correta.

### Classe `Despesa`

A classe Despesa é uma classe abstrata que serve como base para as subclasses que representam classes especificas e para representar uma despesa no sistema. Armazenando informações fundamentais como a descrição da despesa, o valor, a data de vencimento e a categoria a que pertence, o atributo paga indica se a despesa foi quitada, seu construtor inicializa esses atributos e define o estado de paga como false por padrão. O método isPaga() retorna se a despesa foi paga ou não, e o método pagar(double valor) atualiza o status para pago, imprimindo uma mensagem com o valor pago. O método exibirDetalhes() é abstrato, o que significa que deve ser implementado pelas subclasses para fornecer detalhes específicos sobre a despesa. O método toString() retorna uma representação da despesa em formato de string, com os atributos sendo separados por ponto e vírgula para melhor leitura.

### Classe `Transporte`

A classe Transporte é uma subclasse de Despesa que representa despesas relacionadas a transporte. Ela herda todos os atributos e métodos da classe Despesa, mas define a categoria da despesa como "Transporte" através do construtor, já o método exibirDetalhes() é implementado para mostrar informações específicas sobre a despesa de transporte, incluindo a descrição, o valor e a data de vencimento.

### Classe `Alimentacao`

A classe Alimentacao é uma subclasse de Despesa que lida com despesas relacionadas a alimentação, sendo similar à classe Transporte, ela herda da classe Despesa e configura a categoria como "Alimentacao". O método exibirDetalhes() é implementado para exibir os detalhes da despesa de alimentação, como a descrição, o valor e a data de vencimento.

### Classe `Saude`

A classe Saude é uma subclasse de Despesa destinada a despesas com saúde, herda da classe Despesa, definindo a categoria como "Saude". O método exibirDetalhes() é implementado para mostrar os detalhes da despesa de saúde, incluindo a descrição, o valor e a data de vencimento.

### Classe `Lazer`

A classe Lazer é uma subclasse de Despesa que representa despesas relacionadas ao lazer. Assim como as outras subclasses, ela define a categoria como "Lazer" e implementa o método exibirDetalhes() para exibir as informações sobre a despesa de lazer, incluindo a descrição, o valor e a data de vencimento.

### Classe `Moradia`

A classe Moradia é uma subclasse de Despesa que trata de despesas com moradia. Ela define a categoria como "Moradia" e implementa o método exibirDetalhes() para mostrar detalhes sobre a despesa de moradia, como a descrição, o valor e a data de vencimento.

### Classe `Usuario`

A classe Usuario representa um usuário do sistema, com informações essenciais como o login e uma senha criptografada. O construtor da classe recebe um login e uma senha, e utiliza o método criptografarSenha(String senha) para armazenar a senha de forma criptografada. A criptografia é realizada de forma simples, invertendo a string da senha. O método getLogin() retorna o login do usuário, enquanto getSenhaCriptografada() retorna a senha criptografada.

A classe oferece dois métodos estáticos para manipulação de usuários em arquivos. O método salvarUsuario(Usuario usuario, String caminhoArquivo) salva os dados do usuário (login e senha criptografada) em um arquivo especificado pelo caminho. Já o método listarUsuarios(String caminhoArquivo) lê o arquivo e cria uma lista de objetos Usuario a partir das informações armazenadas, onde cada linha do arquivo contém um login e uma senha criptografada separados por ponto e vírgula.

### Classe `TipoDespesa`

A classe TipoDespesa representa os tipos de despesas disponíveis no sistema. Cada objeto dessa classe possui um atributo nome que descreve o tipo de despesa. O construtor da classe inicializa o nome do tipo de despesa, e o método getNome() retorna esse nome.

A classe inclui métodos estáticos para gerenciar os tipos de despesa em arquivos. O método salvarTipoDespesa(TipoDespesa tipo, String caminhoArquivo) adiciona um novo tipo de despesa ao arquivo especificado, escrevendo o nome do tipo de despesa. O método listarTipos(String caminhoArquivo) lê o arquivo e retorna uma lista de objetos TipoDespesa, onde cada linha do arquivo corresponde a um tipo de despesa.

Além disso, a classe oferece métodos para atualizar e excluir tipos de despesa. O método atualizarTipoDespesa(ArrayList<TipoDespesa> tipos, String nomeAntigo, String nomeNovo, String caminhoArquivo) substitui o nome antigo por um novo nome no arquivo, enquanto excluirTipoDespesa(ArrayList<TipoDespesa> tipos, String nomeParaExcluir, String caminhoArquivo) remove um tipo de despesa específico do arquivo.

### Classe `SistemaControleDespesas`

A classe SistemaControleDespesas é a classe principal que gerencia a execução do sistema de controle de despesas, possui um menu principal que permite ao usuário realizar várias operações relacionadas às despesas, pagamentos, tipos de despesas e usuários. O sistema utiliza arquivos de texto para armazenar e recuperar informações.

#### Atributos da Classe

**ARQUIVO_DESPESA**: Define o nome do arquivo onde as despesas são salvas.
**ARQUIVO_USUARIOS**: Define o nome do arquivo onde os usuários são salvos.
**ARQUIVO_TIPOS**: Define o nome do arquivo onde os tipos de despesas são armazenados.
**ARQUIVO_PAGAMENTOS**: Define o nome do arquivo onde os pagamentos são registrados.
**Formato_Data**: Define um formato de data para parsear e formatar datas no padrão "dd/MM/yyyy".

#### Método main

Ele exibe um menu principal para o usuário e executa a opção escolhida. O loop while continua rodando até que o usuário escolha sair. As opções disponíveis no menu incluem:

1. **Entrar Despesa**: Chama o método criarDespesa para adicionar uma nova despesa.
2. **Anotar Pagamento**: Chama o método anotarPagamento para registrar o pagamento de uma despesa.
3. **Listar Despesas em Aberto**: Chama o método listarDespesas para listar todas as despesas que ainda não foram pagas.
4. **Listar Despesas Pagas**: Chama o método listarDespesas para listar todas as despesas que já foram pagas.
5. **Gerenciar Tipos de Despesa**: Chama o método gerenciarTiposDespesa para adicionar, atualizar, excluir ou listar tipos de despesa.
6. **Gerenciar Usuários**: Chama o método gerenciarUsuarios para adicionar ou listar usuários.
7. **Editar ou Excluir Despesa**: Chama o método editarOuExcluirDespesa para editar ou excluir uma despesa existente.
8. **Sair**: Encerra o loop e fecha o programa.

#### Método `criarDespesa`

Este método coleta informações sobre uma nova despesa, como descrição, valor, data de vencimento e categoria. Baseado na categoria informada, o método cria uma instância da classe apropriada (Transporte, Alimentacao, Saude, Lazer, Moradia) e salva a despesa no arquivo despesas.txt.

#### Método `criarDespesaPorCategoria`

Este método cria um objeto Despesa de acordo com a categoria fornecida (por exemplo, Transporte, Alimentacao, etc.). Retorna null se a categoria não for reconhecida.

#### Método `salvarDespesa`

Salva a despesa em um arquivo de texto (despesas.txt), adicionando uma nova linha com os detalhes da despesa.

#### Método `listarDespesas`

Lê o arquivo de despesas e exibe as despesas de acordo com o status de pagamento. O parâmetro apenasPagas determina se apenas despesas pagas ou apenas despesas em aberto devem ser listadas.

#### Método `anotarPagamento`

Registra o pagamento de uma despesa específica, atualizando o status de pagamento da despesa e salvando as informações no arquivo pagamentos.txt. Se o valor pago for menor que o valor da despesa, o pagamento não é registrado, além disso ele atualiza o status no arquivo despesas.txt.

#### Método `registrarPagamento`

Salva as informações de pagamento (descrição da despesa, valor pago e data do pagamento) no arquivo pagamentos.txt.

#### Método `carregarDespesas`

Carrega todas as despesas do arquivo despesas.txt e retorna uma lista de objetos Despesa.

#### Método `salvarTodasDespesas`

Substitui o conteúdo do arquivo despesas.txt com a lista atualizada de despesas.

#### Método `gerenciarTiposDespesa`

Permite adicionar, atualizar, excluir e listar tipos de despesa. Oferece um submenu que controla a permanência no menu até que o usuário decida voltar ao menu principal.

#### Método `gerenciarUsuarios`

Permite adicionar novos usuários e listar os usuários existentes. Também oferece a opção de voltar ao menu principal.

#### Método `editarOuExcluirDespesa`

Permite editar ou excluir uma despesa existente. O usuário pode atualizar informações como valor e data de vencimento, ou remover uma despesa completamente.

Obs. O sistema é capaz de identificar erros, por exemplo quando uma categoria não é encontrada, informar isso ao usuário e continuar funcionando normalmente.

### Estratégia de Construção do Sistema de Controle de Despesas

#### 1. **Identificação dos Requisitos**

Primeiro, foram organizados e pensadas as funções que o sistema deveria ter, Nesse caso baseado nas informações do enunciado do trabalho, ele permiti que o usuário:
1.Adicione despesas, informando detalhes como descrição, valor e data.
2.Registre pagamentos para essas despesas.
3.Liste despesas que estão abertas (não pagas) e aquelas que já foram pagas.
4.Gerencie tipos de despesas, como transporte e alimentação.
5.Adicione e gerencie usuários que poderão acessar o sistema.

#### 2. **Estrutura do Sistema**

Para organizar essas funcionalidades, dividimos o sistema em diferentes partes, as classes sendo que cada uma tem um papel específico, mesmo estando todas em um mesmo arquivo:

**Classe Despesa**: Essa é a classe principal que representa uma despesa, ela é a classe generica ou abstrata. Temos subclasses que herdam dela como Transporte, Alimentacao e etc, que definem tipos específicos de despesas. Cada tipo tem características próprias.

**Classe Usuario**: Essa classe cuida das informações dos usuários, como login e senha. As senhas são armazenadas de forma criptografada para aumentar a segurança.

**Classe TipoDespesa**: É responsavel por Gerenciar os diferentes tipos de despesas. Aqui, podemos adicionar, atualizar ou excluir tipos de despesas.

**Classe SistemaControleDespesas**: Esta é a classe principal que permite ao usuário interagir com o sistema. Ela apresenta um menu com opções, como "Adicionar Despesa", "Listar Despesas", dentre outras opções, que dentro delas ainda possuem submenus que incrementam e possibilitam o uso de funcionalidades complementares de forma organizada.

#### 3. **Armazenamento dos Dados**

Os dados são armazenados em arquivos de texto os aquivos são:
**Despesas**: Armazena todas as despesas registradas.
**Usuários**: Guarda os dados dos usuários do sistema.
**Tipos de Despesas**: Lista os tipos de despesas incluidas pelo usuário.
**Pagamentos**: Registra os pagamentos feitos.

Esses arquivos facilitam a leitura e a escrita de informações, para as consultas dos usuários.

#### 4. **Segurança das Senhas**

Para proteger os dados dos usuários, as senhas são criptografadas usandos uma técnica simples que inverte a string da senha.

#### 5. **Interação com o Usuário**

A interface do sistema é baseada em texto, onde o usuário vê um menu no console, ele pode realizar a escolha das opções digitando números para acessar as funcionalidades.

#### 6. **Tratamento de Erros**

O sistema foi projetado para lidar com possíveis erros, como problemas ao ler ou escrever arquivos. Se algo der errado, ele informa o usuário sobre o que aconteceu.
