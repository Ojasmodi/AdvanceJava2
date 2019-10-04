package utilities;

public class SizeConversion {

	// method to convert size from bytes to MBs
	public double convertFromBytesToMegaBytes(double value){
		return Math.round(value / 1000000 * 100.0) / 100.0;
	}
}
