package com.ludeman.mariokartrandomizer

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class RacerDBHandler(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "marioKartDB.db"
        val TABLE_NAME = "Racer"
        val COLUMN_ID = "RacerID"
        val COLUMN_NAME = "RacerName"
        val COLUMN_IMAGE = "RacerImage"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" +
                COLUMN_ID + "INTEGER PRIMARYKEY," + COLUMN_NAME + "TEXT," + COLUMN_IMAGE + "TEXT )"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun loadHandler(): String {
        var result = "";
        var query = "Select*FROM" + TABLE_NAME
        var db = this.writableDatabase
        var cursor = db.rawQuery(query, null)
        while(cursor.moveToNext()) {
            var result_0 = cursor.getInt(0)
            var result_1 = cursor.getString(1)
            var result_2 = cursor.getString(2)
            result += result_0.toString() + " " + result_1 + " " + result_2 + System.getProperty("line.separator")
        }

        cursor.close()
        db.close()

        return result
    }

    fun addHandler(racer: Racer) {
        var values : ContentValues = ContentValues()
        values.put(COLUMN_ID, racer.racerID)
        values.put(COLUMN_NAME, racer.racerName)
        values.put(COLUMN_IMAGE, racer.racerImage)
        var db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun findHandler(racerName: String): Racer {
        var query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_NAME + " = " + "'" + racerName + "'";
        var db = this.writableDatabase
        var cursor = db.rawQuery(query, null)
        var racer = Racer()
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            racer.racerID = cursor.getInt(0)
            racer.racerName = cursor.getString(1)
            racer.racerImage = cursor.getString(2)
            cursor.close()
        }
        db.close()
        return racer
    }

}