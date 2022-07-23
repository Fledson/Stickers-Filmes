import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // DEFINE A API
        // String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        String url = "https://api.nasa.gov/planetary/apod?api_key=9Lr6FmEEhcagYve1NwIfzT5ej6gcLhQ49YV5MFb4&start_date=2022-06-12&end_date=2022-06-14";
        var http = new ClienteHttp();
        String json = http.consumirApi(url);

        // Chama o Extrador
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        List<Conteudo> listaDeConteudos = extrator.extrairConteudos(json);

        listaDeConteudos.stream().forEach(conteudo -> DescreverConteudo(conteudo));
    }

    private static void DescreverConteudo(Conteudo conteudo) {
        String nomeConteudo = conteudo.getConteudo();
        String posterConteudo = conteudo.getUrlImage();

        ExibirNoConsole(nomeConteudo, posterConteudo);
        GerarFigurinhas(nomeConteudo, posterConteudo);
    }

    private static void ExibirNoConsole(String nomeConteudo, String imagemConteudo) {
        System.out.println("\u001b[37m\u001b[46m***FILME***\u001b[m");

        System.out.println("\u001b[1mNOME: \u001b[m" + nomeConteudo);

        System.out.println("\n\u001b[1mPOSTER: \u001b[m" + imagemConteudo);

        System.out.println();
    }

    private static void GerarFigurinhas(String nomeConteudo, String imagemConteudo) {
        try {
            new GeradorDeFigurinhas().criar(imagemConteudo, nomeConteudo, "TOPZEIRA");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println(String.format("Não foi possivel gerar a figurinha do filme %s", nomeConteudo));
            System.err.println(String.format("Devido ao erro %s", e.getMessage()));
            e.printStackTrace();
        }
    }

}
