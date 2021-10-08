import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToy {
    public static int SIZE = 9;
    public static int DOTS_TO_WIN = 5;
    public static char[][] map = new char[SIZE][SIZE];
    private static final char DOT_EMPTY = '.';
    private static final char DOT_HUM = 'X';
    private static final char DOT_AI = 'O';
    private static Scanner sc = new Scanner(System.in);
    private static Random rnd = new Random();


    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if(isWin(DOT_HUM)) {
                System.out.println("Победил человек");
                break;
            }
            if(isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if(isWin(DOT_AI)) {
                System.out.println("Победил ИИ");
                break;
            }
            if(isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра окончена");
    }
    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }

    private static boolean checkWin(int x, int y, int cX, int cY, char symbol){
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            int sumX = x + i * cX;
            int sumY = y + i * cY;
            if(sumX < SIZE & sumX >= 0 & sumY < SIZE & sumY >= 0){
                if(map[sumX][sumY] != symbol){
                    return false;
                }
            } else{
                return false;
            }
        }
        return true;
    }
    private static boolean isWin(char symbol) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (checkWin(x,y,1,0,symbol)){
                    return true;
                } else if (checkWin(x,y,0,1,symbol)){
                    return true;
                } else if (checkWin(x,y,-1,0,symbol)){
                    return true;
                } else if (checkWin(x,y,0,-1,symbol)){
                    return true;
                }else if (checkWin(x,y,1,1,symbol)){
                    return true;
                }else if (checkWin(x,y,-1,1,symbol)){
                    return true;
                }else if (checkWin(x,y,1,-1,symbol)){
                    return true;
                }else if (checkWin(x,y,-1,-1,symbol)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE)
            return false;
        if (map[x][y] == DOT_EMPTY)
            return true;
        return false;
    }

    private static void humanTurn() {
        int x;
        int y;

        do {
            System.out.println("Введите координаты в формате Х и У");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_HUM;
    }

    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    public static int findMinCount(int [][] mask){
        int min = DOTS_TO_WIN;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (mask[i][j] < min & mask[i][j] != 0){
                    min = mask[i][j];
                }
            }
        }
        return min;
    }
    public static ArrayList findMoves(int [][] mask, int index){
        ArrayList result = new ArrayList();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(mask[i][j]==index){
                    Point pt = new Point();
                    pt.x = i;
                    pt.y = j;
                    result.add(pt);
                }
            }
        }
        return result;
    }
    public static Point getPoint(ArrayList arr){
        return (Point)arr.get(rnd.nextInt(arr.size()));
    }
    public static void aiTurn(){
        Point pt;
        int [][] wins = fillMask(DOT_HUM);
        int [][] loose = fillMask(DOT_AI);
        int minCountToWins = findMinCount(wins);
        int minCountToLoose = findMinCount(loose);
        ArrayList moveWins = findMoves(wins,minCountToWins);
        ArrayList moveLoose = findMoves(loose,minCountToLoose);
        ArrayList commonMoves = findCommonMoves(moveWins,moveLoose);
        ArrayList combo = findMoves(loose,2);
        if(minCountToWins >= minCountToLoose) {
            if (minCountToLoose==1){
                if (commonMoves.size() > 0 ){
                    pt = getPoint(commonMoves);
                } else {
                    pt = getPoint(moveLoose);
                }
            } else{
                if (combo.size() > 0){
                    pt = findForNear(combo,getPoint(combo));
                }else {
                    pt = getPoint(moveLoose);
                }
            }
        } else {
            if (commonMoves.size() > 0 ){
                pt = getPoint(commonMoves);
            } else {
                pt = getPoint(moveWins);
            }
        }
        map[pt.x][pt.y] = DOT_AI;
    }
    public static Point findForNear(ArrayList arr, Point defaultP){
        Point result;
        result = defaultP;
        for (int i = 0; i <arr.size(); i++) {
            Point p = (Point) arr.get(i);
            if (p.x + 1 >= SIZE || p.x - 1 < 0 || p.y + 1 >= SIZE || p.y - 1 < 0) {
                continue;
            }
            if (map[p.x + 1][p.y] == DOT_HUM) {
                result = p;
                break;
            } else if (map[p.x][p.y + 1] == DOT_HUM) {
                result = p;
                break;
            } else if (map[p.x - 1][p.y] == DOT_HUM) {
                result = p;
                break;
            } else if (map[p.x][p.y - 1] == DOT_HUM) {
                result = p;
                break;
            } else if (map[p.x + 1][p.y + 1] == DOT_HUM) {
                result = p;
                break;
            } else if (map[p.x + 1][p.y - 1] == DOT_HUM) {
                result = p;
                break;
            } else if (map[p.x - 1][p.y + 1] == DOT_HUM) {
                result = p;
                break;
            } else if (map[p.x - 1][p.y - 1] == DOT_HUM) {
                result = p;
                break;
            }
        }
        return result;
    }
    public static ArrayList findCommonMoves(ArrayList arr1, ArrayList arr2){
        ArrayList result = new ArrayList();
        for (int i = 0; i < arr1.size(); i++) {
            for (int j = 0; j < arr2.size(); j++) {
                if (arr1.get(i)==arr2.get(j)){
                    result.add(arr1.get(i));
                }
            }
        }
        return result;
    }
    public static int[][] fillMask(char whoWalks){
        int [][] mask = new int[SIZE][SIZE];
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if(map[x][y]!=DOT_EMPTY){
                    mask[x][y]=0;
                }else{
                    mask[x][y] = check(x,y,whoWalks);
                }
            }
        }
        return mask;
    }
    public static int check(int x, int y, char whoWalks){

        int count = DOTS_TO_WIN;
        int tmp = checkDir(x,y,1,0, whoWalks);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,0,1, whoWalks);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,-1,0, whoWalks);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,0,-1, whoWalks);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,1,1, whoWalks);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,-1,1, whoWalks);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,-1,-1, whoWalks);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        tmp = checkDir(x,y,1,-1, whoWalks);
        if (tmp<count & tmp>0){
            count = tmp;
        }
        return count;
    }
    public static int check1(int x, int y, int cX, int cY, char whoWalks){
        int count = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            int sumX = x + i * cX;
            int sumY = y + i * cY;
            if (!(sumX < SIZE & sumX >=0) || !(sumY < SIZE & sumY >=0) ){
                return 0;
            }
            if (map[sumX][sumY] == DOT_EMPTY){
                    count++;
            } if (map[sumX][sumY] == whoWalks){
                return 0;
            }
        }
        return count;
    }
    public static int checkDir(int x, int y, int cX, int cY, char whoWalks){
        int min = DOTS_TO_WIN;
        for (int i = -DOTS_TO_WIN+1 ; i <= 0; i++) {
            int tmp = check1(x + i * cX,y + i * cY, cX, cY, whoWalks);
            if(tmp < min & tmp > 0){
                min = tmp;
            }
        }
        return min;
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
