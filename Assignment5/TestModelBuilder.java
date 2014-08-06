import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;
public class TestModelBuilder {
   public static void main( String[] args ) throws Exception {
      SAXParserFactory factory
         = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();
      XMLReader parser = saxParser.getXMLReader();
      SAXModelBuilder mb = new SAXModelBuilder();
      parser.setContentHandler( mb );
      parser.parse( new InputSource( "WWU_courses.xml" ) );
      Class_Schedule class_schedule = (Class_Schedule)mb.getModel();
      class_schedule.getCourses();
      System.out.println( "Class_Schedule = " + class_schedule );
	}
}
