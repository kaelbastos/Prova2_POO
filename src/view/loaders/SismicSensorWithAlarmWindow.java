package view.loaders;

import controller.SismicSensorWithAlarmWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class SismicSensorWithAlarmWindow {

    public void showAndWait(){
        FXMLLoader loader = new FXMLLoader();
        try {
            Pane pane = loader.load(getClass().getResource("../fxml/FXMLSismicSensorWithAlarmWindow.fxml").openStream());
            SismicSensorWithAlarmWindowController ctrl = loader.getController();
            ctrl.init();
            Stage stage = new Stage();
            stage.setScene(new Scene(pane));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
