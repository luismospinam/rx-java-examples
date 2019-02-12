package co.com.luismospinam.rxjava.observable.operators;

import io.reactivex.Observable;

public class ReducingOperators {

  public static void main(String[] args) {
    ReducingOperators operator = new ReducingOperators();
    operator.allOperator();
  }
  
  public void countOperator() {
    Observable.range(1, 50)
      .count()
      .subscribe(System.out::println);
  }
  
  
  public void reduceOperator() {
    Observable.range(1, 5)
      .reduce((n1, n2) -> n1 * n2)
      .subscribe(System.out::println);
  }
  
  
  public void reduceSeedOperator() {
    Observable.range(1, 2)
      .reduce(0, (n1, n2) -> n1 * n2)
      .subscribe(System.out::println);
  }
  
  
  public void allOperator() {
    Observable.just("Luis", "Luis2", "Luis3")
      .all(s -> s.startsWith("L"))
      .subscribe(System.out::println);
  }
  
  
  public void anyOperator() {
    Observable.range(1, 10)
      .any(i -> i % 2 == 0)
      .subscribe(System.out::println);
  }
  
  
  public void containsOperator() {
    Observable.just("Luis", "Luis2", "Luis3")
      .contains("Luis4")
      .subscribe(System.out::println);
  }
  
}
