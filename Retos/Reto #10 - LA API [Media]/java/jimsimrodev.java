import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class jimsimrodev {

    public static class LaApi {
        public Pokemons apiPokemon() {
            URI url = URI.create("https://pokeapi.co/api/v2/pokemon?limit=151");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url)
                    .build();
            try {
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                return new Gson().fromJson(response.body(), Pokemons.class);
            } catch (Exception e) {
                throw new RuntimeException("No se encontro ese pokemon" + e.getMessage());
            }
        }

        public record Pokemons(List<Pokemon> results){

        }

        public record Pokemon(String name) {

            public String toString() {
                return "Nombre: " + name + "\n";
            }

        }

        public void run(){
            Pokemons principal = apiPokemon();
            System.out.println(principal);
        }


        public static void main(String[] args) {
            LaApi ejecutar = new LaApi();
            ejecutar.run();
        }
    }
}
