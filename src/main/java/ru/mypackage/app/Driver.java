package ru.mypackage.app;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

public class Driver {
    private int size = 4;
    private int[][] data;
    private Random rnd = new Random();

    public Driver(int size) {
        this();
        this.size = size;
    }

    public Driver() {
        init();
        System.out.println(this);
    }

    public void init() {
        data = new int[size][size];
        setRandomValue();
        setRandomValue();
    }

    private void setRandomValue() {
        if (!existFreeSlot()) {
            return;
        }
        int i, j;
        do {
            i = rnd.nextInt(data.length);
            j = rnd.nextInt(data.length);
        }
        while (data[i][j] != 0);
        data[i][j] = rnd.nextBoolean() ? 2 : 4;
    }

    public boolean existFreeSlot() {
        for (int[] ints : data) {
            for (int aInt : ints) {
                if (aInt == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean needNewRandomValue = false;

    public int keyPressed(int keyCode) {
        int sum = 0;
        switch (keyCode) {
            case KeyEvent.VK_UP:
                rotation();
                sum = calculationSumForData();
                rotation();
                rotation();
                rotation();
                break;
            case KeyEvent.VK_DOWN:
                rotation();
                rotation();
                rotation();
                sum = calculationSumForData();
                rotation();
                break;
            case KeyEvent.VK_LEFT:
                sum = calculationSumForData();
                break;
            case KeyEvent.VK_RIGHT:
                rotation();
                rotation();
                sum = calculationSumForData();
                rotation();
                rotation();
                break;
            case 0:
                break;
        }
        if (needNewRandomValue) {
            setRandomValue();
            needNewRandomValue = false;
        }
        return sum;
    }

    public void rotation() {
        int[][] newData = new int[size][size];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                newData[data.length - 1 - j][i] = data[i][j];
            }
        }
        data = newData;
    }

    private int calculationSumForData() {
        int sum = 0;
        for (int[] row : data) {
            sum += calculationSumForLine(row);
        }
        return sum;
    }

    private int calculationSumForLine(int[] row) {
        if (notNeedShift(row)) {
            return 0;
        }
        int sum = 0;
        int position = 0;
        for (int i = 0; i < row.length - 1; i++) {
            shiftArrayToTheLeftIfFirstValueEqualsZero(row, position);
            shiftArrayToTheLeftIfSecondValueEqualsZero(row, position);
            if (row[position + 1] == 0) {
                break;
            }
            if (row[position] == row[position + 1]) {
                sum += row[position] = row[position] << 1;
                row[position + 1] = 0;
                needNewRandomValue = true;
            }
            position++;
        }
        return sum;
    }

    private boolean notNeedShift(int[] row) {
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] == 0 && row[i + 1] != 0
                    || row[i] != 0 && row[i] == row[i + 1])
                return false;
        }
        return true;
    }

    private void shiftArrayToTheLeftIfFirstValueEqualsZero(int[] row, int position) {
        for (int i = 0; i < row.length; i++) {
            if (row[position] != 0) {
                break;
            }
            System.arraycopy(row, position + 1, row, position, row.length - 1 - position);
            row[row.length - 1] = 0;
            needNewRandomValue = true;
        }
    }

    private void shiftArrayToTheLeftIfSecondValueEqualsZero(int[] row, int position) {
        for (int i = 0; i < row.length; i++) {
            if (row[position + 1] != 0) {
                break;
            }
            System.arraycopy(row, position + 2, row, position + 1, row.length - 2 - position);
            row[row.length - 1] = 0;
            needNewRandomValue = true;
        }
    }

    public int[][] getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] ints : data) {
            stringBuilder.append(Arrays.toString(ints)).append("\n");
        }
        return stringBuilder.toString();
    }
}
