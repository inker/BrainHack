import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * Created by Anton on 07.06.2014.
 */
public class Main {

    public static void main(String args[ ]) throws IOException {
        DataArray data = new DataArray(FileSystems.getDefault().getPath("main/src/storage/1", "full.log"));
        float af3 = 0.f;
        int i = 0;
        int els = 1;
        for (DataRow row : data.dataRows) {
            af3 += row.sensors.get(SensorNames.AF3);
            ++i;
            if (i == els) {
                i = 0;
                Float foo = af3 / els;
                String oo = foo.toString();
                oo.replace(".", ",");

                System.out.println(oo + ';');
                af3 = 0.f;
            }
        }
    }
}
