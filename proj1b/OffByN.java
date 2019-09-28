public class OffByN implements CharacterComparator {
    public static int N;

    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        if (x - y == N || y - x == N) {
            return true;
        }
        return false;
    }
}
