package com.abduhanifan.dicoding.githubsearchuser

import android.database.Cursor
import com.abduhanifan.dicoding.githubsearchuser.db.DatabaseContract.FavoriteColumns.Companion.AVATAR_URL
import com.abduhanifan.dicoding.githubsearchuser.db.DatabaseContract.FavoriteColumns.Companion.ID
import com.abduhanifan.dicoding.githubsearchuser.db.DatabaseContract.FavoriteColumns.Companion.LOGIN
import com.abduhanifan.dicoding.githubsearchuser.db.DatabaseContract.FavoriteColumns.Companion.TYPE
import com.abduhanifan.dicoding.githubsearchuser.model.FavoriteItem

object MappingHelper {

    fun mapCursorToArrayList(favoriteCursor: Cursor?): ArrayList<FavoriteItem> {
        val favoriteItemList = ArrayList<FavoriteItem>()

        favoriteCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(ID))
                val login = getString(getColumnIndexOrThrow(LOGIN))
                val avatarUrl = getString(getColumnIndexOrThrow(AVATAR_URL))
                val type = getString(getColumnIndexOrThrow(TYPE))
                favoriteItemList.add(FavoriteItem(id, login, avatarUrl, type))
            }
        }
        return favoriteItemList
    }
}