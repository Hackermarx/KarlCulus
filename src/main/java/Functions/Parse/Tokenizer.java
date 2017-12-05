package Functions.Parse;

// http://cogitolearning.co.uk/2013/04/writing-a-parser-in-java-the-tokenizer/

import Functions.Parse.ExpressionTree.FunctionExpressionNode;
import jdk.nashorn.internal.runtime.ParserException;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private LinkedList<TokenInfo> tokenInfos;

    private LinkedList<Token> tokens;

    public Tokenizer() {
        tokenInfos = new LinkedList<>();
        tokens = new LinkedList<>();

        setTokens();
    }

    public LinkedList<Token> getTokens() {
        return tokens;
    }

    public void add(String regex, int token) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^("+regex+")"), token));
    }

    private class TokenInfo {
        public final Pattern regex;
        public final int token;

        public TokenInfo(Pattern regex, int token) {
            this.regex = regex;
            this.token = token;
        }
    }

    public void tokenize(String str) {
        String s = str.replace(" ", "");
        tokens.clear();

        while (!s.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;

                    String tok = m.group().trim();
                    tokens.add(new Token(info.token, tok));

                    s = m.replaceFirst("");
                    break;
                }
            }
            if (!match) {
                throw new ParserException("Unexpected character in input: " + s);
            }
        }
    }

    public void setTokens() {
        add(FunctionExpressionNode.getAllFunctions(), 1); // function
        add("\\(", 2);                            // open bracket
        add("\\)", 3);                            // close bracket
        add("[+-]", 4);                           // plus or minus
        add("[*/]", 5);                           // multiply or divide
        add("\\^", 6);                            // raised
        add("[0-9]+",7);                          // integer number
        add("[a-zA-Z][a-zA-Z0-9_]*", 8);          // variable
    }
}
