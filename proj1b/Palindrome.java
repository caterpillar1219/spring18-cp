public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> myDeque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            myDeque.addLast(word.charAt(i));
        }
        return myDeque;
    }

    public boolean isPalindrome(String word) {
        return this.isPalindrome(wordToDeque(word));
    }

    private boolean isPalindrome(Deque<Character> mydeque) {
        if (mydeque.size() <= 1) {
            return true;
        }
        if (mydeque.removeFirst() != mydeque.removeLast()) {
            return false;
        }
        return this.isPalindrome(mydeque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return this.isPalindrome(wordToDeque(word), cc) || this.isPalindrome(word);
    }

    private boolean isPalindrome(Deque<Character> mydeque, CharacterComparator cc) {
        if (mydeque.size() <= 1) {
            return true;
        }
        if (!cc.equalChars(mydeque.removeFirst(), mydeque.removeLast())) {
            return false;
        }
        return this.isPalindrome(mydeque, cc);
    }
}
