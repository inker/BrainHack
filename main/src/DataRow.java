import javafx.util.Pair;

import java.util.Map;

public class DataRow {
    public int counter;
    public Map<SensorNames, Float> sensors;
    public Pair<Integer, Integer> gyro;
    public float timestamp;
    public int funcId;
    public int funcValue;
    public int marker;
    public int syncSignal;

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
