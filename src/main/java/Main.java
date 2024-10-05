

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Testar a conexão com o banco de dados
        DatabaseConnection.getConnection(); // Adicione esta linha para testar a conexão

        // Carregar livros do banco de dados ao iniciar o programa
        biblioteca.carregarLivros();  // Certifique-se de que este método está implementado corretamente na classe Biblioteca

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("====== Sistema de Gestão de Biblioteca ======");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Emprestar Livro");
            System.out.println("3. Devolver Livro");
            System.out.println("4. Mostrar Relatório de Livros Disponíveis");
            System.out.println("5. Mostrar Relatório de Livros Emprestados");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Digite o ISBN do livro: ");
                    String isbn = scanner.nextLine();
                    Livro novoLivro = new Livro(titulo, autor, isbn);
                    biblioteca.adicionarLivro(novoLivro);
                    break;

                case 2:
                    System.out.print("Digite o título do livro para emprestar: ");
                    String tituloEmprestimo = scanner.nextLine();
                    biblioteca.emprestarLivro(tituloEmprestimo);
                    break;

                case 3:
                    System.out.print("Digite o título do livro para devolver: ");
                    String tituloDevolucao = scanner.nextLine();
                    biblioteca.devolverLivro(tituloDevolucao);
                    break;

                case 4:
                    biblioteca.relatorioLivrosDisponiveis();
                    break;

                case 5:
                    biblioteca.relatorioLivrosEmprestados();
                    break;

                case 6:
                    System.out.println("Saindo do sistema...");
                    running = false;
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }

        scanner.close();
    }
}
