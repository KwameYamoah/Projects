package Users;

public class PlayerAccount extends Account {
    PlayerAccount(String username, String password, String email, String region) throws InvalidPassword {
        super(username, password, email, region);
    }
}
