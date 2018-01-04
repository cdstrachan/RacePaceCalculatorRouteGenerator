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
        myChart.distance = 15;        
        
        // setup elevation profiles
        // +1 is just to make user input easier
		elevation = new double[(int) Math.ceil(myChart.distance) + 1];  // create elevation profiles
		elevation[1] = -10;
		elevation[2] = 0;
		elevation[3] = 15;
		elevation[4] = 33;
		elevation[5] = 22;
		elevation[6] = -31;
		elevation[7] = -11;
		elevation[8] = -11;
		elevation[9] = 9;
		elevation[10] = -3;
		elevation[11] = 52;
		elevation[12] = -20;
		elevation[13] = -40;
		elevation[14] = -19;
		elevation[15] = -7;
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
		manualWeighting	 [14] = 100;
		manualWeighting [15] = 100;
		myChart.manualWeighting = manualWeighting;
		myChart.raceName = "Hohernort 2018 new route";
		myChart.plannedRaceTimeFirst = LocalTime.of(1,14,30);
		myChart.plannedRaceTimeLast = LocalTime.of(1,59,30);
		myChart.plannedRaceTimeDelta = LocalTime.of(0,5,00);
		myChart.startDelay = LocalTime.of(0,1,00);
		myChart.firstFade = 0;
		myChart.lastFade = 0;

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