package Users;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
    private Account adminAccount;
    private Account playerAccount;
    private String testUsername1 = "KyoumitheChamp";
    private String testPassword1 = "Uber1234";
    private String testEmail1 = "yamoahkwame@gmail.com";
    private String testRegion1 = "Europe";
    private String testUsername2 = "QueenQiyana";
    private String testPassword2 = "Muito123";
    private String testEmail2 = "kwamey1997@gmail.com";
    private String testRegion2 = "Europe";

    @Before
    public void setUp() throws InvalidPassword {
        adminAccount = new AdminAccount(testUsername1, testPassword1, testEmail1, testRegion1);
        playerAccount = new PlayerAccount(testUsername2, testPassword2, testEmail2, testRegion2);
    }

    @Test(expected = InvalidPassword.class)
    public void checkIfPasswordLengthIsInvalid() throws InvalidPassword {
        Account account = new PlayerAccount(testUsername1, "Muito", testEmail1,testRegion1);
    }

    @Test
    public void checkIfPasswordLengthIsCorrect() throws InvalidPassword {
        Account account = new PlayerAccount(testUsername1, "Muito123", testEmail1,testRegion1);
    }

    @Test
    public void checkIfAdminAccountUsernameIsStored(){
        assertEquals(adminAccount.getUsername(), testUsername1);
    }

    @Test
    public void checkIfAdminAccountPasswordIsStored(){
        assertEquals("]_cr):02", adminAccount.getPassword());
    }

    @Test
    public void checkIfAdminAccountEmailIsStored(){
        assertEquals(adminAccount.getEmail(), testEmail1);
    }

    @Test
    public void checkIfAdminAccountRegionIsStored(){
        assertEquals(adminAccount.getRegion(), testRegion1);
    }

    @Test
    public void checkIfPlayerAccountUsernameIsStored(){
        assertEquals(playerAccount.getUsername(), testUsername2);
    }

    @Test
    public void checkIfPlayerAccountPasswordIsStored(){
        assertEquals("Urgtg9/1", playerAccount.getPassword());
    }

    @Test
    public void checkIfPlayerAccountEmailIsStored(){
        assertEquals(playerAccount.getEmail(), testEmail2);
    }

    @Test
    public void checkIfPlayerAccountRegionIsStored(){
        assertEquals(playerAccount.getRegion(), testRegion2);
    }


}