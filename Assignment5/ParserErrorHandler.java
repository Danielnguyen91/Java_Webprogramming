import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
/**
 * Class of objects for handling errors that occur while
 * using a SAX parser
 */
public class ParserErrorHandler implements ErrorHandler {
   /**
    * Handle parser warnings by logging only
    */
   public void warning(SAXParseException exception)
      throws SAXException {
      System.out.println( "Warning:" + exception );
   }
   /**
    * Handle parser errors by logging only
    */
   public void error(SAXParseException exception)
      throws SAXException {
      System.out.println( "Error:" + exception );
   }
   /**
    * Handle fatal parser errors by logging and throwing
    * the exception
    */
   public void fatalError(SAXParseException exception)
      throws SAXException {
      System.out.println( "Fatal Error:" + exception );
      throw exception;
   }
}