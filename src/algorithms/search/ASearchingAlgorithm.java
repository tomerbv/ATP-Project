package algorithms.search;

/**represents an abstarct class for all the types of searching algorithms : DFS,BFS
 *
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    int NodesEvaluated;


    public abstract Solution solve(ISearchable domain);
    public abstract String getName();

    /**
     * @return number of states we checked
     */
    public int getNumberOfNodesEvaluated(){
        return NodesEvaluated;
    }

    public ASearchingAlgorithm() {
        NodesEvaluated = 0;
    }
}