import java.util.Arrays;

public class TicTacToy {
    public static int SIZE = 13;
    public static int DOTS_TO_WIN = 4;
    public static char[][] map = new char[SIZE][SIZE];
    private static final char DOT_EMPTY = '.';
    private static final char DOT_HUM = 'X';
    private static final char DOT_AI = 'O';

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
/*
        int countMaskOfMap = countOfArray(map);
        //System.out.println("Количество размещений маски на карте: " + countMaskOfMap*countMaskOfMap);
        int [][] mask = new int[DOTS_TO_WIN][DOTS_TO_WIN];
        initMask(mask);
        fillMap(map,mask,countMaskOfMap);
        //printArr(mask);
        printArr(map);
*/
        //printArr(map);
        initMap();

//        map[2][2] = DOT_HUM;
//        map[3][3] = DOT_HUM;
//        map[0][2] = DOT_HUM;
//        map[0][0] = DOT_AI;
//        map[2][5] = DOT_AI;
//        map[4][4] = DOT_AI;
        map[6][6] = DOT_AI;
        map[5][0] = DOT_AI;
        map[2][0] = DOT_AI;
        map[4][0] = DOT_AI;
        aiTurn();
//        int f = findMove(8,8);
        System.out.println("------------------");
        printArr(map);
    }
    private static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void aiTurn(){
        int [][] mask = fillMask();
        printArr(mask);
    }
    public static int[][] fillMask(){
        int [][] mask = new int[SIZE][SIZE];
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if(map[x][y]!=DOT_EMPTY){
                    mask[x][y]=0;
                }else{
                    mask[x][y] = check(x,y);
                }
            }
        }
        return mask;
    }
    public static int check(int x, int y){
        int count = DOTS_TO_WIN;
        int tmp = checkDir(x,y,1,0);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,0,1);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,-1,0);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,0,-1);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,1,1);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,-1,1);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,-1,-1);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,1,-1);
        if (tmp<count & tmp>0){
            count = tmp;
        }

        return count;
    }
    public static int checkDir(int x, int y, int cX, int cY){
        int count = 0;
//проверка
        for (int i = -DOTS_TO_WIN + 1; i < DOTS_TO_WIN; i++) {
            int sumX = x + i * cX;
            int sumY = y + i * cY;
            if (!(sumX < SIZE & sumX >=0) || !(sumY < SIZE & sumY >=0) ){
                return 0;
            }
            if (map[sumX][sumY] == DOT_EMPTY){
                    count++;
            } if (map[sumX][sumY] == DOT_HUM){
                return 0;
            }
        }
        return count;
    }
    public static int checkD1(int x, int y){
        int count = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            int sumX = x + i;
            int sumY = y + i;
            if (sumX < SIZE & sumX >=0 & sumY < SIZE & sumY >=0){
                if (map[sumX][sumY] == DOT_EMPTY){
                    count++;
                } if (map[sumX][sumY] == DOT_HUM){
                    count = 0;
                    break;
                }
            } else{
                count = 0;
                break;
            }
        }
        return count;
    }
    public static int checkD2(int x, int y){
        int count = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            int sumX = x - i;
            int sumY = y + i;
            if (sumX < SIZE & sumX >=0 & sumY < SIZE & sumY >=0){
                if (map[sumX][sumY] == DOT_EMPTY){
                    count++;
                } if (map[sumX][sumY] == DOT_HUM){
                    count = 0;
                    break;
                }
            } else{
                count = 0;
                break;
            }
        }
        return count;
    }


    public static void fillMap(int [][] map,int [][] mask, int count){
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                fillMapByMask(map,mask,i,j);
            }
        }
    }
    public static void fillMapByMask(int [][] map,int [][] mask, int x, int y){
        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[i].length; j++) {
                if (map[i+x][j+y]==0){
                    map[i+x][j+y] = map[i+x][j+y] + mask[i][j];
                } else{
                    map[i+x][j+y] = map[i+x][j+y] + mask[i][j] - 1;
                }
            }
        }
    }
    public static int countOfArray(int arr[][]){
        return arr.length - DOTS_TO_WIN + 1;
    }
    public static void fillMaskH(int arr[][]){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = arr[i][j] + 1;
            }
        }
    }
    public static void fillMaskV(int arr[][]){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[j][i] = arr[j][i] + 1;
            }
        }
    }
    public static void fillMaskD(int arr[][]){
        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = arr[i][i] + 1;
        }
    }
    public static void fillMaskD1(int arr[][]){
        for (int i = 0; i < arr.length; i++) {
            arr[arr.length - i -1][i] = arr[arr.length - i - 1][i] + 1;
        }
    }
    public static void initMask(int arr[][]){
        fillMaskH(arr);
        fillMaskV(arr);
        fillMaskD(arr);
        fillMaskD1(arr);
    }

    public static void printArr(int arr[][]){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }
    public static void printArr(char arr[][]){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }
}
