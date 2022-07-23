import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClienteHttp {

  public String consumirApi(String url) {

    try {

      // criando uma URI
      URI endereco = URI.create(url);
      // criando um novo cliente
      var client = HttpClient.newHttpClient();
      // fazendo a requisição
      var request = HttpRequest.newBuilder(endereco).GET().build();
      // obtendo a resposta e convertendo em uma String
      HttpResponse<String> response;
      response = client.send(request, BodyHandlers.ofString());
      // Retornando o Body
      return response.body();

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

}
