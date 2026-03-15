import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingService {
    private List<Train> trainList = new ArrayList<>();
    private List<Ticket> ticketList = new ArrayList<>();

    public BookingService(){
        trainList.add(new Train(101, "Vande Bharat Express", "Mumbai", "Sangli", 100));
        trainList.add(new Train(102, "Shatabdi Express", "Pune", "Kolhapur", 80));
        trainList.add(new Train(103, "Rajdhani Express", "Satara", "Nagpur", 90));
        trainList.add(new Train(104, "Namo Express", "Sangli", "Latur", 70));
        trainList.add(new Train(105, "Raga Express", "Kolhapur", "Mumbai", 50));
    }

    public List<Train> searchTrain(String source, String destination){//add date based search
        List<Train> res = new ArrayList<>();
        for(Train train: trainList){
            if(train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination)) {
                res.add(train);
            }
        }
        return res;
    }

    public Ticket bookTicket(User user, int trainId, int seatCount){
        for(Train train: trainList){
            if(train.getTrainId() == trainId){
                if(train.bookSeats(seatCount)){
                    Ticket ticket = new Ticket(user,train,seatCount);
                    ticketList.add(ticket);
                    return ticket;
                }
                else{
                    System.out.println("Seats not available");
                    return null;
                }
            }
        }
        System.out.println("Train not found");
        return null;
    }

    public List<Ticket> getTicketsByUser(User user){
        List<Ticket> res = new ArrayList<>();
        for(Ticket ticket: ticketList){
            if(ticket.getUser().getUserName().equals(user.getUserName())){
                res.add(ticket);
            }
        }
        return res;
    }

    public boolean cancelTicket(int ticketId, User user){
        Iterator<Ticket> iterator = ticketList.listIterator();
        while(iterator.hasNext()){
            Ticket ticket = iterator.next();
            if(ticket.getTicketId() == ticketId && ticket.getUser().getUserName().equals(user.getUserName())){
                Train train = ticket.getTrain();
                train.cancelSeats(ticket.getTicketId());
                iterator.remove();
                System.out.println("Ticket " + ticketId +" cancelled successfully");
                return true;
            }
        }
        System.out.println("Ticket not found or does not belong to current user");
        return false;
    }
    public void listAllTrain(){
        System.out.println("List of all trains");
        for(Train train: trainList){
            System.out.println(train);
        }
    }

}
