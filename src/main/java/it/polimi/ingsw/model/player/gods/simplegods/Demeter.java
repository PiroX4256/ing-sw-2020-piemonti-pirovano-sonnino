package it.polimi.ingsw.model.player.gods.simplegods;

import it.polimi.ingsw.model.board.GameBoard;
import it.polimi.ingsw.model.board.Space;
import it.polimi.ingsw.model.player.PlayerColors;
import it.polimi.ingsw.model.player.Worker;
import java.util.List;

/**
 * @author Alice Piemonti
 */
public class Demeter extends Worker {

    private Space oldPosition;

    public Demeter(PlayerColors color) {
        super(color);
    }

    @Override
    public void setPhases() {
        setTwoBuildPhases();
    }

    /**
     * return an ArrayList which contains all the buildable spaces
     *
     * @param gameBoard gameBoard
     * @return an ArrayList of spaces
     */
    @Override
    public List<Space> getBuildableSpaces(GameBoard gameBoard) {
        phases.get(5).changeMust(true);
        return super.getBuildableSpaces(gameBoard);
    }

    /**
     * build on the space received
     *
     * @param space space
     * @return boolean false if it's impossible to build on the space or if OutOfBoundException is thrown
     * @throws IllegalArgumentException if space is null
     */
    @Override
    public boolean build(Space space) throws IllegalArgumentException {
        if(super.build(space)){
            if(oldPosition == null){    //first build
                oldPosition = space;
            }
            else oldPosition = null;    //second build
            phases.get(5).changeMust(false);
            return true;
        }
        return false;
    }

    /**
     * return true if the worker can build into the space received
     * Demeter can't build on the same space twice in a turn
     * @param space space of the GameBoard
     * @return boolean value
     * @throws IllegalArgumentException if space is null
     */
    @Override
    public boolean canBuildOnto(Space space) throws IllegalArgumentException {
        if(oldPosition == space) return false;
        return super.canBuildOnto(space);
    }

    /**
     * notify the selectSpacesListener with all the moves the worker can do
     *
     * @param gameBoard of the game
     * @throws IllegalArgumentException if gameBoard is null
     * @throws IllegalStateException    if the worker is blocked
     */
    @Override
    public void notifyWithMoves(GameBoard gameBoard) throws IllegalArgumentException, IllegalStateException {
        oldPosition = null;
        super.notifyWithMoves(gameBoard);
    }
}
