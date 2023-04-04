package web;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.remote.SessionId;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;

public class GetSessionDetails {
    public static void sessionData(SessionId sessionId) throws Exception {
        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accesskey = System.getenv("BROWSERSTACK_ACCESS_KEY");

        URI uri = new URI("https://" + username + ":" + accesskey + "@api.browserstack.com/automate/sessions/" + sessionId + ".json"); //Automate
        String printData = "";
        HttpGet getRequest = new HttpGet(uri);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpResponse httpresponse = (HttpResponse) httpclient.execute(getRequest);

        String jsonResponseData = EntityUtils.toString(httpresponse.getEntity());
        String trimResposneData = jsonResponseData.substring(22, jsonResponseData.length() - 1);

        JSONParser parser = new JSONParser();
        JSONObject bsSessionData = (JSONObject) parser.parse(trimResposneData);

        printData += "Name: " + bsSessionData.get("name")
                + "\nBuild: " + bsSessionData.get("build_name")
                + "\nProject: " + bsSessionData.get("project_name")
                + "\nDevice: " + bsSessionData.get("device")
                + "\nOS: " + bsSessionData.get("os")
                + "\nOS Version: " + bsSessionData.get("os_version")
                + "\nBrowser: " + bsSessionData.get("browser")
                + "\nBrowser Version: " + bsSessionData.get("browser_version")
                + "\nStatus: " + bsSessionData.get("status")
                + "\nReason: " + bsSessionData.get("reason")
                + "\nPublic Session URL: " + bsSessionData.get("public_url");
        System.out.println("=========Session Details===========\n");
        System.out.println(printData);
        System.out.println("=====================\n");
    }

}
