import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        /*******
         *** FAZER CONEXÃO COM A API E BUSCAR O TOP DE 250 FILMES
         *******/

        // definindo a url a ser consumida
        String url = "https://alura-imdb-api.herokuapp.com/movies";

        // criando uma URI
        URI endereco = URI.create(url);

        // criando um novo cliente
        var client = HttpClient.newHttpClient();

        // fazendo a requisição
        var request = HttpRequest.newBuilder(endereco).GET().build();

        // obtendo a resposta e convertendo em uma String
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        // armazenando body
        var body = response.body();

        /******
         *** ABSTRAIR OS DADOS (TITULO, POSTER, CLASSIFICAÇÃO)
         ******/
        // declarando uma classe pronta para separar os filmes em uma lista
        JsonParser parser = new JsonParser();
        // usando a classe para alimentar minha lista
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        /******
         *** EXIBIR E MANIPULAR OS DADOS
         ******/
        listaDeFilmes.stream().forEach(filme -> DescreverFilme(filme));
    }

    private static void DescreverFilme(Map<String, String> filme) {
        String nomeFilme = filme.get("title").replace(":", "-");
        Double nota = Double.parseDouble(filme.get("imDbRating"));
        String posterFilme = filme.get("image").replace("._V1_UX128_CR0,3,128,176_AL_", "");

        ExibirNoConsole(nomeFilme, nota, posterFilme);
        GerarFigurinhas(nomeFilme, nota, posterFilme);
    }

    private static void ExibirNoConsole(String nomeFilme, Double nota, String posterFilme) {
        System.out.println("\u001b[37m\u001b[46m***FILME***\u001b[m");

        System.out.println("\u001b[1mNOME: \u001b[m" + nomeFilme);

        System.out.println("\u001b[1mCLASSIFICAÇÃO: \u001b[m" + nota);

        for (int i = 0; i < (nota.intValue() / 2); i++) {
            System.out.print("🔥");
        }
        System.out.println("\n\u001b[1mPOSTER: \u001b[m" + posterFilme);
        System.out.println();
    }

    private static void GerarFigurinhas(String nomeFilme, Double nota, String posterFilme) {
        try {
            String classificacao = ClassificarFilme(nota);
            new GeradorDeFigurinhas().criar(posterFilme, nomeFilme, classificacao);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println(String.format("Não foi possivel gerar a figurinha do filme %s", nomeFilme));
            System.err.println(String.format("Devido ao erro %s", e.getMessage()));
            e.printStackTrace();
        }
    }

    private static String ClassificarFilme(Double nota) {
        String texto = "";
        if (nota < 7) {
            texto = "Filme Ruim";
        } else if (nota >= 7 && nota <= 8.5) {
            texto = "Filme marrom";
        } else if (nota > 8.5) {
            texto = "Filme Top";
        }
        return texto;
    }
}
