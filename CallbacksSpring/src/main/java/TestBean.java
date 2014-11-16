/**
 * Created by Andrei on 11/16/2014.
 */
public class TestBean {
    private String text;
    private int number;

    public TestBean(){}

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

    public void init(){
        this.show(1);

        this.text = "TestBean: Text insert";
        this.number = 2;

        this.show(2);
    }

    public void destroy() {
        this.show(4);

        this.text = "TestBean: Text destroy";
        this.number = 3;

        this.show(5);
    }
}
