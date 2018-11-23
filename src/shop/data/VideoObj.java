package shop.data;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if object invariant violated.
   */
  VideoObj(String title, int year, String director) {
    _title = title;
    _director = director;
    _year = year;
  }

  public String director() {
	  if ( this._director == null || this._director.trim().equals(" ") || this._director.trim().equals("")){ 
		  throw new IllegalArgumentException("This director is illigal");		  	  	  
	  } else {
		  return this._director;
	  }
  }

  public String title() {
	  if ( this._title == null || this._title.trim().equals(" ") || this._title.trim().equals("")){ 
		  throw new IllegalArgumentException("This kind of title is illigal");		  
	  } else {
		  return this._title;
	  }
  }

  public int year() {
	  if ( this._year <= 1800 || this._year >= 5000 ) {
		  throw new IllegalArgumentException("The year should be between 1800 and 5000");		  
	  }
	  return this._year;
  }

  public boolean equals(Object thatObject) {
	  if ( this == thatObject ){
		  return true;
	  } 
	  // we have to check that Object that is actually the same type; 
	  VideoObj m = (VideoObj) thatObject;
	  // since we know this and that are same types, we can now compare their attributes
	  // here I added .intern() because its implementation follows the flyweight pattern as said in Javadocs
	  if ( this._year == m._year && this._director.intern().equals(m._director.intern()) && this._title.intern().equals(m._title.intern())) {
		return true;
	  } else {
		return false; 
	  }
  }

  public int hashCode() {
	  // use 17 for initial seed.  Use 37 for the multiplier
	  
	  int hCode = 17;
	  hCode = 37*hCode + this._title.hashCode(); 
	  hCode = 37*hCode + this._year;
	  hCode = 37*hCode + this._director.hashCode(); 
		
	  return hCode;
  }

  public int compareTo(Video that) {
	  // check to make sure you are allowed to downcast
	  if (!(that instanceof VideoObj))
	      throw new IllegalArgumentException();
	  // if we got to this point that means that is VideoObj, so downcasting is legal
	  VideoObj j = (VideoObj) that;
	  if (this._title.compareTo(j._title) < 0 || this._year < j._year || this._director.compareTo(j._director) < 0) return -1;
	  else if (this._title.compareTo(j._title) > 0 || this._year > j._year || this._director.compareTo(j._director) > 0) return 1;
	  else if (this._title == j._title && this._year == j._year && this._director == j._director) return 0;
	  else return -1;
  }

  public String toString() {
	  String year = Integer.toString(this._year);
	  return this._title + " (" + year + ") : " + this._director;
  }
}
