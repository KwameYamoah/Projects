package Users;

public class EmailAlreadyExists extends Exception {
    public EmailAlreadyExists(){
        super("Email already registered");
    }
}
