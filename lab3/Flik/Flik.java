/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        return (a.intValue() == b.intValue());
    }

    public static void main(String[] args){
        System.out.println(Flik.isSameNumber(200, 200));
    }
}
