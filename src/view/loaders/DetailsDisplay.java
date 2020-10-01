package view.loaders;

import controller.DetailWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Dam;
import java.io.IOException;

public class DetailsDisplay {

    public void showAndWait(Dam dam){
        FXMLLoader loader = new FXMLLoader();
        try {
            Pane pane = loader.load(getClass().getResource("../fxml/FXMLDetailWindow.fxml").openStream());
            DetailWindowController ctrl = loader.getController();
            ctrl.init(dam);
            Stage stage = new Stage();
            stage.setScene(new Scene(pane));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
