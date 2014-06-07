import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * Created by Anton on 07.06.2014.
 */
public class Main {

    public static void main(String args[ ]) throws IOException {
        DataArray data = new DataArray(FileSystems.getDefault().getPath("main/src/storage/1", "full.log"));
        if (data.fileType.equals(DataArray.FileType.FULL)) {
            System.out.println("good");
        } else {
            System.out.println("bad");
        }
        //for (DataRow row : data.dataRows) {
        //    System.out.println(row.sensors.get(DataRow.SensorNames.AF4));
        //}
    }
}
