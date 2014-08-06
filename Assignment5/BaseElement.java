/** Name: Toan Nguyen
 * Assignment 5
 * Common super class for all classes that represent data
 * from tags in the file WWU_Courses.xml
 */
 
public abstract class BaseElement {
	 private StringBuffer text = new StringBuffer();
	   public void setAttributeValue( String name,
	                                   String value) {
	      throw new Error( "No attributes defined for element"
	                        + getClass() );
	   }
	   public void addText( String s ) {
	   	  text.append( s );
	   }
	   public String getText() {
	   	  return text.toString();
	   }
	   public String toString() {
	      return text.toString();
	   }
}
