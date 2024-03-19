import java.util.Arrays;

/**
 * This class implements the ISpellChecker interface and contains methods for spell-checking words.
 */
public class SpellChecker implements ISpellChecker {
    private String[] dictionary;

    /**
     * This method loads the dictionary and sets the private array dictionary to hold its contents.
     */
    public SpellChecker() {
        DictionaryLoader dictionaryLoader = DictionaryLoader.getInstance();
        dictionary = dictionaryLoader.loadDictionary();
    }

    /**
     * This method runs the checker on all words provide in args.
     * @param words the array of words to check
     */
    @Override
    public void runChecker(String[] words) {
        // Run check on each word and print result
        for (String word : words) {
            SpellCheckResult result = check(word);
            if (result.isCorrect()) {
                //word is correct
                System.out.println(word.toLowerCase() + " correct");
            } else {
                //word is not correct
                String before = result.getBefore();
                String after = result.getAfter();
                if (before != null && after != null) { //cases depending on if there is a before and/or after in dictionary
                    System.out.println(word.toLowerCase() + " not found - nearest neighbour(s) " + before + " and " + after);
                } else if (before != null) {
                    System.out.println(word.toLowerCase() + " not found - nearest neighbour " + before);
                } else if (after != null) {
                    System.out.println(word.toLowerCase() + " not found - nearest neighbour " + after);
                } else {
                    System.out.println(word.toLowerCase() + " not found - no nearest neighbours");
                }
            }
        }
    }

    /**
     * This method searches through the dictionary for an instance of the word.
     * If the word cannot be found, before and after words are returned.
     * @param word the word to check
     */
    @Override
    public SpellCheckResult check(String word) {
        // Implement spell-checking logic using binary search
        int index = Arrays.binarySearch(dictionary, word.toLowerCase());

        if (index >= 0) {
            // The word is found in the dictionary
            return new SpellCheckResult(true, null, null);
        } else {
            // The word is not found in the dictionary
            int insertionPoint = -index - 1; // Calculate the insertion point
            String before = null;
            String after = null;

            // Find words before and after the checked word if possible
            if (insertionPoint > 0) {
                before = dictionary[insertionPoint - 1];
            }
            if (insertionPoint < dictionary.length) {
                after = dictionary[insertionPoint];
            }

            return new SpellCheckResult(false, before, after);
        }
    }

    /**
     * This method is the main method, whcih takes the args and runs them in the spell-checker.
     * @param args words to check
     */
    public static void main(String[] args) { //Main Method
        if (args.length < 1) { //check arg length
            System.err.println("Usage: java SpellChecker <words_to_check>");
            System.exit(1);
        }

        String[] wordsToCheck = Arrays.copyOfRange(args, 0, args.length);
        SpellChecker spellChecker = new SpellChecker();
        spellChecker.runChecker(wordsToCheck);
    }
}
