package UI.JavaFXConsole;

import Domain.Rent;
import Service.RentService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class RentController {
    public TableView tblRent;
    public TableColumn colRent;
    public TableColumn colRentCarId;
    public TableColumn colRentNumberOfDays;
    public TableColumn colRentKilometers;
    public TextField txtRentId;
    public TextField txtRentCarId;
    public TextField txtRentNumberOfDays;
    public TextField txtRentKilometers;
    public Button btnRemoveRent;
    public Button btnGetAllRents;
    public Button btnRentSearch;
    public TextField txtRentSearch;
    public Button btnUndoRent;
    public Button btnRedoRent;
    public Button btnAddRent;
    public Button btnUpdateRent;



    private RentService rentService;
    private ObservableList<Rent> rent = FXCollections.observableArrayList();

    public void setRentService(RentService rentService) {
        this.rentService = rentService;
    }

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            rent.addAll(rentService.getAll());
            tblRent.setItems(rent);
        });

    }

    /**
     * Add a rent
     * @param actionEvent add a rent
     */
    public void btnAddRentClick(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(txtRentId.getText());
            String carId = txtRentCarId.getText();
            double numberOfDays = Double.parseDouble(txtRentNumberOfDays.getText());
            double kilometers = Double.parseDouble(txtRentKilometers.getText());

            rentService.add(id, carId, numberOfDays, kilometers);
            rent.clear();
            rent.addAll(rentService.getAll());
            txtRentId.clear();
            txtRentCarId.clear();
            txtRentNumberOfDays.clear();
            txtRentKilometers.clear();

        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    /**
     * Update a rent
     * @param actionEvent update a rent
     */
    public void btnUpdateRentClick(ActionEvent actionEvent) {
            try {
                int id = Integer.parseInt(txtRentId.getText());
                String carId = txtRentCarId.getText();
                double numberOfDays = Double.parseDouble(txtRentNumberOfDays.getText());
                double kilometers = Double.parseDouble(txtRentKilometers.getText());

                rentService.add(id, carId, numberOfDays, kilometers);
                rent.clear();
                rent.addAll(rentService.getAll());
                txtRentId.clear();
                txtRentCarId.clear();
                txtRentNumberOfDays.clear();
                txtRentKilometers.clear();

            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }


    /**
     * Remove a rent
     * @param actionEvent remove a rent
     */
    public void btnRemoveRentClick(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(txtRentId.getText());
            rentService.delete(id);
            rent.clear();
            rent.addAll(rentService.getAll());
            txtRentId.clear();
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    /**
     * Show all rents
     * @param actionEvent show all rents
     */
    public void btnGetAllRentsClick(ActionEvent actionEvent) {
        try {
            rentService.getAll();
            rent.clear();
            rent.addAll(rentService.getAll());
            txtRentId.clear();
            txtRentCarId.clear();
            txtRentNumberOfDays.clear();
            txtRentKilometers.clear();

        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    /**
     * Search a rent
     * @param actionEvent search a rent
     */
    public void btnSearchRentClick(ActionEvent actionEvent) {
        try {
            String option = txtRentSearch.getText();
            List<Rent> foundMedicines = rentService.searchRent(option);
            rent.clear();
            txtRentSearch.clear();
            rent.addAll(foundMedicines);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }



    /**
     * Undo an operation
     * @param actionEvent undo an operation
     */
    public void btnUndoRentClick(ActionEvent actionEvent) {
        rentService.undo();
        rent.clear();
        rent.addAll(rentService.getAll());
    }

    /**
     * Redo an operation
     * @param actionEvent redo an operation
     */
    public void btnRedoRentClick(ActionEvent actionEvent) {
        rentService.redo();
        rent.clear();
        rent.addAll(rentService.getAll());
    }

}
