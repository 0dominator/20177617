import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Dominate
 */
public class Sudoku{
    public static void main(String[] args) throws IOException {
        int m = Integer.parseInt(args[1]);
        int n = Integer.parseInt(args[3]);
        String input = args[5];
        String output = args[7];
        int[][] qipan = new int[m][m];
        ArrayList<int[][]> problems=new ArrayList<>();
        BufferedReader bf=new BufferedReader(new FileReader(input));
        for (int s = 0; s < n; s++) {
            for (int i = 0; i < qipan.length; i++) {
                String line = bf.readLine();
                for (int j = 0; j < qipan.length; j++) {
                    qipan[i][j] = line.split(" ")[j].charAt(0) - '0';
                }
            }
            bf.readLine();
            problems.add(qipan);
            start(0, 0, problems.get(s), output);
        }
    }

    public static void start(int row, int line, int[][] qipan, String output) throws IOException {
        if (row == qipan.length){
            File file=new File(output);
            FileWriter out=new FileWriter(file,true);
            for(int i=0;i<qipan.length;i++){
                for(int j=0;j<qipan.length;j++){
                    out.write(qipan[i][j]+" ");
                }
                out.write("\r\n");
            }
            out.write("\r\n");
            out.close();
            return;
        }
        if (qipan[row][line] == 0)
        {
            for (int value = 1; value < qipan.length + 1; value++) {
                if (qipan.length == 3 || qipan.length == 5 || qipan.length == 7) {
                    if (f2(row, line, value, qipan) != 0) {
                        qipan[row][line] = value;

                        if (line < qipan.length - 1) {
                            start(row, line + 1, qipan, output);
                        } else if (row < qipan.length && line == qipan.length - 1) {
                            start(row + 1, 0, qipan, output);
                        }

                        qipan[row][line] = 0;
                    }
                } else {
                    if (f1(row, line, value, qipan) != 0 && f2(row, line, value, qipan) != 0)
                    {

                        qipan[row][line] = value;

                        if (line < qipan.length - 1) {
                            start(row, line + 1, qipan, output);
                        } else if (row < qipan.length && line == qipan.length - 1) {
                            start(row + 1, 0, qipan, output);
                        }

                        qipan[row][line] = 0;
                    }
                }

            }

        } else {
            if (line < qipan.length - 1) {
                start(row, line + 1, qipan, output);
            } else if (row < qipan.length && line == qipan.length - 1) {
                start(row + 1, 0, qipan, output);
            }
        }
    }

    public static int f1(int row, int line, int value, int[][] qipan) {
        if (qipan.length == 4) {
            if (row >= 0 && row <= 1) {
                row = 0;
            }
            if (row >= 2 && row <= 3) {
                row = 2;
            }
            if (line >= 0 && line <= 1) {
                line = 0;
            }
            if (line >= 2 && line <= 3) {
                line = 2;
            }

            for (int i = row; i < row + 2; i++) {
                for (int j = line; j < line + 2; j++) {
                    if (qipan[i][j] == value)
                    {
                        return 0;
                    }
                }
            }
        }
        if (qipan.length == 6) {
            if (row >= 0 && row <= 2) {
                row = 0;
            }
            if (row >= 3 && row <= 5) {
                row = 3;
            }
            if (line >= 0 && line <= 1) {
                line = 0;
            }
            if (line >= 2 && line <= 3) {
                line = 2;
            }
            if (line >= 4 && line <= 5) {
                line = 4;
            }

            for (int i = row; i < row + 3; i++) {
                for (int j = line; j < line + 2; j++) {
                    if (qipan[i][j] == value)
                    {
                        return 0;
                    }
                }
            }
        }
        if (qipan.length == 8) {
            if (row >= 0 && row <= 1) {
                row = 0;
            }
            if (row >= 2 && row <= 3) {
                row = 2;
            }
            if (row >= 4 && row <= 5) {
                row = 4;
            }
            if (row >= 6 && row <= 7) {
                row = 6;
            }
            if (line >= 0 && line <= 3) {
                line = 0;
            }
            if (line >= 4 && line <= 7) {
                line = 4;
            }

            for (int i = row; i < row + 2; i++) {
                for (int j = line; j < line + 4; j++) {
                    if (qipan[i][j] == value)
                    {
                        return 0;
                    }
                }
            }
        }
        if (qipan.length == 9) {
            if (row >= 0 && row <= 2) {
                row = 0;
            }
            if (row >= 3 && row <= 5) {
                row = 3;
            }
            if (row >= 6 && row <= 8) {
                row = 6;
            }
            if (line >= 0 && line <= 2) {
                line = 0;
            }
            if (line >= 3 && line <= 5) {
                line = 3;
            }
            if (line >= 6 && line <= 8) {
                line = 6;
            }

            for (int i = row; i < row + 3; i++) {
                for (int j = line; j < line + 3; j++) {
                    if (qipan[i][j] == value)
                    {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    public static int f2(int row, int line, int value, int[][] qipan) {
        for (int i = 0; i < qipan.length; i++) {
            if (qipan[row][i] == value && i != line) {
                return 0;
            }
            if (qipan[i][line] == value && i != row) {
                return 0;
            }
        }
        return 1;
    }

}