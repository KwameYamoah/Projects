package Users;

public class PlayerAccount extends Account {
    PlayerAccount(String username, String password, String email, String region) {
        super(username, password, email, region);
    }

    @Override
    public String encryptPassword(String password) {
        return null;
    }
}
