package br.com.sailboat.canoe.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.sailboat.canoe.base.BaseSQLite;

public class CreateTablesHelper {

    private Context context;
    private SQLiteDatabase database;
    private List<BaseSQLite> tables;


    public static void createTables(Context context, SQLiteDatabase database, List<BaseSQLite> tables) throws Exception {
        new CreateTablesHelper(context, database, tables).createTables();
    }


    private CreateTablesHelper(Context context, SQLiteDatabase database, List<BaseSQLite> tables) {
        this.context = context.getApplicationContext();
        this.database = database;
        this.tables = tables;
    }

    private void createTables() {
        for (BaseSQLite BaseSQLiteTable : tables) {
            database.execSQL(BaseSQLiteTable.getCreateTableStatement());
        }
    }

}
