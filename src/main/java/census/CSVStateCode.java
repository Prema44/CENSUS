package census;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCode {
	@CsvBindByName(column = "State Name",required = true)
	public String state;
	
	@CsvBindByName(column = "StateCode",required = true)
	public String stateCode;

	@Override
	public String toString() {
		return "StateCodeCSV [state=" + state + ", stateCode=" + stateCode + "]";
	}
}