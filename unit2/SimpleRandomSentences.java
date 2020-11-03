package unit2;

/*


Some rules that capture the syntax of this verse:
 
  <sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]

  <simple_sentence> ::= <noun_phrase> <verb_phrase>

  <noun_phrase> ::= <proper_noun> |
                    <determiner> [ <adjective> ]. <common_noun> [ who <verb_phrase> ]

  <verb_phrase> ::= <intransitive_verb> |
                    <transitive_verb> <noun_phrase> |
                    is <adjective> |
                    believes that <simple_sentence>

  <conjunction> ::= and | or | but | because

  <proper_noun> ::= Fred | Jane | Richard Nixon | Miss America

  <common_noun> ::= man | woman | fish | elephant | unicorn

  <determiner> ::= a | the | every | some

  <adjective> ::= big | tiny | pretty | bald

  <intransitive_verb> ::= runs | jumps | talks | sleeps

  <transitive_verb> ::= loves | hates | sees | knows | looks for | finds

 
  This program implements these rules to generate random sentences.  All the
  verses of the rhyme can be generated, plus a lot of sentences that make no
  sense (but still follow the syntax).   Note that an optional item like
  [ <modifier> ] has a chance of being used, depending on the value of some
  randomly generated number.

  The program generates and outputs one random sentence every three seconds until
  it is halted (for example, by typing Control-C in the terminal window where it is
  running).
*/


public class SimpleRandomSentences {

 static final String[] conjunction = { "and", "or", "but", "because"};
                                
 static final String[] proper_noun = { "Fred", "Jane", "Richard Nixon","Miss America"};

 static final String[] common_noun = { "man", "woman", "fish", "elephant", "unicorn"};                                  

 static final String[] determiner = { "a", "the", "every", "some"};

 static final String[] adjective = { "big", "tiny", "pretty", "bald"};

 static final String[] intransitive_verb = { "runs", "jumps", "talks", "sleeps"};

 static final String[] transitive_verb = { "loves", "hates", "sees", "knows", "looks for", "finds"};

 public static void main(String[] args) {
    while (true) {
       randomSentence();
    System.out.println(".\n\n");
       try {
           Thread.sleep(3000);
       }
       catch (InterruptedException e) {
       }
    }
 }

 static void randomSentence() {
    randomNounPhrase();
            randomVerbPhrase();
    if (Math.random() > 0.75) {
            System.out.print(" " + randomItem(conjunction));
            randomSentence();
    }
 }

 static void randomNounPhrase() {

      
        if (Math.random() > 0.75)
           System.out.print(" " + randomItem(proper_noun));
        else
        {
           System.out.print(" " + randomItem(determiner));
           if (Math.random() > 0.5)
       System.out.print(" " + randomItem(adjective)+".");
              System.out.print(" " + randomItem(common_noun));
               if (Math.random() > 0.5){
                    System.out.print(" who" );
                    randomVerbPhrase();
               }
        }
   }

    static void randomVerbPhrase() {
                
        if (Math.random() > 0.75)
           System.out.print(" " + randomItem(intransitive_verb));
              else if (Math.random() > 0.50) {
                      System.out.print(" " + randomItem(transitive_verb));
                      randomNounPhrase();
              }
              else if (Math.random() > 0.25)
                  System.out.print(" is " + randomItem(adjective));
              else {
                  System.out.print(" believes that");
                  randomNounPhrase();
                  randomVerbPhrase();
              }
     }

 static String randomItem(String[] listOfStrings){
     return listOfStrings[(int)(Math.random()*listOfStrings.length)];
 }

}