import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import java.security.Key;

public class TestKey {
    public static void main(String[] args) {
        try {
            String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
            byte[] keyBytes = Decoders.BASE64.decode(SECRET);
            System.out.println("Key bytes length: " + keyBytes.length);
            Key key = Keys.hmacShaKeyFor(keyBytes);
            System.out.println("Key generated successfully for algorithm: " + key.getAlgorithm());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
