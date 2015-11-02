package jadelex;

/**
 *  YYtoken implementation for REPEAT token type
 */

public class Repeat extends BaseToken{
    private int occurences;
    /**
     * Choosen mode.
     * true <==> writing mode
     */
    public int getOccurences(){
        return this.occurences;
    }
    public Repeat(int occurences){
        super(TokenType.REPEAT);
        this.occurences = occurences;
    }
    public String toString(){
        return super.toString()+"["+this.occurences+"]";
    }
}