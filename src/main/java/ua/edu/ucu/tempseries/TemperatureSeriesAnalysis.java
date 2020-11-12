package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final int CRITICAL_TEMP = -237;
    private double[] temperatures;
    private int temperaturesCount;

    public TemperatureSeriesAnalysis() {
        this.temperatures = new double[0];
        this.temperaturesCount = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp: temperatureSeries) {
            if (temp < CRITICAL_TEMP) {
                throw new InputMismatchException();
            }
        }

        this.temperatures = temperatureSeries;
        this.temperaturesCount = temperatureSeries.length;
    }

    public double average() {
        checkArgument();

        double sum = 0;
        for (int i = 0; i < temperaturesCount; i++) {
            sum += temperatures[i];
        }
        return sum / temperaturesCount;
    }

    public double deviation() {
        double value = 0;
        double aver = average();
        for (int i = 0; i < temperaturesCount; i++) {
            value += (temperatures[i] - aver) * (temperatures[i] - aver);
        }
        value /= temperaturesCount;

        return value;
    }

    public double min() {
        checkArgument();

        double currentMin = temperatures[0];
        for (int i = 1; i < temperaturesCount; i++) {
            if (currentMin > temperatures[i]) {
                currentMin = temperatures[i];
            }
        }
        return currentMin;
    }

    public double max() {
        checkArgument();

        double currentMax = temperatures[0];
        for (int i = 1; i < temperaturesCount; i++) {
            if (currentMax < temperatures[i]) {
                currentMax = temperatures[i];
            }
        }
        return currentMax;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        checkArgument();

        double currentClosest = temperatures[0];
        double differenceLast = Math.abs(tempValue - temperatures[0]);
        double differenceCurrent;

        for (int i = 1; i < temperaturesCount; i++) {
            differenceCurrent = Math.abs(tempValue - temperatures[i]);
            if (differenceCurrent < differenceLast) {
                differenceLast = differenceCurrent;
                currentClosest = temperatures[i];
            }
            else if (differenceCurrent == differenceLast) {
                if (currentClosest < temperatures[i]) {
                    currentClosest = temperatures[i];
                }
            }
        }

        return currentClosest;
    }

    public double[] findTempsLessThen(double tempValue) {
        checkArgument();

        double[] values = new double[temperaturesCount];
        int i = 0;

        for (int j = 0; j < temperaturesCount; j++) {
            if (temperatures[j] < tempValue) {
                values[i] = temperatures[j];
                i++;
            }
        }

        double[] results = new double[i];
        System.arraycopy(values, 0, results, 0, i);

        return results;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        checkArgument();

        double[] values = new double[temperaturesCount];
        int i = 0;

        for (int j = 0; j < temperaturesCount; j++) {
            if (temperatures[j] >= tempValue) {
                values[i] = temperatures[j];
                i++;
            }
        }

        double[] results = new double[i];
        System.arraycopy(values, 0, results, 0, i);

        return results;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (double temp: temps) {
            appendToArray(temp);
        }
        return temperaturesCount;
    }

    private void appendToArray(double value) {
        if (value < CRITICAL_TEMP) {
            throw new InputMismatchException();
        }

        if (temperaturesCount >= temperatures.length) {
            double[] tempArray;
            if (temperaturesCount == 0) {
                tempArray = new double[1];
            } else {
                tempArray = new double[temperaturesCount * 2];
            }
            System.arraycopy(temperatures, 0, tempArray, 0, temperaturesCount);
            this.temperatures = tempArray;
        }
        temperatures[temperaturesCount] = value;
        temperaturesCount++;
    }

    private void checkArgument() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
    }
}
