import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Logger;

//import SplitInputTO;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class PaceCalculatorRouteGenerator {

	public static void main(String[] args) throws JsonIOException, IOException {

		Logger log = Logger.getLogger("PaceCalculatorRouteGenerator");
		log.info("*** Starting ***");

		String inputFile;
		if (args.length > 0)
			inputFile = args[0];
		else
			inputFile = "/Users/craigstrachan/Downloads/input.txt";
			log.info("Input file not specified, using default.");

		ArrayList<SplitInputTO> splitInputs;
		PaceChartTO paceChartTO = new PaceChartTO();

		// read input file
		log.info("Reading from file:" + inputFile);
		FileInputStream fstream = new FileInputStream(inputFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String[] line;

		/*
		 * Sample input:
		 raceName|Ommedraai 10k, 2019"
		 plannedRaceTimeFirst|00:39:00
		 plannedRaceTimeLast|01:59:00
		 plannedRaceTimeDelta|00:10:00
		 startDelay|00:01:00
		 firstFade|0
		 lastFade|2
		 distance|10.0
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
			// read the elevation from the input file (remove the \t-tabs which can sneak in
			// from pasting the input file from excel)
			splitInput.setElevation(Integer.parseInt((br.readLine().replace("\t", ""))));
			splitInput.setManualWeight(100);

			if (counter + 1 > paceChartTO.getDistance())
				splitInput.setSplitDistance(paceChartTO.getDistance());
			else
				splitInput.setSplitDistance(counter + 1);
			splitInputs.add((splitInput));

		}
		fstream.close();
		paceChartTO.setSplitInputs(splitInputs);
		Gson gson = new Gson();
		String outputFile = inputFile + ".json";

		log.info("Writing to file:" + outputFile);

		Writer writer = new FileWriter(outputFile);
		String jsonOutput = gson.toJson(paceChartTO);
		writer.write(jsonOutput);
		writer.close();
		log.info("*** Complete ***");
	}

}