package org.rapidpm.vaadin.helloworld.server;


import static io.undertow.Handlers.redirect;
import static io.undertow.servlet.Servlets.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
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
      setContent(new Label("Hello World"));   // Attach to the UI
    }
  }


  public static void main(String[] args) throws ServletException {

    // grap it here
    // http://bit.ly/undertow-servlet-deploy
    DeploymentInfo servletBuilder = Servlets.deployment()
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
                                      .addHttpListener(8080, "localhost")
                                      .setHandler(path)
                                      .build();
    undertowServer.start();

  }


}
