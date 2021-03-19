package com.drwang.common.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

// 10 总公司管理员
// 11 总公司审核员
// 20 经营公司管理员
// 21 经营公司监测院
// 22 经营公司销售主管
// 23 经营公司销售
// 30 市场管理员
// 31 市场员工
// 40 车商
@Parcelize
data class User(
        @SerializedName("accountId") var accountId: String? = "",
        @SerializedName("accountName") var accountName: String? = "",
        @SerializedName("accountPassword") var accountPassword: String? = "",
        @SerializedName("userPasswordChange") var userPasswordChange: Int? = null,
        @SerializedName("applicationId") var applicationId: String? = "",
        @SerializedName("marketLogo") var marketLogo: String? = "",
        @SerializedName("userType") var userType: Int? = null,
        var userId: String? = null,
        var userMobile: String? = "",
        var marketId: String? = null,
        var marketName: String? = "",
        var shopId: String? = null,
        var shopName: String? = "",
        var userName: String? = null,
        @SerializedName("companyId") var companyId: String? = "",
//        @SerializedName("resourceList") var resourceList: List<Resource>? = listOf(),
        @SerializedName("userCreateTime") var userCreateTime: Long? = null,
        @SerializedName("userUpdateTime") var userUpdateTime: Long? = null,
        @SerializedName("userNo") var userNo: String? = null,
        @SerializedName("userRemark") var userRemark: String? = null,
        @SerializedName("userState") var userState: Int? = null,
        @SerializedName("wxOpenId") var wxOpenId: String? = null,
        @SerializedName("departmentType") var departmentType: String? = null,
        @SerializedName("departmentId") var departmentId: String? = null,
        var isSelect: Boolean = false
) :Parcelable {

}