package co.com.luismospinam.rxjava.observable.operators;

import io.reactivex.Observable;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ErrorRecoveryOperators {
  
  public static void main(String[] args) {
    ErrorRecoveryOperators operator = new ErrorRecoveryOperators();
    operator.retryUntilOperator();
  }
  
  
  public void onErrorReturnItemOperator() {
    Observable.range(1, 10)
      .map(i -> {
        if(i > 5) {
          throw new RuntimeException();
        }
        
        return i;
      })
      .onErrorReturnItem(-1)
      .subscribe(System.out::println);
  }
  
  
  public void onErrorReturnOperator() {
    Observable.range(1, 10)
      .map(i -> {
        if(i > 5) {
          throw new RuntimeException("-1");
        }
        
        return i;
      })
      .onErrorReturn(throwable -> Integer.valueOf(throwable.getMessage()))
      .subscribe(System.out::println);
  }
  
  
  public void onErrorResumeNextOperator() {
    Observable.range(1, 10)
    .map(i -> {
      if(i > 5) {
        throw new RuntimeException("-1");
      }
      
      return i;
    })
    .onErrorResumeNext(Observable.just(-1, -1))
    .subscribe(System.out::println);
  }
  
  
  public void onErrorResumeNextFromFunctionOperator() {
    Observable.range(1, 10)
    .map(i -> {
      if(i > 5) {
        throw new RuntimeException("-1");
      }
      
      return i;
    })
    .onErrorResumeNext(t -> {return Observable.just(Integer.valueOf(t.getMessage()));})
    .subscribe(System.out::println);
  }
  
  
  public void retryOperator() {
    AtomicInteger counter = new AtomicInteger(1);
    Observable.range(1, 10)
      .map(i -> {
        if(counter.getAndIncrement() < 3) {
          System.out.println("Throwing error");
          throw new RuntimeException();
        }
        
        return i;
      })
      .retry()
      .subscribe(System.out::println);
  }
  
  
  public void retryTimesOperator() {
    Observable.range(1, 10)
      .map(i -> {
        if(i % 2 == 0) {
          System.out.println("Throwing error");
          throw new RuntimeException();
        }
        
        return i;
      })
      .retry(3)
      .subscribe(System.out::println);
  }
  
  
  public void retryPredicateOperator() {
    AtomicInteger counter = new AtomicInteger(1);
    
    Observable.range(0, 10)
      .map(i -> {
        if(counter.getAndIncrement() < 3) {
          System.out.println("Throwing error");
          throw new RuntimeException();
        }
        
        return i;
      })
      .retry(throwable -> counter.get() < 5)
      .subscribe(System.out::println);    
  }
  
  
  public void retryUntilOperator() {
    Observable.range(0, 10)
    .map(i -> {
      if(i > 5) {
        System.out.println("Throwing error");
        throw new RuntimeException();
      }
      
      return i;
    })
    .retryUntil(() -> new Random().nextBoolean())
    .subscribe(System.out::println);
  }

}
