package shop.data;

import junit.framework.TestCase;

public class VideoTEST extends TestCase {
  public VideoTEST(String name) {
    super(name);
  }
  
  public void testConstructorAndAttributes() {
	    String title1 = "XX";
	    String director1 = "XY";
	    String title2 = " XX ";
	    String director2 = " XY ";
	    int year = 2002;

	    Video v1 = Data.newVideo(title1, year, director1);
	    assertSame(title1, v1.title());
	    assertEquals(year, v1.year());
	    assertSame(director1, v1.director());

	    Video v2 = Data.newVideo(title2, year, director2);
	    assertEquals(title1, v2.title());
	    assertEquals(director1, v2.director());
  }
  
  public void testConstructorExceptionYear() {
	    try {
	      Data.newVideo("X", 1800, "Y");
	      fail();
	    } catch (IllegalArgumentException e) { }
	    try {
	      Data.newVideo("X", 5000, "Y");
	      fail();
	    } catch (IllegalArgumentException e) { }
	    try {
	      Data.newVideo("X", 1801, "Y");
	      Data.newVideo("X", 4999, "Y");
	    } catch (IllegalArgumentException e) {
	      fail();
	    }
  }

  public void testConstructorExceptionTitle() {
	    try {
	      Data.newVideo(null, 2002, "Y");
	      fail();
	    } catch (IllegalArgumentException e) { }
	    try {
	      Data.newVideo("", 2002, "Y");
	      fail();
	    } catch (IllegalArgumentException e) { }
	    try {
	      Data.newVideo(" ", 2002, "Y");
	      fail();
	    } catch (IllegalArgumentException e) { }
  }
  
  public void testConstructorExceptionDirector() {
		try {
		    Data.newVideo("X", 2002, null);
		    fail();
		} catch (IllegalArgumentException e) { }
		try {
			Data.newVideo("X", 2002, "");
		    fail();
		} catch (IllegalArgumentException e) { }
		try {
			Data.newVideo("X", 2002, " ");
		    fail();
		} catch (IllegalArgumentException e) { }
  }

  public void testHashCode() {
	  	assertEquals
	      (-875826552,
	       Data.newVideo("None", 2009, "Zebra").hashCode());
	    assertEquals
	      (-1391078111,
	       Data.newVideo("Blah", 1954, "Cante").hashCode());
  }
  
  public void testEquals() { 
	  Video j = Data.newVideo("X", 1801, "Y");
      Video k = Data.newVideo("X", 4999, "Y");
      
      Video m = Data.newVideo("X", 1809, "Y");
      Video n = Data.newVideo("X", 1809, "Y");
      
      String temp = "X, 1809, Y";
      
      // false case 
	  if (j.equals(k)) {
		  fail();
	  }
	  
	  // true case
	  if (m.equals(n)) { return; } else{ fail(); }
	  
	  // == case, which should be true
	  assertEquals( m.equals(n), m == n );
	  
	  // different type case
	  try {
		  m.equals(temp);
		  fail();
	  } catch (IllegalArgumentException e) {}
	  
  }
  
  public void testCompareTo() { 
	  Video m = Data.newVideo("X", 1809, "Y");
      Video n = Data.newVideo("X", 1809, "Y");
     
      Video j = Data.newVideo("Abc", 1900, "Y");
      Video k = Data.newVideo("Bac", 1900, "Y");
      Video w = Data.newVideo("Bac", 1901, "Y");
      Video l = Data.newVideo("Bac", 1901, "Z");

      
	  // less than case
	  if (j.compareTo(k) == -1) { return; } else { fail();} //checks that Abc < Bac
	  if (k.compareTo(w) == -1) { return; } else { fail();}	//checks that 1900 < 1901
	  if (w.compareTo(l) == -1) { return; } else { fail();}	//checks that Y < Z 
	  if (j.compareTo(l) == -1) { return; } else { fail();}	//checks regular case where all 3 factors are less than

      // more than case 
	  if (k.compareTo(j) == 1) { return; } else { fail();} 	//checks that Bac > Abc
	  if (w.compareTo(k) == 1) { return; } else { fail();}	//checks that 1901 > 1900
	  if (l.compareTo(w) == 1) { return; } else { fail();}	//checks that Z > Y 
	  if (l.compareTo(j) == 1) { return; } else { fail();}	//checks regular case where all 3 factors are more than
	  
	  // equal to case
	  if (m.compareTo(n) == 0) { return; } else { fail(); } //checks that Bac > Abc
  }

  public void testToString() { 
	  Video j = Data.newVideo("X", 1801, "Y");
	  assertEquals( j.toString(), "X (1801) : Y" );
  }
}
