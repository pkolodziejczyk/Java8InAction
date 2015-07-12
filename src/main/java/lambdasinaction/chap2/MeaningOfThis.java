package lambdasinaction.chap2;

public class MeaningOfThis
{
	public final int value = 4;
	public void doIt()
	{
		int value = 6;
		Runnable r = new Runnable(){
			public final int value = 5;
			public void run(){
				int value = 10;
				System.out.println(this.value);
			}
		};
		r.run(); 
	}
	public static void main(String...args)
	{   
		// Java tricks on variable : Scope of this
		MeaningOfThis m = new MeaningOfThis();
		m.doIt(); // ???   
	}
}
