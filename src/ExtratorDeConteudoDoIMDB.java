import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo {

  @Override
  public List<Conteudo> extrairConteudos(String json) {
    // extrair dados
    JsonParser parser = new JsonParser();
    List<Map<String, String>> listaDeAtributos = parser.parse(json);

    // Criar a lista de conteudos
    List<Conteudo> conteudos = new ArrayList<>();

    // Popular a lista de conteudos
    listaDeAtributos.stream().forEach(atributo -> {
      String title = atributo.get("title").replace(":", "-");
      String urlImagem = atributo.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");

      conteudos.add(new Conteudo(title, urlImagem));
    });

    return conteudos;
  }

}
