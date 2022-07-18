import deque.*;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> word_deque = new LinkedListDeque<Character> ();
        for(int i = 0; i < word.length();i++) {
            word_deque.addLast(word.charAt(i));
        }
        return word_deque;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else {
            int num = word.length() / 2;
            Deque<Character> word_deque = wordToDeque(word);
            for (int i = 0; i < num ; i++) {
                Character x = word_deque.removeFirst();
                Character y = word_deque.removeLast();
                if (x != y) {
                   return false;
                }
            }
            return true;
        }
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else {
            int num = word.length() / 2;
            Deque<Character> word_deque = wordToDeque(word);
            for (int i = 0; i < num ; i++) {
                Character x = word_deque.removeFirst();
                Character y = word_deque.removeLast();
                if (!cc.equalChars(x,y)) {
                    return false;
                }
            }
            return true;
        }
    }
}
