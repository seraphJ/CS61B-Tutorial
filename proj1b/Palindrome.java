public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<Character>();
        for (char c : word.toCharArray()) {
            deque.addLast(c);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return helpIsPalindrome(deque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return helpIsPalindrome(deque, cc);
    }
    private boolean helpIsPalindrome(Deque<Character> deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }
        char f = deque.removeFirst();
        char l = deque.removeLast();
        return f == l && helpIsPalindrome(deque);
    }

    private boolean helpIsPalindrome(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }
        char f = deque.removeFirst();
        char l = deque.removeLast();
        return cc.equalChars(f, l) && helpIsPalindrome(deque, cc);
    }
}
