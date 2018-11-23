package shop.main;



import shop.ui.UI;
import shop.ui.UIMenu;
import shop.ui.UIError;
import shop.data.Inventory;

public class Control {
  
  private final int NUMSTATES = 10;
  static public UIMenu[] _menus;
  
  private StateEnum _state;
  private UIMenu _getVideoForm;
  private Inventory _inventory;
  private UI _ui;
  
  private final MenuEnum[] arrStart = {
	    MenuEnum.Default, 
	    MenuEnum.Add_Remove_copies_of_a_video, 
	    MenuEnum.Check_in_a_video,
	    MenuEnum.Check_out_a_video,
	    MenuEnum.Print_the_inventory, 
	    MenuEnum.Clear_the_inventory,
	    MenuEnum.Undo, 
	    MenuEnum.Redo, 
	    MenuEnum.Print_top_ten_all_time_rentals_in_order,
	    MenuEnum.Exit,
	    MenuEnum.Initialize_with_bogus_contents
  };
  
  private final MenuEnum[] arrExit = {
		MenuEnum.default_,
		MenuEnum.yes,
		MenuEnum.no 
  };
  
  final String WANT_TO_EXIT = "Are you sure you want to exit?";
  final String BOBS_VIDEO = "Bob's video";
  final String ENTER_VIDEO = "Enter video";
  final String ERROR = "UI closed";

  private final MenuEnum[] testNames = {
	    MenuEnum.title,
		MenuEnum.year,
		MenuEnum.director
  };
  // very similar to previous code but it is just a lot cleaner because 
  // we do not need to have Tests here and because of testNames array
  Control(Inventory inventory, UI ui) {
    set_inventory(inventory);
    set_ui(ui);

    _menus = new UIMenu[NUMSTATES];
    set_state(StateEnum.START);
    addStatus(StateEnum.START, arrStart, BOBS_VIDEO);
    addStatus(StateEnum.EXIT, arrExit, WANT_TO_EXIT);
    
    set_getVideoForm(new UIMenu(ENTER_VIDEO, testNames));
  }
  // same code as before except the part with _state since now it is StateEnum, 
  // thus go to StateEnum.java to see what getNumber() does
  void run() {
    try {
      while (get_state() != StateEnum.EXITED) {
        get_ui().processMenu(_menus[get_state().getNumber()], this);
      }
    } catch (UIError e) {
      get_ui().displayError(ERROR);
    }
  }
  // now we do not have to have addSTART and addEXIT and instead we have arrStart and arrExit arrays 
  // which contain MenuEnums, so now the code looks cleaner and if you want to add a menu option you 
  // can do it all in one place - note: except also the array.
  private void addStatus (StateEnum stateNum, MenuEnum[] arr, String message) {
	  _menus[stateNum.getNumber()] = new UIMenu(message, arr);
  }
  // created getters and setters in order to keep variables private
  public UI get_ui() {
	return _ui;
  }
  public void set_ui(UI _ui) {
  	this._ui = _ui;
  }
  public UIMenu get_getVideoForm() {
	return _getVideoForm;
  }
  public void set_getVideoForm(UIMenu _getVideoForm) {
	this._getVideoForm = _getVideoForm;
  }
  public Inventory get_inventory() {
	return _inventory;
  }
  public void set_inventory(Inventory _inventory) {
	this._inventory = _inventory;
  }
  public StateEnum get_state() {
	return _state;
  }
  public void set_state(StateEnum _state) {
	this._state = _state;
  }
}
