import javafx.util.Pair;

import java.util.Map;

/**
 * Created by Anton on 07.06.2014.
 */


public class DataRow {
    public int counter;
    public Map<SensorNames, Float> sensors;
    public Pair<Integer, Integer> gyro;
    public float timestamp;
    public int funcId;
    public int funcValue;
    public int marker;
    public int syncSignal;
    static public enum SensorNames {
        AF3,F7,F3, FC5, T7, P7, O1, O2,P8, T8, FC6, F4,F8, AF4
    }
    public DataRow() {}

    public DataRow(int counter, Map<SensorNames, Float> sensors, Pair<Integer, Integer> gyro, float timestamp, int funcId, int funcValue, int marker, int syncSignal) {
        this.counter = counter;
        this.sensors = sensors;
        this.gyro = gyro;
        this.timestamp = timestamp;
        this.funcId = funcId;
        this.funcValue = funcValue;
        this.marker = marker;
        this.syncSignal = syncSignal;
    }
}
