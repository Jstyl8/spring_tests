package hello;

import org.joda.time.LocalTime;
import org.apache.log4j.Logger;

public class HelloWorld {
	private static final Logger LOGGER = Logger.getLogger(HelloWorld.class);
	
  public static void main(String[] args) {
    LocalTime currentTime = new LocalTime();
    System.out.println("The current local time is: " + currentTime);

    Greeter greeter = new Greeter();
    System.out.println(greeter.sayHello());

    MessageService messageService = new MessageService();
	String message = messageService.getMessage();
    LOGGER.info("Received message: " + message);

  }
}