package web;

import okhttp3.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Base64;

public class NetworkThrottlingTest extends BaseTestWeb{
    @Test
    public void networkProfileTest() throws IOException, InterruptedException {
        driver.get("https://bstackdemo.com/");
        SessionId sessionId = ((RemoteWebDriver)driver).getSessionId();
        System.out.println(sessionId);
        /**Provide name of the network profile to be tested**/
        Thread.sleep(2000);
        toggleNetwork(sessionId,"no-network");
        driver.navigate().refresh();
        Thread.sleep(2000);
        toggleNetwork(sessionId,"4g-lte-good");
        Thread.sleep(2000);
        driver.navigate().refresh();
    }
    public void toggleNetwork(SessionId sessionId, String networkProfile) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("networkProfile",networkProfile)
                .build();
        Request request = new Request.Builder()
                .url("https://api.browserstack.com/automate/sessions/"+sessionId+"/update_network.json ")
                .method("PUT", body)
                .addHeader("Authorization", basicAuthHeaderGeneration())
                .build();
        Response response = client.newCall(request).execute();
    }
    public String basicAuthHeaderGeneration(){
        String authCreds = System.getenv("BROWSERSTACK_USERNAME")+":"+System.getenv("BROWSERSTACK_ACCESS_KEY");
        System.out.println("Basic " + Base64.getEncoder().encodeToString(authCreds.getBytes()));
        return "Basic " + Base64.getEncoder().encodeToString(authCreds.getBytes());
    }
}
