package census;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CensusAnalyser {
	
	List<CSVStateCensus> censusCSVList = null;
	
	public int loadCSVData(String csvFile) throws CensusAnalyserException, IOException, CSVBuilderException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			@SuppressWarnings("unchecked")
			ICSVBuilder<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			censusCSVList = csvBuilder.getCSVFileList(reader,CSVStateCensus.class);
			return censusCSVList.size();
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}

	public int loadIndianStateCode(String csvFile) throws IOException, CensusAnalyserException, CSVBuilderException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			ICSVBuilder<CSVStateCode> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<CSVStateCode> censusCSVList = csvBuilder.getCSVFileList(reader,CSVStateCode.class);
			return censusCSVList.size();
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}
	
	public String getStateWiseSortedCensusData() throws CensusAnalyserException {
		if(censusCSVList == null || censusCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
		this.sort(censusComparator);
		String sortedStateCensusJson = new Gson().toJson(censusCSVList);
		return sortedStateCensusJson;
	}

	private void sort(Comparator<CSVStateCensus> censusComparator) {
		for (int i = 0; i < censusCSVList.size(); i++) {
			for (int j = 0; j < censusCSVList.size() - i - 1; j++) {
				CSVStateCensus census1 = censusCSVList.get(j);
				CSVStateCensus census2 = censusCSVList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					censusCSVList.set(j, census2);
					censusCSVList.set(j + 1, census1);
				}
			}
		}
	}
}