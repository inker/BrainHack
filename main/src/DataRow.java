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

    public float getByIndex(int index) {
        switch (index) {
            case 0: return counter;
            case 15: return gyro.getKey();
            case 16: return gyro.getValue();
            case 17: return funcId;
            case 18: return funcValue;
            case 19: return marker;
            case 20: return syncSignal;
            default: {
                if (index < 15) {
                    return sensors.get(SensorNames.values()[index - 1]);
                }
                throw new IndexOutOfBoundsException();
            }
        }
    }
}
