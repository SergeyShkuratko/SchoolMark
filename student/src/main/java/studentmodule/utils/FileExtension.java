package studentmodule.utils;

import org.springframework.stereotype.Service;

@Service
public class FileExtension {

    public String getFileExtention(String fname) {
        if(fname.contains(".")) {
            return fname.substring(fname.lastIndexOf("."));
        }
        return "";
    }

}
