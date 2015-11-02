package jade;
import java.io.IOException;

import drawing.DrawingException;
import jadelex.Jump;
import jadelex.Move;
import jadelex.PenMode;
import jadelex.Repeat;
import jadelex.StepLength;
import jadelex.TokenType;
import jadelex.Yytoken;

public class ParserLevel1 implements JadeParser {
    private final jadelex.Tokenizer tokenizer;
    private final jade.JadeMachine machine;
    
    public ParserLevel1(jadelex.Tokenizer tokenizer, jade.JadeMachine machine){
        this.tokenizer = tokenizer;
        this.machine = machine;
    }
    
    
    /*----------------------------------------------
     * Les méthodes ...Action reçoivent en paramètre un token
     * correspondant à une instruction simple et
     * envoient à la JadeMachine
     * la commande correspondante 
     *------------------------------------------------
     */
    
    /*
     * Déclenche l'action correspondant à un instruction simple
     */
    private void simpleAction(Yytoken t) throws DrawingException, IOException, JadeException {
    	switch(t.getType()){
    	case JUMP :
    		this.jumpAction((Jump)t);
    		break;
    	case REPEAT :
    		this.parseRepeat((Repeat)t);
    		break;
    	case MOVE :
    		this.moveAction((Move)t);
    		break;
    	case STEP_LENGTH:
    		this.stepLengthAction((StepLength)t);
    		break;
    	case PEN_MODE:
    		this.penModeAction((PenMode)t);
    		break;
		default:
			break;
    	}
    }
    
    /*
     * déclenche le déplacement indiqué par le token
     */
    private void moveAction(Move token) throws DrawingException{
    	this.machine.move(token.getDirection());
    	
    }
    
    /*
     * déclenche le changement de mode indiqué par le token
     */
    private void penModeAction(PenMode token){
    	this.machine.setPenMode(token.getMode());
    }
    
    /*
     * déclenche le saut indiqué par le token
     */
    private void jumpAction(Jump token) throws DrawingException{
    	this.machine.jump(token.getX(), token.getY());
    }
    
    /*
     * déclenche le changement de longueur de pas indiqué par le token
     */
    private void stepLengthAction(StepLength token){
    	this.machine.setStepLength(token.getLength());
    }
    
    /*
     * Indique si le token correspond à une instruction simple
    */
    private boolean isSimple(Yytoken t){
    	return t.getType() != TokenType.UNKNOWN;
    }
  
    /*--------------------------------------
     * Analyseur syntaxique proprement dit
     *--------------------------------------
     */
    /*
     * dernier token lu
     */
    private jadelex.Yytoken currentToken;
    
    /*
     * progession de lecture. Modifie currentToken
     */
    private void nextToken() throws java.io.IOException{
        currentToken = tokenizer.yylex();
    }
    
    /*
     * Appelée en cas d'erreur de syntaxe
     */
    private void error() throws JadeException {  
    	throw new JadeException();
    }

    
    
    /*
     * Cette méthode lit une séquence d'instructions terminée par la fin de fichier
     *
     * Initialement, currentToken est le premier token de la séquence à analyser
     * En fin de méthode, currentToken est le premier token QUI SUIT la séquence à analyser (donc ici null)
     *
     * 
     */
    private void parseSequence() throws java.io.IOException, JadeException, DrawingException {
    	for(this.nextToken();this.currentToken!=null;this.nextToken()){
    		if(this.isSimple(this.currentToken))
    			this.simpleAction(this.currentToken);
    			
    	}
    		
    }

    /*
     * Cette méthode lit une répétition complète, c'est à dire un opérateur de répétition
     * suivi d'une instruction simple à répéter
     * 
     * Initialement, currentToken est le premier token à analyser (donc de type REPEAT)
     * En fin de méthode, currentToken est le premier token QUI SUIT l'instruction à répéter
     *
     */
    private void parseRepeat(Repeat m) throws java.io.IOException, JadeException, DrawingException {
    	int occurences = m.getOccurences();
    	this.nextToken();
    	for(int i = 1; i<=occurences;i++)
    		this.simpleAction(this.currentToken);
    }
    
    /**
     * Déclenche l'analyse syntaxique et l'interprétation
     * @throws DrawingException 
     * @throws IOException 
     *
     */
    public void run() throws JadeException{
      // initialise puis lance l'analyse d'une séquence
    	try {
			this.parseSequence();
		} catch (IOException | DrawingException e) {
			this.error();
		}
    }
 
}