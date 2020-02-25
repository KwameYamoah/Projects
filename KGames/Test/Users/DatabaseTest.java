package Users;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {
    private Database database;

    @Before
    public void setUp(){
        database = new Database();
    }


    @Test
    public void checkEmptyPasswordEncryption(){
        String testPasswordString = "";
        assertEquals("", Database.encryptPassword(testPasswordString));
    }


    @Test
    public void checkSingleCharacterEncryption(){
        String testPasswordString = "c";
        assertEquals("k", Database.encryptPassword(testPasswordString));
    }

    @Test
    public void checkDoubleCharacterEncryption(){
        String testPasswordString = "cd";
        assertEquals("ka", Database.encryptPassword(testPasswordString));
    }

    @Test
    public void checkFiveCharacterEncryption(){
        String testPasswordString = "Muito";
        assertEquals("Urgtg", Database.encryptPassword(testPasswordString));
    }


    @Test(expected = AccountNotFound.class)
    public void checkFindingNonExistentAccount() throws AccountNotFound{
        Account account = Database.findPlayerAccount("Parrot");
    }

    @Test
    public void checkFindingExistingAccount() throws AccountNotFound, EmailAlreadyExists, InvalidPassword {
        Account testAccount = new PlayerAccount("Parrot","Muito123", "bob@gmail.com","Asia");
        Database.addAccount(testAccount);
        Account account = Database.findPlayerAccount("Parrot");
        assertEquals(testAccount,account);
    }

    @Test
    public void checkAddingAccount() throws EmailAlreadyExists, InvalidPassword {
        int currentAccountsSize = database.getTotalAccountsNumber();
        Account testAccount = new PlayerAccount("Monkey","Muito123", "Monkey@gmail.com","Europe");
        Database.addAccount(testAccount);
        assertEquals (database.getTotalAccountsNumber(), currentAccountsSize + 1);
    }
}