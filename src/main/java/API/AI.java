package API;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AI {
    private static final String API_URL = "https://api-inference.huggingface.co/models/gpt2";
    private static final String API_KEY = "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";

    public static String getFreeAIResponse(String userMessage) {
        try {
            String jsonInput = """
                {
                    "inputs": "%s"
                }
                """.formatted(userMessage);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInput))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonArray jsonArray = JsonParser.parseString(response.body()).getAsJsonArray();
            String generatedText = jsonArray.get(0).getAsJsonObject().get("generated_text").getAsString();
            return generatedText;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Unable to connect to Hugging Face API.";
        }
    }
}