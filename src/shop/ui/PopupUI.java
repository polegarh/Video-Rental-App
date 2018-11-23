package shop.ui;

import javax.swing.JOptionPane;
//import java.io.IOException;
import shop.main.Control;


public final class PopupUI implements UI {
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null,message);
  }

  public void displayError(String message) {
    JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
  }

  public void processMenu(UIMenu menu, Control c) {
    StringBuilder b = new StringBuilder();
    b.append(menu.getHeading());
    b.append("\n");
    b.append("Enter choice by number:");
    b.append("\n");

    for (int i = 1; i < menu.size(); i++) {
      b.append("  " + i + ". " + menu.getPrompt(i));
      b.append("\n");
    }

    String response = JOptionPane.showInputDialog(b.toString());
    if (response == null) {
      response = "";
    }
    int selection;
    try {
      selection = Integer.parseInt(response, 10);
      if ((selection < 0) || (selection >= menu.size()))
        selection = 0;
    } catch (NumberFormatException e) {
      selection = 0;
    }

    menu.runAction(selection, c);
  }
  
  /** print form heading 
	* 	iterate through the form items
	* 	print form item prompt
	* 	read through string, validate it, then store it in String []
	* 	print form item other prompt
	*/ 
  public String[] processForm(UIMenu form) {
	// create a stringBuilder & String []
	StringBuilder b = new StringBuilder();
	String [] output = new String [form.size()];
	
	// get Heading
	b.append(form.getHeading());
	
	// iterate through the form, by asking prompt then recording response
	for (int i = 0; i < form.size(); i++) {
		// display prompt
		b.append("  " + (i+1) + ". " + form.getPrompt(i));
		// get input after displayed prompt
		String response = JOptionPane.showInputDialog(b.toString());
		// check input & add to output
		if (form.checkInput(i, response)) output[i] = response;
	    b.append("\n");
	}
    return output;
  }
}
