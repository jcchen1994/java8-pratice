package cool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

class TraderTest {
  private List<Transaction> transactions=null;
  @BeforeEach
  public void before(){
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");
    transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );
  }
  @Test
  @DisplayName("发生在2011年的所有交易从低到高")
  public void test1(){
    transactions.stream().filter(transaction -> transaction.getYear()==2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(s->System.out.println(s.getYear()+":"+s.getValue()));
  }

  @Test
  @DisplayName("交易员都在哪些城市工作果")
  public void test2(){
    transactions.stream().map(Transaction::getTrader)
                .map(Trader::getCity).distinct().forEach(s->System.out.println(s));
  }

  @Test
  @DisplayName("查找所有在剑桥的交易员，按姓名排序")
  public void test3(){
    transactions.stream().map(Transaction::getTrader).filter(s->s.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName)).distinct().forEach(s->System.out.println(s.getName()));
  }

  @Test
  @DisplayName("所有交易员姓名字符串，按字母排序")
  public void test4(){
    String names=transactions.stream().map(Transaction::getTrader).sorted(Comparator.comparing(Trader::getName))
                .map(Trader::getName).distinct().reduce("",(n1,n2)->n1+n2);
    System.out.println(names);
  }

  @Test
  @DisplayName("有没有交易员在米兰工作的")
  public void test5(){
    boolean inMilan=transactions.stream().map(Transaction::getTrader).anyMatch(s->s.getCity().equals("Milan"));
    System.out.println(inMilan);
  }

  @Test
  @DisplayName("打印生活在剑桥的交易员的所有交易额")
  public void test6(){
    Integer sum=transactions.stream().filter(s->s.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue)
                .reduce(0,(n1,n2)->n1+n2);
    System.out.println(sum);
  }

  @Test
  @DisplayName("所有交易额中最高的交易额")
  public void test7(){
    Optional<Integer> max=transactions.stream().map(t->t.getValue()).max(Integer::compareTo);
    System.out.println(max.get());
  }

  @Test
  @DisplayName("所有交易额中最小的交易额")
  public void test8(){
    Optional<Integer> min=transactions.stream().map(Transaction::getValue).min(Integer::compareTo);
    System.out.println(min.get());
  }
}

























