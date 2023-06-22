package br.com.agenda.aplicacao;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import br.com.agenda.dao.*;
import br.com.agenda.model.*;
import java.util.Scanner;

//MVC
/*
 * Model
 * View
 * Controller
 */

public class Main {
  private static List<Pessoa> pessoas = new ArrayList<>();
  private static List<Contato> contatos = new ArrayList<>();
  private static List<Endereco> enderecos = new ArrayList<>();

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("\n========== MENU ==========");
      System.out.println("1. Cadastrar Pessoa");
      System.out.println("2. Visualizar Pessoa");
      System.out.println("3. Atualizar Pessoa");
      System.out.println("4. Deletar Pessoa");
      System.out.println("5. Cadastrar Contato");
      System.out.println("6. Visualizar Contato");
      System.out.println("7. Atualizar Contato");
      System.out.println("8. Deletar Contato");
      System.out.println("9. Cadastrar Endereço");
      System.out.println("10. Visualizar Endereço");
      System.out.println("11. Atualizar Endereço");
      System.out.println("12. Deletar Endereço");
      System.out.println("13. Visualizar todas as pessoas");
      System.out.println("14. Visualizar todos os contatos");
      System.out.println("15. Visualizar todos os endereços");
      System.out.println("0. Sair");
      System.out.print("Escolha uma opção: ");

      int opcao = scanner.nextInt();
      scanner.nextLine(); // Limpa o buffer do scanner

      switch (opcao) {
        case 1:
          cadastrarPessoa(scanner);
          break;
        case 2:
          visualizarPessoa(scanner);
          break;
        case 3:
          atualizarPessoa(scanner);
          break;
        case 4:
          deletarPessoa(scanner);
          break;
        case 5:
          cadastrarContato(scanner);
          break;
        case 6:
          visualizarContato(scanner);
          break;
        case 7:
          atualizarContato(scanner);
          break;
        case 8:
          deletarContato(scanner);
          break;
        case 9:
          cadastrarEndereco(scanner);
          break;
        case 10:
          visualizarEndereco(scanner);
          break;
        case 11:
          atualizarEndereco(scanner);
          break;
        case 12:
          deletarEndereco(scanner);
          break;
        case 13:
          visualizarPessoas();
          break;
        case 14:
          visualizarContatos();
          break;
        case 15:
          visualizarEnderecos();
          break;
        case 0:
          System.out.println("Encerrando o programa...");
          return;
        default:
          System.out.println("Opção inválida. Tente novamente.");
      }
    }
  }

  private static void cadastrarPessoa(Scanner scanner) {
    System.out.print("Nome: ");
    String nome = scanner.nextLine();
    System.out.print("Data de Nascimento: ");
    String dataNascimento = scanner.nextLine();
    System.out.print("CPF: ");
    String cpf = scanner.nextLine();
    System.out.print("RG: ");
    String rg = scanner.nextLine();

    Pessoa pessoa = new Pessoa();
    pessoa.setNome(nome);
    pessoa.setDataNascimento(parseData(dataNascimento));
    pessoa.setCPF(cpf);
    pessoa.setRG(rg);

    pessoas.add(pessoa);

    PessoaDAO pessoaDAO = new PessoaDAO();
    pessoaDAO.save(pessoa);

    System.out.println("Pessoa cadastrada com sucesso!");
  }

  private static void visualizarPessoa(Scanner scanner) {
    System.out.print("Nome da Pessoa: ");
    String nome = scanner.nextLine();

    PessoaDAO pessoaDAO = new PessoaDAO();

    pessoas = pessoaDAO.getPessoas();

    for (Pessoa pessoa : pessoas) {
      if (pessoa.getNome().equalsIgnoreCase(nome)) {
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Data de Nascimento: " + pessoa.getDataNascimento());
        System.out.println("CPF: " + pessoa.getCPF());
        System.out.println("RG: " + pessoa.getRG());
        return;
      }
    }

    System.out.println("Pessoa não encontrada.");
  }

  private static void visualizarPessoas() {

    PessoaDAO pessoaDAO = new PessoaDAO();

    pessoas = pessoaDAO.getPessoas();

    for (Pessoa pessoa : pessoas) {
      System.out.println("Nome: " + pessoa.getNome());
      System.out.println("Data de Nascimento: " + pessoa.getDataNascimento());
      System.out.println("CPF: " + pessoa.getCPF());
      System.out.println("RG: " + pessoa.getRG());
      return;
    }
  }

  private static void atualizarPessoa(Scanner scanner) {
    System.out.print("Nome da Pessoa: ");
    String nome = scanner.nextLine();

    PessoaDAO pessoaDAO = new PessoaDAO();

    pessoas = pessoaDAO.getPessoas();

    for (Pessoa pessoa : pessoas) {
      if (pessoa.getNome().equalsIgnoreCase(nome)) {
        System.out.print("Novo Nome: ");
        String novoNome = scanner.nextLine();
        System.out.print("Nova Data de Nascimento: ");
        String novaDataNascimento = scanner.nextLine();
        System.out.print("Novo CPF: ");
        String novoCpf = scanner.nextLine();
        System.out.print("Novo RG: ");
        String novoRg = scanner.nextLine();

        pessoa.setNome(novoNome);
        pessoa.setDataNascimento(parseData(novaDataNascimento));
        pessoa.setCPF(novoCpf);
        pessoa.setRG(novoRg);

        pessoaDAO.update(pessoa);

        System.out.println("Pessoa atualizada com sucesso!");
        return;
      }
    }

    System.out.println("Pessoa não encontrada.");
  }

  private static void deletarPessoa(Scanner scanner) {
    System.out.print("Nome da Pessoa: ");
    String nome = scanner.nextLine();

    PessoaDAO pessoaDAO = new PessoaDAO();

    pessoas = pessoaDAO.getPessoas();

    for (Pessoa pessoa : pessoas) {
      if (pessoa.getNome().equalsIgnoreCase(nome)) {
        pessoas.remove(pessoa);
        pessoaDAO.deleteByID(pessoa.getId());
        System.out.println("Pessoa removida com sucesso!");
        return;
      }
    }

    System.out.println("Pessoa não encontrada.");
  }

  private static void cadastrarContato(Scanner scanner) {

    System.out.println("Cadastro de Contato");

    System.out.print("Pessoa do Contato: ");
    String pessoaNome = scanner.nextLine();

    Pessoa pessoa = existePessoa(pessoaNome);

    // Verificar se a pessoa existe (exemplo: através de uma lista de pessoas)
    if (!pessoa.getNome().equalsIgnoreCase(pessoaNome)) {
      System.out.println("Pessoa não encontrada. Por favor, preencha novamente.");
      cadastrarContato(scanner); // Chama o método novamente para permitir novo preenchimento
      return; // Retorna para evitar o cadastro do endereço caso a pessoa não seja encontrada
    }

    System.out.print("Celular 1: ");
    String celular1 = scanner.nextLine();
    System.out.print("Celular 2: ");
    String celular2 = scanner.nextLine();
    System.out.print("Telefone: ");
    String telefone = scanner.nextLine();
    System.out.print("Email: ");
    String email = scanner.nextLine();
    System.out.print("LinkedIn: ");
    String linkedin = scanner.nextLine();
    System.out.print("Instagram: ");
    String instagram = scanner.nextLine();
    System.out.print("Facebook: ");
    String facebook = scanner.nextLine();

    Contato contato = new Contato();
    contato.setCelular1(celular1);
    contato.setCelular2(celular2);
    contato.setTelefone(telefone);
    contato.setEmail(email);
    contato.setLinkedin(linkedin);
    contato.setInstagram(instagram);
    contato.setFacebook(facebook);
    contato.setPessoa(pessoa);
    contatos.add(contato);

    ContatoDAO contatoDAO = new ContatoDAO();
    contatoDAO.save(contato);

    System.out.println("Contato cadastrado com sucesso!");
  }

  private static void visualizarContato(Scanner scanner) {
    System.out.print("Email do Contato: ");
    String email = scanner.nextLine();

    ContatoDAO contatoDAO = new ContatoDAO();
    contatos = contatoDAO.getContato();

    PessoaDAO pessoaDAO = new PessoaDAO();

    for (Contato contato : contatos) {
      if (contato.getEmail().equalsIgnoreCase(email)) {
        contato.setPessoa(pessoaDAO.getPessoaById(contato.getPessoa().getId()));
        System.out.println("Pessoa do Contato: " + contato.getPessoa().getNome());
        System.out.println("Celular 1: " + contato.getCelular1());
        System.out.println("Celular 2: " + contato.getCelular2());
        System.out.println("Telefone: " + contato.getTelefone());
        System.out.println("Email: " + contato.getEmail());
        System.out.println("LinkedIn: " + contato.getLinkedin());
        System.out.println("Instagram: " + contato.getInstagram());
        System.out.println("Facebook: " + contato.getFacebook());
        return;
      }
    }

    System.out.println("Contato não encontrado.");
  }

  private static void visualizarContatos() {

    ContatoDAO contatoDAO = new ContatoDAO();
    contatos = contatoDAO.getContato();

    PessoaDAO pessoaDAO = new PessoaDAO();

    for (Contato contato : contatos) {
      contato.setPessoa(pessoaDAO.getPessoaById(contato.getPessoa().getId()));
      System.out.println("Pessoa do Contato: " + contato.getPessoa().getNome());
      System.out.println("Celular 1: " + contato.getCelular1());
      System.out.println("Celular 2: " + contato.getCelular2());
      System.out.println("Telefone: " + contato.getTelefone());
      System.out.println("Email: " + contato.getEmail());
      System.out.println("LinkedIn: " + contato.getLinkedin());
      System.out.println("Instagram: " + contato.getInstagram());
      System.out.println("Facebook: " + contato.getFacebook());
      return;
    }
  }

  private static void atualizarContato(Scanner scanner) {

    System.out.print("Pessoa do Contato: ");
    String pessoaNome = scanner.nextLine();

    // Verificar se a pessoa existe (exemplo: através de uma lista de pessoas)
    if (!existePessoa(pessoaNome).getNome().equalsIgnoreCase(pessoaNome)) {
      System.out.println("Pessoa não encontrada. Por favor, preencha novamente.");
      atualizarContato(scanner); // Chama o método novamente para permitir novo preenchimento
      return; // Retorna para evitar o cadastro do endereço caso a pessoa não seja encontrada
    }

    System.out.print("Email do Contato: ");
    String email = scanner.nextLine();

    ContatoDAO contatoDAO = new ContatoDAO();
    contatos = contatoDAO.getContato();

    for (Contato contato : contatos) {
      if (contato.getEmail().equalsIgnoreCase(email)) {
        System.out.print("Novo Celular 1: ");
        String novoCelular1 = scanner.nextLine();
        System.out.print("Novo Celular 2: ");
        String novoCelular2 = scanner.nextLine();
        System.out.print("Novo Telefone: ");
        String novoTelefone = scanner.nextLine();
        System.out.print("Novo Email: ");
        String novoEmail = scanner.nextLine();
        System.out.print("Novo LinkedIn: ");
        String novoLinkedin = scanner.nextLine();
        System.out.print("Novo Instagram: ");
        String novoInstagram = scanner.nextLine();
        System.out.print("Novo Facebook: ");
        String novoFacebook = scanner.nextLine();

        contato.setCelular1(novoCelular1);
        contato.setCelular2(novoCelular2);
        contato.setTelefone(novoTelefone);
        contato.setEmail(novoEmail);
        contato.setLinkedin(novoLinkedin);
        contato.setInstagram(novoInstagram);
        contato.setFacebook(novoFacebook);

        contatoDAO.update(contato);

        System.out.println("Contato atualizado com sucesso!");
        return;
      }
    }

    System.out.println("Contato não encontrado.");
  }

  private static void deletarContato(Scanner scanner) {
    System.out.print("Email do Contato: ");
    String email = scanner.nextLine();

    ContatoDAO contatoDAO = new ContatoDAO();
    contatos = contatoDAO.getContato();

    for (Contato contato : contatos) {
      if (contato.getEmail().equalsIgnoreCase(email)) {
        contatos.remove(contato);
        contatoDAO.deleteByID(contato.getId());
        System.out.println("Contato removido com sucesso!");
        return;
      }
    }

    System.out.println("Contato não encontrado.");
  }

  private static void cadastrarEndereco(Scanner scanner) {

    System.out.println("Cadastro de Endereço");

    System.out.print("Pessoa do Endereço: ");
    String pessoaNome = scanner.nextLine();

    Pessoa pessoa = existePessoa(pessoaNome);

    // Verificar se a pessoa existe (exemplo: através de uma lista de pessoas)
    if (!pessoa.getNome().equalsIgnoreCase(pessoaNome)) {
      System.out.println("Pessoa não encontrada. Por favor, preencha novamente.");
      cadastrarEndereco(scanner); // Chama o método novamente para permitir novo preenchimento
      return; // Retorna para evitar o cadastro do endereço caso a pessoa não seja encontrada
    }

    System.out.print("Rua: ");
    String rua = scanner.nextLine();
    System.out.print("Número: ");
    String numero = scanner.nextLine();
    System.out.print("Complemento: ");
    String complemento = scanner.nextLine();
    System.out.print("Cidade: ");
    String cidade = scanner.nextLine();
    System.out.print("Estado: ");
    String estado = scanner.nextLine();
    System.out.print("CEP: ");
    String cep = scanner.nextLine();

    Endereco endereco = new Endereco();

    // Recuperar a pessoa
    endereco.setPessoa(pessoa);
    // Recuperar a rua
    endereco.setRua(rua);
    // Recuperar o numero
    endereco.setNumero(numero);
    // Recuperar o complemento
    endereco.setComplemento(complemento);
    // Recuperar a cidade
    endereco.setCidade(cidade);
    // Recuperar o estado
    endereco.setEstado(estado);
    // Recuperar o CEP
    endereco.setCep(cep);

    enderecos.add(endereco);

    EnderecoDAO enderecoDAO = new EnderecoDAO();
    enderecoDAO.save(endereco);

    System.out.println("Endereço cadastrado com sucesso!");
  }

  private static void visualizarEndereco(Scanner scanner) {
    System.out.print("CEP do Endereço: ");
    String cep = scanner.nextLine();
    System.out.print("Número do Endereço: ");
    String numero = scanner.nextLine();

    EnderecoDAO enderecoDAO = new EnderecoDAO();
    enderecos = enderecoDAO.getEndereco();

    PessoaDAO pessoaDAO = new PessoaDAO();

    for (Endereco endereco : enderecos) {
      if (endereco.getCep().equals(cep) && endereco.getNumero().equals(numero)) {
        endereco.setPessoa(pessoaDAO.getPessoaById(endereco.getPessoa().getId()));
        System.out.println("Pessoa do Endereço: " + endereco.getPessoa().getNome());
        System.out.println("Rua: " + endereco.getRua());
        System.out.println("Número: " + endereco.getNumero());
        System.out.println("Complemento: " + endereco.getComplemento());
        System.out.println("Cidade: " + endereco.getCidade());
        System.out.println("Estado: " + endereco.getEstado());
        System.out.println("CEP: " + endereco.getCep());
        return;
      }
    }

    System.out.println("Endereço não encontrado.");
  }

  private static void visualizarEnderecos() {
    EnderecoDAO enderecoDAO = new EnderecoDAO();
    enderecos = enderecoDAO.getEndereco();

    PessoaDAO pessoaDAO = new PessoaDAO();

    for (Endereco endereco : enderecos) {
      endereco.setPessoa(pessoaDAO.getPessoaById(endereco.getPessoa().getId()));
      System.out.println("Pessoa do Endereço: " + endereco.getPessoa().getNome());
      System.out.println("Rua: " + endereco.getRua());
      System.out.println("Número: " + endereco.getNumero());
      System.out.println("Complemento: " + endereco.getComplemento());
      System.out.println("Cidade: " + endereco.getCidade());
      System.out.println("Estado: " + endereco.getEstado());
      System.out.println("CEP: " + endereco.getCep());
      return;
    }
  }

  private static void atualizarEndereco(Scanner scanner) {

    System.out.print("Pessoa do Endereço: ");
    String pessoaNome = scanner.nextLine();

    // Verificar se a pessoa existe (exemplo: através de uma lista de pessoas)
    if (!existePessoa(pessoaNome).getNome().equalsIgnoreCase(pessoaNome)) {
      System.out.println("Pessoa não encontrada. Por favor, preencha novamente.");
      atualizarEndereco(scanner); // Chama o método novamente para permitir novo preenchimento
      return; // Retorna para evitar o cadastro do endereço caso a pessoa não seja encontrada
    }

    System.out.print("CEP do Endereço: ");
    String cep = scanner.nextLine();
    System.out.print("Número do Endereço: ");
    String numero = scanner.nextLine();

    EnderecoDAO enderecoDAO = new EnderecoDAO();
    enderecos = enderecoDAO.getEndereco();

    for (Endereco endereco : enderecos) {
      if (endereco.getCep().equals(cep) && endereco.getNumero().equals(numero)) {
        System.out.print("Nova Rua: ");
        String novaRua = scanner.nextLine();
        System.out.print("Novo Número: ");
        String novoNumero = scanner.nextLine();
        System.out.print("Novo Complemento: ");
        String novoComplemento = scanner.nextLine();
        System.out.print("Nova Cidade: ");
        String novaCidade = scanner.nextLine();
        System.out.print("Novo Estado: ");
        String novoEstado = scanner.nextLine();
        System.out.print("Novo CEP: ");
        String novoCep = scanner.nextLine();

        endereco.setRua(novaRua);
        endereco.setNumero(novoNumero);
        endereco.setComplemento(novoComplemento);
        endereco.setCidade(novaCidade);
        endereco.setEstado(novoEstado);
        endereco.setCep(novoCep);

        enderecoDAO.update(endereco);

        System.out.println("Endereço atualizado com sucesso!");
        return;
      }
    }

    System.out.println("Endereço não encontrado.");
  }

  private static void deletarEndereco(Scanner scanner) {
    System.out.print("CEP do Endereço: ");
    String cep = scanner.nextLine();
    System.out.print("Número do Endereço: ");
    String numero = scanner.nextLine();

    EnderecoDAO enderecoDAO = new EnderecoDAO();
    enderecos = enderecoDAO.getEndereco();

    for (Endereco endereco : enderecos) {
      if (endereco.getCep().equals(cep) && endereco.getNumero().equals(numero)) {
        enderecos.remove(endereco);
        enderecoDAO.deleteByID(endereco.getId());
        System.out.println("Endereço removido com sucesso!");
        return;
      }
    }

    System.out.println("Endereço não encontrado.");
  }

  private static Pessoa existePessoa(String nomePessoa) {

    PessoaDAO pessoaDao = new PessoaDAO();

    pessoas = pessoaDao.getPessoas();

    for (Pessoa pessoa : pessoas) {
      if (pessoa.getNome().equalsIgnoreCase(nomePessoa)) {
        return pessoa;
      }
    }

    return new Pessoa();
  }

  private static Date parseData(String data) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try {
      return dateFormat.parse(data);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }
}
