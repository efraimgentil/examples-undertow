package br.com.efraimgentil.examplesundertow.util;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import br.com.efraimgentil.examplesundertow.util.exception.PaginaInexistenteException;

public class HtmlToStringTest {

    private HtmlToString htmlToString;

    @Before
    public void setup() {
        htmlToString = new HtmlToString();
    }

    @Test
    public void dadoUmaPaginaExistenteDeveRetornarConteudoComoString() throws IOException, PaginaInexistenteException {
        String pagina = "index.html";
        
        String conteudoPagina = htmlToString.getHtmlPage( pagina );
        
        assertNotNull(conteudoPagina);
        assertNotEquals("Deveria ter conteudo", "", conteudoPagina );
    }
    
    @Test(expected = PaginaInexistenteException.class)
    public void dadoUmaPaginaInexistenteDeveRetornarConteudoComoString() throws IOException, PaginaInexistenteException {
        String PAGINA_INEXISTENTE = "indexes.html";
        
        String conteudoPagina = htmlToString.getHtmlPage( PAGINA_INEXISTENTE );
    }

}
