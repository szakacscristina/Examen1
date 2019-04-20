package UI.JavaFXConsole;

import Service.CarService;
import Service.RentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerController {
    public Button btnRent;
    public Button btnCar;
   // public Button btnTransaction;

    private RentService rentService;
    private CarService carService;
  //  private TransactionService transactionService;

    public void setServices(RentService rentService, CarService carService) {
        this.rentService = rentService;
        this.carService = carService;
       // this.transactionService = transactionService;
    }

    /**
     *
     * @param actionEvent
     */
    public void btnRentClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/RentWindow.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            Stage stage = new Stage();
            stage.setTitle("Rent manager");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            RentController controller =  fxmlLoader.getController();
            controller.setRentService(rentService);
            stage.showAndWait();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window", e);
        }
    }

    /**
     *
     * @param actionEvent
     */
    public void btnCarClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/CarWindow.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            Stage stage = new Stage();
            stage.setTitle("Car manager");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            CarController controller =  fxmlLoader.getController();
            controller.setCarService(carService);
            stage.showAndWait();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window", e);
        }
    }

    }

