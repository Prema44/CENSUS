package census;


import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;

import com.google.gson.Gson;

public class CensusAnalyserTest {
	private static final String STATECENSUS_CSVFILE = "H:\\bridgelab\\CensusAnalyser\\IndiaStateCensusData.csv";
	private static final String WRONG_FILE = "H:\\bridgelab\\CensusAnalyser\\IndiaStateCode.csv";
	private static final String WRONG_EXTENSION = "H:\\bridgelab\\CensusAnalyser\\IndiaStateCensusData.txt";
	private static final String USCSVFILE = "H:\\bridgelab\\CensusAnalyser\\USCensusData.csv";
	private static final String STATE_CODE_CSV = "H:\\bridgelab\\CensusAnalyser\\IndiaStateCode.csv";

	/**
	 * UC1TestCase1
	 * 
	 * @throws IOException
	 */
	@Test
	public void givenCSVFile_IfMatchNumberOfRecords_ShouldReturnTrue() throws IOException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(STATECENSUS_CSVFILE);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
			System.out.println(count);
	}		
	

	/**
	 * UC1TestCase2
	 * 
	 * @throws IOException
	 */
	@Test
	public void givenCSVFile_IfWrongFile_ShouldThrowError() throws IOException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(WRONG_FILE);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
		
	}

	/**
	 * UC1TestCase3
	 * 
	 * @throws IOException
	 */
	@Test
	public void givenCSVFile_WhenFileCorrect_ButExtensionIncorrect_ShouldThrowError() throws IOException, CSVBuilderException  {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(WRONG_EXTENSION);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.NO_FILE, e.type);
		}
	}

	/**
	 * UC1TestCase4
	 * 
	 * @throws IOException
	 */
	@Test
	public void givenCSVFile_WhenFileCorrect_ButDelimiterIncorrect_ShouldThrowError() throws IOException,CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(STATECENSUS_CSVFILE);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	/**
	 * UC1TestCase5
	 * 
	 * @throws IOException
	 */
	@Test
	public void givenCSVFile_WhenFileCorrect_ButHeaderIncorrect_ShouldThrowError() throws IOException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		int count = 0;
		try {
			count = analyser.loadCSVData(USCSVFILE);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	/**UC2TestCase1
	 * @throws IOException
	 */
	@Test
	public void givenStateFile_IfMatchNumberOfRecords_ShouldReturnTrue() throws IOException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		try {
			int count = analyser.loadIndianStateCode(STATE_CODE_CSV);
			System.out.println(count);
			assertEquals(37, count);
		} catch (CensusAnalyserException e) {}
	}
	/**
	 * UC2TestCase2
	 * 
	 * @throws IOException
	 */
	@Test
	public void givenStateFile_IfWrongFile_ShouldThrowError() throws IOException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		try {
			int count = analyser.loadIndianStateCode(WRONG_FILE);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	/**
	 * UC2TestCase3
	 * 
	 * @throws IOException
	 */
	@Test
	public void givenStateFile_WhenFileCorrect_ButExtensionIncorrect_ShouldThrowError() throws IOException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		try {
			int count = analyser.loadIndianStateCode(WRONG_EXTENSION);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.NO_FILE, e.type);
		}
	}

	/**
	 * UC2TestCase4
	 * 
	 * @throws IOException
	 */
	@Test
	public void givenStateFile_WhenFileCorrect_ButDelimiterIncorrect_ShouldThrowError() throws IOException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		try {
			int count = analyser.loadIndianStateCode(USCSVFILE);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}

	/**
	 * UC2TestCase5
	 * 
	 * @throws IOException
	 */
	@Test
	public void givenStateFile_WhenFileCorrect_ButHeaderIncorrect_ShouldThrowError() throws IOException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		try {
			int count = analyser.loadIndianStateCode(USCSVFILE);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
			assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
		}
	}
	
	/**
	 * TestCase for Usecase3 for sorting CSV file data
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getStateWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Andhra Pradesh", censusCSV[0].state);
	}

	/**
	 * TestCase for Usecase3 for sorting CSV file data
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResultForLastState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getStateWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);
	}
	
	/**
	 * TestCase for UC4 Sorting StateCode CSV File data
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStateCode_ShouldReturnSortedResult()
			    throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadIndianStateCode(STATE_CODE_CSV);
		String sortedCensusData = analyser.getStateCodeWiseSortedCensusData();
		CSVStateCode[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCode[].class);
		assertEquals("Andhra Pradesh New", censusCSV[0].state);
	}

	/**
	 * TestCase for UC4 Sorting StateCode CSV File data
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStateCode_ShouldReturnSortedResultForLastState()
			    throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadIndianStateCode(STATE_CODE_CSV);
		String sortedCensusData = analyser.getStateCodeWiseSortedCensusData();
		CSVStateCode[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCode[].class);
		assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);
	}
	
	/**
	 * TestCase for UC5 Sorting StateCensus CSV File data on population
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStatePopulation_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getPopulationWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Uttar Pradesh", censusCSV[0].state);
	}
	
	/**
	 * TestCase for UC5 Sorting StateCensus CSV File data on population
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStatePopulation_ShouldReturnSortedResultForLast()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getPopulationWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Sikkim", censusCSV[censusCSV.length - 1].state);
	}
	
	/**
	 * TestCase for Usecase6 for sorting CSV file on population density
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getPopulationDensityWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Bihar", censusCSV[0].state);
	}

	/**
	 * TestCase for Usecase6 for sorting CSV file on population density
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResultForLast()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getPopulationDensityWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Arunachal Pradesh", censusCSV[censusCSV.length - 1].state);
	}
	
	/**
	 * TestCase for Usecase7 for sorting CSV file on State Area
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnArea_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getAreaWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Rajasthan", censusCSV[0].state);
	}
	
	/**
	 * TestCase for Usecase7 for sorting CSV file on State Area
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnArea_ShouldReturnSortedResultforsmallState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		CensusAnalyser analyser = new CensusAnalyser();
		analyser.loadCSVData(STATECENSUS_CSVFILE);
		String sortedCensusData = analyser.getAreaWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Goa", censusCSV[censusCSV.length - 1].state);
	}
}