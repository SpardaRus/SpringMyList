package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import list.MyList;

/**
 * Class for test class "MyList"
 */
public class Test {
    /**
     * test class "MyList"
     * @param ctx ApplicationContext
     */
    @SuppressWarnings("unchecked")
    public void start(ApplicationContext ctx){
        System.out.println("Start test");

        System.out.println("Test: BigInteger");

        MyList<BigInteger> in=(MyList<BigInteger>) ctx.getBean("myList");

        in.add(new BigInteger("2568979879879879878978485184"));
        in.add(new BigInteger("3259278637868769786789999999999"));
        in.add(new BigInteger("1868678678678687"));
        in.add(new BigInteger("45645654687678678678687687678678"));
        in.add(new BigInteger("-89678978987978987654313153486786"));

        System.out.println(in);
        System.out.println();

        System.out.println("Test: String");
        MyList<String> ms=(MyList<String>)ctx.getBean("myList");

        ms.add("AAAAAfdsfdsf");
        ms.add("qwewq");
        ms.add("fds123fdsf");
        ms.add("Afdbdf");
        ms.add("AFweqwrqwr");
        ms.add("FGDGwwrw");
        ms.add("fdsDSFWRfdsf");

        System.out.println(ms);
        System.out.println();


        System.out.println("Test: Human - no implements Comparable");
        MyList<Human> mh=(MyList<Human>)ctx.getBean("myList");
        mh.add(new Human("Grand"));
        mh.add(new Human("Stefan"));
        mh.add(new Human("Alex"));
        mh.add(new Human("Frodo"));
        mh.add(new Human("Pipin"));
        mh.add(new Human("Givi"));
        System.out.println(mh);
        System.out.println("    Test: add Bean in Human - "+(Human)ctx.getBean("human"));
        mh.add((Human)ctx.getBean("human"));

        System.out.println(mh);
        System.out.println();

        System.out.println("Test: Car - implements Comparable");
        MyList<Car> mc=(MyList<Car>)ctx.getBean("myList");

        mc.add(new Car(123));
        mc.add(new Car(3333));
        mc.add(new Car(6666666));
        mc.add(new Car(555555));
        mc.add(new Car(44444));
        mc.add(new Car(2222));
        System.out.println(mc);
        System.out.println("    Test: add Bean in Car - "+(Car)ctx.getBean("car"));
        System.out.println(mc);
        mc.add((Car)ctx.getBean("car"));

        System.out.println(mc);
        System.out.println("    Test: Car - Iterator");

        Iterator<Car> iter = mc.iterator();
        while(iter.hasNext()){
            Car c=iter.next();
            System.out.println(""+mc.indexOf(c)+" "+c);
        }
        System.out.println();

        System.out.println("Test: ArrayList<Car> - addAll MyList<Car>");
        List<Car> lc=new ArrayList<>();
        lc.add(new Car(321321));
        System.out.println();
        System.out.println("    ArrayList<Car>:");
        System.out.println(lc);
        lc.addAll(0,mc);
        System.out.println("    ArrayList<Car> - addAll MyList<Car>:");
        System.out.println(lc);
        mc.addAll(lc);
        System.out.println("    MyList<Car> - addAll ArrayList<Car>:");
        System.out.println(mc);
        System.out.println();


        System.out.println("Test: Time sort BigInteger");
        MyList<BigInteger> ib=(MyList<BigInteger>)ctx.getBean("myList");
        Random randomizer = new Random();
        long startTime = System.currentTimeMillis();
        int sss=10000;
        for ( int i = 0; i < sss; i++ ) {

            ib.add(new BigInteger(""+randomizer.nextInt( 10_000 ))) ;

        }
        long endTime = System.currentTimeMillis();
        System.out.println("    N= "+sss+"; Time: "+(endTime-startTime)/1000);
        System.out.println("End test");
    }
}
