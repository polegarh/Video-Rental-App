package shop.ui;

import java.io.BufferedReader;
import shop.main.Control;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.swing.JOptionPane;

public final class TextUI implements UI {
  final BufferedReader _in;
  final PrintStream _out;

  public TextUI() {
    _in = new BufferedReader(new InputStreamReader(System.in));
    _out = System.out;
  }

  public void displayMessage(String message) {
    _out.println(message);
  }

  public void displayError(String message) {
    _out.println(message);
  }

  private String getResponse() {
    String result;
    try {
      result = _in.readLine();
    } catch (IOException e) {
      throw new UIError(); // re-throw UIError
    }
    if (result == null) {
      throw new UIError(); // input closed
    }
    return result;
  }

  public void processMenu(UIMenu menu, Control c) {
    _out.println(menu.getHeading());
    _out.println("Enter choice by number:");

    for (int i = 1; i < menu.size(); i++) {
      _out.println("  " + i + ". " + menu.getPrompt(i));
    }

    String response = getResponse();
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
  
  /** 	print form heading 
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
