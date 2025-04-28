package de.devofvictory.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class ApiCommunicationUtils {

    public static String ENDPOINT_LOCAL = "http://127.0.0.1:{port}";
    public static String ENDPOINT = "https://pd.{shard}.a.pvp.net";
    public static String ENDPOINT_GLZ = "https://glz-{region}-1.{shard}.a.pvp.net";
    public static String ENDPOINT_SHARED = "https://shared.{shard}.a.pvp.net";

    public static ValoLockFile getLockFile() {
        Path filePath = Path.of(System.getenv("LOCALAPPDATA")+"\\Riot Games\\Riot Client\\Config\\lockfile");
        String content = "";
        try {
            content = Files.readString(filePath);
        }catch (IOException ex){
            System.err.println("The game is not running.");
            ex.printStackTrace();
        }

        String[] fields = content.split(":");

        return new ValoLockFile(fields[0], fields[1], fields[2], fields[3], fields[4]);
    }

    private static JSONObject requestRestEndpoint(String method, String url, String payload) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {

        // Send post request with payload and headers to url
        HttpClient httpClient = HttpClients.custom()
                .setSSLContext(new SSLContextBuilder()
                        .loadTrustMaterial(null, new TrustAllStrategy())
                        .build())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();
        HttpUriRequest request = RequestBuilder.create(method)
                .setUri(url)
                .setEntity(new StringEntity(payload, ContentType.APPLICATION_JSON))
                .build();
        HttpResponse response = httpClient.execute(request);
        return new JSONObject(response.getEntity().getContent());

    }

    public static void main(String[] args) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("username", "Malte");
            obj.put("passwordTest", "test");
            System.out.println(requestRestEndpoint("GET", "http://httpbin.org/get", obj.toString()));
        } catch (NoSuchAlgorithmException | IOException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
        }
    }
}
