import Domain.*;
import Repository.IRepository;
import Repository.JsonRepository;
import Service.CarService;
import Service.RentService;
import UI.JavaFXConsole.ManagerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ManagerWindow.fxml"));
        Parent root = fxmlLoader.load();

        IValidator<Rent> rentValidator = new RentValidator();
        IValidator<Car> carValidator = new CarValidator();
       // IValidator<Transaction> transactionValidator = new TransactionValidator();

//       IRepository<Rent> medicineRepository = new InMemoryRepository<>(medicineValidator);
//        IRepository<Car> clientRepository = new InMemoryRepository<>(clientValidator);
//        IRepository<Transaction> transactionRepository = new InMemoryRepository<>(transactionValidator);
        IRepository<Rent> rentRepository = new JsonRepository<>(rentValidator, "rent.json", Rent.class);
        IRepository<Car> carRepository = new JsonRepository<>(carValidator, "car.json", Car.class);
       // IRepository<Transaction> transactionRepository = new JsonRepository<>(transactionValidator, "transaction.json", Transaction.class);

      RentService rentService = new RentService(rentRepository);
//        Rent medicine1 = new Rent(1,"Rent","A", "Producer", 10, false );
//        medicineRepository.add(medicine1);
//        Rent medicine2 = new Rent(2,"Rent","B", "Producer", 15, true );
//        medicineRepository.add(medicine2);
//        Rent medicine3 = new Rent(3,"Rent","C", "Producer", 17, false );
//        medicineRepository.add(medicine3);

        CarService carService = new CarService(carRepository);
//        Car client1 = new Car(1, "Andreea", "A", "1234567891234", "12.10.2010", "12.12.2019");
//        clientRepository.add(client1);
//        Car client2 = new Car(2, "Maria", "B", "2234567891234", "13.10.2010", "12.13.2019");
//        clientRepository.add(client2);
//        Car client3 = new Car(3, "Ioana", "C", "3234567891234", "14.10.2010", "12.14.2019");
//        clientRepository.add(client3);

        //TransactionService transactionService = new TransactionService(transactionRepository, medicineRepository);
//        Transaction transaction1 = new Transaction(1, 1, 1, 5, "12.12.2012","10:00",false);
//        transactionRepository.add(transaction1);
//        Transaction transaction2 = new Transaction(2, 2, 2, 15, "12.12.2013","12:00", false);
//        transactionRepository.add(transaction2);
//        Transaction transaction3 = new Transaction(3, 1, 3, 25, "12.12.2014","10:00", true);
//        transactionRepository.add(transaction3);

        ManagerController managerController = fxmlLoader.getController();
        managerController.setServices(rentService, carService);

        primaryStage.setTitle("Command manager");
        primaryStage.setScene(new Scene(root, 300, 100));
        primaryStage.show();

    }

    public static void main(String[] args){
        launch(args);
    }
}
