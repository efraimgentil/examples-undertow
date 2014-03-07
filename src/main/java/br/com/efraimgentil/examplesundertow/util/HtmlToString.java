package br.com.efraimgentil.examplesundertow.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import br.com.efraimgentil.examplesundertow.util.exception.PaginaInexistenteException;

public class HtmlToString {

    public String getHtmlPage(String page) throws IOException, PaginaInexistenteException {
        URL url = HtmlToString.class.getResource("/views/" + page);
        if(url == null)
            throw new PaginaInexistenteException();
        return getFileContentAsString( url.getPath() );
    }

    protected String getFileContentAsString(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.ready()) {
                sb.append(br.readLine());
            }
            return sb.toString();
        }
    }

}
