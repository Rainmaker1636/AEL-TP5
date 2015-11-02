package jadelex;

/**
 *  YYtoken implementation for JUMP token type
 */

public class Jump extends BaseToken{
    private int x;
    private int y;
    /**
     * Choosen mode.
     * true <==> writing mode
     */
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public Jump(int x, int y){
        super(TokenType.JUMP);
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return super.toString()+"["+x+","+y+"]";
    }
}