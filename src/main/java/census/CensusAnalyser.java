package census;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import com.google.gson.Gson;

public class CensusAnalyser<E> {
	List<CSVStateCensus> censusCSVList = null;
	List<CSVStateCode> stateCodeCSVList = null;
	public int loadCSVData(String csvFile) throws CensusAnalyserException, IOException, CSVBuilderException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			@SuppressWarnings("unchecked")
			ICSVBuilder<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			censusCSVList =  csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
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
			stateCodeCSVList =  csvBuilder.getCSVFileList(reader, CSVStateCode.class);
			return stateCodeCSVList.size();
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}
	
	public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
		if(stateCodeCSVList == null || stateCodeCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCode> censusComparator = Comparator.comparing(census -> census.stateCode);
		this.sort(stateCodeCSVList, censusComparator);
		String sorted = new Gson().toJson(stateCodeCSVList);
		return sorted;
	}

	public String getStateWiseSortedCensusData() throws CensusAnalyserException {
		if(censusCSVList == null || censusCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
		this.sort(censusCSVList, censusComparator);
		String sortedStateCensusJson = new Gson().toJson(censusCSVList);
		return sortedStateCensusJson;
	}

	private <E> void sort(List<E> censusList, Comparator<E> censusComparator) {
		for (int i = 0; i < censusList.size(); i++) {
			for (int j = 0; j < censusList.size() - i - 1; j++) {
				E census1 =  censusList.get(j);
				E census2 =  censusList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					censusList.set(j, census2);
					censusList.set(j + 1, census1);
				}
			}
		}
	}

	

}
