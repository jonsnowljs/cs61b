public class test {
    public static class Walrus{
        public int weight;
        public double tuskSize;

        public Walrus(int w, double ts){
            weight = w;
            tuskSize = ts;
        }

    }        

    public static void main(String[] args){
        Walrus a = new Walrus(1000, 8.3);
        Walrus b;
        b = a;
        b.weight = 5;
        System.out.println(a);
        System.out.println(b);
    }
    
}