package org.openlca.io.xls.process.output;

import org.apache.poi.ss.usermodel.Sheet;
import org.openlca.core.database.LocationDao;
import org.openlca.core.model.Location;
import org.openlca.io.xls.Excel;

class LocationSheet {

	private Config config;
	private Sheet sheet;
	private int row = 0;

	private LocationSheet(Config config) {
		this.config = config;
		sheet = config.workbook.createSheet("Locations");
	}

	public static void write(Config config) {
		new LocationSheet(config).write();
	}

	private void write() {
		writeHeader();
		LocationDao dao = new LocationDao(config.database);
		for (Location location : dao.getAll()) {
			row++;
			write(location);
		}
		Excel.autoSize(sheet, 0, 5);
	}

	private void writeHeader() {
		config.header(sheet, row, 0, "UUID");
		config.header(sheet, row, 1, "Code");
		config.header(sheet, row, 2, "Name");
		config.header(sheet, row, 3, "Description");
		config.header(sheet, row, 4, "Latitude");
		config.header(sheet, row, 5, "Longitude");
	}

	private void write(Location location) {
		Excel.cell(sheet, row, 0, location.getRefId());
		Excel.cell(sheet, row, 1, location.getCode());
		Excel.cell(sheet, row, 2, location.getName());
		Excel.cell(sheet, row, 3, location.getDescription());
		Excel.cell(sheet, row, 4, location.getLatitude());
		Excel.cell(sheet, row, 5, location.getLongitude());
	}

}