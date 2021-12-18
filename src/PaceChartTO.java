import java.util.ArrayList;
// Note: This has been adapted to create the input parameters only

public class PaceChartTO {
	// inputs
	private double distance;
	ArrayList<SplitInputTO> splitInputs;
	private String raceName;
	private String plannedRaceTimeFirst;
	private String plannedRaceTimeLast;
	private String plannedRaceTimeDelta;
	private String startDelay;
	private int firstFade;
	private int lastFade;
		
	public ArrayList<SplitInputTO> getSplitInputs() {
		return splitInputs;
	}

	public void setSplitInputs(ArrayList<SplitInputTO> splitInputs) {
		this.splitInputs = splitInputs;
	}

	public String getRaceName() {
		return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	public String getPlannedRaceTimeFirst() {
		return plannedRaceTimeFirst;
	}

	public void setPlannedRaceTimeFirst(String plannedRaceTimeFirst) {
		this.plannedRaceTimeFirst = plannedRaceTimeFirst;
	}

	public String getPlannedRaceTimeLast() {
		return plannedRaceTimeLast;
	}

	public void setPlannedRaceTimeLast(String plannedRaceTimeLast) {
		this.plannedRaceTimeLast = plannedRaceTimeLast;
	}

	public String getPlannedRaceTimeDelta() {
		return plannedRaceTimeDelta;
	}

	public void setPlannedRaceTimeDelta(String plannedRaceTimeDelta) {
		this.plannedRaceTimeDelta = plannedRaceTimeDelta;
	}

	public String getStartDelay() {
		return startDelay;
	}

	public void setStartDelay(String startDelay) {
		this.startDelay = startDelay;
	}

	public int getFirstFade() {
		return firstFade;
	}

	public void setFirstFade(int firstFade) {
		this.firstFade = firstFade;
	}

	public int getLastFade() {
		return lastFade;
	}

	public void setLastFade(int lastFade) {
		this.lastFade = lastFade;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}




	