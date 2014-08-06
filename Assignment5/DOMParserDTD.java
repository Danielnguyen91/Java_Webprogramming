/** Name: Toan Nguyen
 * Assignment 5
 * Class to use DTD file to test the validing of the XML file
 */
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
public class DOMParserDTD {
	public static void main( String [] args ) throws Exception {
	      DocumentBuilderFactory factory
	         = DocumentBuilderFactory.newInstance();
	      factory.setValidating( true );
	      DocumentBuilder parser
	         = factory.newDocumentBuilder();
	      parser.setErrorHandler( new ParserErrorHandler() );
	      Document document
	         = parser.parse(
	            new InputSource("WWU_courses.xml") );
	      

}
}
