package team.nexters.data.moim.model

import com.google.gson.annotations.SerializedName

class MoimCreateResponse (
    @SerializedName("result")
    val result: String,
    @SerializedName("saveId")
    val saveId: Int?
)