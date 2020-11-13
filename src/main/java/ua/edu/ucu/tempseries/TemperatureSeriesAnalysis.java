package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final int CRITICAL_TEMP = -237;
    private static final double DELTA = 0.00000001;
    private double[] temperatures;
    private int temperaturesCount;

    /**
     * Create empty obj
     */
    public TemperatureSeriesAnalysis() {
        this.temperatures = new double[0];
        this.temperaturesCount = 0;
    }

    /**
     * Create obj with given array of temps
     * @param temperatureSeries array of temps
     */
    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp: temperatureSeries) {
            if (temp < CRITICAL_TEMP) {
                throw new InputMismatchException();
            }
        }

        this.temperatures = new double[temperatureSeries.length];
        System.arraycopy(temperatureSeries, 0, temperatures,
                0, temperatures.length);
        this.temperaturesCount = temperatureSeries.length;
    }

    /**
     * Returns average of temps
     * @return average of temps
     */
    public double average() {
        checkArgument();

        double sum = 0;
        for (int i = 0; i < temperaturesCount; i++) {
            sum += temperatures[i];
        }
        return sum / temperaturesCount;
    }

    /**
     * Returns deviation of temps
     * @return deviation of temps
     */
    public double deviation() {
        double value = 0;
        double aver = average();
        for (int i = 0; i < temperaturesCount; i++) {
            value += (temperatures[i] - aver) * (temperatures[i] - aver);
        }
        value /= temperaturesCount;

        return value;
    }

    /**
     * Returns minimal temperature
     * @return minimal temperature
     */
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

    /**
     * Returns maximal temperature
     * @return maximal temperature
     */
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

    /**
     * Returns closest to 0 temperature
     * @return closest to 0 temperature
     */
    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    /**
     * Returns closest to 0 temperature
     * @param tempValue value ro find closest
     * @return closest to 0 temperature
     */
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
            else if (Math.abs(differenceCurrent - differenceLast) < DELTA) {
                if (currentClosest < temperatures[i]) {
                    currentClosest = temperatures[i];
                }
            }
        }

        return currentClosest;
    }

    /**
     * Returns array of temperatures, less than value
     * @param tempValue limit of temperatures
     * @return array of temperatures
     */
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

    /**
     * Returns array of temperatures, greater\equal than value
     * @param tempValue limit of temperatures
     * @return array of temperatures
     */
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

    /**
     * Returns TempSummaryStatistics with main statictic
     * @return TempSummaryStatistics
     */
    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    /**
     * Adds temperatures to array
     * @param temps values of temperature
     * @return number of temperatures in array
     */
    public int addTemps(double... temps) {
        for (double temp: temps) {
            appendToArray(temp);
        }
        return temperaturesCount;
    }

    /**
     * Appends single value to array
     * @param value temperature to append
     */
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

    /**
     * Check if array is not empty
     */
    private void checkArgument() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
    }
}
