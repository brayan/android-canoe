package br.com.sailboat.canoe.helper;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.sailboat.canoe.base.BaseSQLite;

public class CreateTablesHelper {

    private SQLiteDatabase database;
    private List<BaseSQLite> tables;


    public static void createTables(SQLiteDatabase database, List<BaseSQLite> tables) throws Exception {
        new CreateTablesHelper(database, tables).createTables();
    }


    private CreateTablesHelper(SQLiteDatabase database, List<BaseSQLite> tables) {
        this.database = database;
        this.tables = tables;
    }

    private void createTables() {
        for (BaseSQLite BaseSQLiteTable : tables) {
            database.execSQL(BaseSQLiteTable.getCreateTableStatement());
        }
    }

}
