import javafx.util.Pair;
import sun.plugin.dom.exception.WrongDocumentException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
    private Path filePath;
    public FileType fileType;
    public List<DataRow> dataRows;

    private DataArray() {}

    public DataArray(String filePath) throws IOException {
        parseFromFile(FileSystems.getDefault().getPath(filePath));
    }

    public DataArray (Path filePath) throws IOException {
        parseFromFile(filePath);
    }

    private void parseFromFile(Path filePath) throws IOException {
        this.filePath = filePath;
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
            dataRow.sensors = new HashMap<SensorNames, Float>();
            int i = 0;
            for (SensorNames sensorName : SensorNames.values()) {
                dataRow.sensors.put(sensorName, Float.parseFloat(stringArray[++i]));
            }
            dataRow.gyro = new Pair<Integer, Integer>(Integer.parseInt(stringArray[15]), Integer.parseInt(stringArray[16]));
            dataRow.timestamp = Float.parseFloat(stringArray[17]);
            dataRow.funcId = Integer.parseInt(stringArray[18]);
            dataRow.funcValue = Integer.parseInt(stringArray[19]);
            dataRow.marker = Integer.parseInt(stringArray[20]);
            dataRow.syncSignal = Integer.parseInt(stringArray[21]);
            dataRows.add(dataRow);
        }
        br.close();
    }

    public DataRow getDataRow(int index) {
        return dataRows.get(index);
    }

    public void export(String outFilePath, char delimiter) throws IOException {
        export(FileSystems.getDefault().getPath(outFilePath), delimiter);
        throw new NotImplementedException();
    }

    public void export(Path outFilePath, char delimiter) throws IOException {
        String fileString = outFilePath.getFileName().toString();
        if (fileString.substring(fileString.lastIndexOf('.')).equals(".csv")) {
            PrintWriter out = new PrintWriter(outFilePath.toString());
            for (DataRow row : dataRows) {
                StringBuffer sb = new StringBuffer();
                sb.append(row.counter).append(delimiter);
                for (SensorNames sensorName : SensorNames.values()) {
                    sb.append(row.sensors.get(sensorName)).append(delimiter);
                }
                sb.append(row.gyro.getKey()).append(delimiter)
                        .append(row.gyro.getValue()).append(delimiter)
                        .append(row.timestamp).append(delimiter)
                        .append(row.funcId).append(delimiter)
                        .append(row.funcValue).append(delimiter)
                        .append(row.marker).append(delimiter)
                        .append(row.syncSignal).append(delimiter);
                out.println(sb);
            }
        } else {
            throw new WrongDocumentException("wrong file format, CSV required");
        }
    }

    public void split() throws IOException {
        // to be implemented
        DataArray onArray = new DataArray();
        DataArray offArray = new DataArray();
        String parentPathString = filePath.getParent().toString();
        Path onPath = FileSystems.getDefault().getPath(parentPathString, "on.log");
        Path offPath = FileSystems.getDefault().getPath(parentPathString, "off.log");
        onArray.export(onPath, ',');
        offArray.export(offPath, ',');
        throw new NotImplementedException();
    }
}
