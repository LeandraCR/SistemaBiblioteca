
import java.util.ArrayList;

public class Usuario {
    private String nome;
    private int id;
    private ArrayList<Livro> livrosEmprestados;

    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
        this.livrosEmprestados = new ArrayList<>();
    }

    // Método para emprestar livro
    public void emprestarLivro(Livro livro) {
        if (livrosEmprestados.size() < 3 && !livro.isEmprestado()) {
            livrosEmprestados.add(livro);
            livro.emprestarLivro();
            System.out.println("Livro emprestado pelo usuário (ID: " + id + "): " + nome);
        } else {
            System.out.println("Limite de empréstimos atingido ou livro já emprestado.");
        }
    }

    // Método para devolver livro
    public void devolverLivro(Livro livro) {
        if (livrosEmprestados.contains(livro)) {
            livrosEmprestados.remove(livro);
            livro.devolverLivro();
            System.out.println("Livro devolvido pelo usuário (ID: " + id + "): " + nome);
        } else {
            System.out.println("O livro não está emprestado por este usuário.");
        }
    }

    // Exibe o histórico de livros emprestados
    public void exibirHistorico() {
        System.out.println("Histórico de livros emprestados pelo usuário (ID: " + id + "): " + nome);
        for (Livro livro : livrosEmprestados) {
            livro.exibirDetalhes();
        }
    }

    // Getter para id
    public int getId() {
        return id;
    }
}
