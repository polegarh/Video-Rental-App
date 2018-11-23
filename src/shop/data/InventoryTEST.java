package shop.data;

import junit.framework.TestCase;
import shop.command.UndoableCommand;

import java.util.HashSet;

public class InventoryTEST extends TestCase {
	public InventoryTEST(String name) {
	  super(name);
  	}
	
  	InventorySet s = (InventorySet) Data.newInventory();
	final Video v1 = Data.newVideo("A", 1801, "B");
	final Video v2 = Data.newVideo("B", 1801, "B");
	//copy of A
	final Video v3 = Data.newVideo("A", 1801, "B");
	
	public void testSize() {
			  								assertEquals( 0, s.size() );
			    s.addNumOwned(v1,  1); 		assertEquals( 1, s.size() );
			    s.addNumOwned(v1,  2); 		assertEquals( 1, s.size() );
			    s.addNumOwned(v2,  1); 		assertEquals( 2, s.size() );
			    s.addNumOwned(v2, -1); 		assertEquals( 1, s.size() );
			    s.addNumOwned(v1, -3); 		assertEquals( 0, s.size() );
			    // test to make sure size is never negative
		  try { s.addNumOwned(v1, -3); } catch ( IllegalArgumentException e ) {}
			   								assertEquals( 0, s.size() );
	}
	
	public void testAddNumOwned() {
	    									assertEquals( 0, s.size() );
	    		s.addNumOwned(v1, 1);     	assertEquals( v1, s.get(v1).video() );
	    									assertEquals( 1, s.get(v1).numOwned() );
	    		// check for addition of change
	    		s.addNumOwned(v1, 2);     	assertEquals( 3, s.get(v1).numOwned() );
	    		// check for subtraction of change
			    s.addNumOwned(v1, -1);    	assertEquals( 2, s.get(v1).numOwned() );
			    s.addNumOwned(v2, 1);     	assertEquals( 2, s.get(v1).numOwned() );
			    s.addNumOwned(v3, 1); 		assertEquals( 3, s.get(v1).numOwned() );
			      							assertEquals( 2, s.size() );	
			    s.addNumOwned(v1, -3);    
	    									assertEquals( 1, s.size() );
	    try { s.addNumOwned(null, 1);   fail(); } catch ( IllegalArgumentException e ) {}
	}
	
	public void testCheckOutCheckIn() {
		  try { s.checkOut(null);     fail(); } catch ( IllegalArgumentException e ) {}
		  try { s.checkIn(null);      fail(); } catch ( IllegalArgumentException e ) {}
		        s.addNumOwned(v1, 2); 		assertTrue( s.get(v1).numOut() == 0 && s.get(v1).numRentals() == 0 );
		        s.checkOut(v1);       		assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
		  try { s.addNumOwned(v1,-3); fail(); } catch ( IllegalArgumentException e ) {}
		  try { s.addNumOwned(v1,-2); fail(); } catch ( IllegalArgumentException e ) {}
		        s.addNumOwned(v1,-1); 		assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
		        s.addNumOwned(v1, 1); 		assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
		        s.checkOut(v1);       		assertTrue( s.get(v1).numOut() == 2 && s.get(v1).numRentals() == 2 );
		  try { s.checkOut(v1);       fail(); } catch ( IllegalArgumentException e ) {}
		        s.checkIn(v1);        		assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 2 );	
		        s.checkIn(v3);   			assertTrue( s.get(v1).numOut() == 0 && s.get(v1).numRentals() == 2 );
		  try { s.checkIn(v1);        fail(); } catch ( IllegalArgumentException e ) {}
		  try { s.checkOut(v2);       fail(); } catch ( IllegalArgumentException e ) {}	
		  		s.checkOut(v1);      		assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 3 );
	}
	
	public void testClear() {
				s.addNumOwned(v1,  1); 		assertEquals( 1, s.size() );
				s.addNumOwned(v2,  2); 		assertEquals( 2, s.size() );
				s.clear(); 			 		assertEquals( 0, s.size() );
		  try { s.checkOut(v2); fail(); } catch ( IllegalArgumentException e ) {}
	}
	
	public void testGet() {
		  UndoableCommand comd = Data.newAddCmd(s, v1,  1); 
		  comd.run();
		  
		  Record m = s.get(v1);
		  Record n = s.get(v1);
											 assertTrue( m.equals(n) );
											 assertTrue( m == n );  
	}
	
	public void testIterator1() {
	  // Create a local HashSet<Video> and an InventorySet; put some videos in the InventorySet
	  HashSet<Video> temp = new HashSet<Video>() ;
	  InventorySet inv = new InventorySet();
	  Video video1 = new VideoObj("XX", 2004, "XX");
	  Video video2 = new VideoObj("XY", 2000, "XY");
	  inv.addNumOwned(video1,10);
	  inv.addNumOwned(video2,20);
	  temp.add(video1);
	  temp.add(video2);
	  
	  java.util.Iterator<Record> i = inv.iterator();
	  try { i.remove(); fail(); }
	  catch (UnsupportedOperationException e) { }
	  while(i.hasNext()) {
	    Record r = i.next(); 
	    assertTrue(temp.contains(r.video()));
	    temp.remove(r.video());
	  }
	  assertTrue(temp.isEmpty());
	}
	
	public void testIterator2() {
		class NumOwnedComparator implements java.util.Comparator<Record> {
			public int compare(Record o1, Record o2) {
				return o2.numOwned() - o1.numOwned();
			}
		}
	}
}