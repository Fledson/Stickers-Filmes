import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {

  public void criar(String link, String filme, String classificacao) throws IOException {
    /***
     * Ler a Imagem
     ***/
    InputStream imagemNet = new URL(link).openStream();
    BufferedImage imagemOriginal = ImageIO.read(imagemNet);

    /***
     * Criar nova imagem em memória com transparência e com novo tamanho
     ***/
    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    // criando uma nova altura para a nova imagem
    int tarjaTransparente = altura / 5;
    int novaAltura = altura + tarjaTransparente;
    // gerando a nova imagem (em branco com fundo transparente)
    BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    /**
     * Copiar a imagem original para nova imagem (em memoria)
     */
    // criando o editor da nova imagem
    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    // colocando a imagem original na nova imagem na posição 0 e altura 0
    graphics.drawImage(imagemOriginal, 0, 0, null);

    /**
     * Configurando tamanho da fonte do TExto
     */
    var fonte = new Font("Fira Code", Font.BOLD, 150);
    graphics.setFont(fonte);
    graphics.setColor(Color.RED);
    /***
     * Escrever uma frase na nova figurinha
     ***/
    graphics.drawString(classificacao, (int) ((largura - (largura / 2)) / 2.5),
        novaAltura - (tarjaTransparente / 3)); // (tarjaTransparente
    // / 2) pegando a
    // metade do tamanho da tarja para
    // escreve ao centro

    /***
     * Escrever a nova imagem em um arquivo
     */
    // define um caminho
    File saida = new File(String.format("saida/%s.png", filme));
    // verifica se o caminho exite
    if (!saida.exists())
      saida.mkdirs(); // se não existir ele cria

    ImageIO.write(novaImagem, "png", saida);
  }
}
