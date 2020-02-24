package Users;

public class AdminAccount extends Account {
    AdminAccount(String username, String password, String email, String region) {
        super(username, password, email, region);
    }

    @Override
    public String encryptPassword(String password) {
        return null;
    }
}
