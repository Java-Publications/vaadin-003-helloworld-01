package junit.org.rapidpm.vaadin.helloworld.server;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rapidpm.vaadin.helloworld.server.HelloWorldMain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 */
public class HelloWorldMainTest {


  @Before
  public void setUp() throws Exception {
    HelloWorldMain.main(null);
  }

  @After
  public void tearDown() throws Exception {
    //HelloWorldMain.shutdown(); //NotYetImplemented
  }

  @Test
  public void test001() throws Exception {
    Request request = new Request.Builder()
        .url("http://127.0.0.1:8080/")
        .build();

    OkHttpClient client = new OkHttpClient();
    Response response = client.newCall(request).execute();

    Assert.assertNotNull(response);
    ResponseBody body = response.body();
    Assert.assertNotNull(body);
    System.out.println("body = " + body);
    Assert.assertEquals("Hello World", body.string());


  }


}
