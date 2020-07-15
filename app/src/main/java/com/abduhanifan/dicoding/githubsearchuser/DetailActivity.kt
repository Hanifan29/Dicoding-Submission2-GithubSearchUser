package com.abduhanifan.dicoding.githubsearchuser

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abduhanifan.dicoding.githubsearchuser.adapter.TabsPagerAdapter
import com.abduhanifan.dicoding.githubsearchuser.model.UserItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_tabs.*
import org.json.JSONObject

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setTitle(R.string.detail_label)
        supportActionBar?.elevation = 0F

        val user = intent?.getParcelableExtra(EXTRA_USER) as UserItem
        getDetailUser(user.login.toString())

        Glide.with(this)
            .load(user.avatar_url)
            .apply(RequestOptions().override(86, 86))
            .into(imgAvatar)

        showTabs()
        add()
    }

    private fun showTabs() {
        val sectionsPagerAdapter = TabsPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }

    private fun getDetailUser(username: String) {
        val url = "https://api.github.com/users/$username"

        val client = AsyncHttpClient()
        client.addHeader("Authorization", "9eb6c532fc801bae706df9f0b12775c0c4ffeea4")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                try {

                    val result = String(responseBody)
                    val jsonObject = JSONObject(result)

                    val tvDetailName: TextView = findViewById(R.id.textName)
                    val tvDetailFollowers: TextView = findViewById(R.id.textFollower)
                    val tvDetailFollowing: TextView = findViewById(R.id.textFollowing)
                    val tvDetailCompany: TextView = findViewById(R.id.textCompany)
                    val tvDetailLocation: TextView = findViewById(R.id.textLocation)
                    val tvDetailRepository: TextView = findViewById(R.id.textRepository)

                    tvDetailName.text = jsonObject.getString("name").toString()
                    tvDetailLocation.text = jsonObject.getString("location").toString()
                    tvDetailCompany.text = jsonObject.getString("company").toString()
                    tvDetailRepository.text = jsonObject.getString("public_repos").toString()
                    tvDetailFollowers.text = jsonObject.getInt("followers").toString()
                    tvDetailFollowing.text = jsonObject.getInt("following").toString()

                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message.toString())
            }
        })
    }

    private fun add() {

    }
}
