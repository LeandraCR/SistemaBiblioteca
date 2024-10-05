
public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private boolean emprestado;

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.emprestado = false;
    }

    public void emprestarLivro() {
        if (!emprestado) {
            emprestado = true;
            System.out.println("O livro " + titulo + " foi emprestado.");
        } else {
            System.out.println("O livro já está emprestado.");
        }
    }

    public void devolverLivro() {
        if (emprestado) {
            emprestado = false;
            System.out.println("O livro " + titulo + " foi devolvido.");
        } else {
            System.out.println("O livro não estava emprestado.");
        }
    }

    public void exibirDetalhes() {
        System.out.println("Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn);
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    // Getter para o atributo título
    public String getTitulo() {
        return titulo;
    }

    // Getter para o atributo autor
    public String getAutor() {
        return autor;
    }

    // Getter para o atributo ISBN
    public String getIsbn() {
        return isbn;
    }
}
