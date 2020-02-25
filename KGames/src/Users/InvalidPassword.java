package Users;

public class InvalidPassword extends Exception {
    public InvalidPassword(String error){
        super(error);
    }
}
