package edu.cnm.deepdive.parsing;

import edu.cnm.deepdive.token.Token;
import java.util.LinkedList;

public class TokenList {
  private TokenContainer first;
  private TokenContainer last;
  private int count;

  public int getCount(){
    return count;
  }

  public TokenContainer getLast(){
    return last;
  }

  public TokenContainer getFirst(){
    return first;
  }

  public TokenList(LinkedList<Token> from){
    for (Token t : from){
      addLast(t);
    }
  }

  public void addLast(Token value){
    TokenContainer newNode = new TokenContainer(value);
    count++;
    if (first == null){
      first = newNode;
      last = newNode;
    }
    else{
      last.next = newNode;
      newNode.previous = last;
      last = newNode;
    }
  }

  public static class TokenContainer{
    public Token value;
    public TokenContainer next;
    public TokenContainer previous;

    public TokenContainer(Token value){
      this.value = value;
    }
  }
}
