package Users;

import java.util.ArrayList;

public class Database {
    private static ArrayList<AdminAccount> adminAccounts;
    private static ArrayList<PlayerAccount> playerAccounts;
    private static final String KEY = "83208";
    private static final String KEY_OPERATION = "+--+-";

    Database(){
        adminAccounts = new ArrayList<>();
        playerAccounts = new ArrayList<>();
    }

    static void addAccount(Account account) throws EmailAlreadyExists {
        if(account instanceof AdminAccount){
            checkIfAdminEmailIsUnique(account);
            adminAccounts.add((AdminAccount) account);
        }
        else{
            checkIfPlayerEmailIsUnique(account);
            playerAccounts.add((PlayerAccount) account);
        }
    }


    private static void checkIfAdminEmailIsUnique(Account acc) throws EmailAlreadyExists{
        for(Account account : adminAccounts){
            if(account.getEmail().equals(acc.getEmail())){
                throw new EmailAlreadyExists();
            }
        }
    }

    private static void checkIfPlayerEmailIsUnique(Account acc) throws EmailAlreadyExists{
        for(Account account : playerAccounts){
            if(account.getEmail().equals(acc.getEmail())){
                throw new EmailAlreadyExists();
            }
        }
    }

    public static String encryptPassword(String password) {
        StringBuilder encryptedPassword = new StringBuilder();
        char[] passwordToCharArray = password.toCharArray();
        for(int i = 0; i < passwordToCharArray.length; i++){
            encryptedPassword.append(encryptLetter(passwordToCharArray[i], i));
        }
        return encryptedPassword.toString();
    }

    private static char encryptLetter(char character, int i) {
        int positionToKeyPosition = (i%KEY.length());
        char encryptedCharacter = encrypt(character, positionToKeyPosition);
        return encryptedCharacter;
    }

    private static char encrypt(char character, int positionToKeyPosition){
        char encryptedCharacter = character;
        char keyOperation = getKeyOperation(positionToKeyPosition);
        switch (keyOperation){
            case '+':
                encryptedCharacter += Character.getNumericValue(KEY.charAt(positionToKeyPosition));
                break;
            case '-':
                encryptedCharacter -= Character.getNumericValue(KEY.charAt(positionToKeyPosition));
                break;
        }
        return encryptedCharacter;
    }

    private static char getKeyOperation(int positionToKeyPosition) {
        return KEY_OPERATION.charAt(positionToKeyPosition);
    }


    public static Account findPlayerAccount(String username) throws AccountNotFound {
        for(Account account : playerAccounts){
            if(account.getUsername().equals(username)){
                return account;
            }
        }
        throw new AccountNotFound();
    }

    public int getPlayerAccountsNumber(){
        return playerAccounts.size();
    }

    public int getAdminAccountsNumber(){
        return adminAccounts.size();
    }

    public int getTotalAccountsNumber(){
        return playerAccounts.size() + adminAccounts.size();
    }
}
