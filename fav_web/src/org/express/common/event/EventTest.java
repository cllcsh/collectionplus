package org.express.common.event;

public class EventTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		start();
	}
	
	public static void start()
	{
		EventHandler e1 = new EventHandler();
		
		a aa = new a();
		b bb = new b();
		
		e1.AddEvent(aa , "callme" , "Wahahaha");
		e1.AddEvent(bb , "callme" , "");
		
		try {
			e1.Notify();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static class a
	{
		public a() {
			// TODO Auto-generated constructor stub
			System.out.println("I am A");
		}
		
		public void callme(String name)
		{
			System.out.println("Hello I am A " + name);
		}
	}
	
	public static class b
	{
		public b() {
			// TODO Auto-generated constructor stub
			System.out.println("I am B");
		}
		
		public void callme()
		{
			System.out.println("Hello I am B");
		}
	}
}
