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
		elevation[1] = 0;
		elevation[2] = 0;
		elevation[3] = 0;
		elevation[4] = 0;
		elevation[5] = 0;
		elevation[6] = 0;
		elevation[7] = 0;
		elevation[8] = 0;
		elevation[9] = 0;
		elevation[10] = 0;
		
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
		myChart.manualWeighting = manualWeighting;
		myChart.raceName = "Test";
		myChart.plannedRaceTimeFirst = LocalTime.of(1,00,00);
		myChart.plannedRaceTimeLast = LocalTime.of(1,30,00);
		myChart.plannedRaceTimeDelta = LocalTime.of(0,55,00);
		myChart.startDelay = LocalTime.of(0,0,0);
		myChart.firstFade = 3;
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