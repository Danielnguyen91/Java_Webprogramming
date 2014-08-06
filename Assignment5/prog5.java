/** Name: Toan Nguyen
 * Assignment 5
 * Main class which use the DOM parse technique to parse the
 * content of XML file and find the conflict between courses
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;

public class prog5 {
	
   public static void main( String [] args ) throws Exception {
	   int i;
      DocumentBuilderFactory factory
         = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser
         = factory.newDocumentBuilder();
      Document document
         = parser.parse(
            new InputSource("WWU_courses.xml") );
      Element class_Schedule = document.getDocumentElement();
      NodeList course
         = class_Schedule.getElementsByTagName("Course");
      Element currElement = null; 
      Element tempElement = null;
      DateFormat format = new SimpleDateFormat("HH:mm");
      for(i=0; i<course.getLength(); i++ ) {
         currElement = (Element)course.item(i);
       
         String coursenum
            = getSimpleElementText( currElement,
                                    "CourseNum" );
         String coursename = currElement.getAttribute("CourseName");
         String CRN =
        		 getSimpleElementText( currElement,
                         "CRN" ); 
         String Professor =
        		 getSimpleElementText( currElement,
                 "Professor" );  
         String start =
        		 getSimpleElementText( currElement,
                         "Starttime" );
         Date startmins = (Date)format.parse(start);
         String end =
        		 getSimpleElementText( currElement,
                         "Endtime" );
         Date endmins = (Date)format.parse(end);
         String room = 
        		 getSimpleElementText( currElement,
                         "Room" );
         for (int m = i + 1; m < course.getLength(); m++)
         {
        	 tempElement = (Element)course.item(m);
        	 String tempcourse = tempElement.getAttribute("CourseName");
        	 String startime  = getSimpleElementText( tempElement,
                     "Starttime" );
        	 Date tempstart = (Date)format.parse(startime);
        	 String endtime = getSimpleElementText( tempElement,
                     "Endtime" );
        	 Date tempend = (Date)format.parse(endtime);
        	 String CRNtemp = getSimpleElementText( tempElement,
                     "CRN" ); 
        	 String Roomtemp =
        			 getSimpleElementText( tempElement,
                             "Room" );
        	 String Protemp =
        			 getSimpleElementText( tempElement,
                             "Professor" ); 
        	 long different = Math.abs((endmins.getTime() - tempstart.getTime()) / (60 * 1000) % 60);
        	 if (different < 15 && room.equals(Roomtemp))
        	 {
        		 System.out.println("There is a conflict between course" + " " + coursename + " CRN: " + CRN +  " "
        		 		+ "and courses " + tempcourse + " CRN: " + CRNtemp + " because not " +
        		 		"enough 15 minutes between classes ");
        	 }
        	 if (start.equals(startime) && end.equals(endtime) && room.equals(Roomtemp) && Professor.equals(Protemp))
        	 {
        		 System.out.println("There is a conflict between course" + " " + coursename + " CRN: " + CRN +  " "
        		 		+ "and courses " + tempcourse + " CRN: " + CRNtemp + " because " +
        		 		"same professor in same time and same room " );
        	 }
        	 
          }
      
          }
     
      }

   
   /**
    * Method to return the first element of a specified
    * name from the given element
    */
   public static Element
   getFirstElement( Element element, String name ) {
      NodeList nl = element.getElementsByTagName( name );
      if ( nl.getLength() < 1 ) {
         throw new RuntimeException( "Element: "
             + element + " does not contain: " + name);
      }
      return (Element)nl.item(0);
   }
   /**
    * Method to return the text contained within the
    * element with the given name found within the
    * specified element
    */
   public static String
   getSimpleElementText( Element node, String name ) {
      Element nameEl = getFirstElement( node, name );
      Node textNode = nameEl.getFirstChild();
      if ( textNode instanceof Text ) {
         return textNode.getNodeValue();
      } else {
         throw new RuntimeException( "No text in " + name);
      }
   }
}