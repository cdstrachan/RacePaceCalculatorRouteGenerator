import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;

//import SplitInputTO;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;


public class TemplateGenerator {
	  
	public static void main(String[] args) throws JsonIOException, IOException  {
		ArrayList<SplitInputTO> splitInputs;
		PaceChartTO paceChartTO = new PaceChartTO();
		
		// read input file
		// Open the file
		FileInputStream fstream = new FileInputStream("input.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String[] line;
		
		/*
		 * Sample input: 
		 *  raceName|Ommedraai 10k, 2019"
		 * 	plannedRaceTimeFirst|00:39:00
		 *  plannedRaceTimeLast|01:59:00
		 * 	plannedRaceTimeDelta|00:10:00
		 * 	startDelay|00:01:00
		 * 	firstFade|0
		 * 	lastFade|2
		 * 	distance|10.0
		 */
		
		line = br.readLine().split("\\|");
		paceChartTO.setRaceName(line[1]);
		
		line = br.readLine().split("\\|");
		paceChartTO.setPlannedRaceTimeFirst(line[1]);
		
		line = br.readLine().split("\\|");
		paceChartTO.setPlannedRaceTimeLast(line[1]);

		line = br.readLine().split("\\|");
		paceChartTO.setPlannedRaceTimeDelta(line[1]);

		line = br.readLine().split("\\|");
		paceChartTO.setStartDelay(line[1]);
		
		line = br.readLine().split("\\|");
		paceChartTO.setFirstFade(Integer.parseInt(line[1]));

		line = br.readLine().split("\\|");
		paceChartTO.setLastFade(Integer.parseInt(line[1]));

		line = br.readLine().split("\\|");
		paceChartTO.setDistance(Double.valueOf(line[1]));
		
		// setup elevations & manual weighting
		splitInputs = new ArrayList<SplitInputTO>();
		for (int counter = 0; counter < Math.ceil(paceChartTO.getDistance()); counter++) {
			SplitInputTO splitInput = new SplitInputTO();
			splitInput.setSplitNumber(counter + 1);
			// read the elevation from the input file (remove the \t-tabs which can sneak in from pasting the input file from excel)
			splitInput.setElevation(Integer.parseInt((br.readLine().replace("\t",""))));
			splitInput.setManualWeight(100);
			
			if (counter + 1 > paceChartTO.getDistance())
				splitInput.setSplitDistance(paceChartTO.getDistance());
			else
				splitInput.setSplitDistance(counter + 1);
			splitInputs.add((splitInput));

		}
		fstream.close();
		paceChartTO.setSplitInputs(splitInputs);
		Gson gson =new Gson();
		Writer writer = new FileWriter(paceChartTO.getRaceName() + ".json");
		String jsonOutput = gson.toJson(paceChartTO);
		writer.write(jsonOutput);
		writer.close();
		System.out.println("*** Complete ***");
		
        
	    }

}