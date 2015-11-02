package jadelex;

import jade.Direction;
/**
 *  YYtoken implementation for MOVE token type
 */

public class Move extends BaseToken{
    private Direction direction;
    /**
     * Choosen mode.
     * true <==> writing mode
     */
    public Direction getDirection(){
        return direction;
    }
    public Move(Direction direction){
        super(TokenType.MOVE);
        this.direction = direction;
    }
    public String toString(){
        return super.toString()+"["+direction+"]";
    }
}