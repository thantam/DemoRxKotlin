package com.example.kotlindemo.model

import android.os.Parcel
import android.os.Parcelable
import com.example.kotlindemo.adapters.AdapterConstants
import com.example.kotlindemo.adapters.ViewType
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_id") val userId: Long,
    @SerializedName("display_name") val displayName: String?,
    @SerializedName("reputation") val reputation: Long,
    @SerializedName("profile_image") val profileImage: String?
) : Parcelable, ViewType {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readLong(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(userId)
        dest.writeString(displayName)
        dest.writeLong(reputation)
        dest.writeString(profileImage)
    }

    override fun getViewType(): Int = AdapterConstants.USER_DETAILS
}