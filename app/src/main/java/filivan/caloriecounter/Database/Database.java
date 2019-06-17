package filivan.caloriecounter.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import filivan.caloriecounter.Model.Calorie;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "calorie.db";
    private static final int DB_VERSION = 1;



    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public List<Calorie> getCalories() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"_id", "Name", "Proteins", "Fats", "Carbohydrates", "Kcal"};
        String tableName = "Foods";

        queryBuilder.setTables(tableName);
        Cursor cursor = queryBuilder.query(db, sqlSelect, null, null,
                null, null, null);
        List<Calorie> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Calorie calorie = new Calorie();
                calorie.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                calorie.setName(cursor.getString(cursor.getColumnIndex("Name")));
                calorie.setProteins(cursor.getString(cursor.getColumnIndex("Proteins")));
                calorie.setFats(cursor.getString(cursor.getColumnIndex("Fats")));
                calorie.setCarbohydrates(cursor.getString(cursor.getColumnIndex("Carbohydrates")));
                calorie.setKcal(cursor.getString(cursor.getColumnIndex("Kcal")));

                result.add(calorie);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public List<String> getNames() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Name"};
        String tableName = "Foods";

        queryBuilder.setTables(tableName);
        Cursor cursor = queryBuilder.query(db, sqlSelect, null, null,
                null, null, null);

        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(cursor.getColumnIndex("Name")));
            } while (cursor.moveToNext());
        }
        return result;
    }

    public List<Calorie> getCalorieByName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"_id", "Name", "Proteins", "Fats", "Carbohydrates", "Kcal"};
        String tableName = "Foods";

        queryBuilder.setTables(tableName);
        Cursor cursor = queryBuilder.query(db, sqlSelect, "Name LIKE ?",
                new String[]{"%" + name + "%"}, null, null, null);
        List<Calorie> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Calorie calorie = new Calorie();
                calorie.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                calorie.setName(cursor.getString(cursor.getColumnIndex("Name")));
                calorie.setProteins(cursor.getString(cursor.getColumnIndex("Proteins")));
                calorie.setFats(cursor.getString(cursor.getColumnIndex("Fats")));
                calorie.setCarbohydrates(cursor.getString(cursor.getColumnIndex("Carbohydrates")));
                calorie.setKcal(cursor.getString(cursor.getColumnIndex("Kcal")));

                result.add(calorie);
            } while (cursor.moveToNext());
        }
        return result;
    }
}
