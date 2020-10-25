package census;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CensusAnalyser {
	public int loadCSVData(String csvFile) throws CensusAnalyserException, IOException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			Iterator<CSVStateCensus> censusIterator = new OpenCSVBuilder().getCSVFileIterator(reader, CSVStateCensus.class);
			return this.getCount(censusIterator);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}

	public int loadIndianStateCode(String csvFile) throws IOException, CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			Iterator<CSVStateCode> censusIterator = new OpenCSVBuilder().getCSVFileIterator(reader, CSVStateCode.class);
			return this.getCount(censusIterator);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}
	
	private <E> int getCount(Iterator<E> iterator) {
		int countOfRecord = 0;
		while (iterator.hasNext()) {
			countOfRecord++;
			E censusData = iterator.next();
		}
		return countOfRecord;
	}
}
	