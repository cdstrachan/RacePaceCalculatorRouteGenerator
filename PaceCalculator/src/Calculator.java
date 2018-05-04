import java.io.IOException;
import java.time.LocalTime;


public class Calculator {
	  
	public static void main(String[] args)  {
		double[] elevation;
		double [] manualWeighting;
        Pacechart myChart = new Pacechart();
        System.out.println("*** Starting ***");
        
        // setup race distance
        myChart.distance = 10;        
        
        // setup elevation profiles
        // +1 is just to make user input easier
		elevation = new double[(int) Math.ceil(myChart.distance) + 1];  // create elevation profiles
		elevation[1] = 1;
		elevation[2] = 2;
		elevation[3] = 3;
		elevation[4] = 4;
		elevation[5] = 5;
		elevation[6] = 4;
		elevation[7] = 3;
		elevation[8] = 2;
		elevation[9] = 1;
		elevation[10] = 0;
		
		myChart.elevation = elevation;
		
		int baseWeightDelta = 0; //tweaking the hills
		
		// setup manual weighting		
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
		myChart.raceName = "Tesst";
		myChart.plannedRaceTimeFirst = LocalTime.of(1,00,00);
		myChart.plannedRaceTimeLast = LocalTime.of(1,30,00);
		myChart.plannedRaceTimeDelta = LocalTime.of(0,55,00);
		myChart.startDelay = LocalTime.of(0,0,30);
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