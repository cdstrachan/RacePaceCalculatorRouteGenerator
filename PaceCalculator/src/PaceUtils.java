import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PaceUtils {
	
		static double TimeToDouble(LocalTime theTime) {
			double result = theTime.getSecond();
			
			result = result + (double) theTime.getMinute()*60;
			result = result + theTime.getHour() * 3600;
			result = result + (double)theTime.getNano()/1000000000 ;
			return result;
		}

		static LocalTime DoubleToTime(Double theTimeDec) {
			int hours;
			int minutes;
			int seconds;
			int nano;
			
			hours = (int) (theTimeDec / 3600);
			theTimeDec = theTimeDec - (hours * 3600);
			minutes = (int) (theTimeDec / 60);
			theTimeDec = theTimeDec - (minutes * 60);
			seconds = (int) (theTimeDec*1);
			theTimeDec = theTimeDec - seconds;
			nano = (int) (theTimeDec * 1000000000);
			
			return LocalTime.of(hours,minutes,seconds,nano);
		}
		
		
		static String formatTime(LocalTime theTime){
			DateTimeFormatter timeFormatHH = DateTimeFormatter.ofPattern("hh:mm:ss");
			DateTimeFormatter timeFormatH = DateTimeFormatter.ofPattern("h:mm:ss");
			DateTimeFormatter timeFormatMM = DateTimeFormatter.ofPattern("mm:ss");
			DateTimeFormatter timeFormatM = DateTimeFormatter.ofPattern("m:ss");
			if (theTime.getHour() > 9) return theTime.format(timeFormatHH);
			if (theTime.getHour() > 0) return theTime.format(timeFormatH);
			if (theTime.getMinute() > 9) return theTime.format(timeFormatMM);
			return theTime.format(timeFormatM);
			
		}
		
		// max ZZ
		static String getCharForNumber(int i) {
		    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		    
		    int small = i % 26;
		    int large = i / 26 -1;
		    if (large >25) return null;
		    
		    //string base;
		    if (large >=0)
		    	return Character.toString(alphabet[large]) + Character.toString(alphabet[small]);
		    else 
		    	return Character.toString(alphabet[small]);
		}
		
		// TODO: make static variables
		static double calcPaceImpact(double elevation) {
			if (elevation > 25) return 0.4;
			if (elevation > 0) return 0.5;
			if (elevation < -20) return 0.15;
			if (elevation < 0) return 0.3;
			return 0;
			
		}
		

}
