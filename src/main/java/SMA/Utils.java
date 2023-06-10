package SMA;

public class Utils {

    public static final double ALPHA = 0.1;
    public static final double GAMMA = 0.9;
    public static final int AGENTS_NUMBER = 5;
    public static final int MAX_EPOCH = 10000;
    public static final int GRID_SIZE = 6;
    public static final int ACTION_SIZE = 4;

    public static int[][] GRID = new int[][]{
            {0,0,0,0,0,0},
            {0,0,0,0,0,-1},
            {0,0,0,-1,0,0},
            {0,0,0,0,0,0},
            {0,-1,0,-1,0,0},
            {-1,0,0,0,0,1}

    };

    public static double[][] QTABLE = new double[GRID_SIZE * GRID_SIZE][ACTION_SIZE];
    public static int[][] ACTIONS = new int[][]{
            {0, -1}, // gauche
            {0, 1},  // droite
            {1, 0},  // bas
            {-1, 0}   // haut
    };
    public static int stateI, stateJ;


}
