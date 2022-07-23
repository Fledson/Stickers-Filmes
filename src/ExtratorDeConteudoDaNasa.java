import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {

  @Override
  public List<Conteudo> extrairConteudos(String json) {
    // extrair apenas dados que interessamz
    JsonParser parser = new JsonParser();
    List<Map<String, String>> listaDeItens = parser.parse(json);

    // criando a lista de conteudos
    List<Conteudo> conteudos = new ArrayList<>();

    // populando a lista de conteudos
    listaDeItens.stream().forEach(atributo -> {
      String titulo = atributo.get("title");
      String urlImage = atributo.get("url");

      conteudos.add(new Conteudo(titulo, urlImage));
    });

    return conteudos;
  }

}
