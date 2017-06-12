# Minimal Vaadin App with Undertow

Here I created a very small version of a Vaadin project.
To make it easy and simple I am using Wildfly Swarm.
You can find it under [http://wildfly-swarm.io](http://wildfly-swarm.io)

What I want to give here, is a minimal start point 
without any fancy stuff around, to start with the 
first examples of your Vaadin UI.


## How to start

Let´s check the source code. First we need a Servlet. The basic Servlet in 
our case is the VaadinServlet. The only information that we are adding 
here is the connection to the class that is holding the UI elements.
***ui = MyUI.class***

```java
@WebServlet("/*")
  @VaadinServletConfiguration(productionMode = false, ui = MyUI.class)
  public class MyProjectServlet extends VaadinServlet {}
```

The UI class itself is very easy and is only adding one instance of a Label to the 
screen. 

```java
public class MyUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
      setContent(new Label("Hello World"));   // Attach to the UI
    }
  }
```

To play with this you only have to start the main - method from the class  ```org.wildfly.swarm.Swarm``` and 
go with your browser to the address: [http://localhost:8080`vaadin/](http://localhost:8080/vaadin)

Ok, you got your first Vaadin "Hello World".


