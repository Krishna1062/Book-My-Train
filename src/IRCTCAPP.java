import java.util.List;
import java.util.Scanner;

public class IRCTCAPP {
    public final Scanner scanner= new Scanner(System.in);
    public final UserService userService= new UserService();
    private final BookingService bookingService= new BookingService();

    public static void main(String[] args) {
        new IRCTCAPP().start();
    }

    public void start(){
        while (true){
            System.out.println("-----Welcome to IRCTC-----");
            if(!userService.isLoggedin()){
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("Enter your choice :");
                int choice = scanner.nextInt();

                switch (choice){
                    case 1 -> register();
                    case 2 -> login();
                    case 3 -> exitApp();
                    default -> System.out.println("Invalid choice");
                }
            }
            else{
                showUserMenu();
            }
        }
    }
    public void register(){
        System.out.println("Enter username");
        String userName = scanner.next();
        System.out.println("Enter password");
        String password = scanner.next();
        System.out.println("Enter your full name");
        String fullName = scanner.next();
        System.out.println("Enter your contact no");
        scanner.nextLine();
        String contact = scanner.nextLine();

        userService.registerUser(userName, password, fullName, contact);
        userService.loginUser(userName, password);
    }
    public void login(){
        System.out.println("Enter username");
        String userName = scanner.next();
        System.out.println("Enter password");
        String password = scanner.next();

        userService.loginUser(userName, password);
    }

    private void exitApp(){
        System.out.println("Thankyou for using IRTC APP");
        System.exit(0);
    }

    private void showUserMenu(){
        while(userService.isLoggedin()){
            System.out.println("\n----User Menu----\n");
            System.out.println("1. Search Train");
            System.out.println("2. Book Ticket");
            System.out.println("3. View my tickets");
            System.out.println("4. Cancel tickets");
            System.out.println("5. View all trains");
            System.out.println("6. Logout");
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();

            switch (choice){
                case 1-> searchTrain();
                case 2-> bookTicket();
                case 3-> viewMyTicket();
                case 4-> cancelTicket();
                case 5-> bookingService.listAllTrain();
                case 6-> userService.logout();
                default -> System.out.println("invalid choice");
            }
        }
    }

    private void searchTrain(){
        System.out.println("Enter Source station");
        String source = scanner.next();
        System.out.println("Enter Destination station");
        String destination = scanner.next();

        List<Train> trains = bookingService.searchTrain(source, destination);
        if(trains.isEmpty()){
            System.out.println("No trains found");
            return;
        }
        System.out.println("Trains found");
        for(Train train: trains){
            System.out.println(train);
        }

        System.out.println("Do you want to book ticket from this window (y/n)");
        String choice = scanner.next();
        if(choice.equalsIgnoreCase("y")){
            System.out.println("Enter train Id");
            int trianId = scanner.nextInt() ;
            System.out.println("Enter no of seats");
            int seats = scanner.nextInt();
            Ticket ticket = bookingService.bookTicket(userService.getCurrentUser(), trianId, seats);
            if(ticket!=null){
                System.out.println("Booking successfull");
                System.out.println(ticket);
            }

        }
        else{
            System.out.println("Returning to user menu....");
        }
    }

    public void bookTicket(){
        System.out.println("Enter Source station");
        String source = scanner.next();
        System.out.println("Enter Destination station");
        String destination = scanner.next();
        List<Train> trains = bookingService.searchTrain(source, destination);
        if(trains.isEmpty()){
            System.out.println("No trains found");
            return;
        }
        System.out.println("Available trains");
        for(Train train: trains){
            System.out.println(train);
        }
        System.out.println("Enter train Id");
        int trianId = scanner.nextInt() ;
        System.out.println("Enter no of seats");
        int seats = scanner.nextInt();
        Ticket ticket = bookingService.bookTicket(userService.getCurrentUser(), trianId, seats);
        if(ticket!=null){
            System.out.println("Booking successfull");
            System.out.println(ticket);
        }

    }

    public void viewMyTicket(){
        List<Ticket> tickerByUser = bookingService.getTicketsByUser(userService.getCurrentUser());
        if(tickerByUser.isEmpty()){
            System.out.println("No tickets found");
        }
        else {
            System.out.println("your tickets");
            for(Ticket ticket: tickerByUser){
                System.out.println(ticket);
            }
        }
    }

    private void cancelTicket(){
        System.out.println("Enter ticketid Id to cancel");
        int ticketid = scanner.nextInt() ;
        bookingService.cancelTicket(ticketid, userService.getCurrentUser());
    }
}

