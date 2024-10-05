

public class Funcionario extends Usuario {
    private String cargo;

    public Funcionario(String nome, int id, String cargo) {
        super(nome, id);
        this.cargo = cargo;
    }

    // Método para adicionar livro à biblioteca
    public void adicionarLivro(Biblioteca biblioteca, Livro livro) {
        biblioteca.adicionarLivro(livro);
        System.out.println("Livro adicionado à biblioteca pelo " + cargo + ": " + livro.getTitulo());
    }

    // Método para remover livro da biblioteca
    public void removerLivro(Biblioteca biblioteca, Livro livro) {
        biblioteca.removerLivro(livro);  // Agora, esse método existe e funciona corretamente
        System.out.println("Livro removido da biblioteca pelo " + cargo + ": " + livro.getTitulo());
    }

    // Getter para o cargo
    public String getCargo() {
        return cargo;
    }
}
