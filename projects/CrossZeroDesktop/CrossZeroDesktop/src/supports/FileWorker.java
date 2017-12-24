package supports;

import java.io.*;

/**
 * Created by Валера on 06.08.2017.
 */
public class FileWorker {
    public static String getText(File file) {
        StringBuilder sb = new StringBuilder();
        String text;
        try {
            InputStreamReader inputStreamReader =
                    new InputStreamReader(new FileInputStream(file), Strings.CHARSET_UTF8);
            BufferedReader br = new BufferedReader(inputStreamReader);
            while ((text = br.readLine()) != null) {
                sb.append(text + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
