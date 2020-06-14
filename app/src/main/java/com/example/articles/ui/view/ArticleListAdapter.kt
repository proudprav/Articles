package com.example.articles.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.articles.R
import com.example.articles.model.ArticleResponse
import com.example.articles.utils.Utility
import com.squareup.picasso.Picasso

class ArticleListAdapter : RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {

    private var listOfArticle = ArrayList<ArticleResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_article, parent, false)
    )

    override fun getItemCount() = listOfArticle.size

    fun updateList(listofArticleResponse: ArrayList<ArticleResponse>) {
        (listOfArticle).addAll(listofArticleResponse)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = listOfArticle[position]
        holder.onBind(article)
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.tv_user_name)
        private val designation = itemView.findViewById<TextView>(R.id.tv_user_designation)
        private val desc = itemView.findViewById<TextView>(R.id.tv_image_desc)
        private val imageTitle = itemView.findViewById<TextView>(R.id.tv_image_title)
        private val imageUrl = itemView.findViewById<TextView>(R.id.tv_image_url)
        private val postedImage = itemView.findViewById<ImageView>(R.id.iv_posted_image)
        private val userImage = itemView.findViewById<ImageView>(R.id.iv_user_image)
        private val likesCount = itemView.findViewById<TextView>(R.id.tv_likes_count)
        private val commentsCount = itemView.findViewById<TextView>(R.id.tv_comments_count)
        private val postedOn = itemView.findViewById<TextView>(R.id.tv_posted_before)
        fun onBind(articleResponse: ArticleResponse) {
            articleResponse.apply {
                val likes = "${Utility.numberFormatter(likes.toLong())} Likes"
                val comments = "${Utility.numberFormatter(comments.toLong())} Comments"
                val timePosted = Utility.timeFromNow(createdAt)
                title.text = user[0].name
                designation.text = user[0].designation
                desc.text = content
                likesCount.text = likes
                commentsCount.text = comments
                postedOn.text = timePosted
                imageTitle.text = media[0].title
                imageUrl.text = media[0].url
                Picasso.with(itemView.context).load(media[0].image).into(postedImage)
                Picasso.with(itemView.context).load(user[0].avatar).into(userImage)

            }

        }
    }
}