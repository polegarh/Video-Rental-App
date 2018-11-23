package shop.main;

import java.util.Comparator;
import java.util.Iterator;


import shop.command.Command;
import shop.data.Data;
import shop.data.Record;
import shop.data.Video;
import shop.ui.UIFormTestImpl;
import shop.ui.UIMenu;

public enum MenuEnum {
		yearTest("yearTest"), 
		numberTest("numberTest"), 
		stringTest("stringTest"),
		title("Title"),
		year("Year"), 
		director("Director"), 
		Default("Defualt"), 
		Number_of_copies_to_add_remove("Please enter the number of copies to add or remove"),
		Add_Remove_copies_of_a_video("Add/Remove copies of a video"), 
		Check_in_a_video("Check in a video"),
		Check_out_a_video("Check out a video"),
		Print_the_inventory("Print the inventory"), 
		Clear_the_inventory("Clear the inventory"),
		Undo("Undo"), 
		Redo("Redo"), 
		Print_top_ten_all_time_rentals_in_order("Print top ten all time rentals in order"),
		Exit("Exit"),
		Initialize_with_bogus_contents("Initialize with bogus contents"), 
		default_("default"), 
		yes("Yes"),
		no("No");
	
	String name; 
	MenuEnum(String val){
		name=val;
	}
	
	public String toString(){
		return name; 
	}
	
	// UIMenu checkInput 
	public boolean run (String input){
		return UIFormTestImpl.run(input, this);
	}
	// UIMenu runAction
	public void run (Control c) {
        switch (this) {
        case Default: c.get_ui().displayError("doh!"); break;
        case Add_Remove_copies_of_a_video: 
        	String[] result1 = c.get_ui().processForm(c.get_getVideoForm());
            Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
            
            MenuEnum[] f = {Number_of_copies_to_add_remove};
            String[] result2 = c.get_ui().processForm(new UIMenu("", f));           
                                               
            Command co = Data.newAddCmd(c.get_inventory(), v, Integer.parseInt(result2[0]));
            if (! co.run()) {
              c.get_ui().displayError("Command failed");
            } 
            break;
        case Check_in_a_video: 
        	String[] result11 = c.get_ui().processForm(c.get_getVideoForm());
            Video v1 = Data.newVideo(result11[0], Integer.parseInt(result11[1]), result11[2]);
            
        	Command c1 = Data.newInCmd(c.get_inventory(), v1);
            if (! c1.run()) {
              c.get_ui().displayError("Command failed");
            } 
            break; 
        case Check_out_a_video:
        	String[] result111 = c.get_ui().processForm(c.get_getVideoForm());
            Video v11 = Data.newVideo(result111[0], Integer.parseInt(result111[1]), result111[2]);
            
        	Command c2 = Data.newOutCmd(c.get_inventory(), v11);
            if (! c2.run()) {
              c.get_ui().displayError("Command failed");
            }
            break;
        case Print_the_inventory: 
            c.get_ui().displayMessage(c.get_inventory().toString());
        	break;
        case Clear_the_inventory:
        	if (!Data.newClearCmd(c.get_inventory()).run()) {
                c.get_ui().displayError("Command failed");
              }
        	break;
        case Undo:
        	if (!Data.newUndoCmd(c.get_inventory()).run()) {
                c.get_ui().displayError("Command failed");
              }
        	break;
        case Redo:
        	if (!Data.newRedoCmd(c.get_inventory()).run()) {
                c.get_ui().displayError("Command failed");
              }
        	break;
        case Print_top_ten_all_time_rentals_in_order:
        	int counter = 1;
        	String output = "";
        	class comparator implements Comparator<Record> 
        	{ 
        	    public int compare(Record a, Record b) 
        	    { 
        	        return a.numRentals() - b.numRentals(); 
        	    } 
        	} 
        	
        	Iterator<Record> i = c.get_inventory().iterator(new comparator());
            while (i.hasNext() && counter<=10) {
              String j = i.next().toString();
              output = output + (counter + ". " + j + "\n");
              counter+=1;
            }
            c.get_ui().displayMessage(output);
        	break;
        case Exit:
            c.set_state(StateEnum.EXIT);
        	break;
        case Initialize_with_bogus_contents:
        	for (char h = 'a'; h <= 'z'; h++){
        		Data.newAddCmd(c.get_inventory(), Data.newVideo("" + h, 2000, "m"), (int) (h - 'a' + 1)).run();
        	}
        	break;
        case default_: c.set_state(StateEnum.EXIT); break; 
        case yes: c.set_state(StateEnum.EXITED); break; 
        case no:  c.set_state(StateEnum.START); break;
		default:
			break;
        }
	}
}
