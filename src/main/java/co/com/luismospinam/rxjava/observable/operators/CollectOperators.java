package co.com.luismospinam.rxjava.observable.operators;

import io.reactivex.Observable;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CollectOperators {
  
  public static void main(String[] args) {
    CollectOperators operator = new CollectOperators();
    operator.collectOperator();
  }
  
  
  public void toListOperator() {
    final List<Integer> listInteger;
    
    //Default Array
    listInteger = Observable.range(1, 100)
      .toList()
      .blockingGet();
    
    listInteger.forEach(System.out::println);
  }
  
  
  public void toListSpecifyingTypeOperator() {
    List<Integer> list = Observable.range(1, 100)
      .toList(LinkedList::new)
      .blockingGet();
    
    list.forEach(System.out::println);
  }
  
  
  public void toSortedListOperator() {
    List<Integer> list = Observable.just(4,6,1,2,9,3,8,7)
      .toSortedList()
      .blockingGet();
    
    list.forEach(System.out::println);
  }
  
  
  public void toMapOperator() {
    AtomicInteger keyInt = new AtomicInteger(1);
    
    Observable.just("value1", "value2", "value3")
      .toMap(s -> keyInt.getAndIncrement())
      .subscribe(System.out::println);
  }
  
  
  public void toMapGeneratingValueOperator() {
    Observable.range(1, 20)
      .toMap(i -> "key" + i, i -> "value" + i)
      .subscribe(System.out::println);
  }
  
  
  public void toMapSpecifyingType() {
    Observable.range(1, 20)
    .toMap(i -> "key" + i,
        i -> "value" + i,
        LinkedHashMap::new)
    .subscribe(System.out::println);
  }
  
  
  public void toMultiMapOperator() {
    Observable.just(1,1,2,2,3,4,5,6,6)
      .toMultimap(i -> "key" + i)  //if the key exists do not replace it, create a list of values
      .subscribe(System.out::println);     
  }
  
  
  public void collectOperator() {
    Observable.just("Luis", "Luis", "Miguel", "Ospina", "Ospina")
      .collect(HashSet::new, (set, string) -> set.add(string))
      .subscribe(System.out::println);   
  }

}
