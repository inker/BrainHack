import javafx.util.Pair;
import sun.plugin.dom.exception.WrongDocumentException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Anton on 07.06.2014.
 */
public class DataArray {
    enum FileType {
        FULL, ON, OFF
    }
    FileType fileType;
    List<DataRow> dataRows;
    DataArray (String filePath) throws IOException {
        String fileName = filePath.substring(8);
        if (fileName.equals("on.log")) {
            fileType = FileType.ON;
        } else if (fileName.equals("off.log")) {
            fileType = FileType.OFF;
        } else if (fileName.equals("full.log")) {
            fileType = FileType.FULL;
        } else {
            throw new WrongDocumentException("wrong file name");
        }
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        dataRows = new ArrayList<DataRow>(256);
        line = br.readLine(); // skip the first string
        while ((line = br.readLine()) != null) {
            String[] stringArray = line.split(",");
            DataRow dataRow = new DataRow();
            dataRow.counter = Integer.parseInt(stringArray[0]);
            dataRow.sensors = new HashMap<String, Float>();
            dataRow.sensors.put("af3", Float.parseFloat(stringArray[1]));
            dataRow.sensors.put("f7", Float.parseFloat(stringArray[2]));
            dataRow.sensors.put("f3", Float.parseFloat(stringArray[3]));
            dataRow.sensors.put("fc5", Float.parseFloat(stringArray[4]));
            dataRow.sensors.put("t7", Float.parseFloat(stringArray[5]));
            dataRow.sensors.put("p7", Float.parseFloat(stringArray[6]));
            dataRow.sensors.put("o1", Float.parseFloat(stringArray[7]));
            dataRow.sensors.put("o2", Float.parseFloat(stringArray[8]));
            dataRow.sensors.put("p8", Float.parseFloat(stringArray[9]));
            dataRow.sensors.put("t8", Float.parseFloat(stringArray[10]));
            dataRow.sensors.put("fc6", Float.parseFloat(stringArray[11]));
            dataRow.sensors.put("f4", Float.parseFloat(stringArray[12]));
            dataRow.sensors.put("f8", Float.parseFloat(stringArray[13]));
            dataRow.sensors.put("af4", Float.parseFloat(stringArray[14]));
            dataRow.gyro = new Pair<Integer, Integer>(Integer.parseInt(stringArray[15]), Integer.parseInt(stringArray[16]));
            dataRow.funcId = Integer.parseInt(stringArray[15]);
            dataRow.funcValue = Integer.parseInt(stringArray[16]);
            dataRow.marker = Integer.parseInt(stringArray[17]);
            dataRow.syncSignal = Integer.parseInt(stringArray[18]);
        }

    }
}
