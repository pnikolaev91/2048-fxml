package sample;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

public class Driver {
    private int size = 4;
    private int[][] date;
    private Random rnd = new Random();
    private boolean stop = false;


    public void rotation() {
        int[][] date1 = new int[size][size];
        for (int i = 0; i < date.length; i++) {
            for (int j = 0; j < date[i].length; j++) {
                date1[date.length - 1 - j][i] = date[i][j];
            }
        }
        date = date1;
    }

    public int[][] getDate() {
        return date;
    }

    public Driver() {
        init();
        System.out.println(this);
    }

    public void init() {
        date = new int[size][size];
        setRandomValue();
        setRandomValue();
    }

    public boolean existFreeSlot() {
        for (int[] ints : date) {
            for (int aInt : ints) {
                if (aInt == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean setRandomValue() {
        if (!existFreeSlot()) {
            return false;
        }
        int i;
        int i1;
        do {
            i = rnd.nextInt(date.length);
            i1 = rnd.nextInt(date.length);
        }
        while (date[i][i1] != 0);
        date[i][i1] = rnd.nextBoolean() ? 2 : 4;
        return true;
    }

    public int keyPressed(int keyCode, boolean checkSum) {
        int sum = 0;
        switch (keyCode) {
            case KeyEvent.VK_UP:
                rotation();
                sum = keyPressed(KeyEvent.VK_LEFT, checkSum);
                rotation();
                rotation();
                rotation();
                break;
            case KeyEvent.VK_DOWN:
                rotation();
                rotation();
                rotation();
                sum = keyPressed(KeyEvent.VK_LEFT, checkSum);
                rotation();
                break;
            case KeyEvent.VK_LEFT:
                int flag = -1;
                for (int[] ints : date) {
                    int b = 0;
                    boolean merge = false;
                    for (int i = 0; i < ints.length - 1; i++) {
                        if (ints[b] == 0) {
                            if (ints[b + 1] != 0) {
                                flag = 0;
                            }
                            System.arraycopy(ints, b + 1, ints, b, ints.length - 1 - b);
                            ints[ints.length - 1] = 0;
                        } else {
                            if (!merge) {
                                if (ints[b] == ints[b + 1]) {
                                    flag = 0;
                                    ints[b + 1] = ints[b + 1] << 1;
                                    sum += ints[b + 1];
                                    System.arraycopy(ints, b + 1, ints, b, ints.length - 1 - b);
                                    ints[ints.length - 1] = 0;
                                    merge = true;
                                } else if (b != 0 && ints[b - 1] == ints[b]) {
                                    flag = 0;
                                    ints[b] = ints[b] << 1;
                                    sum += ints[b];
                                    System.arraycopy(ints, b, ints, b - 1, ints.length - b);
                                    ints[ints.length - 1] = 0;
                                    merge = true;
                                }
                            } else {
                                merge = false;
                            }
                            b++;
                        }
                    }

                    if (!merge && b != 0 && ints[b - 1] == ints[b]) {
                        ints[b] = ints[b] << 1;
                        sum += ints[b];
                        System.arraycopy(ints, b, ints, b - 1, ints.length - b);
                        ints[ints.length - 1] = 0;
                    }
                }
                sum += flag;
                if (!checkSum) {
                    if (sum >= 0) {
                        setRandomValue();
                    } else if (sum == -1 && !existFreeSlot()) {
                        stop = true;
                    }
                }

                break;
            case KeyEvent.VK_RIGHT:
                rotation();
                rotation();
                sum = keyPressed(KeyEvent.VK_LEFT, checkSum);
                rotation();
                rotation();
                break;
            case 0:
                stop = true;
                break;
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] ints : date) {
            stringBuilder.append(Arrays.toString(ints)).append("\n");
        }
        return stringBuilder.toString();
    }
}
