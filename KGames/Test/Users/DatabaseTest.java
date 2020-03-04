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
    public void checkAddingAccount() throws EmailAlreadyExists, InvalidPassword {
        int currentAccountsSize = database.getTotalAccountsNumber();
        Account testAccount = new PlayerAccount("Monkey","Muito123", "Monkey@gmail.com","Europe");
        Database.addAccount(testAccount);
        assertEquals (database.getTotalAccountsNumber(), currentAccountsSize + 1);
    }

    @Test(expected = AccountNotFound.class)
    public void checkDeletingNonExistentPlayerAccount() throws EmailAlreadyExists, InvalidPassword, AccountNotFound {
        Account testAccount = new PlayerAccount("Monkey","Muito123", "Monkey@gmail.com","Europe");
        Database.addAccount(testAccount);
        int currentAccountsSize = database.getTotalAccountsNumber();
        Database.deletePlayerAccount("hello@gmail.com");
        assertEquals (currentAccountsSize - 1, database.getTotalAccountsNumber());
    }

    @Test
    public void checkDeletingExistentPlayerAccount() throws EmailAlreadyExists, InvalidPassword, AccountNotFound {
        Account testAccount = new PlayerAccount("Monkey","Muito123", "Monkey@gmail.com","Europe");
        Database.addAccount(testAccount);
        int currentAccountsSize = database.getTotalAccountsNumber();
        Database.deletePlayerAccount("Monkey@gmail.com");
        assertEquals (currentAccountsSize - 1, database.getTotalAccountsNumber());
    }

    @Test(expected = AccountNotFound.class)
    public void checkDeletingNonExistentAdminAccount() throws EmailAlreadyExists, InvalidPassword, AccountNotFound {
        Account testAccount = new AdminAccount("Monkey","Muito123", "Monkey@gmail.com","Europe");
        Database.addAccount(testAccount);
        int currentAccountsSize = database.getTotalAccountsNumber();
        Database.deleteAdminAccount("hello@gmail.com");
        assertEquals (currentAccountsSize - 1, database.getTotalAccountsNumber());
    }

    @Test
    public void checkDeletingExistentAdminAccount() throws EmailAlreadyExists, InvalidPassword, AccountNotFound {
        Account testAccount = new AdminAccount("Monkey","Muito123", "Monkey@gmail.com","Europe");
        Database.addAccount(testAccount);
        int currentAccountsSize = database.getTotalAccountsNumber();
        Database.deleteAdminAccount("Monkey@gmail.com");
        assertEquals (currentAccountsSize - 1, database.getTotalAccountsNumber());
    }

    @Test
    public void removeAllPlayerAccounts() throws AccountNotFound, EmailAlreadyExists, InvalidPassword {
        Account testAccount1 = new PlayerAccount("Parrot","Muito123", "bob@gmail.com","Asia");
        Account testAccount2 = new PlayerAccount("Monkey","Muito123", "Monkey@gmail.com","Europe");
        Database.addAccount(testAccount1);
        Database.addAccount(testAccount2);
        Database.removeAllPlayerAccounts();
        assertEquals(0, database.getTotalAccountsNumber());
    }

    @Test
    public void removeAllAdminAccounts() throws AccountNotFound, EmailAlreadyExists, InvalidPassword {
        Account testAccount1 = new AdminAccount("Parrot","Muito123", "bob@gmail.com","Asia");
        Account testAccount2 = new AdminAccount("Monkey","Muito123", "Monkey@gmail.com","Europe");
        Database.addAccount(testAccount1);
        Database.addAccount(testAccount2);
        Database.removeAllAdminAccounts();
        assertEquals(0, database.getTotalAccountsNumber());
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

    @Test(expected = IncorrectPassword.class)
    public void checkIfExistingAccountPasswordIsIncorrect() throws InvalidPassword, EmailAlreadyExists, AccountNotFound, IncorrectPassword {
        Account testAccount = new PlayerAccount("Parrot","Muito123", "bob@gmail.com","Asia");
        Database.addAccount(testAccount);
        Database.checkLogin(Database.findPlayerAccount("Parrot"),Database.encryptPassword("muito123"));
    }

    @Test
    public void checkIfExistingAccountPasswordIsCorrect() throws InvalidPassword, EmailAlreadyExists, AccountNotFound, IncorrectPassword {
        Account testAccount = new PlayerAccount("Parrot","Muito123", "bob@gmail.com","Asia");
        Database.addAccount(testAccount);
        Database.checkLogin(Database.findPlayerAccount("Parrot"), "Muito123");
    }




    //TODO: saveAccounts, loadAccounts then move on to admin account and player accounts




}