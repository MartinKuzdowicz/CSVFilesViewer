package org.csv.viewer.parser;

import java.util.List;

public class CsvTableDTO {

	private List<List<String>> listOfRows;

	public CsvTableDTO(List<List<String>> listOfRows) {
		super();
		this.listOfRows = listOfRows;
	}

	public List<List<String>> getListOfRows() {
		return listOfRows;
	}

	public void setListOfRows(List<List<String>> listOfRows) {
		this.listOfRows = listOfRows;
	}

}
