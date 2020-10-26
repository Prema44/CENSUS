package census;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
	@CsvBindByName(column = "State")
	public String state;

	@CsvBindByName(column = "Population", required = true)
	public long population;

	@CsvBindByName(column = "AreaInSqKm")
	public long areaInSqKm;

	@CsvBindByName(column = "DensityPerSqKm", required = true)
	public long densityPerSqKm;
	
	@Override
	public String toString() {
		return "CSVStateCensus [state=" + state + ", population=" + population + ", areaInSqKm=" + areaInSqKm
				+ ", densityPerSqKm=" + densityPerSqKm + "]";
	}
}