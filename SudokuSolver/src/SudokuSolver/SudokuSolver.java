package SudokuSolver;

public class SudokuSolver {

    private static final int GRID_SIZE = 9; //Should always be result of a number powered by 2 (1,4,9,16, etc.)

    public static void main(String[] args){
        int [][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        initCheck(board, GRID_SIZE);
        printBoard(board);
        solveBoard(board);
        printBoard(board);

    }

    private static final void initCheck(int[][] board, int gridSize){
        if(isBoardValid(board, gridSize) && isPerfectSquare((double)gridSize)){
            System.out.println("Init check successful, starting to solve..");
        } else {
            System.out.println("Init check unsuccessful, closing the program");
            System.exit(1);
        }
    }

    private static final boolean isBoardValid(int[][] board, int gridSize){
        if(board.length != gridSize){
            return false;
        }
        for(int i = 0; i < gridSize; i++){
            if(board[i].length != gridSize){
                return false;
            }
        }
        return true;
    }

    private static final boolean isPerfectSquare(double number) {
        double squared = Math.sqrt(number);
        return (squared - Math.floor(squared) == 0);
    }

    private static void printBoard(int[][] board) {
        for(int row = 0; row < GRID_SIZE; row++){
            if(row % Math.sqrt(GRID_SIZE) == 0){
                System.out.print("\n");
            }
            System.out.print("\n");
            for(int column = 0; column < GRID_SIZE; column++){
                if(column % Math.sqrt(GRID_SIZE) == 0){
                    System.out.print(" ");
                }
                System.out.print(board[row][column] + " ");
            }
        }
    }

    private static final boolean solveBoard(int[][] board){
        for(int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if(board[row][column] == 0){
                    for(int triedNumber = 1; triedNumber <= GRID_SIZE; triedNumber++){
                        if(isValidNumber(board, triedNumber, row, column)){
                            board[row][column] = triedNumber;

                            if(solveBoard(board)){
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static final boolean isValidNumber(int[][] board, int number, int row, int column){
        return !isInRow(board, number, row) &&
                !isInColumn(board, number, column) &&
                !isInBox(board, number, row, column);
    }

    private static boolean isInBox(int[][] board, int number, int row, int column) {
        int boxEdgeLength = (int)Math.sqrt(GRID_SIZE);
        int cornerRow = row / boxEdgeLength * boxEdgeLength;
        int cornerColumn = column / boxEdgeLength * boxEdgeLength;
        for(int x = cornerRow; x < cornerRow + boxEdgeLength; x++){
            for(int y = cornerColumn; y < cornerColumn + boxEdgeLength; y++){
                //System.out.println("Happens " + x + "," +  y);
                if(board[x][y] == number){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isInColumn(int[][] board, int number, int column){
        for(int row = 0; row < GRID_SIZE; row++){
            if(number == board[row][column]){
                return true;
            }
        }
        return false;
    }

    private static boolean isInRow(int[][] board, int number, int row) {
        for(int column = 0; column < GRID_SIZE; column++){
            if(number == board[row][column]){
                return true;
            }
        }
        return false;
    }
}
