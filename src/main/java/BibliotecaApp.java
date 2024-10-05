import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BibliotecaApp extends Application {

    private Biblioteca biblioteca = new Biblioteca();

    @Override
    public void start(Stage primaryStage) {
        // Título da janela
        primaryStage.setTitle("Sistema de Gestão de Biblioteca");

        // Criar os campos de texto para inserir dados
        TextField tituloField = new TextField();
        tituloField.setPromptText("Digite o título do livro");

        TextField autorField = new TextField();
        autorField.setPromptText("Digite o autor do livro");

        TextField isbnField = new TextField();
        isbnField.setPromptText("Digite o ISBN do livro");

        // Criando os botões para as opções
        Button adicionarLivroBtn = new Button("Adicionar Livro");
        Button emprestarLivroBtn = new Button("Emprestar Livro");
        Button devolverLivroBtn = new Button("Devolver Livro");
        Button mostrarLivrosDisponiveisBtn = new Button("Mostrar Livros Disponíveis");
        Button mostrarLivrosEmprestadosBtn = new Button("Mostrar Livros Emprestados");

        // Ações para cada botão

        // Adicionar Livro
        adicionarLivroBtn.setOnAction(e -> {
            String titulo = tituloField.getText();
            String autor = autorField.getText();
            String isbn = isbnField.getText();

            if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty()) {
                mostrarAlerta("Erro", "Todos os campos devem ser preenchidos!", AlertType.ERROR);
            } else {
                Livro novoLivro = new Livro(titulo, autor, isbn);
                biblioteca.adicionarLivro(novoLivro);
                mostrarAlerta("Sucesso", "Livro adicionado com sucesso!", AlertType.INFORMATION);
                limparCampos(tituloField, autorField, isbnField);
            }
        });

        // Emprestar Livro
        emprestarLivroBtn.setOnAction(e -> {
            String titulo = tituloField.getText();
            if (titulo.isEmpty()) {
                mostrarAlerta("Erro", "Digite o título do livro para emprestar!", AlertType.ERROR);
            } else {
                biblioteca.emprestarLivro(titulo);
                limparCampos(tituloField, autorField, isbnField);
            }
        });

        // Devolver Livro
        devolverLivroBtn.setOnAction(e -> {
            String titulo = tituloField.getText();
            if (titulo.isEmpty()) {
                mostrarAlerta("Erro", "Digite o título do livro para devolver!", AlertType.ERROR);
            } else {
                biblioteca.devolverLivro(titulo);
                limparCampos(tituloField, autorField, isbnField);
            }
        });

        // Mostrar Relatório de Livros Disponíveis
        mostrarLivrosDisponiveisBtn.setOnAction(e -> {
            biblioteca.relatorioLivrosDisponiveis();
        });

        // Mostrar Relatório de Livros Emprestados
        mostrarLivrosEmprestadosBtn.setOnAction(e -> {
            biblioteca.relatorioLivrosEmprestados();
        });

        // Criar um layout vertical (VBox) e adicionar os botões e campos de texto
        VBox layout = new VBox(10); // Espaçamento de 10 pixels entre os componentes
        layout.getChildren().addAll(tituloField, autorField, isbnField, adicionarLivroBtn, emprestarLivroBtn, 
                                    devolverLivroBtn, mostrarLivrosDisponiveisBtn, mostrarLivrosEmprestadosBtn);

        // Criar a cena e definir o layout
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);

        // Exibir a janela
        primaryStage.show();
    }

    // Método para limpar os campos de texto após a ação
    private void limparCampos(TextField titulo, TextField autor, TextField isbn) {
        titulo.clear();
        autor.clear();
        isbn.clear();
    }

    // Método para exibir uma caixa de alerta
    private void mostrarAlerta(String titulo, String mensagem, AlertType tipoAlerta) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
