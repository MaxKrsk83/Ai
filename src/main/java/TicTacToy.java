import java.util.Arrays;

public class TicTacToy {
    public static int SIZE = 10;
    public static int DOTS_TO_WIN = 3;
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
//        map[2][4] = DOT_AI;
        map[4][4] = DOT_AI;
//        map[2][0] = DOT_AI;
//        map[4][0] = DOT_AI;
        aiTurn();
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
        int [][] mask = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(map[i][j] == DOT_EMPTY) {
                    mask[i][j] = findMove(i, j);
                }else{
                    mask[i][j] = 0;
                }
            }
        }
        printArr(mask);
    }
    public static int findMove(int x, int y){
        int ret = DOTS_TO_WIN;
        int tmp = findMoveН(x,y);
        if (tmp>0 & tmp < ret){
            ret = tmp;
        }
        return ret;
    }
    public static int findMoveН(int x, int y){
        int count = DOTS_TO_WIN;
        for (int i = -DOTS_TO_WIN + 1; i <DOTS_TO_WIN ; i++) {
//            if (!(y+i >= 0 & y+i < DOTS_TO_WIN) & !(x+i >= 0 & x+i < DOTS_TO_WIN)){
//                continue;
//            }
            int tmp = checkH(x,y+i);
            if (count > tmp & tmp > 0){
                count = tmp;
            }
//            tmp = checkV(x+i,y);
//            if (count > tmp & tmp > 0){
//                count = tmp;
//            }
//            tmp = checkD1(x+i,y+i);
//            if (count > tmp & tmp > 0){
//                count = tmp;
//            }
//            int tmp = checkD2(x+i,y+i);
//            if (count > tmp & tmp > 0){
//                count = tmp;
//            }

        }

        return count;
    }
    public static int checkH(int x, int y){
        int count = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (y + i < SIZE & y + i >=0){
                if (map[x][y + i] == DOT_EMPTY){
                    count++;
                } if (map[x][y + i] == DOT_HUM){
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
    public static int checkV(int x, int y){
        int count = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (x + i < SIZE & x + i >=0){
                if (map[x + i][y] == DOT_EMPTY){
                    count++;
                } if (map[x + i][y] == DOT_HUM){
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
