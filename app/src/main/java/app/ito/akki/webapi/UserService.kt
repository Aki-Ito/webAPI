package app.ito.akki.webapi

import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    //GETとはHTTPリクエストの一つで主にサーバーから情報を取得する時に使われる
    //括弧の中はユーザー情報を取得するためのリンク
    //userIdが{}で囲まれているのは変更可能にするため
    @GET("users/{userId}")
    //ユーザー情報を取得するメソッド
    //GitHubのユーザー名を引数にとって、取得したユーザー情報であるUserクラスを返すメソッド
            suspend fun getUser(@Path("userId") userId: String) : User

}