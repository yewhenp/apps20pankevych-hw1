package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test
    public void testAverageWithEmptyInit() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        double expResult = -1.0;
        seriesAnalysis.addTemps(-1.0);

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.average();
    }


    @Test(expected = InputMismatchException.class)
    public void testInputMismatchException() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(10.0);
        seriesAnalysis.addTemps(0.0);
        seriesAnalysis.addTemps(3.0);
        seriesAnalysis.addTemps(-295.0);
    }

    @Test(expected = InputMismatchException.class)
    public void testInputMismatchExceptionInInti() {
        double[] temperatureSeries = {10.0, 1.0, 0.0, -299.3, 44.5};
        new TemperatureSeriesAnalysis(temperatureSeries);
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testAverageWithEmptyArrayAndElemAdding() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        seriesAnalysis.addTemps(3.0, -5.0);
        seriesAnalysis.addTemps(1.0, 5.0);


        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test
    public void testDeviation() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 14.0;

        seriesAnalysis.addTemps(3.0, -5.0);
        seriesAnalysis.addTemps(1.0, 5.0);


        double actualResult = seriesAnalysis.deviation();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.0;

        seriesAnalysis.addTemps(3.0, -5.0);
        seriesAnalysis.addTemps(1.0, 5.0);


        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 5.0;

        seriesAnalysis.addTemps(3.0, -5.0);
        seriesAnalysis.addTemps(1.0, 5.0);


        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        seriesAnalysis.addTemps(3.0, -5.0, -1.0);
        seriesAnalysis.addTemps(1.0, 5.0);


        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.0;

        seriesAnalysis.addTemps(3.0, -5.0, -1.0);
        seriesAnalysis.addTemps(1.0, 5.0);


        double actualResult = seriesAnalysis.findTempClosestToValue(-4.0);
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-5.0, -1.0};

        seriesAnalysis.addTemps(3.0, -5.0, -1.0);
        seriesAnalysis.addTemps(1.0, 0.0, 5.0);


        double[] actualResult = seriesAnalysis.findTempsLessThen(0.0);
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {3.0, 1.0, 0.0, 5.0};

        seriesAnalysis.addTemps(3.0, -5.0, -1.0);
        seriesAnalysis.addTemps(1.0, 0.0, 5.0);


        double[] actualResult = seriesAnalysis.findTempsGreaterThen(0.0);
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics expResult = new TempSummaryStatistics(1.0, 14, -5.0, 5.0);

        seriesAnalysis.addTemps(3.0, -5.0);
        seriesAnalysis.addTemps(1.0, 5.0);


        TempSummaryStatistics actualResult = seriesAnalysis.summaryStatistics();
        assertEquals(expResult, actualResult);
    }
}
