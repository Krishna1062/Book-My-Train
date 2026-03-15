import java.util.HashMap;
import java.util.Map;
public class UserService {
    private Map<String, User> userMap = new HashMap<>();
    private User currentUser = null;

    public boolean registerUser(String userName, String password, String fullName, String contact){
        if(userMap.containsKey(userName)){
            System.out.println("Username already taken");
            return false;
        }
        User user = new User(userName, password, fullName, contact);
        userMap.put(userName, user);
        System.out.println("Registration successfull! You are loggedin now");
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean loginUser(String userName, String password){
        if(!userMap.containsKey(userName)){
            System.out.println("No user found with this username");
            return false;
        }
        User user = userMap.get(userName);
        if(!user.getPassword().equals(password)){
            System.out.println("Incorrect password");
            return false;
        }
        currentUser = user;
        System.out.println("Welcome " + currentUser.getFullname());
        return true;
    }

    public void logout(){
        if(currentUser != null){
            System.out.println("Logged out " + currentUser.getFullname());
        }
        currentUser = null;
    }

    public boolean isLoggedin(){
        return currentUser != null;
    }
}
