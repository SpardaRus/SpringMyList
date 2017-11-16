import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.Car;
import test.Human;

import java.math.BigInteger;


public class Main {
    public static void main(String[] args) {
        System.out.println("Start");
        ApplicationContext ctx=new ClassPathXmlApplicationContext("Spring.xml");


        MyList<BigInteger> in=new MyList<BigInteger>();
        in.add(new BigInteger("2568979879879879878978485184"));
        in.add(new BigInteger("3259278637868769786789999999999"));
        in.add(new BigInteger("1868678678678687"));
        in.add(new BigInteger("45645654687678678678687687678678"));
        in.add(new BigInteger("-89678978987978987654313153486786"));

        System.out.println(in);
        System.out.println();

        MyList<String> ms=new MyList<String> ();

        ms.add("AAAAAfdsfdsf");
        ms.add("qwewq");
        ms.add("fds123fdsf");
        ms.add("Afdbdf");
        ms.add("AFweqwrqwr");
        ms.add("FGDGwwrw");
        ms.add("fdsDSFWRfdsf");

        System.out.println(ms);
        System.out.println();



        MyList<Human> mh=new MyList<Human>();
        mh.add(new Human("Grand"));
        mh.add(new Human("Stefan"));
        mh.add(new Human("Alex"));
        mh.add(new Human("Frodo"));
        mh.add(new Human("Pipin"));
        mh.add(new Human("Givi"));
        mh.add((Human)ctx.getBean("human"));

        System.out.println(mh);
        System.out.println();


        MyList<Car> mc=new MyList<Car>();
        mc.add(new Car(123));
        mc.add(new Car(3333));
        mc.add(new Car(6666666));
        mc.add(new Car(555555));
        mc.add(new Car(44444));
        mc.add(new Car(2222));
        mc.add((Car)ctx.getBean("car"));

        System.out.println(mc);
    }
}
