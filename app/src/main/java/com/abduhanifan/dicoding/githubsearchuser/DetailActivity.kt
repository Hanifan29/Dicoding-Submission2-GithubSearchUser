package com.abduhanifan.dicoding.githubsearchuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abduhanifan.dicoding.githubsearchuser.adapter.DetailAdapter
import com.abduhanifan.dicoding.githubsearchuser.adapter.TabsPagerAdapter
import com.abduhanifan.dicoding.githubsearchuser.model.UserItem
import com.abduhanifan.dicoding.githubsearchuser.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_tabs.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var adapter: DetailAdapter
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setTitle(R.string.detail_label)
        supportActionBar?.elevation = 0F

        val username = intent?.getParcelableExtra(EXTRA_USER) as UserItem

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(DetailViewModel::class.java)

        detailViewModel.setDetailUser(username.login.toString())
        detailViewModel.getDetailUser().observe(this, Observer { detailUserItem ->
            if (detailUserItem != null) {
                adapter.setData(detailUserItem)
            }
        })

        showTabs()
        showDetailUser()
    }

    private fun showDetailUser() {
        adapter = DetailAdapter()
        adapter.notifyDataSetChanged()

        rv_DetailUser.layoutManager = LinearLayoutManager(this)
        rv_DetailUser.adapter = adapter
    }

    private fun showTabs() {
        val sectionsPagerAdapter = TabsPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }
}
