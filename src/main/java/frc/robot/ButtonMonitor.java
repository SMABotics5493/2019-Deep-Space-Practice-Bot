
package frc.robot;

import edu.wpi.first.wpilibj.buttons.Button;

public class ButtonMonitor {

    public enum ButtonState {
        Active,
        Inactive,

        NeverActive,
    }
    private Button button;
    private ButtonState state;

    public ButtonMonitor(Button buttonToMonitor) {
        button = buttonToMonitor;
        state = ButtonState.NeverActive;
    }
    public ButtonState checkButtonState(){

        if(button.get()){
            state = ButtonState.Active;
        }
        else if (!button.get() && state == ButtonState.Active) {
            state = ButtonState.Inactive;
        }
        return state;
    }
    public Button getButton(){
        return button;
    }
}
