import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Mosquera on 21.01.2016.
 */
public class VierGewinnLogicTest {

    VierGewinnLogic logic;

    @Before
    public void setUp() throws Exception {
        //Always init the Field or null exception!
		logic = new VierGewinnLogic();
        logic.initializeField();
    }

    @Test
    public void simulatePlayerWin() {
        //player_1_T1
        logic.setPlayerToken(0, 0);
        assertThat("It should be PLAYER_2", logic.PLAYER_1, is(1));
        assertThat("It should be false",logic.checkNeighbourNewToken(0,0),is(false));

        //player_2_T1
        logic.setPlayerToken(1, 0);
        assertThat("It should be PLAYER_1", logic.PLAYER_1, is(0));
        assertThat("It should be false",logic.checkNeighbourNewToken(1,0),is(false));

        //player_1_T2
        logic.setPlayerToken(0, 1);
        assertThat("It should be PLAYER_2", logic.PLAYER_1, is(1));
        assertThat("It should be false",logic.checkNeighbourNewToken(0,1),is(false));

        //player_2_T2
        logic.setPlayerToken(1, 2);
        assertThat("It should be PLAYER_1", logic.PLAYER_1, is(0));
        assertThat("It should be false",logic.checkNeighbourNewToken(1,2),is(false));

        //player_1_T3
        logic.setPlayerToken(0, 2);
        assertThat("It should be PLAYER_2", logic.PLAYER_1, is(1));
        assertThat("It should be false",logic.checkNeighbourNewToken(0,3),is(false));

        //player_2_T3
        logic.setPlayerToken(5, 6);
        assertThat("It should be PLAYER_1", logic.PLAYER_1, is(0));
        assertThat("It should be false",logic.checkNeighbourNewToken(5,6),is(false));

        //player_1_Winning Token
        logic.setPlayerToken(0, 3);
        assertThat("It should be PLAYER_2", logic.PLAYER_1, is(1));
        assertThat("It should be true",logic.checkNeighbourNewToken(0,3),is(true));
        System.out.println("PLAYER_1 WIN");
    }

    @Test
    public void testGetCurrentPlayer() throws Exception {

        //PLAYER_1 will set token first
        logic.setPlayerToken(1, 1);
        //Now it should be player_2 turn now, cause player_1 has the value 1
        assertThat("It should be PLAYER_2", logic.PLAYER_1, is(1));
        //PLAYER_2 will set token now
        logic.setPlayerToken(2, 2);
        //Now vice versa it should be PLAYER_1 now, because PLAYER_1=0 now
        assertThat("It should be PLAYER_1", logic.PLAYER_1, is(0));
    }

    @Test
    public void testCheckNorthAndWin(){
        logic.table[0][0] = 1;
        logic.table[1][0] = 1;
        logic.table[2][0] = 1;
        logic.table[3][0] = 1;
        boolean res = logic.checkNeighbourNewToken(3, 0);
        assertThat("Fail",res,is(true));
    }

    @Test
    public void testCheckSouthAndWin() {
        logic.table[3][0] = 1;
        logic.table[4][0] = 1;
        logic.table[5][0] = 1;
        logic.table[6][0] = 1;
        boolean res = logic.checkNeighbourNewToken(3, 0);
        assertThat("Fail",res,is(true));
    }

    @Test
    public void testCheckWestAndWin() {
        logic.table[0][0] = 1;
        logic.table[0][1] = 1;
        logic.table[0][2] = 1;
        logic.table[0][3] = 1;
        boolean res = logic.checkNeighbourNewToken(0, 3);
        assertThat("Fail",res,is(true));
    }

    @Test
    public void testCheckEastAndWin() {
        logic.table[0][0] = 1;
        logic.table[0][1] = 1;
        logic.table[0][2] = 1;
        logic.table[0][3] = 1;
        boolean res = logic.checkNeighbourNewToken(0, 0);
        assertThat("Fail",res,is(true));
    }

    @Test
    public void testCheckNorthWestAndWin() {
        logic.table[0][0] = 1;
        logic.table[1][1] = 1;
        logic.table[2][2] = 1;
        logic.table[3][3] = 1;
        boolean res = logic.checkNeighbourNewToken(3, 3);
        assertThat("Fail",res,is(true));
    }

    @Test
    public void testCheckSouthWestAndWin() {
        logic.table[3][3] = 1;
        logic.table[4][2] = 1;
        logic.table[5][1] = 1;
        logic.table[6][0] = 1;
        boolean res = logic.checkNeighbourNewToken(3, 3);
        assertThat("Fail",res,is(true));
    }

    @Test
    public void testCheckNorthEasttAndWin() {
        logic.table[3][3] = 1;
        logic.table[2][4] = 1;
        logic.table[1][5] = 1;
        logic.table[0][6] = 1;
        boolean res = logic.checkNeighbourNewToken(3, 3);
        assertThat("Fail",res,is(true));
    }

    @Test
    public void testCheckSouthEasttAndWin() {
        logic.table[3][3] = 1;
        logic.table[2][4] = 1;
        logic.table[1][5] = 1;
        logic.table[0][6] = 1;
        boolean res = logic.checkNeighbourNewToken(3, 3);
        assertThat("Fail",res,is(true));
    }

}