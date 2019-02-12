package co.com.luismospinam.rxjava.observable.operators;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class SuppressingOperators {
  
  public static void main(String args[]) throws InterruptedException {
    SuppressingOperators operators = new SuppressingOperators();
    
    operators.elementAtOrErrorOperator();
  }
  
  
  public void filterOperator() {
    Observable.range(1, 50)
      .filter(i -> i % 2 == 0)
      .subscribe(System.out::println);
  }
  
  
  public void takeOperator() {
    Observable.range(1, 50)
      .take(10)
      .subscribe(System.out::println);
  }
  
  
  public void takeTimeUnitOperator() throws InterruptedException {
    Observable.interval(1, TimeUnit.SECONDS)
      .take(4, TimeUnit.SECONDS)
      .subscribe(System.out::println);
    
    TimeUnit.SECONDS.sleep(10);
  }
  
  
  public void takeLastOperator() {
    Observable.range(1, 50)
      .takeLast(10)
      .subscribe(System.out::println);
  }
  
  
  public void skipOperator() {
    Observable.range(1, 20)
      .skip(10)
      .subscribe(System.out::println,
          e -> System.out.println("Error"),
          () -> System.out.println("Completed"));
  }
  
  
  public void skipTimeUnitOperator() throws InterruptedException {
    Observable.interval(1, TimeUnit.SECONDS)
      .skip(3, TimeUnit.SECONDS)
      .take(5)
      .subscribe(System.out::println,
          e -> System.out.println("Error"),
          () -> System.out.println("Completed"));
    
    TimeUnit.SECONDS.sleep(10);
  }
  
  
  public void skipLastOperator() {
    Observable.range(1, 50)
      .skipLast(10)
      .subscribe(System.out::println);    
  }
  
  
  public void takeWhileOperator() {
    Observable.range(1, 50)
      .takeWhile(i -> i < 30)
      .subscribe(System.out::println);
  }
  
  
  public void skipWhileOperator() {
    Observable.range(1, 50)
      .skipWhile(i -> i < 30)
      .subscribe(System.out::println);
  }

  
  public void takeUntilOperator() throws InterruptedException {
    Observable.interval(1, TimeUnit.SECONDS)
      .takeUntil(Observable.interval(5, TimeUnit.SECONDS))
      .subscribe(System.out::println);
    
    TimeUnit.SECONDS.sleep(10);
  }
  
  
  public void skipUntilOperator() throws InterruptedException {
    Observable.interval(1, TimeUnit.SECONDS)
      .skipUntil(Observable.interval(4, TimeUnit.SECONDS))
      .take(10)
      .subscribe(System.out::println);
    
    TimeUnit.SECONDS.sleep(10);
  }
  
  
  public void distinctOperator() {
    Observable.just(1,1,2,3,3,3,4,5,5,6)
      .distinct()
      .subscribe(System.out::println);
  }
  
  
  public void distinctGeneratingKeyOperator() {
    Observable.just("luis", "miguel", "ospina", "1234")
      .distinct(s -> s.length())
      .subscribe(System.out::println);
  }
  
  
  public void distinctUntilChangeOperator() {
    Observable.just(1,2,2,3,1,1,2,2,7)
      .distinctUntilChanged()
      .subscribe(System.out::println);
  }
  
  
  public void distinctUntilChangeGeneratingKeyOperator() {
    Observable.just("luis", "1234", "miguel", "123456")
      .distinctUntilChanged(String::length)
      .subscribe(System.out::println);
  }
  
  
  public void elementAtOperator() {
    Observable.just(0,1,2,3,4,5,6,7)
    .elementAt(8)   //Retorna un Maybe
    .subscribe(System.out::println);
  }
  
  
  public void elementAtOrErrorOperator() {
    Observable.just(0,1,2,3,4,5,6,7)
    .elementAtOrError(8)   //Retorna un Single
    .subscribe(System.out::println,
        e -> System.out.println("onError: " + e.toString()));
  }

}
