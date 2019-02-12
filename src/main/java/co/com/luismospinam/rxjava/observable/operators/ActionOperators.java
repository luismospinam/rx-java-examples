package co.com.luismospinam.rxjava.observable.operators;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ActionOperators {

  public static void main(String[] args) throws InterruptedException {
    ActionOperators operator = new ActionOperators();
    operator.doOnDispose();
  }
  
  
  public void doOnNextOperator() {
    Observable.just("Luis", "Miguel", "Ospina")
      .map(s -> "concat: " + s)
      .doOnNext(s -> System.out.println("doOnNext: " + s))
      .subscribe(System.out::println);
  }
  
  
  public void doOnCompleteOperator() {
    Observable.just("Luis", "Miguel", "Ospina")
    .doOnComplete(() -> System.out.println("doOnComplete1"))
    .map(s -> "concat: " + s)
    .doOnComplete(() -> System.out.println("doOnComplete2"))
    .subscribe(System.out::println,
        t -> {},
        () -> System.out.println("Observable completed"));
  }
  
  
  public void doOnErrorOperator() {
    Observable.error(new Exception("Error Luis"))
      .doOnError(t -> System.out.println("doOnError"))
      .subscribe(o -> {},
          t -> System.out.println("onError Observable"));
  }
  
  
  public void doOnTerminateOperator() {
    Observable.just(new Random().nextInt())
      .map(i -> {
        if(i % 2 == 0)
          throw new Exception("Error Even");
          
        return i;
      })
      .doOnTerminate(() -> System.out.println("Observable Completed either Eror or Completed"))
      .subscribe(System.out::println,
          Throwable::printStackTrace,
          () -> System.out.println("Completed succesfully"));
  }
  
  
  public void doOnSubscribe() {
    Observable<String> observable = Observable.just("Luis", "Miguel", "Ospina")
      .doOnSubscribe(disposable -> System.out.println("A new Subscriber " + disposable));
  
    observable.subscribe();
    observable.subscribe();
  }
  
  
  public void doOnDispose() throws InterruptedException {
    Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
      .doOnDispose(() -> System.out.println("Called Disposed()"))
      .subscribe(System.out::println);
    
    TimeUnit.SECONDS.sleep(3);
    
    disposable.dispose();
  }
  
  
}
