package app.ito.akki.webapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.api.load
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //以下でRetrofitとGSONを組み合わせて、通信する準備をしている
        val gson: Gson
                = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val userService: UserService = retrofit.create(UserService::class.java)


        requestButton.setOnClickListener {
            //runBlockingで並列処理を行う
            //{}の中では別スレッドになるため通信処理もできる
            runBlocking {
                //UserServiceで定義したメソッドを使ってUserIdを取得する
                runCatching {
                    userService.getUser("lifeistech")
                }
                //データの取得に成功した時に実行される
            }.onSuccess {
                avatarImageView.load(it.avatarurl)
                nameTextView.text = it.name
                userIdTextView.text = it.userId
                followingTextView.text = it.following.toString()
                followersTextView.text = it.followers.toString()
            }.onFailure {
                Toast.makeText(this, "失敗", Toast.LENGTH_LONG).show()
            }
        }
    }
}