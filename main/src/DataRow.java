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

    public DataRow() {}

    public float getByIndex(int index) {
        switch (index) {
            case 0: return counter;
            case 15: return gyro.getKey();
            case 16: return gyro.getValue();
            case 17: return timestamp;
            case 18: return funcId;
            case 19: return funcValue;
            case 20: return marker;
            case 21: return syncSignal;
            default: {
                if (index < 15) {
                    return sensors.get(SensorNames.values()[index - 1]);
                }
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public void setByIndex(int index, float value) {
        switch (index) {
            case 0: counter = (int)value; break;
            case 15: {
                int oldV = gyro.getValue();
                gyro = new Pair<Integer, Integer>((int)value, oldV);
                break;
            }
            case 16: {
                int oldKey = gyro.getKey();
                gyro = new Pair<Integer, Integer>(oldKey, (int)value);
                break;
            }
            case 17: timestamp = value; break;
            case 18: funcId = (int)value; break;
            case 19: funcValue = (int)value; break;
            case 20: marker = (int)value; break;
            case 21: syncSignal = (int)value; break;
            default: {
                if (index < 15) {
                    sensors.put(SensorNames.values()[index - 1], value);
                }
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public float[] toArray() {
        float[] array = {
            counter, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f,
            gyro.getKey(), gyro.getValue(), funcId, funcValue, marker, syncSignal
        };
        for (int i = 0; i < 14; ++i) {
            array[i + 1] = sensors.get(SensorNames.values()[i]);
        }
        return array;
    }

    public DataRow add(DataRow row) {
        for (int i = 0; i < 22; ++i) {
            setByIndex(i, getByIndex(i) + row.getByIndex(i));
        }
        return this;
    }

    public DataRow subtract(DataRow row) {
        for (int i = 0; i < 22; ++i) {
            setByIndex(i, getByIndex(i) - row.getByIndex(i));
        }
        return this;
    }
}
