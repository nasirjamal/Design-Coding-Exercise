import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class check_status{

	static BufferedReader br = null;


	public static class status_of implements Runnable{

		String file="";
		int time_interval=0;

		public status_of(String f, int t){
			file=f;
			time_interval=t;

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true)
			{
				try {
					br = new BufferedReader(new FileReader(file));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					String line = br.readLine();
					int check=0;
					while (line != null) {
						String server = line.split(" ")[0];
						String instance = line.split(" ")[1];
						String status = line.split(" ")[2];
						//		    	System.out.println("Server Name: " + server);
						//		    	System.out.println("Instances Running: " + instance);
						//		    	System.out.println("Status :" + status);
						//		        System.out.println(line);

						int current_instance_state= Integer.parseInt(instance.split("/")[0]);
						if ( current_instance_state > 3 || current_instance_state < 0)
						{
							System.out.println("Incorrect input for " +server+ " Instance. Maximum allowed instance '3' found "+current_instance_state);
						}
						switch (current_instance_state)
						{
						case 1:
							System.out.println("AMBER Alert for "+server);
							check=1;
							break;

						case 2:
							System.out.println("RED Alert for "+server);
							check=1;
							break;

						case 3:
							System.out.println("RED Alert for "+server);
							check=1;
							break;

						default:
							//System.out.println("NO ALERTS");
							break;
						}

						line = br.readLine();
					}
					if (check==0)
					{
						System.out.println("NO ALERTS");
					}
				} 

				catch(IOException e)
				{
					System.out.println(e.toString());
				}

				finally 
				{
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(time_interval);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("--------------------------------------------------------------------------------");
			}


		}

	}


	public static void main(String args[]) throws IOException
	{
		String file = "";
		int time_interval=0;
		try {
			br = new BufferedReader(new FileReader("check_status.conf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			file = br.readLine().split("=")[1];
			time_interval = Integer.parseInt(br.readLine().split("=")[1])*1000*60;
			System.out.println(file);
			System.out.println(time_interval);

		}
		finally 
		{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		

		Thread t1= new Thread(new status_of(file,time_interval));
		t1.start();
	}
}
