package com.abduhanifan.dicoding.githubsearchuser.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.abduhanifan.dicoding.githubsearchuser.db.DatabaseContract.UserColumns.Companion.ID
import com.abduhanifan.dicoding.githubsearchuser.db.DatabaseContract.UserColumns.Companion.LOGIN
import com.abduhanifan.dicoding.githubsearchuser.db.DatabaseContract.UserColumns.Companion.TABLE_NAME
import java.sql.SQLException

class UserHelper (context: Context) {

    // Sebuah constructor
    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: UserHelper? = null

        // Metode menginisiasi database
        fun getInstance(context: Context): UserHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserHelper(context)
            }
    }

    // Metide membuka dan menutup koneksi ke database
    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    fun close() {
        dataBaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    // Metode melakukan proses CRUD, metode pertama mengambil data
    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$ID ASC",
            null)
    }

    // Metode untuk mengambil data dgn login
    fun queryByLogin(login: String): Cursor {
      return database.query(
        DATABASE_TABLE,
        null,
        "$LOGIN = ?",
        arrayOf(login),
        null,
        null,
        null,
        null)
    }

    // Metode untuk mengambil data dgn id tertentu
//    fun queryById(id: String): Cursor {
//        return database.query(
//            DATABASE_TABLE,
//            null,
//            "$ID = ?",
//            arrayOf(id),
//            null,
//            null,
//            null,
//            null)
//    }

    // Metode untuk menyimpan data
    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

//    // Metode untuk memperbaharui data
//    fun update(id: String, values: ContentValues?): Int {
//        return database.update(DATABASE_TABLE, values, "$ID = ?", arrayOf(id))
//    }

    // Metode untuk menghapus data
    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$ID = '$id'", null)
    }
}