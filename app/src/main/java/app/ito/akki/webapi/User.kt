package app.ito.akki.webapi

import com.google.gson.annotations.SerializedName

//データクラス
//複数の異なるデータ型の変数を一つにまとめたもの

data class User (
    //ダブルクオーテーションの中に書くパラメータは使いたいJSONのパラメータに一致させる
    //APIから受けたったJSONをデータクラスに代入する処理を書く
    val name: String,
    @SerializedName("login") val userId: String,
    @SerializedName("avatar_url") val avatarurl: String,
    val following: Int,
    val followers: Int
)
