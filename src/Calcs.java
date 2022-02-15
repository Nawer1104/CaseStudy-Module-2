public class Calcs {
    int root;

    Calcs (int root) {
        this.root = root++;
    }

    public static void main(String[] args) {
        Calcs c = new Calcs(42);
    }
    

}
