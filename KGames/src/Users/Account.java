package Users;

public abstract class Account {
    private String username;
    private String password;
    private String email;
    private String region;

    Account(String username, String password, String email, String region) {
        this.username = username;
        this.password = encryptPassword(password);
        this.email = email;
        this.region = region;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRegion() {
        return region;
    }

    public abstract String encryptPassword(String password);
}