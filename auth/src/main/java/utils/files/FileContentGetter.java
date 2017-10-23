package utils.files;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileContentGetter {

    private static final Logger logger = Logger.getLogger(FileContentGetter.class);

    public String getFileContent(InputStream in) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException x) {
            logger.error(x.getMessage());
            throw new IOException(x);
        } catch (NullPointerException npe) {
            logger.error(npe);
            throw new IOException(npe);
        }
    }

}
