package lambdasinaction.chap5;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.*;
import java.nio.charset.Charset;
import java.nio.file.*;

public class BuildingStreams {

    public static void main(String...args) throws Exception{
        
    	System.out.println("Building a stream with Stream.of(Object...)");
        // Stream.of
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        System.out.println("Building a empty stream with Stream.empty()");
        // Stream.empty
        Stream<String> emptyStream = Stream.empty();

        System.out.println("Building a stream with Arrays.stream");
        System.out.println("If on a int[] it's return an IntStream (Stream of int privitive)");
        // Arrays.stream
        int[] numbers = {2, 3, 5, 7, 11, 13};
        System.out.println(Arrays.stream(numbers).sum());

        // Stream.iterate
        System.out.println("Building a stream with Stream.iterate (Nice for building custom sequence)");
        Stream.iterate(0, n -> n + 2)
              .limit(10)
              .forEach(System.out::println);

        // fibonnaci with iterate
        System.out.println("Demonstration of Stream.iterate with fibonnaci implemantation (n and n+1 values)");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1],t[0] + t[1]})
              .limit(10)
              .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
        System.out.println("Demonstration of Stream.iterate with fibonnaci implemantation (Only the n value)");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1],t[0] + t[1]})
              .limit(10)
              . map(t -> t[0])  
              .forEach(System.out::println);

        System.out.println("Random stream of doubles with Stream.generate");
        // random stream of doubles with Stream.generate
        Stream.generate(Math::random)
              .limit(10)
              .forEach(System.out::println);
 
        System.out.println("Stream of 1s with Stream.generate");
        // stream of 1s with Stream.generate
        IntStream.generate(() -> 1)
                 .limit(5)
                 .forEach(System.out::println);

        System.out.println("Stream with Stream.generate and an anonymous IntSupplier");
        IntStream.generate(new IntSupplier(){
            public int getAsInt(){
                return 2;
            }
        }).limit(5)
          .forEach(System.out::println);
   
        System.out.println("Declaring anonymous IntSupplier implementing fibonnati");
        IntSupplier fib = new IntSupplier(){
                  private int previous = 0;
                  private int current = 1;
                  public int getAsInt(){
                      int nextValue = this.previous + this.current;
                      this.previous = this.current;
                      this.current = nextValue;
                      return this.previous;
                  }
              };
         System.out.println("Using the fibonnation IntSupplier");
         IntStream.generate(fib).limit(10).forEach(System.out::println);

         System.out.println("Counting unique words in a file :");
         System.out.println("\tUsing Files.lines for getting the lines in the file as a Stream");
         System.out.println("\tUsing Stream.flatMap and Arrays.stream for getting all words in the files");
         System.out.println("\tUsing Stream.distinct for removing doublon");
         System.out.println("\tUsing Stream.count for counting !");
         long uniqueWords = Files.lines(Paths.get("lambdasinaction/chap5/data.txt"), Charset.defaultCharset())
                                 .flatMap(line -> Arrays.stream(line.split(" ")))
                                 .distinct()
                                 .count();

         System.out.println("There are " + uniqueWords + " unique words in data.txt");


    }
}
