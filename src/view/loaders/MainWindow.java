package view.loaders;

import controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Observer;

public class MainWindow extends Application {
    private  static MainWindowController ctrl;
    public static void main(String[] args) {
        launch(args);
    }

    public static Observer getController() {
        return ctrl;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        try{
            Pane pane = loader.load(getClass().getResource("../fxml/FXMLMainWindow.fxml").openStream());
            ctrl = loader.getController();
            ctrl.fillTable();
            stage.setScene(new Scene(pane));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

