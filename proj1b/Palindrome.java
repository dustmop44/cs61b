public class Palindrome {
    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> chardeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i ++) {
            chardeque.addLast(word.charAt(i));
        }
        return chardeque;
    }

    public static boolean isPalindrome(Deque<Character> test) {
        if (test.size() <= 1) {
            return true;
        } else if (test.get(0) == test.get(test.size() - 1)) {
            test.removeFirst();
            test.removeLast();
            return isPalindrome(test);
        } else {
            return false;
        }
    }

    public static boolean isPalindrome(String word) {
        Deque<Character> test = Palindrome.wordToDeque(word);
        return isPalindrome(test);
    }

    public static boolean isPalindrome(Deque<Character> test, CharacterComparator cc) {
        if (test.size() <= 1) {
            return true;
        } else {
            Character x = test.removeFirst();
            Character y = test.removeLast();
            if (cc.equalChars(x, y)) {
                return isPalindrome(test, cc);
            } else {
                return false;
            }
        }
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> chardeque = Palindrome.wordToDeque(word);
        return isPalindrome(chardeque, cc);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 53; i++) {
            In in = new In("words.txt");
            String bigword = "a";
            OffByN cc = new OffByN(i);
            int count = 0;
            while (!in.isEmpty()) {
                String word = in.readString();

                if (isPalindrome(word, cc) && word.length() > 2) {
                    if (word.length() > bigword.length()) {
                        bigword = word;
                    }
                    count += 1;
                }

            }
            System.out.println("This is the biggest word " + bigword);
            System.out.println("There are "+ count +"palindromes that are off by " +i);
        }
            return;
    }
}
