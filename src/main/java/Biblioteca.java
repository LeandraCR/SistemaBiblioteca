import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Biblioteca {

    // Lista de livros que será carregada a partir do banco de dados
    private ArrayList<Livro> livros = new ArrayList<>();

    // Método para adicionar um livro ao banco de dados
    public void adicionarLivro(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, isbn, emprestado) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setString(3, livro.getIsbn());
            pstmt.setBoolean(4, livro.isEmprestado());
            pstmt.executeUpdate();

            System.out.println("Livro adicionado ao banco de dados.");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }

    // Método para remover um livro do banco de dados
    public void removerLivro(Livro livro) {
        String sql = "DELETE FROM livros WHERE titulo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getTitulo());
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Livro removido do banco de dados.");
            } else {
                System.out.println("Livro não encontrado no banco de dados.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover livro: " + e.getMessage());
        }
    }

    // Método para carregar os livros do banco de dados
    public void carregarLivros() {
        String sql = "SELECT * FROM livros";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            livros.clear();  // Limpa a lista antes de carregar novos dados

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String isbn = rs.getString("isbn");
                boolean emprestado = rs.getBoolean("emprestado");

                Livro livro = new Livro(titulo, autor, isbn);
                if (emprestado) {
                    livro.emprestarLivro();
                }
                livros.add(livro);
            }

            System.out.println("Livros carregados do banco de dados.");
        } catch (SQLException e) {
            System.out.println("Erro ao carregar livros: " + e.getMessage());
        }
    }

    // Método para emprestar um livro
    public void emprestarLivro(String titulo) {
        String sql = "UPDATE livros SET emprestado = ? WHERE titulo = ? AND emprestado = false";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, true);  // Atualiza o status para emprestado
            pstmt.setString(2, titulo);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("O livro foi emprestado com sucesso.");
            } else {
                System.out.println("Livro não encontrado ou já está emprestado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao emprestar livro: " + e.getMessage());
        }
    }

    // Método para devolver um livro
    public void devolverLivro(String titulo) {
        String sql = "UPDATE livros SET emprestado = ? WHERE titulo = ? AND emprestado = true";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, false);  // Atualiza o status para disponível
            pstmt.setString(2, titulo);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("O livro foi devolvido com sucesso.");
            } else {
                System.out.println("Livro não encontrado ou não estava emprestado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao devolver livro: " + e.getMessage());
        }
    }

    // Relatório de livros disponíveis
    public void relatorioLivrosDisponiveis() {
        String sql = "SELECT * FROM livros WHERE emprestado = false";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Livros disponíveis:");

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String isbn = rs.getString("isbn");

                System.out.println("Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao gerar relatório de livros disponíveis: " + e.getMessage());
        }
    }

    // Relatório de livros emprestados
    public void relatorioLivrosEmprestados() {
        String sql = "SELECT * FROM livros WHERE emprestado = true";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Livros emprestados:");

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String isbn = rs.getString("isbn");

                System.out.println("Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao gerar relatório de livros emprestados: " + e.getMessage());
        }
    }
}
