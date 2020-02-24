package Users;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
    private Account adminAccount;
    private Account playerAccount;
    private String testUsername = "KyoumitheChamp";
    private String testPassword = "Uber1234";
    private String testEmail = "yamoahkwame@gmail.com";
    private String testRegion = "Europe";
    @Before
    public void setUp(){
        adminAccount = new AdminAccount(testUsername, testPassword, testEmail, testRegion);
        playerAccount = new PlayerAccount(testUsername, testPassword, testEmail, testRegion);

    }

    @Test
    public void checkIfAccountUsernameIsStored(){
        assertEquals(adminAccount.getUsername(),testUsername);
    }

    @Test
    public void checkIfAccountPasswordIsStored(){
        assertEquals(adminAccount.getPassword(),testPassword);
    }

    @Test
    public void checkIfAccountEmailIsStored(){
        assertEquals(adminAccount.getEmail(),testEmail);
    }

    @Test
    public void checkIfAccountRegionIsStored(){
        assertEquals(adminAccount.getRegion(),testRegion);
    }

}