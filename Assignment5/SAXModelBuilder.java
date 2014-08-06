import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.*;
import java.lang.reflect.*;
public class SAXModelBuilder extends DefaultHandler {
   Stack stack = new Stack();
   BaseElement element;
 public void startElement( String namespace, String localname, String qname, Attributes attrs ) 
      throws SAXException {
      BaseElement element = null;
      try {
         Class c = Class.forName( qname );
         element = (BaseElement)c.newInstance();
         for (int i=0; i<attrs.getLength(); i++) {
            element.setAttributeValue( attrs.getQName(i), attrs.getValue(i) );
         }
         stack.push( element );
      } catch ( Exception e ) {
      	 System.err.println( e.toString() );
      }
   }

 public void endElement( String namespace, String localname, String qname ) throws SAXException {
      element = (BaseElement)stack.pop();
      if ( !stack.empty() )
         try {
            setProperty( qname, stack.peek(), element );
         } catch ( Exception e ) {
            System.err.println( e.toString() );
         }
   }

 public void characters( char[] ch, int start, int len ) {
      String text = new String( ch, start, len );
      ((BaseElement)(stack.peek())).addText( text );
   }

   void setProperty( String name, Object target, Object value ) throws SAXException {
      Method method = null;
      try { 
         method = target.getClass().getMethod("add"+name, new Class[] { value.getClass() } );
      } catch ( NoSuchMethodException e ) {
      }
      if ( method == null ) try { 
         method = target.getClass().getMethod( "set"+name, new Class[] { value.getClass() } );
      } catch ( NoSuchMethodException e ) { }
      try {
         method.invoke( target, new Object [] { value } );
      } catch ( Exception e ) {throw new SAXException( e.toString() );}
   }

 public BaseElement getModel() { return element; }
  }