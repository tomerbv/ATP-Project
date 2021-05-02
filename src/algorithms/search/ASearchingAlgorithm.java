package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    int NodesEvaluated;



    public abstract Solution solve(ISearchable domain) throws Exception;
    public abstract String getName();

    public int getNumberOfNodesEvaluated(){
        return NodesEvaluated;
    }

    public ASearchingAlgorithm() {
        NodesEvaluated = 0;
    }
}
