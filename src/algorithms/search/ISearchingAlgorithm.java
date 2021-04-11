package algorithms.search;

public interface ISearchingAlgorithm {
   /**solve
    * @param domain represents the domain we are searching
    * @return the path step by step of the solution to the problem in the domain
    */
   Solution solve(ISearchable domain) throws Exception;
   int getNumberOfNodesEvaluated();
   String getName();

}
