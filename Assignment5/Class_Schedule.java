/** Name: Toan Nguyen
 * Assignment 5
 * The root class_schedule to add the course to the array courses
 */
 
import java.util.*;
public class Class_Schedule extends BaseElement {
	  private List<BaseElement> courses = new ArrayList<BaseElement>();
	  //private List courses = new ArrayList();
	   public void addCourse( Course course ) {
	      courses.add( course );
	   }
	   public List<BaseElement> getCourses() {
	   	  return courses;
	   }
	   public void setCourses( List<BaseElement> courses ) {
	   	  this.courses = courses;
	   }
	   public String toString() {
	   	  return courses.toString();
	   }
}
