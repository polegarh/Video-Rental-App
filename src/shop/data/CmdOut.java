package shop.data;

import shop.command.UndoableCommand;

/**
 * Implementation of command to check out a video.
 * @see Data
 */
final class CmdOut implements UndoableCommand {
  private InventorySet _inventory;
  private Record _oldvalue;
  private Video _video;
  
  CmdOut(InventorySet inventory, Video video) {
    _inventory = inventory;
    _video = video;
  }
  
  public boolean run() {
	  try {
		  _oldvalue = _inventory.checkOut(_video);
	      _inventory.getHistory().add(this);
	      return true;
	  } catch (Exception e) {
	      return false;
	  }
//	  catch (ClassCastException e) {
//	      return false;
//	  }

  }
  public void undo() {
	  //System.out.println(_inventory.get(_video));
	  _inventory.replaceEntry(_video,_oldvalue);
	  //System.out.println(_inventory.get(_video));
  }
  public void redo() {
	  _inventory.checkOut(_video);
  }
}
