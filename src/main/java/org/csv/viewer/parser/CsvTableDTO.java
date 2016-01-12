package org.csv.viewer.parser;

import java.util.List;

public class CsvTableDTO {

	private List<List<String>> listOfRows;

	private List<String> header;

	public CsvTableDTO(List<List<String>> listOfRows, boolean withHeaderFlag) {
		super();
		if (withHeaderFlag) {

			setHeader(listOfRows.get(0));
			listOfRows.remove(0);
			this.listOfRows = listOfRows;

		} else {

			this.listOfRows = listOfRows;

		}

	}

	public List<List<String>> getListOfRows() {
		return listOfRows;
	}

	public void setListOfRows(List<List<String>> listOfRows) {
		this.listOfRows = listOfRows;
	}

	public List<String> getHeader() {
		return header;
	}

	public void setHeader(List<String> header) {
		this.header = header;
	}

}
