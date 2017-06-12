package org.rapidpm.vaadin.helloworld.server;


import static io.undertow.Handlers.redirect;
import static io.undertow.servlet.Servlets.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.rapidpm.vaadin.helloworld.server.ui.MyNewUI;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

/**
 *
 */
public class Main {

  public static final String CONTEXT_PATH = "/";


  @WebServlet
  @VaadinServletConfiguration(productionMode = false, ui = MyUI.class)
  public static class MyProjectServlet extends VaadinServlet {}


  public static class MyUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
      final VerticalLayout layout = new VerticalLayout();
//      layout.addComponents(new Label("Hello World"), new MyNewUI());
      final Button button = new Button("klick me");
      layout.addComponents(button);

      button.addClickListener((Button.ClickListener) event -> {
        Label label = new Label("was klicked");
        layout.addComponents(label);
      });

      setContent(layout);
    }
  }

  public static void main(String[] args) throws ServletException {

    // grap it here
    // http://bit.ly/undertow-servlet-deploy
    DeploymentInfo servletBuilder
        = Servlets.deployment()
                  .setClassLoader(Main.class.getClassLoader())
                  .setContextPath(CONTEXT_PATH)
                  .setDeploymentName("ROOT.war")
                  .setDefaultEncoding("UTF-8")
                  .addServlets(
                      servlet(
                          MyProjectServlet.class.getSimpleName(),
                          MyProjectServlet.class)
                          .addMapping("/*"));

    DeploymentManager manager = Servlets
        .defaultContainer()
        .addDeployment(servletBuilder);

    manager.deploy();

    PathHandler path = Handlers.path(redirect(CONTEXT_PATH))
                               .addPrefixPath(CONTEXT_PATH, manager.start());

    Undertow undertowServer = Undertow.builder()
                                      .addHttpListener(8080, "0.0.0.0")
                                      .setHandler(path)
                                      .build();
    undertowServer.start();
    undertowServer.getListenerInfo().forEach(System.out::println);

  }

  public static void shutdown() {
    //shutdown the container, release all resources
  }

}
