import java.util.Stack;
import java.util.Arrays;
public class Board {
    final private int n;
    final private int[][] tiles;
    public Board(int[][] blocks) {
        n = blocks.length;
        tiles = new int[n][];
        for (int i=0; i<n; i++){
            tiles[i] = Arrays.copyOf(blocks[i], n);
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }


    public int dimension() {
        return this.n;
    }

    public int hamming() {
        int f = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n && i + j < 2*n -2; j++) {
                if (tiles[i][j] != (i * n + j + 1)) {
                    f ++ ;
                }
            }
        }
        return f;
    }
    public int manhattan() {
        int f = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int e = tiles[i][j];
                if (e != 0) {
                    e = e - 1;
                    int r = e / n;
                    int c = e % n;
                    f = f + Math.abs(r - i) + Math.abs(c - j);
                }
            }
        }
        return f;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public boolean equals(Object y){
        if (y == this) return true;

        if (y == null) return false;

        if (y.getClass() != this.getClass()){
            return false;
        }

        Board thatBoard = (Board)y;
        if (this.n != thatBoard.n){
            return false;
        }

        int[][] arr_thatBoard = thatBoard.tiles;
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                if (tiles[i][j] != arr_thatBoard[i][j]){
                    return false;
                }
            }
        }
        return true;
    }


    public Iterable<Board> neighbors() {
        int blank_i = n;
        int blank_j = n;
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                if (tiles[i][j] == 0){
                    //this is where the blank is
                    blank_i = i;
                    blank_j = j;
                }
            }
        }
        Stack<Board> q = new Stack<Board>();
        if (blank_j - 1 >= 0){
            int[][] arr_temp = getCopy();
            arr_temp[blank_i][blank_j] = arr_temp[blank_i][blank_j - 1];
            arr_temp[blank_i][blank_j - 1] = 0;
            q.push(new Board(arr_temp));
//    		arr_temp = blocks.clone();
        }
        if (blank_j + 1 < n){
            int[][] arr_temp = getCopy();
            arr_temp[blank_i][blank_j] = arr_temp[blank_i][blank_j + 1];
            arr_temp[blank_i][blank_j + 1] = 0;
            q.push(new Board(arr_temp));
//    		arr_temp = blocks.clone();
        }
        if (blank_i - 1 >= 0){
            int[][] arr_temp = getCopy();
            arr_temp[blank_i][blank_j] = arr_temp[blank_i - 1][blank_j];
            arr_temp[blank_i - 1][blank_j] = 0;
            q.push(new Board(arr_temp));
//    		arr_temp = blocks.clone();
        }
        if (blank_i + 1 < n){
            int[][] arr_temp = getCopy();
            arr_temp[blank_i][blank_j] = arr_temp[blank_i + 1][blank_j];
            arr_temp[blank_i + 1][blank_j] = 0;
            q.push(new Board(arr_temp));
//    		arr_temp = blocks.clone();
        }
        return q;

    }



    public Board twin() {
        int[][] twinBoard = new int[n][n];
        twinBoard = tiles.clone();
        if (tiles[0][0] != 0 && tiles[0][1] != 0){
            int temp = twinBoard[0][0];
            twinBoard[0][0] = twinBoard[0][1];
            twinBoard[0][1] = temp;
        }else{
            int temp = twinBoard[1][0];
            twinBoard[1][0] = twinBoard[1][1];
            twinBoard[1][1] = temp;
        }
        return new Board(twinBoard);

    }

    private int[][] getCopy(){
        int[][] result = new int[n][];
        for (int i=0; i<n; i++){
            result[i] = Arrays.copyOf(tiles[i], n);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
