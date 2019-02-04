package co.com.luismospinam.rxjava.observable.sources;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class SourceObservable {

  public static void main(String[] args) throws Exception {
    SourceObservable instance = new SourceObservable();
    instance.fromCallableSource();;
  }


  public void justSource() {
    Observable.just("s1", "s2", "s3", "s4", "s5", "s6", "s6", "s7", "s8")
        .map(s -> "Source Just " + s).subscribe(System.out::println);
  }


  public void intervalSource() throws InterruptedException {
    Observable
      .interval(1, TimeUnit.SECONDS).map(i -> "Source Interval " + i)
      .subscribe(System.out::println);

    TimeUnit.SECONDS.sleep(10); // Interval runs in separate Thread
  }


  public void createSource() {
    Observable<String> source = Observable.create(emitter -> {
      emitter.onNext("s1");
      emitter.onNext("s2");
      emitter.onNext("s3");
      emitter.onNext("s4");

      emitter.onComplete();
    });

    source
      .map(s -> "Create Source " + s)
      .subscribe(System.out::println);
  }


  public void fromIterableSource() {
    List<String> list = Arrays.asList("s1", "s2", "s3", "s4", "s5", "s6");

    Observable.fromIterable(list)
      .map(s -> "From Iterable Source" + s)
      .subscribe(System.out::println);
  }


  public void rangeSource() {
    Observable.range(10, 10) // Start, CountOfEmits
        .map(i -> "Range Source " + i)
        .subscribe(System.out::println);
  }


  public void fromFutureSource() throws Exception {
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
      }
      return "Future Completed";
    });

    Observable.fromFuture(future)
      .map(s -> "From Future Source " + s)
      .subscribe(System.out::println);
  }


  public void emptySource() {
    Observable.empty()
      .subscribe(System.out::println, 
          Throwable::printStackTrace,
          () -> System.out.println("The emittion is completed"));
  }

  public void neverSource() {
    Observable.never()
      .subscribe(System.out::println, 
          Throwable::printStackTrace,
          () -> System.out.println("The emmition is completed"));

    System.out.println("It should not print anything, even the onComplete method");
  }


  public void errorSource() {
    Observable.error(new Exception("Error Occurred"))
      .subscribe(System.out::println,
        e -> System.out.println("onError method " + e.getMessage()),
        () -> System.out.println("The emmition is completed")); // onComplete does not get executed
  }


  /**
   * Creates a new fresh observable from each observer, that's why it takes source variable changes
   * in consideration.
   */
  public void deferSource() {
    final StringBuilder source = new StringBuilder("source");

    Observable<String> obs = Observable.defer(() -> Observable.just(source.toString()));
    obs.subscribe(System.out::println);

    source.append(" modified");
    obs.subscribe(System.out::println);
  }
  
  
  public void fromCallableSource() {
    //without exception
    Callable<String> callable = () -> "s1";
    
    Observable.fromCallable(callable)
      .map(s -> "from callable " + s)
      .subscribe(s -> System.out.println("onNext: " + s));
    
    //throwing exception
    Callable<String> callableException = () -> {throw new Exception("error");};
    
    Observable.fromCallable(callableException)
      .map(s -> "from callable " + s)
      .subscribe(System.out::println,
            e -> System.out.println("onError " + e.getMessage()));
  }

}
