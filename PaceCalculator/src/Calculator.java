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
        myChart.distance = 42.2;        
        
        // setup elevation profiles
        // +1 is just to make user input easier
		elevation = new double[(int) Math.ceil(myChart.distance) + 1];  // create elevation profiles
		elevation[1] = -10;
		elevation[2] = -11;
		elevation[3] = -3;
		elevation[4] = -4;
		elevation[5] = -3;
		elevation[6] = 3;
		elevation[7] = -3;
		elevation[8] = 3;
		elevation[9] = 6;
		elevation[10] = 5;
		elevation[11] = 10;
		elevation[12] = 19;
		elevation[13] = 12;
		elevation[14] = -3;
		elevation[15] = 8;
		elevation[16] = 24;
		elevation[17] = -31;
		elevation[18] = -11;
		elevation[19] = -1;
		elevation[20] = 19;
		elevation[21] = 30;
		elevation[22] = 14;
		elevation[23] = 8;
		elevation[24] = 11;
		elevation[25] = 30;
		elevation[26] = 19;
		elevation[27] = 13;
		elevation[28] = 23;
		elevation[29] = -7;
		elevation[30] = 4;
		elevation[31] = 35;
		elevation[32] = -17;
		elevation[33] = -17;
		elevation[34] = -12;
		elevation[35] = -5;
		elevation[36] = -6;
		elevation[37] = -20;
		elevation[38] = -10;
		elevation[39] = -8;
		elevation[40] = -29;
		elevation[41] = -12;
		elevation[42] = -18;
		elevation[43] = 20;
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
		myChart.raceName = "Athens Marathon";
		myChart.plannedRaceTimeFirst = LocalTime.of(4,14,15);
		myChart.plannedRaceTimeLast = LocalTime.of(5,30,00);
		myChart.plannedRaceTimeDelta = LocalTime.of(0,15,00);
		myChart.startDelay = LocalTime.of(0,2,0);
		myChart.firstFade = 1;
		myChart.lastFade = 5;

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