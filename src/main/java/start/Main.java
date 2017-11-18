package start;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This method starts the program
 */
public class Main {
    /**
     * This method starts the program
     * Here, we declare ApplicationContext
     * and run the test
     * @see test.Test
     * @param args Command line value
     */
    public static void main(String[] args) {
       ApplicationContext ctx=new ClassPathXmlApplicationContext("Spring.xml");

        new test.Test().start(ctx);

    }
}
