package co.com.luismospinam.rxjava.observable.operators;

import io.reactivex.Observable;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TransformingOperators {
  
  public static void main(String[] args) throws InterruptedException {
    TransformingOperators operators = new TransformingOperators();
    operators.scanWithInitialOperator();
  }
  
  
  public void mapOperator() {
    Observable.range(1,10)
      .map(i -> "emision: " + i)
      .subscribe(System.out::println);
  }
  
  
  public void castOperator() {
    Observable.just("Object1", "Object2")
      .cast(Object.class)
      .subscribe(System.out::println);
  }
  
  
  public void startWithOperator() {
    Observable.just("Miguel", "Ospina", "Munoz")
      .startWith("Luis")
      .subscribe(System.out::println);  
  }
  
  
  public void startWithArrayOperator() {
    Observable.just(3,4,5,6,7)
      .startWithArray(1,2)
      .subscribe(System.out::println);  
  }
  
  
  public void defaultIfEmptyOperator() {
    Observable.range(1, 5)
      .skip(10)
      .defaultIfEmpty(0)
      .subscribe(System.out::println,
          t -> {},
          () -> System.out.println("Completed"));
  }
  
  
  public void switchIfEmptyOperator() {
    Observable.range(1, 5)
      .skip(10)
      .switchIfEmpty(Observable.range(1, 10))
      .subscribe(System.out::println);
  }
  
  
  public void sortedOperator() {
    Observable.just(4,6,1,3,8,2)
      .sorted()  //Items must implement Comparable or runtime excepetion
      .subscribe(System.out::println);
  }
  
  
  public void sortedComparatorOperator() {
    Observable.just("Luis","Miguel", "Ospina", "Munoz")
      .sorted(Comparator.reverseOrder())
      .subscribe(System.out::println);
  }
  
  
  public void delayOperator() throws InterruptedException {
    Observable.range(1, 10)
      .delay(1, TimeUnit.SECONDS)
      .subscribe(System.out::println);
    
    TimeUnit.SECONDS.sleep(15);
  }
  
  
  public void delaySubscriptionOperator() throws InterruptedException {
    Observable.range(1, 10)
      .delaySubscription(1, TimeUnit.SECONDS)
      .subscribe(System.out::println);
    
    TimeUnit.SECONDS.sleep(5);
  }
  
  
  public void repeatOperator() {
    Observable.just("Luis", "Miguel", "Ospina", "Munoz")
      .repeat()
      .take(20)
      .subscribe(System.out::println);
  }
  
  
  public void repeatUntilOperator() {
    AtomicInteger count = new AtomicInteger(0);
    
    Observable.just("Luis", "Miguel", "Ospina", "Munoz")
      .repeatUntil(() -> {
        count.set(count.get() + 4);
        return count.get() > 20;
      })
      .subscribe(System.out::println);
  }
  
  
  public void scanOperator() {
    Observable.range(1, 10)
      .scan((acc,emmition) -> acc + emmition)
      .subscribe(System.out::println);
  }
  
  
  public void scanWithInitialOperator() {
    Observable.range(1, 10)
      .scan(0,(acc,emmition) -> acc + emmition)
      .subscribe(System.out::println);
  }
  
  
  public void flatMapOperator() {
    
  }
  
  
  public void concatMapOperator() {
    
  }
  
}
