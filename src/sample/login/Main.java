package sample.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = (Parent) loader.load();
        Controller controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setTitle("Авторизация");
        Scene scene = new Scene(root, 290, 265);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
