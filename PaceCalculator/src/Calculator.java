import java.io.IOException;
import java.time.LocalTime;

public class Calculator {
	  
	public static void main(String[] args)  {
		double[] elevation;
		double [] manualWeighting;
		double fade;
        Pacechart myChart = new Pacechart();
        System.out.println("*** Starting ***");
        
        // setup race distance
        myChart.distance = 21.1;        
        
        // setup elevation profiles
        // +1 is just to make user input easier
		elevation = new double[(int) Math.ceil(myChart.distance) + 1];  // create elevation profiles
		elevation[1] = 16;
		elevation[2] = 26;
		elevation[3] = -14;
		elevation[4] = 0;
		elevation[5] = 42;
		elevation[6] = -32;
		elevation[7] = -27;
		elevation[8] = 12;
		elevation[9] = 80;
		elevation[10] = -62;
		elevation[11] = -24;
		elevation[12] = -6;
		elevation[13] = -7;
		elevation[14] = 0;
		elevation[15] = 8;
		elevation[16] = -5;
		elevation[17] = 7;
		elevation[18] = -3;
		elevation[19] = 11;
		elevation[20] = -1;
		elevation[21] = -1;
		elevation[22] = 0;
		myChart.elevation = elevation;
		
		// setup manual weighting
		manualWeighting = new double[(int) Math.ceil(myChart.distance) + 1];  // create elevation profiles
		manualWeighting [1] = 100;
		manualWeighting [2] = 100;
		manualWeighting [3] = 100;
		manualWeighting	 [4] = 100;
		manualWeighting [5] = 100;
		manualWeighting [6] = 100;
		manualWeighting [7] = 100;
		manualWeighting [8] = 100;
		manualWeighting [9] = 100;
		manualWeighting [10] = 100;
		manualWeighting [11] = 100;
		manualWeighting [12] = 100;
		manualWeighting [13] = 100;
		manualWeighting [14] = 100;
		manualWeighting [15] = 100;
		manualWeighting [16] = 100;
		manualWeighting [17] = 100;
		manualWeighting [18] = 100;
		manualWeighting [19] = 100;
		manualWeighting [20] = 100;
		manualWeighting [21] = 100;
		manualWeighting [22] = 100;
		myChart.manualWeighting = manualWeighting;
		myChart.raceName = "Slave Route";
		myChart.plannedRaceTimeFirst = LocalTime.of(2,14,15);
		myChart.plannedRaceTimeLast = LocalTime.of(3,30,00);
		myChart.plannedRaceTimeDelta = LocalTime.of(0,15,00);
		myChart.startDelay = LocalTime.of(0,0,45);
		myChart.firstFade = 1;
		myChart.lastFade = 1;

		// calculate results
		try {
			myChart.createPaceCharts();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		System.out.println("*** Complete ***");
		
        
	    }

}