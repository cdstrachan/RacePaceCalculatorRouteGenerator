import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;

// This class creates the splits and the final output file
public class Pacechart {
	double distance;
	double[] elevation;
	double[] manualWeighting;
	private LocalTime averageMovingPace;
	private LocalTime averageEndToEndPace;
	ArrayList<Split> raceSplits;
	String raceName;
	Workbook wb;
	
	LocalTime plannedRaceTimeFirst;
	LocalTime plannedRaceTimeLast;
	LocalTime plannedRaceTimeDelta;
	LocalTime startDelay;
	int firstFade;
	int lastFade;
	
	public void createPaceCharts() throws IOException {
		wb = new XSSFWorkbook();

        double plannedRaceTimeFirstDec = utils.TimeToDouble(plannedRaceTimeFirst); 
        double plannedRaceTimeLastDec = utils.TimeToDouble(plannedRaceTimeLast);
        double plannedRaceTimeDeltaDec = utils.TimeToDouble(plannedRaceTimeDelta);
        
        // count through the different race times
        for (double raceTimeDec = plannedRaceTimeFirstDec; raceTimeDec <= plannedRaceTimeLastDec; raceTimeDec += plannedRaceTimeDeltaDec) {
    		int colOffset = 1;
        	LocalTime raceTime = utils.DoubleToTime(raceTimeDec);
        	String chartName = raceTime.getHour() + "h" + raceTime.getMinute();
            Sheet sheet = wb.createSheet(chartName);
            
            // count through the fades
	        for (int fade = firstFade; fade <= lastFade; fade ++) {
	        	System.out.println("Creating chart for time:" + utils.formatTime(raceTime) + ", fade: " +  fade);
		        createPaceChart(sheet, raceTime, startDelay, fade, colOffset);
	        	colOffset += 6;
	        }
        }
		// Write the output to a file 
		String file = raceName + ".xlsx";
		FileOutputStream out = new FileOutputStream(file);
		wb.write(out);
		out.close();
	}
	
	public void createPaceChart(Sheet sheet, LocalTime plannedRaceTime, LocalTime startDelay, double fade, int colOffset) {
		
		// calculate the average pace from the planned race time and the start delay
		double totalWeightedTimeDec = 0;
		double timeOverrunFactor;
		double finalElapsedTimeDec = 0;
		
		
		raceSplits = new ArrayList<Split>();
		
		averageMovingPace = utils.DoubleToTime((utils.TimeToDouble(plannedRaceTime) - utils.TimeToDouble(startDelay)) / distance);
		averageEndToEndPace = utils.DoubleToTime((utils.TimeToDouble(plannedRaceTime)) / distance);
		
		// calculate what we can without totals
		for (int counter = 1;counter < Math.ceil(distance) + 1; counter ++)  //TODO: cater for fractions eg 21.1
		{
			Split raceSplit = new Split();
			
	        // the last lap may be a different (shorter) distance
			raceSplit.splitNumber = counter;
	        if (counter == Math.ceil(distance))
	        	raceSplit.distance = ((double)Math.round((distance - Math.floor(distance))*100))/100;
	        else
	        	raceSplit.distance = 1;
	        
	        raceSplit.elevation = elevation[counter];
	        raceSplit.manualWeighting = 100; //manualWeighting[counter];
	        
			// calculate the split time
	        if (counter == 1)
			{
				raceSplit.nominalTime = utils.DoubleToTime((utils.TimeToDouble(averageMovingPace) + utils.TimeToDouble(startDelay)) * raceSplit.distance);
			}
			else
				raceSplit.nominalTime = utils.DoubleToTime(utils.TimeToDouble(averageMovingPace) * raceSplit.distance);

			// cater for the fade 
	        if (counter <= 1+ distance/2)
	        	raceSplit.fadeFactor = 1 - fade/100;
	        else
	        	raceSplit.fadeFactor = 1 + fade/100;
	        	        
	        raceSplit.calculatePacePerSplit();
	        totalWeightedTimeDec += raceSplit.weightedTimeDec;
	        raceSplits.add((raceSplit));	         
		}
		// calculate totals how much out our total weighted time is
		timeOverrunFactor = totalWeightedTimeDec / utils.TimeToDouble(plannedRaceTime);

		// calculate final times
		for (Split raceSplit : raceSplits)
		{
			raceSplit.finalTimeDec = raceSplit.weightedTimeDec / timeOverrunFactor;
			raceSplit.finalTime = utils.DoubleToTime(raceSplit.finalTimeDec);
			raceSplit.finalPace = utils.DoubleToTime(raceSplit.finalTimeDec / raceSplit.distance); 
			finalElapsedTimeDec += raceSplit.finalTimeDec;
			raceSplit.finalElapsedTime = utils.DoubleToTime(finalElapsedTimeDec);
			
		}
		
		//create spreadsheet
		Map<String, CellStyle> styles = createStyles(wb);
		int rowOffset = 0;
		Row row;
		Cell cell;
		rowOffset ++;
		
		row = createRow (sheet,rowOffset);
		cell = CreateCell(styles,row,"styleTitle",colOffset,"Race");
		cell = CreateCell(styles,row,"styleSub",colOffset + 2,raceName);
		cell = CreateCell(styles,row,"styleTitle",colOffset + 1,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 3,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 4,"");
		rowOffset ++;

		row = createRow (sheet,rowOffset);
		cell = CreateCell(styles,row,"styleTitle",colOffset,"Distance");
		cell = CreateCell(styles,row,"styleSub",colOffset + 2,String.valueOf(distance));
		cell = CreateCell(styles,row,"styleTitle",colOffset + 1,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 3,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 4,"");
		rowOffset ++;

		row = createRow(sheet, rowOffset);
		cell = CreateCell(styles,row,"styleTitle",colOffset,"Time");
		cell = CreateCell(styles,row,"styleSub",colOffset + 2,utils.formatTime(plannedRaceTime));
		cell = CreateCell(styles,row,"styleTitle",colOffset + 1,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 3,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 4,"");
		rowOffset ++;

		row = createRow(sheet, rowOffset);		
		cell = CreateCell(styles,row,"styleTitle",colOffset,"Start Delay");
		cell = CreateCell(styles,row,"styleSub",colOffset + 2,utils.formatTime(startDelay) + " min");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 1,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 3,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 4,"");
		rowOffset ++;

		row = createRow(sheet, rowOffset);		
		cell = CreateCell(styles,row,"styleTitle",colOffset,"Fade");
		cell = CreateCell(styles,row,"styleSub",colOffset + 2,(int)fade + "%");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 1,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 3,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 4,"");
		rowOffset ++;
		
		row = createRow(sheet, rowOffset);		
		cell = CreateCell(styles,row,"styleTitle",colOffset,"Average pace");
		cell = CreateCell(styles,row,"styleSub",colOffset + 2,utils.formatTime(averageEndToEndPace));
		cell = CreateCell(styles,row,"styleTitle",colOffset + 1,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 3,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 4,"");
		rowOffset ++;

		row = createRow(sheet, rowOffset);		
		cell = CreateCell(styles,row,"styleTitle",colOffset,"Moving pace");
		cell = CreateCell(styles,row,"styleSub",colOffset + 2,utils.formatTime(averageMovingPace));
		cell = CreateCell(styles,row,"styleTitle",colOffset + 1,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 3,"");
		cell = CreateCell(styles,row,"styleTitle",colOffset + 4,"");
		rowOffset ++;

		// setup merged cells
		for (int counter = 2; counter < 9; counter ++) {
			String first = utils.getCharForNumber(colOffset);
			String second = utils.getCharForNumber(colOffset + 1);
			String range = "$" + first + "$" + counter + ":$" + second + "$" + counter;
			sheet.addMergedRegion(CellRangeAddress.valueOf(range));
		}
		for (int counter = 2; counter < 9; counter ++) {
			String first = utils.getCharForNumber(colOffset + 2);
			String second = utils.getCharForNumber(colOffset + 4);
			String range = "$" + first + "$" + counter + ":$" + second + "$" + counter;
			sheet.addMergedRegion(CellRangeAddress.valueOf(range));
		}
		

		rowOffset ++;
		rowOffset ++;

		row = createRow(sheet, rowOffset);
		row.setHeightInPoints(35);
		cell = CreateCell(styles,row,"styleCentreAligned",colOffset,"Km");
		cell = CreateCell(styles,row,"styleRightAligned",colOffset + 1,"Elev (m)");
		cell = CreateCell(styles,row,"styleRightAligned",colOffset + 2,"Pace");
		cell = CreateCell(styles,row,"styleRightAligned",colOffset + 3,"Split time");
		cell = CreateCell(styles,row,"styleRightAligned",colOffset + 4,"Total time");
		
        sheet.setColumnWidth(colOffset,5*256);
        sheet.setColumnWidth(colOffset + 1,7*256);
        sheet.setColumnWidth(colOffset + 2,6*256);
        sheet.setColumnWidth(colOffset + 3,6*256);
        sheet.setColumnWidth(colOffset + 4,7*256);
		
		rowOffset ++;

		for (Split raceSplit : raceSplits)
		{	
			row = createRow(sheet, rowOffset);
			cell = CreateCell(styles,row,"styleLeftAligned",colOffset,String.valueOf(raceSplit.splitNumber));
			cell = CreateCell(styles,row,"styleClean",colOffset + 1,String.valueOf(raceSplit.elevation));
			cell = CreateCell(styles,row,"styleClean",colOffset + 2,utils.formatTime(raceSplit.finalTime));
			cell = CreateCell(styles,row,"styleClean",colOffset + 3,utils.formatTime(raceSplit.finalPace));
			cell = CreateCell(styles,row,"styleClean",colOffset + 4,utils.formatTime(raceSplit.finalElapsedTime));
			rowOffset ++;
		}		
	}
	
	private Cell CreateCell(Map<String, CellStyle> styles, Row row, String cellStyle,int col, String value) {
		Cell cell = row.createCell(col);
		cell.setCellStyle(styles.get(cellStyle));
		cell.setCellValue(value);
		return cell;
	}
	
	// utility to get a rol or create it if it does not already exist
	private Row createRow(Sheet sheet,int rowOffset)
	{
		Row row = sheet.getRow(rowOffset);
		if (row == null) row = sheet.createRow(rowOffset);
		return row;		
	}
	
	
	private static Map<String, CellStyle> createStyles(Workbook wb){
		String defaultFont = "Calibri";
        Map<String, CellStyle> styles = new HashMap<>();
        
        // setup the font
        Font font = wb.createFont();
        font.setFontHeightInPoints((short)12);
        font.setFontName(defaultFont);
        
        CellStyle style;
        
        style = wb.createCellStyle();
        style.setFont(font);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());        
        style.setFillForegroundColor(IndexedColors.TAN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        styles.put("styleTitle", style);
        
        style = wb.createCellStyle();
        style.setFont(font);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());        
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        styles.put("styleLeftAligned", style);

        style = wb.createCellStyle();
        style.setFont(font);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());        
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        styles.put("styleRightAligned", style);
        
        style = wb.createCellStyle();
        style.setFont(font);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());        
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        styles.put("styleCentreAligned", style);
        
        style = wb.createCellStyle();
        style.setFont(font);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());        
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        styles.put("styleSub", style);
        
        style = wb.createCellStyle();
        style.setFont(font);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());        
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        styles.put("styleClean", style);

        return styles;
    }

	
}
