package com.github.kazuhito_m.sample.dbunit;

import com.github.kazuhito_m.sample.db.DbAccesser;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.dataset.excel.XlsDataSetWriter;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Ignore(value = "書いてみたものの…毎回Excel吐くのもどうかと思いスキップ")
public class DbUnitExportTest {

	private static DbAccesser db;

	@Before
	public void setup() {
		db = new DbAccesser();
	}

	@After
	public void tearDown() throws Exception {
		db.close();
		db = null;
	}



	@Test
	public void 本題とは関係ないがDBUnitのエクスポート従来の機能を試すサンプル() throws Exception {
		// act
		oldMain(null);
		// assertion
		assertThat(new File("export.xls").exists(), is(true));
	}

	@Test
	public void 従来機能のぱたーん２() throws Exception {
		// act
		oldMain2(null);
		// assertion
		assertThat(new File("export2.xls").exists(), is(true));
	}


	@Test
	public void 新規機能でxlsxファイルを吐く() throws Exception {
		// act
		main(null);
		// assertion
		assertThat(new File("export2.xls").exists(), is(true));
	}

	public static void oldMain(String[] args) throws Exception {
		IDatabaseConnection con = null;
		try  {
			con = getConnection();
			IDataSet dataset = con.createDataSet();
			XlsDataSet.write(dataset, new FileOutputStream("export.xls"));
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


	public static void oldMain2(String[] args) throws Exception {
		IDatabaseConnection con = null;
		try  {
			con = getConnection();
			IDataSet dataset = con.createDataSet();
			XlsDataSetWriter writer = new XlsDataSetWriter();
			writer.write(dataset, new FileOutputStream("export2.xls"));
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


	public static void main(String[] args) throws Exception {
		IDatabaseConnection con = null;
		try  {
			con = getConnection();
			IDataSet dataset = con.createDataSet();
			XlsDataSetWriter writer = new XlsDataSetWriter() {
				@Override
				public Workbook createWorkbook() {
					return new XSSFWorkbook();
				}
			};
			writer.write(dataset, new FileOutputStream("export.xlsx"));
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


	public static DatabaseConnection getConnection() throws SQLException, ClassNotFoundException, DatabaseUnitException {
		return new DatabaseConnection( db.getConnection());
	}

}
