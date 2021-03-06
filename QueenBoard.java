public class QueenBoard {

  private int[][] board;

  public static void main(String[] args) {
    QueenBoard board = new QueenBoard(Integer.parseInt(args[0]));
    //System.out.println(board.solve());
    System.out.println(board);
    System.out.println(board.countSolutions());
    System.out.println(board);
  }

  public QueenBoard(int size) { // loop to add 0 to each spot
    board = new int[size][size];
    for (int row = 0; row < board.length; row ++) {
      for (int col = 0; col < board[row].length; col ++) {
        board[row][col] = 0;
      }
    }
  }

  private boolean addQueen(int row, int col) {
    if (board[row][col] == 0 && row < board.length && col < board[row].length) {
      board[row][col] = -1;
      for (int r = 0; r < board.length; r ++) {
        for (int c = 0; c < board[row].length; c ++) {
          if (r == row && c != col) { // every spot in the row but the queen
            board[r][c] += 1;
          }
          if (c == col && r != row) { // every spot in the col but the queen
            board[r][c] += 1;
          }
          if (c != col && r != row) { // uses slope for diagonals
            if ((((r - row) / (c - col) == 1) || ((r - row) / (c - col) == -1)) && ((r - row) % (c - col) == 0)) {
              board[r][c] += 1;
            }
          }
        }
      }
      return true;
    }
    return false;
  }
  private boolean removeQueen(int row, int col) {
    if (board[row][col] == -1 && row < board.length && col < board[row].length) {
      board[row][col] = 0;
      for (int r = 0; r < board.length; r ++) {
        for (int c = 0; c < board[row].length; c ++) {
          if (r == row && c != col) { // every spot in the row but the queen
            board[r][c] -= 1;
          }
          if (c == col && r != row) { // every spot in the col but the queen
            board[r][c] -= 1;
          }
          if (c != col && r != row) { // uses slope for diagonals
            if ((((r - row) / (c - col) == 1) || ((r - row) / (c - col) == -1)) && ((r - row) % (c - col) == 0)) {
              board[r][c] -= 1;
            }
          }
        }
      }
      return true;
    }
    return false;
  }
  /**
  *@return The output string forboardted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  */
  public String toString() {
    String ans = "";
    for (int row = 0; row < board.length; row ++) {
      for (int col = 0; col < board[row].length; col ++) {
        if (board[row][col] == -1) {
          ans += "Q ";
        } else {
          ans += "_ ";
        }
      }
      ans += "\n";
    }
    return ans;
  }
  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve() {
    for (int row = 0; row < board.length; row ++) {
      for (int col = 0; col < board[row].length; col ++) {
        if (board[row][col] != 0) {
          throw new IllegalStateException("Board contains non-zero value.");
        }
      }
    }
    return solveHelper(0);
  }
  public boolean solveHelper(int col) {
    if (col >= board.length) {
      return true;
    }
    for (int row = 0; row < board.length; row++) {
      if (addQueen(row,col) && solveHelper(col + 1)) {
        return true;
      }
      removeQueen(row,col);
    }
    return false;
  }
  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions() {
    for (int row = 0; row < board.length; row ++) {
      for (int col = 0; col < board[row].length; col ++) {
        if (board[row][col] != 0) {
          throw new IllegalStateException("Board contains non-zero value.");
        }
      }
    }
    return countSolutionsHelper(0);
  }
  public int countSolutionsHelper(int col) {
    int ans = 0;
    if (col >= board.length) {
      return 1;
    }
    for (int row = 0; row < board.length; row++) {
      if (addQueen(row, col)) {
        ans += countSolutionsHelper(col + 1);
      }
      removeQueen(row, col);
    }
    return ans;
  }
}
