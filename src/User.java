public class User {
    private String userName;
    private String password;
    private String fullname;
    private String contact;

    public User(String userName, String password, String fullname, String contact){
        this.userName = userName;
        this.password = password;
        this.fullname = fullname;
        this.contact = contact;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString(){
        return fullname + " User Id : " + userName;
    }
}
