package shop.main;

import junit.framework.TestCase;
import shop.command.UndoableCommand;
import shop.data.Data;
import shop.data.Record;
import shop.data.Video;
import shop.data.Inventory;

public class TEST1 extends TestCase {
  private Inventory _inventory = Data.newInventory();
  public TEST1(String name) {
    super(name);
  }
  private void check(Video v, int numOwned, int numOut, int numRentals) {
    Record r = _inventory.get(v);
    assertEquals(numOwned, r.numOwned());
    assertEquals(numOut, r.numOut());
    assertEquals(numRentals, r.numRentals());
  }
  
  private void expect(Video v, String s) {
    assertEquals(s,_inventory.get(v).toString());
  }
  
  private void expect(Record r, String s) {
    assertEquals(s,r.toString());
  }
  
  public void test1() {
	  
	  	UndoableCommand clearCmd = Data.newClearCmd(_inventory);
	    clearCmd.run();
	    
	    Video v1 = Data.newVideo("Title1", 2000, "Director1");
	    assertEquals(0, _inventory.size());
	    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
	    assertEquals(1, _inventory.size());
	    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
	    assertEquals(1, _inventory.size());
	    //System.out.println(_inventory.get(v1));
	    expect(v1,"Title1 (2000) : Director1 [10,0,0]");
	    
	    //checking regular cases for check in and check out
	    assertTrue(Data.newOutCmd(_inventory, v1).run());
	    assertEquals(1, _inventory.size());
	    expect(v1,"Title1 (2000) : Director1 [10,1,1]");
	    
	    assertTrue(Data.newOutCmd(_inventory, v1).run());
	    assertEquals(1, _inventory.size());
	    expect(v1,"Title1 (2000) : Director1 [10,2,2]");

	    assertTrue(Data.newInCmd(_inventory, v1).run());
	    assertEquals(1, _inventory.size());
	    expect(v1,"Title1 (2000) : Director1 [10,1,2]");
	    
	    assertTrue(Data.newInCmd(_inventory, v1).run());
	    assertEquals(1, _inventory.size());
	    expect(v1,"Title1 (2000) : Director1 [10,0,2]");

	    //checks whether CmdIn returns false with illegal actions
	    if (Data.newInCmd(_inventory, v1).run()) fail();
	    
	    assertEquals(1, _inventory.size());
	    clearCmd.redo();
	    assertEquals(0, _inventory.size());
	    
	    //checking regular cases for check out
	    assertTrue(Data.newAddCmd(_inventory, v1, 1).run());
	    assertEquals(1, _inventory.size());
	    assertTrue(Data.newAddCmd(_inventory, v1, 1).run());
	    assertEquals(1, _inventory.size());
	    expect(v1,"Title1 (2000) : Director1 [2,0,0]");
	    
	    assertTrue(Data.newOutCmd(_inventory, v1).run());
	    assertEquals(1, _inventory.size());
	    expect(v1,"Title1 (2000) : Director1 [2,1,1]");
	    
	    assertTrue(Data.newOutCmd(_inventory, v1).run());
	    assertEquals(1, _inventory.size());
	    expect(v1,"Title1 (2000) : Director1 [2,2,2]");
	    
	    //checks whether CmdOut returns false with illegal actions
	    if (Data.newOutCmd(_inventory, v1).run()) fail();
	    
	    clearCmd.redo();
	    assertEquals(0, _inventory.size());
	    
	    // check whether CmdAdd returns false with illegal actions
	    assertFalse(Data.newAddCmd(_inventory, null, 1).run());
	    assertFalse(Data.newAddCmd(_inventory, v1, 0).run());
  }
}
