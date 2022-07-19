import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Soundbank;

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
        listaDeFilmes.stream().forEach(filme -> {
            System.out.println("*** FILME ***");
            System.out.println("NOME: " + filme.get("title"));
            System.out.println("CLASSIFICAÇÃO: " + filme.get("imDbRating"));
            System.out.println("POSTER: " + filme.get("image"));
            System.out.println();
        });
    }
}
