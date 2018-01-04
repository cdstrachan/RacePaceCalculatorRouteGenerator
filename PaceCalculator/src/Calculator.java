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
        myChart.distance = 10;        
        
        // setup elevation profiles
        // +1 is just to make user input easier
		elevation = new double[(int) Math.ceil(myChart.distance) + 1];  // create elevation profiles
		elevation[1] = 2;
		elevation[2] = 5;
		elevation[3] = 2;
		elevation[4] = 23;
		elevation[5] = 37;
		elevation[6] = -18;
		elevation[7] = -30;
		elevation[8] = -2;
		elevation[9] = -3;
		elevation[10] = -3;
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
		myChart.manualWeighting = manualWeighting;
		myChart.raceName = "Ommedraai";
		myChart.plannedRaceTimeFirst = LocalTime.of(0,54,30);
		myChart.plannedRaceTimeLast = LocalTime.of(0,54,30);
		myChart.plannedRaceTimeDelta = LocalTime.of(0,15,00);
		myChart.startDelay = LocalTime.of(0,0,30);
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