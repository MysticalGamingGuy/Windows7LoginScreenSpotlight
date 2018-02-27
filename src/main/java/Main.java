import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * @author Ian Moody
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        try {
            int n = 8;
            HttpURLConnection request = (HttpURLConnection) new URL("https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n="+n).openConnection();
            request.connect();
            JsonElement root = new JsonParser().parse(new InputStreamReader((InputStream) request.getContent()));
            BufferedImage image = ImageIO.read(new URL("https://www.bing.com"+root.getAsJsonObject().get("images").getAsJsonArray().get(new Random().nextInt(n)).getAsJsonObject().get("url").getAsString()));
            ImageIO.write(image,"jpg",new File("C:/Windows/System32/oobe/info/backgrounds/backgroundDefault.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
