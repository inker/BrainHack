import javafx.util.Pair;
import sun.plugin.dom.exception.WrongDocumentException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Anton on 07.06.2014.
 */
public class DataArray {
    static public enum FileType {
        FULL, ON, OFF
    }
    public FileType fileType;
    public List<DataRow> dataRows;

    public DataArray(String filePath) throws IOException {
        parseFromFile(FileSystems.getDefault().getPath(filePath));
    }

    public DataArray (Path filePath) throws IOException {
        parseFromFile(filePath);
    }

    private void parseFromFile(Path filePath) throws IOException {
        if (filePath.endsWith("on.log")) {
            fileType = FileType.ON;
        } else if (filePath.endsWith("off.log")) {
            fileType = FileType.OFF;
        } else if (filePath.endsWith("full.log")) {
            fileType = FileType.FULL;
        } else {
            throw new WrongDocumentException("wrong file name");
        }
        BufferedReader br = new BufferedReader(new FileReader(filePath.toString()));
        String line;
        dataRows = new ArrayList<DataRow>(256);
        line = br.readLine(); // skip the first string
        while ((line = br.readLine()) != null) {
            String[] stringArray = line.split(",");
            DataRow dataRow = new DataRow();
            dataRow.counter = Integer.parseInt(stringArray[0]);
            dataRow.sensors = new HashMap<DataRow.SensorNames, Float>();
            dataRow.sensors.put(DataRow.SensorNames.AF3, Float.parseFloat(stringArray[1]));
            dataRow.sensors.put(DataRow.SensorNames.F7, Float.parseFloat(stringArray[2]));
            dataRow.sensors.put(DataRow.SensorNames.F3, Float.parseFloat(stringArray[3]));
            dataRow.sensors.put(DataRow.SensorNames.FC5, Float.parseFloat(stringArray[4]));
            dataRow.sensors.put(DataRow.SensorNames.T7, Float.parseFloat(stringArray[5]));
            dataRow.sensors.put(DataRow.SensorNames.P7, Float.parseFloat(stringArray[6]));
            dataRow.sensors.put(DataRow.SensorNames.O1, Float.parseFloat(stringArray[7]));
            dataRow.sensors.put(DataRow.SensorNames.O2, Float.parseFloat(stringArray[8]));
            dataRow.sensors.put(DataRow.SensorNames.P8, Float.parseFloat(stringArray[9]));
            dataRow.sensors.put(DataRow.SensorNames.T8, Float.parseFloat(stringArray[10]));
            dataRow.sensors.put(DataRow.SensorNames.FC6, Float.parseFloat(stringArray[11]));
            dataRow.sensors.put(DataRow.SensorNames.F4, Float.parseFloat(stringArray[12]));
            dataRow.sensors.put(DataRow.SensorNames.F8, Float.parseFloat(stringArray[13]));
            dataRow.sensors.put(DataRow.SensorNames.AF4, Float.parseFloat(stringArray[14]));
            dataRow.gyro = new Pair<Integer, Integer>(Integer.parseInt(stringArray[15]), Integer.parseInt(stringArray[16]));
            dataRow.timestamp = Float.parseFloat(stringArray[17]);
            dataRow.funcId = Integer.parseInt(stringArray[18]);
            dataRow.funcValue = Integer.parseInt(stringArray[19]);
            dataRow.marker = Integer.parseInt(stringArray[20]);
            dataRow.syncSignal = Integer.parseInt(stringArray[21]);
            dataRows.add(dataRow);
        }

    }

    public DataRow getDataRow(int index) {
        return dataRows.get(index);
    }

    public void export(Path filePath) {
        throw new NotImplementedException();
    }
}
