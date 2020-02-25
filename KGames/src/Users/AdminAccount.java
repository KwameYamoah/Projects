package Users;

public class AdminAccount extends Account {
    AdminAccount(String username, String password, String email, String region) throws InvalidPassword {
        super(username, password, email, region);
    }

}
