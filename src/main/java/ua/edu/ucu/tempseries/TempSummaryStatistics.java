package ua.edu.ucu.tempseries;

import lombok.Getter;

import java.util.Objects;

public final class TempSummaryStatistics {
    @Getter
    private final double avgTemp;
    @Getter
    private final double devTemp;
    @Getter
    private final double minTemp;
    @Getter
    private final double maxTemp;

    public TempSummaryStatistics(double avgTemp, double devTemp,
                                 double minTemp,double maxTemp) {
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TempSummaryStatistics that = (TempSummaryStatistics) o;
        return Double.compare(that.avgTemp, avgTemp) == 0
                && Double.compare(that.devTemp, devTemp) == 0
                && Double.compare(that.minTemp, minTemp) == 0
                && Double.compare(that.maxTemp, maxTemp) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(avgTemp, devTemp, minTemp, maxTemp);
    }
}
