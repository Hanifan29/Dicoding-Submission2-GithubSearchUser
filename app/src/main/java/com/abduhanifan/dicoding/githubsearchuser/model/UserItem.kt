package com.abduhanifan.dicoding.githubsearchuser.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserItem(
    var id: Int = 0,
    var login: String? = null,
    var avatar_url: String? = null,
    var type: String? = null
) : Parcelable