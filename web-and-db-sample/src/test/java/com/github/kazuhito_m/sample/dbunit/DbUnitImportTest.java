package com.github.kazuhito_m.sample.dbunit;

import com.github.kazuhito_m.sample.db.DbAccesser;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.SQLException;

@Ignore("作ったものの、毎回インポートされるのも害悪なので一旦無効に")
public class DbUnitImportTest {

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
    public void DBUnitのエクスポート従来機能を試すサンプル() throws Exception {
        // act
        oldMain(null);
    }

    @Test
    public void DBUnitのエクスポート新機能を試すサンプル() throws Exception {
        // act
        main(null);
    }


    public static void main(String[] args) throws Exception {
        IDatabaseConnection con = null;
        try {
            con = getConnection();
            IDataSet dataset = new XlsDataSet(new File(testPath("import.xlsx")));
            DatabaseOperation.CLEAN_INSERT.execute(con, dataset);            // deleteしてからinsert
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }


    public static void oldMain(String[] args) throws Exception {
        IDatabaseConnection con = null;
        try {
            con = getConnection();
            IDataSet dataset = new XlsDataSet(new File(testPath("import.xls")));
            DatabaseOperation.CLEAN_INSERT.execute(con, dataset);            // deleteしてからinsert
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }


    public static DatabaseConnection getConnection() throws SQLException, ClassNotFoundException, DatabaseUnitException {
        return new DatabaseConnection(db.getConnection());
    }

    private static String testPath(String filename) throws URISyntaxException {
        return DbUnitImportTest.class.getResource(filename).toURI().getPath();
    }

}
