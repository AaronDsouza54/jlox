package com.craftinginterpreters.lox;

import static com.craftinginterpreters.lox.TokenType.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;
    private static final HashMap<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("and", AND);
        keywords.put("class", CLASS);
        keywords.put("else", ELSE);
        keywords.put("false", FALSE);
        keywords.put("for", FOR);
        keywords.put("function", FUN);
        keywords.put("if", IF);
        keywords.put("null", NIL);
        keywords.put("or", OR);
        keywords.put("print", PRINT);
        keywords.put("return", RETURN);
        keywords.put("super", SUPER);
        keywords.put("this", THIS);
        keywords.put("true", TRUE);
        keywords.put("let", LET);
        keywords.put("while", WHILE);
    }

    Scanner(String source) {
        this.source = source;
    }
    List<Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }
        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }
    private boolean isAtEnd() {
        return current >= source.length();
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(' : addToken(LEFT_PAREN); break;
            case ')' : addToken(RIGHT_PAREN); break;
            case '{' : addToken(LEFT_BRACE); break;
            case '}' : addToken(RIGHT_BRACE); break;
            case ',' : addToken(COMMA); break;
            case '.' : addToken(DOT); break;
            case '-' : addToken(MINUS); break;
            case '+' : addToken(PLUS); break;
            case ';' : addToken(SEMICOLON); break;
            case '*' : addToken(STAR); break;
            case '!' : addToken(match('=') ? BANG_EQUAL : BANG); break;
            case '=' : addToken(match('=') ? EQUAL_EQUAL : EQUAL); break;
            case '<' : addToken(match('=') ? LESS_EQUAL : LESS); break;
            case '>' : addToken(match('=') ? GREATER_EQUAL : GREATER); break;
            case '/' :
                if (match('/')) {
                    while (peek() != '\n' && !isAtEnd()) advance();
                } else {
                    addToken(SLASH);
                }
                break;
            case ' ' :
            case '\r' :
            case '\t' : break;
            case '\n' : line++; break;
            case '"': string(); break;
            default: 
            if (isDigit(c)) {
                number();
            } else if (isAlpha(c)) {
                identifier();
            }else {
                Lox.error(line, "Unexpected character."); break;
            }
        } 
    }

    private void identifier() {
        while (isAlphaNumeric(peek())) advance();
        
        String text = source.substring(start, current);
        TokenType type = keywords.get(text);
        if (type == null) type=IDENTIFIER;        
        addToken(type);
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private void number() {
        while (isDigit(peek())) advance();
        
        //look for a fractional part
        if (peek() == '.' && isDigit(peekNext())) {
            advance();
            while (isDigit(peek())) advance();
        }
        addToken(NUMBER, Double.valueOf(source.substring(start, current)));
    }

    private char peekNext() {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current+1);
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }
        if (isAtEnd()) {
            Lox.error(line, "Unterminated String");
            return;
        }
        
        //for the closing "
        advance();

        //trims the surrounding quotation marks and then creates a token for it.
        String value = source.substring(start+1, current-1);
        addToken(STRING, value);
    }

    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;

        current++;
        return true;
    }

    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private char advance() {
        current++;
        return source.charAt(current-1);
    }
    private void addToken(TokenType type) {
        addToken(type, null);
    }
    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }
}
