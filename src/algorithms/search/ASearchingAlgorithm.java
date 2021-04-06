package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    public abstract Solution solve(ISearchable domain);
    public abstract String getName();

    public int getNumberOfNodesEvaluated(){

    }


}
