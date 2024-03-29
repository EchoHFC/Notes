import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.Stack;

public class Solver {
    private boolean isSolve = false;
    private int move = -1;

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int move;
        private final int priority;
        private final SearchNode parent;
        private final boolean isTwin;

        public SearchNode(Board board, int move, SearchNode parent, boolean isTwin) {
            this.board = board;
            this.move = move;
            this.priority = board.manhattan() + move;
            this.parent = parent;
            this.isTwin = isTwin;
        }

        public int compareTo(SearchNode that) {
            if (this.board.equals(that.board)){
                return 0;
            }
            if (this.priority < that.priority) {
                return -1;
            }
            else return 1;
        }

}
    private MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>(new Comparator<SearchNode>() {
        public int compare(SearchNode o1, SearchNode o2) {
            if (o1.priority < o2.priority) return -1;
            else if (o1.priority == o2.priority) return 0;
            else return 1;
        }
    });

    private Stack<Board> solutionQueue = new Stack<Board>();
    public Solver(Board initial){
        Board initialTwin = initial.twin();
        SearchNode initSearchNode = new SearchNode(initial, 0, null, false);
        SearchNode initSearchNodeTwin = new SearchNode(initialTwin, 0, null, true);
        minPQ.insert(initSearchNode);
        minPQ.insert(initSearchNodeTwin);
        solve();
    }

    private void solve(){
        while(true){
            //solve for original
            SearchNode searchNode = minPQ.delMin();
            if (searchNode.board.isGoal()){
                if (searchNode.isTwin){
                    this.isSolve = false;
                    this.move = -1;
                } else {
                    this.isSolve = true;
                    this.move = searchNode.move;
                    this.solutionQueue.push(searchNode.board);
                    while(searchNode.parent != null){
                        searchNode = searchNode.parent;
                        this.solutionQueue.push(searchNode.board);
                    }
                }
                break;
            }else{
                for (Board neiborBoard: searchNode.board.neighbors()){
                    SearchNode neiborNode = new SearchNode(neiborBoard, searchNode.move+1, searchNode, searchNode.isTwin);
                    if (searchNode.parent == null){
                        minPQ.insert(neiborNode);
                    } else if (!searchNode.parent.board.equals(neiborNode.board)){
                        minPQ.insert(neiborNode);
                    }
                }
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable(){
        return this.isSolve;
    }

    // min number of moves to solve initial board; -1 if no solution
    public int moves(){
        return this.move;
    }

    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution(){
        if (this.isSolve){
            return this.solutionQueue;
        }else{
            return null;
        }

    }
}
