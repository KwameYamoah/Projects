package Launcher;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class LoginScreenTest {
    private LoginScreen loginScreen;
    @Before
    public void setUP(){
         loginScreen = new LoginScreen();
    }

    @Test
    public void checkIfMaximumSizeSet(){
        assertEquals (loginScreen.getMaximumSize(), new Dimension(LoginScreen.LOGIN_SCREEN_WIDTH, LoginScreen.LOGIN_SCREEN_HEIGHT));
    }


    @Test
    public void checkIfMinimumSizeSet(){
        assertEquals (loginScreen.getMaximumSize(), new Dimension(LoginScreen.LOGIN_SCREEN_WIDTH, LoginScreen.LOGIN_SCREEN_HEIGHT));
    }

    @Test
    public void checkIfGridBagConstraintsReturnsInputtedCoordinates1(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(0,0, GridBagConstraints.NONE);
        assert(constraint.gridx == 0);
        assert(constraint.gridy == 0);
    }

    @Test
    public void checkIfGridBagConstraintsReturnsInputtedCoordinates2(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(1,2, GridBagConstraints.NONE);
        assert(constraint.gridx == 1);
        assert(constraint.gridy == 2);
    }

    @Test
    public void checkIfGridBagConstraintsReturnsInputtedCoordinates3(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(2,1, GridBagConstraints.NONE);
        assert(constraint.gridx == 2);
        assert(constraint.gridy == 1);
    }

    @Test
    public void checkIfGridBagConstraintsReturnsCorrectGridWidth(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(2,1, GridBagConstraints.NONE);
        assert(constraint.gridwidth == 1);
    }

    @Test
    public void checkIfGridBagConstraintsReturnsCorrectGridHeight(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(2,1, GridBagConstraints.NONE);
        assert(constraint.gridheight == 1);
    }

    @Test
    public void checkIfGridBagConstraintsReturnsWestAnchorIfXIs0(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(0,3, GridBagConstraints.NONE);
        assert(constraint.anchor==GridBagConstraints.WEST);
    }

    @Test
    public void checkIfGridBagConstraintsReturnsEastAnchorIfXIs1(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(1,3, GridBagConstraints.NONE);
        assert(constraint.anchor==GridBagConstraints.EAST);
    }

    @Test
    public void checkIfGridBagConstraintsReturnsEastAnchorIfXIsAbove1(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(4,3, GridBagConstraints.NONE);
        assert(constraint.anchor==GridBagConstraints.EAST);
    }

    @Test
    public void checkIfGridBagConstraintsReturnsInputtedFill1(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(4,3, GridBagConstraints.NONE);
        assert(constraint.fill==GridBagConstraints.NONE);
    }

    @Test
    public void checkIfGridBagConstraintsReturnsInputtedFill2(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(4,3, GridBagConstraints.HORIZONTAL);
        assert(constraint.fill==GridBagConstraints.HORIZONTAL);
    }

    @Test
    public void checkIfGridBagConstraintsReturnsInputtedFill3(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(4,3, GridBagConstraints.VERTICAL);
        assert(constraint.fill==GridBagConstraints.VERTICAL);
    }

    @Test
    public void checkIfGridBagConstraintsReturnsWestInsetIfXIs0(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(0,3, GridBagConstraints.HORIZONTAL);
        assertEquals(constraint.insets,LoginScreen.WEST_INSETS);
    }

    @Test
    public void checkIfGridBagConstraintsReturnsEastInsetIfXIs1(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(1,3, GridBagConstraints.HORIZONTAL);
        assertEquals(constraint.insets,LoginScreen.EAST_INSETS);
    }

    @Test
    public void checkIfGridBagConstraintsReturnsEastInsetIfXIsAbove1(){
        GridBagConstraints constraint = loginScreen.createGridBagConstraints(3,3, GridBagConstraints.HORIZONTAL);
        assertEquals(constraint.insets,LoginScreen.EAST_INSETS);
    }

    @Test
    public void checkIfJPanelContainsComponent(){
        assert(loginScreen.ifJPanelContainsComponentTest(loginScreen));
    }

    @Test
    public void checkIfUsernameLabelExists(){
        assert(loginScreen.isUserNameLabelSet());
    }

    @Test
    public void checkIfUsernameFieldExists(){
        assert(loginScreen.isUserNameFieldSet());
    }

    @Test
    public void checkIfPasswordLabelExists(){
        assert(loginScreen.isPasswordLabelSet());
    }


    @Test
    public void checkIfPasswordFieldExists(){
        assert(loginScreen.isPasswordFieldSet());
    }

    @Test
    public void checkIfSignInButtonExists(){
        assert(loginScreen.isSignInButtonSet());
    }
    @Test
    public void checkIfExitButtonExists(){
        assert(loginScreen.isExitButtonSet());
    }


}