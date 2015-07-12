package lambdasinaction.chap3;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
public class ExecuteAround {

	public static void main(String ...args) throws IOException{
        // method we want to refactor to make more flexible
		System.out.println("Processing a file with a static method");
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");

        System.out.println("Processing a file with lamdba implementation of an interface");
		String oneLine = processFile((BufferedReader b) -> b.readLine());
		System.out.println(oneLine);

		System.out.println("Processing a file with an other lamdba implementation");
		String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
		System.out.println(twoLines);

	}

    public static String processFileLimited() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("lambdasinaction/chap3/data.txt"))) {
            return br.readLine();
        }
    }


	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader("lambdasinaction/chap3/data.txt"))){
			return p.process(br);
		}

	}

	public interface BufferedReaderProcessor{
		public String process(BufferedReader b) throws IOException;

	}
}
