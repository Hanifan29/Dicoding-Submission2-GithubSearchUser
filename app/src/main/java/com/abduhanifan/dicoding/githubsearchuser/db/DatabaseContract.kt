package com.abduhanifan.dicoding.githubsearchuser.db

import android.provider.BaseColumns

internal class UserContract  {

    internal class UserColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "favorite_user"
            const val ID = "id"
            const val LOGIN = "login"
            const val AVATAR_URL = "avatar_url"
            const val TYPE = "type"
        }
    }
}