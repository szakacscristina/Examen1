package UI.JavaFXConsole;

import Domain.Medicine;
import Service.MedicineService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicineController {
    public TableView tblMedicine;
    public TableColumn colMedicine;
    public TableColumn colMedicineName;
    public TableColumn colMedicineFirstName;
    public TableColumn colMedicineProducer;
    public TableColumn colMedicinePrice;
    public TableColumn colMedicineRecipe;
    public TextField txtMedicineId;
    public TextField txtMedicineName;
    public TextField txtMedicineFirstName;
    public TextField txtMedicineProducer;
    public TextField txtMedicinePrice;
    public CheckBox chkMedicineRecipe;
    public Button btnRemoveMedicine;
    public Button btnGetAllMedicine;
    public Button btnMedicineSearch;
    public TextField txtMedicineSearch;
//    public Button btnMedicineSort;
    public Button btnMedicineExpensive;
    public TextField txtMedicineExpensive;
    public Button btnUndoMedicine;
    public Button btnRedoMedicine;
    public Button btnAddMedicine;
    public Button btnUpdateMedicine;
    public TextField txtMedicinePercent;


    private MedicineService medicineService;
    private ObservableList<Medicine> medicine = FXCollections.observableArrayList();

    public void setMedicineService(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            medicine.addAll(medicineService.getAll());
            tblMedicine.setItems(medicine);
        });

    }

    /**
     * Add a medicine
     * @param actionEvent add a medicine
     */
    public void btnAddMedicineClick(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(txtMedicineId.getText());
            String name = txtMedicineName.getText();
            String firstName = txtMedicineFirstName.getText();
            String producer = txtMedicineProducer.getText();
            double price = Double.parseDouble(txtMedicinePrice.getText());
            boolean recipe = chkMedicineRecipe.isSelected();

            medicineService.add(id, name, firstName, producer, price, recipe);
            medicine.clear();
            medicine.addAll(medicineService.getAll());
            txtMedicineId.clear();
            txtMedicineName.clear();
            txtMedicineFirstName.clear();
            txtMedicineProducer.clear();
            txtMedicinePrice.clear();
            chkMedicineRecipe.setSelected(false);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    /**
     * Update a medicine
     * @param actionEvent update a medicine
     */
    public void btnUpdateMedicineClick(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(txtMedicineId.getText());
            String name = txtMedicineName.getText();
            String firstName = txtMedicineFirstName.getText();
            String producer = txtMedicineProducer.getText();
            double price = Double.parseDouble(txtMedicinePrice.getText());
            boolean recipe = chkMedicineRecipe.isSelected();

            medicineService.update(id, name, firstName, producer, price, recipe);
            medicine.clear();
            medicine.addAll(medicineService.getAll());
            txtMedicineId.clear();
            txtMedicineName.clear();
            txtMedicineFirstName.clear();
            txtMedicineProducer.clear();
            txtMedicinePrice.clear();
            chkMedicineRecipe.setSelected(false);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    /**
     * Remove a medicine
     * @param actionEvent remove a medicine
     */
    public void btnRemoveMedicineClick(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(txtMedicineId.getText());
            medicineService.delete(id);
            medicine.clear();
            medicine.addAll(medicineService.getAll());
            txtMedicineId.clear();
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    /**
     * Show all medicines
     * @param actionEvent show all medicines
     */
    public void btnGetAllMedicinesClick(ActionEvent actionEvent) {
        try {
            medicineService.getAll();
            medicine.clear();
            medicine.addAll(medicineService.getAll());
            txtMedicineId.clear();
            txtMedicineName.clear();
            txtMedicineFirstName.clear();
            txtMedicineProducer.clear();
            txtMedicinePrice.clear();
            chkMedicineRecipe.setSelected(false);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    /**
     * Search a medicine
     * @param actionEvent search a medicine
     */
    public void btnSearchMedicineClick(ActionEvent actionEvent) {
        try {
            String option = txtMedicineSearch.getText();
            List<Medicine> foundMedicines = medicineService.searchMedicine(option);
            medicine.clear();
            txtMedicineSearch.clear();
            medicine.addAll(foundMedicines);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

//    /**
//     * Sort medicines after sales
//     * @param actionEvent sort medicines after sales
//     */
//    public void btnSortMedicinesClick(ActionEvent actionEvent) {
//        try {
//            List<Medicine> sortedMedicines = medicineService.sortMedicineBySales();
//            medicine.clear();
//            medicine.addAll(sortedMedicines);
//        } catch (RuntimeException rex) {
//            Common.showValidationError(rex.getMessage());
//        }
//    }

    /**
     * Increase the medicine price with a given percent
     * @param actionEvent increase the medicine price with a given percent
     */
    public void btnIncreaseMedicinesClick(ActionEvent actionEvent) {
        try {
            double option = Double.parseDouble(txtMedicineExpensive.getText());
            int option1 = Integer.parseInt(txtMedicinePercent.getText());
            List<Medicine> medicinesUpdated = medicineService.medicinesExpensive(option, option1);
            medicine.clear();
            medicine.addAll(medicinesUpdated);
            txtMedicineExpensive.clear();
            txtMedicinePercent.clear();
        } catch (RuntimeException rex){
            Common.showValidationError(rex.getMessage());
        }
    }

    /**
     * Undo an operation
     * @param actionEvent undo an operation
     */
    public void btnUndoMedicineClick(ActionEvent actionEvent) {
        medicineService.undo();
        medicine.clear();
        medicine.addAll(medicineService.getAll());
    }

    /**
     * Redo an operation
     * @param actionEvent redo an operation
     */
    public void btnRedoMedicineClick(ActionEvent actionEvent) {
        medicineService.redo();
        medicine.clear();
        medicine.addAll(medicineService.getAll());
    }

}
