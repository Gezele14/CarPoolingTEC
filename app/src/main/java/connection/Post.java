package connection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Clase encargada de hacer post al servidor
 */

public class Post {

    public String httpPost(String myUrl, JSONObject jsonObject) throws IOException, JSONException {

        URL url = new URL(myUrl);

        // 1. create HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        // 2. build JSON object
        //JSONObject jsonObject = buidJsonObject(attributes, values);

        // 3. add JSON content to POST request body
        setPostRequestContent(conn, jsonObject);

        // 4. make POST request to the given URL
        conn.connect();

        String json_response = "";
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(in);
        String text = "";
        while ((text = br.readLine()) != null) {
            json_response += text;
        }

        // 5. return response message
        //return conn.getResponseMessage() + "";
        return json_response;

    }

    /**
     * Crea el objeto JSON con los atributos que se pasan por parametro
     * @param attributes atributos del JSON
     * @param values los valores de los atributos del JSON
     * @return objetos JSON formado
     * @throws JSONException
     */
    private JSONObject buidJsonObject(String[] attributes, String[] values) throws JSONException {

        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < attributes.length; ++i) {

            if (android.text.TextUtils.isDigitsOnly(values[i])) {
                jsonObject.accumulate(attributes[i], Integer.parseInt(values[i]));
            } else {
                jsonObject.accumulate(attributes[i], values[i]);
            }
        }

        return jsonObject;
    }

    /**
     * Envia el mensaje al servidor
     * @param conn
     * @param jsonObject
     * @throws IOException
     */
    private void setPostRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException {

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonObject.toString());
        writer.flush();
        writer.close();
        os.close();
    }
}
