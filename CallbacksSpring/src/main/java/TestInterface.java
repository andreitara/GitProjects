import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by Andrei on 11/16/2014.
 */
public class TestInterface implements InitializingBean, DisposableBean {
    private String text;
    private int number;

    public TestInterface(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void show(int i){
        System.out.println(i + ". " + this.getText() + " : " + this.getNumber());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.show(1);

        this.text = "TestInterface: Text after properties set";
        this.number = 2;

        this.show(2);
    }

    @Override
    public void destroy() throws Exception {
        this.show(4);

        this.text = "TestInterface: Text destroy";
        this.number = 3;

        this.show(5);
    }
}
