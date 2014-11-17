import javafx.util.Pair;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by Andrei on 11/16/2014.
 */
public class Main {

    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Collections collections = (Collections) context.getBean("collections");

        System.out.println("Set:");
        for(String item : collections.getSet()){
            System.out.print(item + "  ");
        }
        System.out.println();

        System.out.println("List:");
        for(Integer item : collections.getList()){
            System.out.print(item + "  ");
        }
        System.out.println();

        System.out.println("Set:");
        for(Map.Entry<String, Integer> item : collections.getMap().entrySet()){
            System.out.println(item.getKey() + " : " + item.getValue());
        }

        System.out.println("Props:");
        for(Map.Entry<Object, Object> item : collections.getProps().entrySet()){
            System.out.println(item.getKey() + " : " + item.getValue());
        }

    }

}
