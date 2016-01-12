package org.csv.viewer.parser;

import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CsvParserService {

	private final static Logger logger = Logger
			.getLogger(CsvParserService.class);

	private final static String newLineDelimiter = "\n";

	private String dataInRowSeparator;

	public CsvTableDTO parseISToDTO(InputStream is, boolean withHeaderFlag) {

		return new CsvTableDTO(
				buildListOfRowsWithListOfData(parseISToLinesList(is)),
				withHeaderFlag);
	}

	private void guessAndSetSeparator(String fileTextContent) {

		final char comaSeparator = ',';
		int comaSepCount = 0;
		final char semicolonSeparator = ';';
		int semiColonSepCount = 0;

		char[] charArr = fileTextContent.toCharArray();

		for (int i = 0; i < charArr.length; i++) {

			switch (charArr[i]) {
			case comaSeparator:
				comaSepCount++;
				break;
			case semicolonSeparator:
				semiColonSepCount++;
				break;
			default:
				break;
			}

		}

		if (comaSepCount > semiColonSepCount) {
			dataInRowSeparator = comaSeparator + "";
		} else {
			dataInRowSeparator = semicolonSeparator + "";
		}

	}

	private String parseContentFromInputStreamToString(InputStream is) {

		StringBuilder sb = new StringBuilder();

		try {

			int data;

			while ((data = is.read()) != -1) {

				sb.append((char) data);

			}

		} catch (IOException e) {
			logger.debug(e);
		}

		return sb.toString();

	}

	private String normalizeStringFromNonASCIChars(String text) {

		return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll(
				"[^\\p{ASCII}]", "");
	}

	private List<String> parseISToLinesList(InputStream is) {
		logger.debug("parseISToLinesList");

		List<String> fileLinesList = new ArrayList<>();

		String content = parseContentFromInputStreamToString(is);

		guessAndSetSeparator(content);

		String noramlizedText = normalizeStringFromNonASCIChars(content);
		String[] fileLines = noramlizedText.split(newLineDelimiter);

		for (int i = 0; i < fileLines.length; i++) {
			fileLinesList.add(fileLines[i]);
		}

		return fileLinesList;
	}

	private List<List<String>> buildListOfRowsWithListOfData(
			List<String> fileLinesList) {

		List<List<String>> listOfRowsWithListOfData = new ArrayList<>();

		fileLinesList.forEach(row -> {

			List<String> listOfDataInRow = new ArrayList<>();
			String[] dataArr = row.split(dataInRowSeparator);
			for (int i = 0; i < dataArr.length; i++) {
				listOfDataInRow.add(dataArr[i]);
			}

			listOfRowsWithListOfData.add(listOfDataInRow);

		});

		return listOfRowsWithListOfData;

	}

}
