/**
 * Created by Mosquera on 21.01.2016.
 */
public class VierGewinnLogic
{

    public int table[][]; //0 - Empty, 1- player_1, 2-player_2 | 7R 14C
    public final int ROWS = 7;
    public final int COLUMNS = 14;
    public  int PLAYER_1 = 0;  //Start Value of p1 is 0 => new game
    public  int PLAYER_2=2;


    /**
     * Ask who is playing
     * @return the current player
     */
    public int getCurrentPlayer() {
        if (PLAYER_1 == 0) {
            PLAYER_1=1;
            return 1;
        }
        PLAYER_1--;
        return PLAYER_2;
    }

    /**
     *Always Before playing! Initialize the field and set the default value 0
     */
    public void initializeField()
    {
        table = new int[ROWS][COLUMNS];
        for (int r = 0; r<ROWS;r++) {
            for (int c = 0; c < COLUMNS; c++) {
                table[r][c] = 0;
            }
        }
    }

    /**
     * set 1 or 2 to the block with the same row and col
     * @param row
     * @param col
     * @return true => block is occupied with the correct player token ( 1 or 2 )
     *         false => block is already occupied, retry!
     */
    public boolean setPlayerToken(int row, int col) {
        if (table[row][col] == 0) {
            table[row][col]=getCurrentPlayer();

            return true;
        }
        return false;
    }

    /**
     * check the nearby tokens of the new block
     * check if the new token is the winnning token
     * @param curTokenRow
     * @param curTokenCol
     * @return
     */
    public boolean checkNeighbourNewToken(int curTokenRow, int curTokenCol) {
        //Direction: N,W,S,E
        if (checkNorth(curTokenRow, curTokenCol)) {
            return true;
        }
        if (checkSouth(curTokenRow, curTokenCol)) {
            return true;
        }
        if (checkWest(curTokenRow, curTokenCol)) {
            return true;
        }
        if (checkEast(curTokenRow, curTokenCol)) {
            return true;
        }
        //Direction: NW,NE,SW,SE
        if (checkNorthEast(curTokenRow, curTokenCol)) {
            return true;
        }
        if (checkNorthWest(curTokenRow, curTokenCol)) {
            return true;
        }
        if (checkSouthWest(curTokenRow, curTokenCol)) {
            return true;
        }
        if (checkSouthEast(curTokenRow, curTokenCol)) {
            return true;
        }
        return false;

    }

    /**
     * check 3-step south-east
     * true = gameover -> currentPlayer is winner
     * @param curTokenRow
     * @param curTokenCol
     * @return
     */
    private boolean checkSouthEast(int curTokenRow, int curTokenCol) {

        String totalIs4 = "";
        if (curTokenRow <= (ROWS-3) && curTokenCol<=(COLUMNS-3)) {
            int startPos = curTokenCol;
            int endPos=curTokenCol+3;
            int nextPosRow = curTokenRow+3; //  will decrease by 1 to move up, it will start 3 step down from the originalPos
            for (int c = startPos; c <= endPos; c++) {
                totalIs4 += String.valueOf(table[nextPosRow][c]);
                nextPosRow--; // Position will move 1 Row UP
            }
            if (checkString(totalIs4)) {
                return true;
            }
        }
        return false;

    }

    /**
     * check 3-step south-west
     * true = gameover -> currentPlayer is winner
     * @param curTokenRow
     * @param curTokenCol
     * @return
     */
    private boolean checkSouthWest(int curTokenRow, int curTokenCol) {
        String totalIs4 = "";
        if (curTokenRow <= (ROWS-3) && curTokenCol>=3) {
            int startPos = curTokenCol-3;
            int endPos=curTokenCol;
            int nextPosRow = curTokenRow+3;
            for (int c = startPos; c <= endPos; c++) {
                totalIs4 += String.valueOf(table[nextPosRow][c]);
                nextPosRow--; // Position will move 1 Row UP
            }
            if (checkString(totalIs4)) {
                return true;
            }

        }
        return false;

    }

    /**
     * check 3-step north-west
     * true = gameover -> currentPlayer is winner
     * @param curTokenRow
     * @param curTokenCol
     * @return
     */
    private boolean checkNorthWest(int curTokenRow, int curTokenCol) {
        String totalIs4 = "";
        if (curTokenRow >= 3 && curTokenCol>=3) {
            int startPos = curTokenCol;
            int endPos=curTokenCol-3;
            int nextPosRow = curTokenRow; // will decrease by 1 to move up
            for (int c = startPos; c >= endPos; c--) {
                totalIs4 += String.valueOf(table[nextPosRow][c]);
                nextPosRow--; // Position will move 1 Row UP
            }
            if (checkString(totalIs4)) {
                return true;
            }

        }
        return false;

    }

    /**
     * check 3-step north-east
     * true = gameover -> currentPlayer is winner
     * @param curTokenRow
     * @param curTokenCol
     * @return
     */
    private boolean checkNorthEast(int curTokenRow, int curTokenCol) {
        String totalIs4 = "";
        if (curTokenRow >= 3&&curTokenCol<=(COLUMNS-3)) {
            int startPos = curTokenCol;
            int endPos=curTokenCol+3;
            int nextPosRow = curTokenRow; // will decrease by 1 to move up
            for (int c = startPos; c <= endPos; c++) {
                totalIs4 += String.valueOf(table[nextPosRow][c]);
                nextPosRow--; // Position will move 1 Row UP
            }
            if (checkString(totalIs4)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check 3-step east
     * true = gameover -> currentPlayer is winner
     * @param curTokenRow
     * @param curTokenCol
     * @return
     */
    private boolean checkEast(int curTokenRow, int curTokenCol) {

        String totalIs4 = "";
        if (curTokenCol <= (COLUMNS-3)) {
            int startPos = curTokenCol;
            int endPos = curTokenCol + 3;
            for (int c = startPos; c <= endPos; c++) {
                totalIs4+=String.valueOf(table[curTokenRow][c]);
            }
            if (checkString(totalIs4)) {
                return true;
            }

        }
        return false;
    }

    /**
     * check 3-step west
     * true = gameover -> currentPlayer is winner
     * @param curTokenRow
     * @param curTokenCol
     * @return
     */
    private boolean checkWest(int curTokenRow, int curTokenCol) {

        String totalIs4="";
        if (curTokenCol >= 3) {
            int startPos = curTokenCol - 3;
            for (int c = startPos; c <= curTokenCol; c++) {
                totalIs4+=String.valueOf(table[curTokenRow][c]);
            }
            if (checkString(totalIs4)) return true;
        }
        return false;
    }

    /**
     * check 3-step south
     * true = gameover -> currentPlayer is winner
     * @param curTokenRow
     * @param curTokenCol
     * @return
     */
    private boolean checkSouth(int curTokenRow, int curTokenCol) {
        String totalIs4="";
        if (curTokenRow <= (ROWS-3)) {
            int startPos = curTokenRow ;
            int endPos=curTokenRow+3;
            for (int r = startPos; r <=endPos; r++) {
                totalIs4 += String.valueOf(table[r][curTokenCol]);
            }
            if (checkString(totalIs4)) return true;
        }
        return false;
    }

    /**
     * check 3-step north
     * true = gameover -> currentPlayer is winner
     * @param curTokenRow
     * @param curTokenCol
     * @return
     */
    private boolean checkNorth(int curTokenRow, int curTokenCol) {
        String totalIs4="";
        if (curTokenRow >= 3) {
            int startPos = curTokenRow - 3;
            for (int r = startPos; r <=curTokenRow; r++) {
                totalIs4 += String.valueOf(table[r][curTokenCol]);
            }
            if (checkString(totalIs4)) return true;
        }
        return false;
    }

    /**check if the given string matches the pattern(1111 or 2222)
     * @param totalIs4
     * @return true if 1111 or 2222
     */
    private boolean checkString(String totalIs4) {
        if (totalIs4.contentEquals("1111")) {
            return true;
        }
        if (totalIs4.contains("2222")) {
            return true;
        }
        return false;
    }

}
