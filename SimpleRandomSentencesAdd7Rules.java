// uopeople homework

public class SimpleRandomSentencesAdd7Rules {

    // define the constant array
    static final String[] conjunction = {
        "and", "or", "but", "because"
    };

    static final String[] proper_noun = {
        "Fred", "Jane", "Richard Nixon", "Miss America"
    };

    static final String[] common_noun = {
        "man", "woman", "fish", "elephant", "unicorn"
    };

    static final String[] determiner = {
        "a", "the", "every", "some"
    };

    static final String[] adjective = {
        "big", "tiny", "pretty", "blad"
    };

    static final String[] intransitive_verb = {
        "runs", "jumps", "talks", "sleeps"
    };

    static final String[] transitive_verb = {
        "loves", "hates", "sees", "knows", "looks for", "finds"
    };

    // program starts
    public static void main(String[] args) {
        while (true) {
            System.out.println(getSentence());
            System.out.println(".\n\n");
            try {
            Thread.sleep(3000);
            }
            catch (InterruptedException e) {
            }
        }
    }

    // get sentence function according to the rule
    // <sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]
    static String getSentence() {
        double random = Math.random();
        if (random <= 0.5) {
        return getSimpleSentence();
        } else {
        return getSimpleSentence() + " " + getConjunction() + " " + getSentence();
        }
    }
    // get simple sentence according to the rule
    // <simple_sentence> ::= <noun_phrase> <verb_phrase>
    static String getSimpleSentence() {
        return getNounPhrase() + " " + getVerbPhrase();
    }
    // get noun phrase according to the rule <noun_phrase> ::= <proper_noun> |
    // <determiner> [ <adjective> ]. <common_noun> [ who <verb_phrase> ]
    static String getNounPhrase() {
        double random = Math.random();
        if (random <= 0.5) {
            return getProperNoun();
        } else {
            return getDeterminerCommonNoun();
        }
    }
    static String getDeterminerCommonNoun() {
        double random = Math.random();
        int i = (int)(Math.random()*determiner.length);
        int j = (int)(Math.random()*common_noun.length);
        if (random <= 0.3) {
            return determiner[i] + " " + common_noun[j];
        } else if (random > 0.3 && random <= 0.6) {
            return determiner[i] + " " + getAdjective() + " " + common_noun[j];
        } else if (random > 0.6 && random <= 0.8) {
            return determiner[i] + " " + common_noun[j] + " " + "who " + getVerbPhrase();
        } else {
            return determiner[i] + " " + getAdjective() + " "+ common_noun[j] + " " + "who " + getVerbPhrase();
        }
    }
    // get verb phrase according to the rule <verb_phrase> ::= <intransitive_verb> |
    // <transitive_verb> <noun_phrase> |is <adjective> | believes that <simple_sentence>
    static String getVerbPhrase() {
        double random = Math.random();
        if (random <= 0.2) {
            return getIntransitiveVerb();
        } else if (random > 0.2 && random <= 0.4) {
            return " is " + getAdjective();
        } else if (random > 0.4 && random <= 0.6) {
            return getTransitiveVerb() + " " + getNounPhrase();
        } else {
            return " believes that " + getSimpleSentence();
        }
    }
    // get random intransitive verb from array
    static String getIntransitiveVerb() {
        int i = (int)(Math.random()*intransitive_verb.length);
        return intransitive_verb[i];
    }
    // get random adjective from array
    static String getAdjective() {
        int i = (int)(Math.random()*adjective.length);
        return adjective[i];
    }
    // get random proper noun from array
    static String getProperNoun() {
        int i = (int)(Math.random()*proper_noun.length);
        return proper_noun[i];
    }
    // get random transitive verb from array
    static String getTransitiveVerb() {
    int i = (int)(Math.random()*transitive_verb.length);
    return transitive_verb[i];
    }
    // get conjunction from array
    static String getConjunction() {
        int i = (int)(Math.random()*conjunction.length);
        return conjunction[i];
    }
}
