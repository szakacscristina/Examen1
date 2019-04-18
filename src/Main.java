import Domain.*;
import Repository.IRepository;
import Repository.JsonRepository;
import Service.ClientService;
import Service.MedicineService;
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

        IValidator<Medicine> medicineValidator = new MedicineValidator();
        IValidator<Client> clientValidator = new ClientValidator();
       // IValidator<Transaction> transactionValidator = new TransactionValidator();

//       IRepository<Medicine> medicineRepository = new InMemoryRepository<>(medicineValidator);
//        IRepository<Client> clientRepository = new InMemoryRepository<>(clientValidator);
//        IRepository<Transaction> transactionRepository = new InMemoryRepository<>(transactionValidator);
        IRepository<Medicine> medicineRepository = new JsonRepository<>(medicineValidator, "medicine.json", Medicine.class);
        IRepository<Client> clientRepository = new JsonRepository<>(clientValidator, "client.json", Client.class);
       // IRepository<Transaction> transactionRepository = new JsonRepository<>(transactionValidator, "transaction.json", Transaction.class);

      MedicineService medicineService = new MedicineService(medicineRepository);
//        Medicine medicine1 = new Medicine(1,"Medicine","A", "Producer", 10, false );
//        medicineRepository.add(medicine1);
//        Medicine medicine2 = new Medicine(2,"Medicine","B", "Producer", 15, true );
//        medicineRepository.add(medicine2);
//        Medicine medicine3 = new Medicine(3,"Medicine","C", "Producer", 17, false );
//        medicineRepository.add(medicine3);

        ClientService clientService = new ClientService(clientRepository);
//        Client client1 = new Client(1, "Andreea", "A", "1234567891234", "12.10.2010", "12.12.2019");
//        clientRepository.add(client1);
//        Client client2 = new Client(2, "Maria", "B", "2234567891234", "13.10.2010", "12.13.2019");
//        clientRepository.add(client2);
//        Client client3 = new Client(3, "Ioana", "C", "3234567891234", "14.10.2010", "12.14.2019");
//        clientRepository.add(client3);

        //TransactionService transactionService = new TransactionService(transactionRepository, medicineRepository);
//        Transaction transaction1 = new Transaction(1, 1, 1, 5, "12.12.2012","10:00",false);
//        transactionRepository.add(transaction1);
//        Transaction transaction2 = new Transaction(2, 2, 2, 15, "12.12.2013","12:00", false);
//        transactionRepository.add(transaction2);
//        Transaction transaction3 = new Transaction(3, 1, 3, 25, "12.12.2014","10:00", true);
//        transactionRepository.add(transaction3);

        ManagerController managerController = fxmlLoader.getController();
        managerController.setServices(medicineService, clientService);

        primaryStage.setTitle("Command manager");
        primaryStage.setScene(new Scene(root, 300, 100));
        primaryStage.show();

    }

    public static void main(String[] args){
        launch(args);
    }
}
