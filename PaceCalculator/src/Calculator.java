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
        myChart.distance = 30;        
        
        // setup elevation profiles
        // +1 is just to make user input easier
		elevation = new double[(int) Math.ceil(myChart.distance) + 1];  // create elevation profiles
		elevation[1] = -22;
		elevation[2] = 5;
		elevation[3] = -1;
		elevation[4] = -2;
		elevation[5] = 6;
		elevation[6] = 3;
		elevation[7] = 25;
		elevation[8] = 45;
		elevation[9] = 31;
		elevation[10] = 32;
		elevation[11] = -51;
		elevation[12] = -67;
		elevation[13] = -5;
		elevation[14] = 12;
		elevation[15] = 13;
		elevation[16] = -12;
		elevation[17] = -12;
		elevation[18] = 14;
		elevation[19] = 65;
		elevation[20] = 58;
		elevation[21] = -32;
		elevation[22] = -27;
		elevation[23] = -42;
		elevation[24] = -27;
		elevation[25] = -3;
		elevation[26] = -10;
		elevation[27] = 1;
		elevation[28] = 6;
		elevation[29] = -12;
		elevation[30] = 15;
		myChart.elevation = elevation;
		
		int baseWeightDelta = 0; //tweaking the hills
		
		// setup manual weighting
		
		// TODO: change to import file
		// TODO check if the elevation is  >1, and then allow for a base weighting delta of hills
		manualWeighting = new double[(int) Math.ceil(myChart.distance) + 1];  // create elevation profiles
		manualWeighting [1] = 100;
		manualWeighting [2] = 100;
		manualWeighting [3] = 100 + baseWeightDelta;
		manualWeighting	[4] = 100 + baseWeightDelta;
		manualWeighting [5] = 100 + baseWeightDelta;
		manualWeighting [6] = 100;
		manualWeighting [7] = 100;
		manualWeighting [8] = 100;
		manualWeighting [9] = 100 + baseWeightDelta;
		manualWeighting [10] = 100;
		manualWeighting [11] = 100 + baseWeightDelta;
		manualWeighting [12] = 100;
		manualWeighting [13] = 100;
		manualWeighting	 [14] = 100;
		manualWeighting [15] = 100;
		myChart.manualWeighting = manualWeighting;
		myChart.raceName = "Bay to Bay";
		myChart.plannedRaceTimeFirst = LocalTime.of(3,14,00);
		myChart.plannedRaceTimeLast = LocalTime.of(4,14,30);
		myChart.plannedRaceTimeDelta = LocalTime.of(0,15,00);
		myChart.startDelay = LocalTime.of(0,1,15);
		myChart.firstFade = 0;
		myChart.lastFade = 3;

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