import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

interface Pagavel {
    void pagar(double valor);
}

abstract class Despesa implements Pagavel {
    protected String descricao;
    protected double valor;
    protected Date dataVencimento;
    protected String categoria;
    protected boolean paga;

    public Despesa(String descricao, double valor, Date dataVencimento, String categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.categoria = categoria;
        this.paga = false;
    }

    public boolean isPaga() {
        return paga;
    }

    @Override
    public void pagar(double valor) {
        this.paga = true;
        System.out.println("Despesa paga com valor: " + valor);
    }

    public abstract void exibirDetalhes();

    @Override
    public String toString() {
        return descricao + ";" + valor + ";" + SistemaControleDespesas.Formato_Data.format(dataVencimento) + ";" + categoria + ";" + paga;
    }
}

class Transporte extends Despesa {
    public Transporte(String descricao, double valor, Date dataVencimento) {
        super(descricao, valor, dataVencimento, "Transporte");
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Transporte: " + descricao + ", Valor: " + valor + ", Data de Vencimento: " + dataVencimento);
    }
}

class Alimentacao extends Despesa {
    public Alimentacao(String descricao, double valor, Date dataVencimento) {
        super(descricao, valor, dataVencimento, "Alimentacao");
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Alimentação: " + descricao + ", Valor: " + valor + ", Data de Vencimento: " + dataVencimento);
    }
}

class Saude extends Despesa {
    public Saude(String descricao, double valor, Date dataVencimento) {
        super(descricao, valor, dataVencimento, "Saude");
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Saúde: " + descricao + ", Valor: " + valor + ", Data de Vencimento: " + dataVencimento);
    }
}

class Lazer extends Despesa {
    public Lazer(String descricao, double valor, Date dataVencimento) {
        super(descricao, valor, dataVencimento, "Lazer");
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Lazer: " + descricao + ", Valor: " + valor + ", Data de Vencimento: " + dataVencimento);
    }
}

class Moradia extends Despesa {
    public Moradia(String descricao, double valor, Date dataVencimento) {
        super(descricao, valor, dataVencimento, "Moradia");
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Moradia: " + descricao + ", Valor: " + valor + ", Data de Vencimento: " + dataVencimento);
    }
}

class Usuario {
    private String login;
    private String senhaCriptografada;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senhaCriptografada = criptografarSenha(senha);
    }

    private String criptografarSenha(String senha) {
        return new StringBuilder(senha).reverse().toString();
    }

    public String getLogin() {
        return login;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }

    public static void salvarUsuario(Usuario usuario, String caminhoArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            writer.write(usuario.getLogin() + ";" + usuario.getSenhaCriptografada());
            writer.newLine();
        }
    }

    public static ArrayList<Usuario> listarUsuarios(String caminhoArquivo) throws IOException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 2) {
                    usuarios.add(new Usuario(dados[0], dados[1]));
                }
            }
        }
        return usuarios;
    }
}

class TipoDespesa {
    private String nome;

    public TipoDespesa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static void salvarTipoDespesa(TipoDespesa tipo, String caminhoArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            writer.write(tipo.getNome());
            writer.newLine();
        }
    }

    public static ArrayList<TipoDespesa> listarTipos(String caminhoArquivo) throws IOException {
        ArrayList<TipoDespesa> tipos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                tipos.add(new TipoDespesa(linha));
            }
        }
        return tipos;
    }

    public static void atualizarTipoDespesa(ArrayList<TipoDespesa> tipos, String nomeAntigo, String nomeNovo, String caminhoArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (TipoDespesa tipo : tipos) {
                if (tipo.getNome().equals(nomeAntigo)) {
                    writer.write(nomeNovo);
                } else {
                    writer.write(tipo.getNome());
                }
                writer.newLine();
            }
        }
    }

    public static void excluirTipoDespesa(ArrayList<TipoDespesa> tipos, String nomeParaExcluir, String caminhoArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (TipoDespesa tipo : tipos) {
                if (!tipo.getNome().equals(nomeParaExcluir)) {
                    writer.write(tipo.getNome());
                    writer.newLine();
                }
            }
        }
    }
}

public class SistemaControleDespesas {
    private static final String ARQUIVO_DESPESAS = "despesas.txt";
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";
    private static final String ARQUIVO_TIPOS = "tipos_despesa.txt";
    private static final String ARQUIVO_PAGAMENTOS = "pagamentos.txt"; 
    public static final SimpleDateFormat Formato_Data = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("Menu Principal:");
            System.out.println("1. Entrar Despesa");
            System.out.println("2. Anotar Pagamento");
            System.out.println("3. Listar Despesas em Aberto");
            System.out.println("4. Listar Despesas Pagas");
            System.out.println("5. Gerenciar Tipos de Despesa");
            System.out.println("6. Gerenciar Usuários");
            System.out.println("7. Editar ou Excluir Despesa");
            System.out.println("8. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    criarDespesa(scanner);
                    break;
                case 2:
                    anotarPagamento(scanner);
                    break;
                case 3:
                    listarDespesas(false);
                    break;
                case 4:
                    listarDespesas(true);
                    break;
                case 5:
                    gerenciarTiposDespesa(scanner);
                    break;
                case 6:
                    gerenciarUsuarios(scanner);
                    break;
                case 7:
                    editarOuExcluirDespesa(scanner);
                    break;
                case 8:
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    private static void criarDespesa(Scanner scanner) {
        try {
            System.out.println("Descrição da despesa:");
            String descricao = scanner.nextLine();
            System.out.println("Valor:");
            double valor = scanner.nextDouble();
            System.out.println("Data de Vencimento (dd/MM/yyyy):");
            scanner.nextLine();  
            String dataVencimentoStr = scanner.nextLine();
            Date dataVencimento = Formato_Data.parse(dataVencimentoStr);
            System.out.println("Categoria: (Transporte, Lazer, Moradia, Alimentacao, Saude)");
            String categoria = scanner.nextLine();

            Despesa despesa = criarDespesaPorCategoria(descricao, valor, dataVencimento, categoria);
            if (despesa != null) {
                salvarDespesa(despesa);
            } else {
                System.out.println("Categoria inválida.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar despesa: " + e.getMessage());
        }
    }

    private static Despesa criarDespesaPorCategoria(String descricao, double valor, Date dataVencimento, String categoria) {
        switch (categoria) {
            case "Transporte":
                return new Transporte(descricao, valor, dataVencimento);
            case "Alimentacao":
                return new Alimentacao(descricao, valor, dataVencimento);
            case "Saude":
                return new Saude(descricao, valor, dataVencimento);
            case "Lazer":
                return new Lazer(descricao, valor, dataVencimento);
            case "Moradia":
                return new Moradia(descricao, valor, dataVencimento);
            default:
                return null;
        }
    }

    private static void salvarDespesa(Despesa despesa) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DESPESAS, true))) {
            writer.write(despesa.toString());
            writer.newLine();
        }
    }

    private static void listarDespesas(boolean apenasPagas) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DESPESAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 5) {
                    String descricao = dados[0];
                    double valor = Double.parseDouble(dados[1]);
                    Date dataVencimento = Formato_Data.parse(dados[2]);
                    String categoria = dados[3];
                    boolean paga = Boolean.parseBoolean(dados[4]);
                    if (apenasPagas == paga) {
                        Despesa despesa = criarDespesaPorCategoria(descricao, valor, dataVencimento, categoria);
                        if (despesa != null) {
                            despesa.paga = paga;
                            despesa.exibirDetalhes();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar despesas: " + e.getMessage());
        }
    }

    private static void anotarPagamento(Scanner scanner) {
        try {
            ArrayList<Despesa> despesas = carregarDespesas();
            System.out.println("Informe a descrição da despesa a ser paga:");
            String descricao = scanner.nextLine();
            
            for (Despesa despesa : despesas) {
                if (despesa.descricao.equalsIgnoreCase(descricao) && !despesa.isPaga()) {
                    System.out.println("Informe o valor pago:");
                    double valorPago = scanner.nextDouble();
                    scanner.nextLine(); 
                    System.out.println("Informe a data do pagamento (dd/MM/yyyy):");
                    String dataPagamentoStr = scanner.nextLine();
                    Date dataPagamento = Formato_Data.parse(dataPagamentoStr);
                    
                    if (valorPago >= despesa.valor) {
                        despesa.pagar(valorPago);
                        registrarPagamento(despesa, valorPago, dataPagamento);
                        salvarTodasDespesas(despesas);
                    } else {
                        System.out.println("Valor pago é menor que o valor da despesa. Pagamento não registrado.");
                    }
                    return;
                }
            }
            System.out.println("Despesa não encontrada ou já está paga.");
        } catch (Exception e) {
            System.out.println("Erro ao anotar pagamento: " + e.getMessage());
        }
    }

    private static void registrarPagamento(Despesa despesa, double valorPago, Date dataPagamento) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PAGAMENTOS, true))) {
            writer.write(despesa.descricao + ";" + valorPago + ";" + Formato_Data.format(dataPagamento));
            writer.newLine();
        }
    }

    private static ArrayList<Despesa> carregarDespesas() throws IOException {
        ArrayList<Despesa> despesas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DESPESAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 5) {
                    String descricao = dados[0];
                    double valor = Double.parseDouble(dados[1]);
                    Date dataVencimento = Formato_Data.parse(dados[2]);
                    String categoria = dados[3];
                    boolean paga = Boolean.parseBoolean(dados[4]);
                    Despesa despesa = criarDespesaPorCategoria(descricao, valor, dataVencimento, categoria);
                    if (despesa != null) {
                        despesa.paga = paga;
                        despesas.add(despesa);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar despesas: " + e.getMessage());
        }
        return despesas;
    }

    private static void salvarTodasDespesas(ArrayList<Despesa> despesas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DESPESAS))) {
            for (Despesa despesa : despesas) {
                writer.write(despesa.toString());
                writer.newLine();
            }
        }
    }

    private static void gerenciarTiposDespesa(Scanner scanner) {
        try {
            boolean continuar = true; // Controla a permanência no menu
    
            while (continuar) {
                System.out.println("1. Adicionar Tipo");
                System.out.println("2. Atualizar Tipo");
                System.out.println("3. Excluir Tipo");
                System.out.println("4. Listar Tipos");
                System.out.println("5. Voltar ao Menu Principal"); // Opção de voltar
    
                int opcao = scanner.nextInt();
                scanner.nextLine(); 
    
                switch (opcao) {
                    case 1:
                        System.out.println("Nome do novo tipo de despesa:");
                        String novoTipo = scanner.nextLine();
                        TipoDespesa.salvarTipoDespesa(new TipoDespesa(novoTipo), ARQUIVO_TIPOS);
                        System.out.println("Tipo de despesa adicionado com sucesso.");
                        break;
                    case 2:
                        System.out.println("Nome do tipo de despesa a ser atualizado:");
                        String nomeAntigo = scanner.nextLine();
                        System.out.println("Novo nome do tipo de despesa:");
                        String nomeNovo = scanner.nextLine();
                        ArrayList<TipoDespesa> tipos = TipoDespesa.listarTipos(ARQUIVO_TIPOS);
                        TipoDespesa.atualizarTipoDespesa(tipos, nomeAntigo, nomeNovo, ARQUIVO_TIPOS);
                        System.out.println("Tipo de despesa atualizado com sucesso.");
                        break;
                    case 3:
                        System.out.println("Nome do tipo de despesa a ser excluído:");
                        String nomeParaExcluir = scanner.nextLine();
                        tipos = TipoDespesa.listarTipos(ARQUIVO_TIPOS);
                        TipoDespesa.excluirTipoDespesa(tipos, nomeParaExcluir, ARQUIVO_TIPOS);
                        System.out.println("Tipo de despesa excluído com sucesso.");
                        break;
                    case 4:
                        System.out.println("Tipos de despesa cadastrados:");
                        tipos = TipoDespesa.listarTipos(ARQUIVO_TIPOS);
                        for (TipoDespesa tipo : tipos) {
                            System.out.println("- " + tipo.getNome());
                        }
                        break;
                    case 5:
                        continuar = false;
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao gerenciar tipos de despesa: " + e.getMessage());
        }
    }
    
    private static void gerenciarUsuarios(Scanner scanner) {
        try {
            boolean continuar = true; 
    
            while (continuar) {
                System.out.println("1. Adicionar Usuário");
                System.out.println("2. Listar Usuários");
                System.out.println("3. Voltar ao Menu Principal");
    
                int opcao = scanner.nextInt();
                scanner.nextLine();  
    
                switch (opcao) {
                    case 1:
                        System.out.println("Login:");
                        String login = scanner.nextLine();
                        System.out.println("Senha:");
                        String senha = scanner.nextLine();
                        Usuario.salvarUsuario(new Usuario(login, senha), ARQUIVO_USUARIOS);
                        System.out.println("Usuário adicionado com sucesso.");
                        break;
                    case 2:
                        ArrayList<Usuario> usuarios = Usuario.listarUsuarios(ARQUIVO_USUARIOS);
                        System.out.println("Usuários cadastrados:");
                        for (Usuario usuario : usuarios) {
                            System.out.println("Login: " + usuario.getLogin());
                        }
                        break;
                    case 3:
                        continuar = false;
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao gerenciar usuários: " + e.getMessage());
        }
    }
    

    private static void editarOuExcluirDespesa(Scanner scanner) {
        try {
            ArrayList<Despesa> despesas = carregarDespesas();
            boolean continuar = true; 
    
            while (continuar) {
                System.out.println("1. Editar Despesa");
                System.out.println("2. Excluir Despesa");
                System.out.println("3. Voltar ao Menu Principal"); 
                int opcao = scanner.nextInt();
                scanner.nextLine();  
    
                switch (opcao) {
                    case 1:
                        System.out.println("Descrição da despesa a ser editada:");
                        String descricaoAntiga = scanner.nextLine();
                        boolean despesaEncontrada = false;
                        for (Despesa despesa : despesas) {
                            if (despesa.descricao.equalsIgnoreCase(descricaoAntiga)) {
                                System.out.println("Novo valor:");
                                double novoValor = scanner.nextDouble();
                                scanner.nextLine();  
                                System.out.println("Nova data de vencimento (dd/MM/yyyy):");
                                String novaDataVencimentoStr = scanner.nextLine();
                                Date novaDataVencimento = Formato_Data.parse(novaDataVencimentoStr);
                                despesa.valor = novoValor;
                                despesa.dataVencimento = novaDataVencimento;
                                salvarTodasDespesas(despesas);
                                System.out.println("Despesa atualizada com sucesso.");
                                despesaEncontrada = true;
                                break;
                            }
                        }
                        if (!despesaEncontrada) {
                            System.out.println("Despesa não encontrada.");
                        }
                        break;
                    case 2:
                        System.out.println("Descrição da despesa a ser excluída:");
                        String descricaoParaExcluir = scanner.nextLine();
                        boolean removido = despesas.removeIf(d -> d.descricao.equalsIgnoreCase(descricaoParaExcluir));
                        if (removido) {
                            salvarTodasDespesas(despesas);
                            System.out.println("Despesa excluída com sucesso.");
                        } else {
                            System.out.println("Despesa não encontrada.");
                        }
                        break;
                    case 3:
                        continuar = false; 
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao editar ou excluir despesa: " + e.getMessage());
        }
    }    
}