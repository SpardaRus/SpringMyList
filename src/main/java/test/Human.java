package test;
/**
 * Test Object Human has no method "compareTo"
 */
public class Human {
    private String name;
    public Human(){
        name="Kolya";
    }

    public Human(String s){
        name=s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ""+name;
    }
}
