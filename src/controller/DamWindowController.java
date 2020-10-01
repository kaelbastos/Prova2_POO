package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DamWindowController {

    @FXML private TextField nameInput;
    @FXML private TextField cityInput;
    @FXML private Button cancelButton;

    public void addDam(){
        if(!nameInput.getText().isEmpty() && !cityInput.getText().isEmpty()){
            MainWindowController.addDam(nameInput.getText(), cityInput.getText());
            clearText();
        }
    }

    public void removeDam(){
        if(!nameInput.getText().isEmpty() && !cityInput.getText().isEmpty()){
            MainWindowController.removeDam(nameInput.getText(), cityInput.getText());
            clearText();
        }
    }

    private void clearText(){
        nameInput.clear();
        cityInput.clear();
    }

    public void close(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
