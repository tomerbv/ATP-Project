package algorithms.search;
import java.util.ArrayList;

/** A interface that represents a searchable domain
 *
 */
public interface ISearchable {
    AState getStartState();
    AState getGoalState();

    /**
     * @param s the state we are are at or looking at
     * @return all the states we can reach from s
     */
    ArrayList<AState> getAllSuccessors(AState s);
}
