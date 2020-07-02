package com.abduhanifan.dicoding.githubsearchuser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abduhanifan.dicoding.githubsearchuser.R
import com.abduhanifan.dicoding.githubsearchuser.model.DetailUserItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_detail_user.view.*

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private val mData = ArrayList<DetailUserItem>()

    fun setData(items: ArrayList<DetailUserItem>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): DetailViewHolder {
        val mView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_detail_user, viewGroup, false)
        return DetailViewHolder(mView)
    }

    override fun onBindViewHolder(detailViewHolder: DetailViewHolder, position: Int) {
        detailViewHolder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(detailUserItems: DetailUserItem) {
            with(itemView) {
                textName.text = detailUserItems.name
                textLocation.text = detailUserItems.location
                textCompany.text = detailUserItems.company
                textRepository.text = detailUserItems.public_repos
                textFollower.text = detailUserItems.followers
                textFollowing.text = detailUserItems.following
                Glide.with(context)
                    .load(detailUserItems.avatar_url)
                    .apply(RequestOptions().override(86, 86))
                    .into(imgAvatar)
            }
        }
    }

}