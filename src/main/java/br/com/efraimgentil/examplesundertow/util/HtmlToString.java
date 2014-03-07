package br.com.efraimgentil.examplesundertow.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class HtmlToString {

    public String getHtmlPage(String page) throws IOException {
        URL url = HtmlToString.class.getResource("/views/" + page);
        return getFileContentAsString(new File(url.getPath()));
    }

    public String getFileContentAsString(File file) throws FileNotFoundException {
        try( Scanner scanner = new Scanner(file, "UTF-8") ){
            return scanner.useDelimiter("\\A").next();
        }
    }

}
