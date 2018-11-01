public class OffByOne implements CharacterComparator{
    public boolean equalChars(char a, char b) {
        if (a - b == 1 || a -b == -1) {
            return true;
        } else {
            return false;
        }
    }
}
