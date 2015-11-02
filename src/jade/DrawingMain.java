/**
 * 
 */
package jade;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import drawing.DrawingException;
import jadelex.Tokenizer;
import jadelex.TokenizerV1;

/**
 * @author Eddy El Khatib
 *
 */
public class DrawingMain {

	/**
	 * @param args
	 * @throws JadeException 
	 * @throws DrawingException 
	 */
	public static void main(String[] arg) throws IOException, JadeException, DrawingException {
	    // créer un Yylex qui va prendre ses entrées dans le fichier
	      // de nom arg[0]
	    Reader input;
	    if (arg.length>0){
	      input = new BufferedReader(new FileReader(arg[0]));
	    }
	    else {
	      input = new InputStreamReader(System.in);
	    }
	    Tokenizer yy = new TokenizerV1(input) ;
	    DrawingMachine drawingMachine = new DrawingMachine();
	    ParserLevel1 parser = new ParserLevel1(yy, drawingMachine);
	    parser.run();
		/*DrawingFrame lel = new DrawingFrame(100,100);
		lel.reset();
		lel.goTo(new Point(50, 50));
		lel.drawTo(new Point(70,70));*/
	}
}