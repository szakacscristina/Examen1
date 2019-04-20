package UI.JavaFXConsole;

import Domain.Car;
import Service.CarService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class CarController {
    public TableView tblCar;
    public TableColumn colCarModel;
    public TableColumn colCarBuyKilometers;
    public TableColumn colCarRentPerDay;
   // public TableColumn colClientCNP;
   // public TableColumn colClientDateOfBirth;
   // public TableColumn colClientDateOfRegistration;
    public TextField txtCarId;
    public TextField txtCarModel;
    public TextField txtCarBuyKilometers;
    public TextField txtCarRentPerDay;
   // public TextField txtClientDateOfBirth;
   // public TextField txtClientDateOfRegistration;
    public Button btnRemoveCar;
    public Button btnGetAllCars;
    public Button btnCarSearch;
    public TextField txtCarSearch;
    public Button btnUndoCar;
    public Button btnRedoCar;
    public Button btnAddCar;
    public Button btnUpdateCar;

    private CarService carService;
    private ObservableList<Car> car = FXCollections.observableArrayList();

    public void setCarService(CarService carService) {
        this.carService = carService;
    }
    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            car.addAll(carService.getAll());
            tblCar.setItems(car);
        });
    }

    /**
     * Add a car
     * @param actionEvent add a Car
     */
    public void btnAddCarClick(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(txtCarId.getText());
            String model = txtCarModel.getText();
            double buyKilometers = Double.parseDouble(txtCarBuyKilometers.getText());
            double rentPerDay = Double.parseDouble(txtCarRentPerDay.getText());

            carService.add(id, model, buyKilometers, rentPerDay);

            car.clear();
            car.addAll(carService.getAll());
            txtCarId.clear();
            txtCarModel.clear();
            txtCarBuyKilometers.clear();
            txtCarRentPerDay.clear();
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    /**
     * Update a car
     * @param actionEvent Update a car
     */
    public void btnUpdateCarClick(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(txtCarId.getText());
            String model = txtCarModel.getText();
            double buyKilometers = Double.parseDouble(txtCarBuyKilometers.getText());
            double rentPerDay = Double.parseDouble(txtCarRentPerDay.getText());

            carService.add(id, model, buyKilometers, rentPerDay);

            car.clear();
            car.addAll(carService.getAll());
            txtCarId.clear();
            txtCarModel.clear();
            txtCarBuyKilometers.clear();
            txtCarRentPerDay.clear();
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    /**
     * Remove a car
     * @param actionEvent Remove a car
     */
    public void btnRemoveCarClick(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(txtCarId.getText());

            carService.delete(id);
            car.clear();
            car.addAll(carService.getAll());
            txtCarId.clear();
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    /**
     * Get all car
     * @param actionEvent Get all cars
     */
    public void btnGetAllCarsClick(ActionEvent actionEvent) {
        try {
            carService.getAll();
            car.clear();
            car.addAll(carService.getAll());
            txtCarId.clear();
            txtCarModel.clear();
            txtCarBuyKilometers.clear();
            txtCarRentPerDay.clear();
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    /**
     * Search a car
     * @param actionEvent search a car
     */
    public void btnSearchCar(ActionEvent actionEvent) {
        try {
            String option = txtCarSearch.getText();
            List<Car> foundCars = carService.searchCar(option);
            car.clear();
            car.addAll(foundCars);
            txtCarSearch.clear();
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }
    /**
     * Undo an operation
     * @param actionEvent Undo an operation
     */
    public void btnUndoCarClick(ActionEvent actionEvent) {
        carService.undo();
        car.clear();
        car.addAll(carService.getAll());
    }

    /**
     * Redo an operation
     * @param actionEvent Redo an operation
     */
    public void btnRedoCarClick(ActionEvent actionEvent) {
        carService.redo();
        car.clear();
        car.addAll(carService.getAll());
    }

}
