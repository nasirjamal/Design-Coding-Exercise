
public class swap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=10;
		int b =20;
		System.out.println("Value of a and b before swapping, a: "+a +" b: "+b);
		
		a=a+b;
		b=a-b;
		a=a-b;
		
		System.out.println("Value of a and b after swapping, a: "+a +" b: "+b);	
	}
}
