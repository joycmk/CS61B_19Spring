public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y){
        if ( x- y > 1 || x- y < -1) {
            return false;
        } else {
            return true;
        }
    }
}
