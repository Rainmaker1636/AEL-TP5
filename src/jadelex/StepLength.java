package jadelex;

/**
 *  YYtoken implementation for STEP_LENGTH token type
 */

public class StepLength extends BaseToken{
    private int length;
    /**
     * Choosen mode.
     * true <==> writing mode
     */
    public int getLength(){
        return length;
    }
    public StepLength(int length){
        super(TokenType.STEP_LENGTH);
        this.length = length;
    }
    public String toString(){
        return super.toString()+"["+length+"]";
    }
}