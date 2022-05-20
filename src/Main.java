import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlInlogPagina = "/userinterface/test.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlInlogPagina));
        Parent root = loader.load();

//        stage.getIcons().add(new Image("sorting_hat.png"));
//        stage.setTitle("The Sorting Hat");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
