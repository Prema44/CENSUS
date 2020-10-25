package census;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;


public class CensusAnalyser {
	public int loadCSVData(String csvFile) throws CensusAnalyserException, IOException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFile));
			@SuppressWarnings("unchecked")
			ICSVBuilder<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<CSVStateCensus> censusIterator = csvBuilder.getCSVFileIterator(reader,CSVStateCensus.class);
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
			ICSVBuilder<CSVStateCode> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<CSVStateCode> censusIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCode.class);
			return this.getCount(censusIterator);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		}
	}

	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		int countOfRecords = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return countOfRecords;
	}
}