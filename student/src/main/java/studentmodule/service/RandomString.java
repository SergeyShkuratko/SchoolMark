package studentmodule.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@Service
public class RandomString {

    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public String renameFile(String path) {
        ArrayList<String> explodedpath = new ArrayList(Arrays.asList(path.split("/")));
        /**
         * убираем первый если пустой
         */
        if(explodedpath.get(0).equals("")) {
            explodedpath.remove(0);
        }
        ArrayList<String> explodedfile =
                new ArrayList(Arrays.asList(explodedpath.get(explodedpath.size() - 1).split("\\.")));
        /**
         * убираем последний элемент - имя файла
         */
        explodedpath.remove(explodedpath.size() - 1);
        String ext = explodedfile.size() != 0 ? explodedfile.get(explodedfile.size() - 1) : "";
        String fname = this.getSaltString() + "." + ext;
        StringBuilder outputpath = new StringBuilder("/");
        for(String one : explodedpath) {
            outputpath.append(one + "/");
        }
        outputpath.append(fname);
        return outputpath.toString();
    }

}
