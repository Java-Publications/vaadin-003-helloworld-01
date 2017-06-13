package org.rapidpm.vaadin.helloworld.server;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
public class MyUI extends UI {
  @Override
  protected void init(VaadinRequest request) {
    final VerticalLayout layout = new VerticalLayout();
    final Button button = new Button("click me");
    layout.addComponents(button);

    button.addClickListener((Button.ClickListener) event -> {
      Label label = new Label("clicked again");
      layout.addComponents(label);
    });

    setContent(layout);
  }
}
