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

	public CsvTableDTO parseISToDTO(InputStream is) {

		return new CsvTableDTO(
				buildListOfRowsWithListOfData(parseISToLinesList(is)));
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
			String[] dataArr = row.split(";");
			for (int i = 0; i < dataArr.length; i++) {
				listOfDataInRow.add(dataArr[i]);
			}

			listOfRowsWithListOfData.add(listOfDataInRow);

		});

		return listOfRowsWithListOfData;

	}

}
