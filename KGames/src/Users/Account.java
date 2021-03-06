package Users;

public abstract class Account {
    private String username;
    private String password;
    private String email;
    private String region;

    Account(String username, String password, String email, String region) throws InvalidPassword {
        this.username = username;
        checkPasswordValidity(password);
        this.password = Database.encryptPassword(password);
        this.email = email;
        this.region = region;
    }

    private void checkPasswordValidity(String password) throws InvalidPassword {
        if(password.length() < 8){
            throw new InvalidPassword("Password should be 8 or more characters");
        }
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

    @Override
    public boolean equals(Object obj) {
        Account other = (Account) obj;
        return this.email.equals(other.email);
    }

    @Override
    public String toString() {
        return username;
    }
}