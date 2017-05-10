package br.com.sailboat.canoe.base;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.text.ParseException;
import java.util.Calendar;

import br.com.sailboat.canoe.helper.DateHelper;
import br.com.sailboat.canoe.helper.StringHelper;

public abstract class BaseSQLite {

    private SQLiteOpenHelper database;

    public BaseSQLite(SQLiteOpenHelper database) {
        this.database = database;
    }

    public abstract String getCreateTableStatement();

    protected long insert(SQLiteStatement statement) throws SQLiteException {
        try {
            getWritableDatabase().beginTransactionNonExclusive();
            long id = statement.executeInsert();
            statement.clearBindings();
            getWritableDatabase().setTransactionSuccessful();

            return id;
        } finally {
            getWritableDatabase().endTransaction();
        }
    }

    protected void update(SQLiteStatement statement) throws SQLiteException {
        executeUpdateOrDelete(statement);
    }

    protected void delete(SQLiteStatement statement) throws SQLiteException {
        executeUpdateOrDelete(statement);
    }

    private void executeUpdateOrDelete(SQLiteStatement statement) throws SQLiteException {
        try {
            getWritableDatabase().beginTransactionNonExclusive();
            statement.executeUpdateDelete();
            statement.clearBindings();
            getWritableDatabase().setTransactionSuccessful();
        } finally {
            getWritableDatabase().endTransaction();
        }
    }

    protected int getCountFromQuery(String query) throws SQLiteException {
        Cursor cursor = performQuery(query);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    protected Cursor performQuery(String query) {
        return getReadableDatabase().rawQuery(query, null);
    }

    protected SQLiteStatement compileStatement(String statement) {
        return getWritableDatabase().compileStatement(statement);
    }

    protected int getInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName));
    }

    protected long getLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(columnName));
    }

    protected double getDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndexOrThrow(columnName));
    }

    protected String getString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName));
    }

    protected boolean getBoolean(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName)) == 1;
    }

    protected String parseCalendarToString(Calendar date) {
        return DateHelper.parseCalendarWithDatabaseFormatToString(date);
    }

    protected Calendar parseStringToCalendar(String date) throws ParseException {
        return DateHelper.parseStringWithDatabaseFormatToCalendar(date);
    }

    protected String getValueOrEmptyString(String value) {
        return StringHelper.getValueOrEmptyString(value);
    }

    protected String getCalendarToBind(Calendar calendar) {
        try {
            return parseCalendarToString(calendar);

        } catch (Exception e) {
            return "";
        }
    }

    protected int parseBooleanToInt(boolean value) {
        return value ? 1 : 0;
    }

    private SQLiteDatabase getReadableDatabase() {
        return database.getReadableDatabase();
    }

    private SQLiteDatabase getWritableDatabase() {
        return database.getWritableDatabase();
    }


}
