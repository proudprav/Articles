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
import com.example.articles.utils.Utility.Companion.numberFormatter
import com.example.articles.utils.Utility.Companion.timeFromNow
import com.example.articles.utils.Utility.Companion.withNotNullNorEmpty
import com.squareup.picasso.Picasso

class ArticleListAdapter : RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {

    private var listOfArticle = ArrayList<ArticleResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_article, parent, false)
    )

    override fun getItemCount() = listOfArticle.size

    fun updateList(listofArticleResponse: ArrayList<ArticleResponse>) {
        (listOfArticle).addAll(listofArticleResponse)
        notifyItemRangeChanged(listOfArticle.size, listofArticleResponse.size)    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = listOfArticle[position]
        holder.onBind(article)
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userName = itemView.findViewById<TextView>(R.id.tv_user_name)
        private val designation = itemView.findViewById<TextView>(R.id.tv_user_designation)
        private val description = itemView.findViewById<TextView>(R.id.tv_image_desc)
        private val imageTitle = itemView.findViewById<TextView>(R.id.tv_image_title)
        private val imageUrl = itemView.findViewById<TextView>(R.id.tv_image_url)
        private val postedImage = itemView.findViewById<ImageView>(R.id.iv_posted_image)
        private val userImage = itemView.findViewById<ImageView>(R.id.iv_user_image)
        private val likesCount = itemView.findViewById<TextView>(R.id.tv_likes_count)
        private val commentsCount = itemView.findViewById<TextView>(R.id.tv_comments_count)
        private val postedOn = itemView.findViewById<TextView>(R.id.tv_posted_before)
        fun onBind(articleResponse: ArticleResponse) {
            articleResponse.apply {
                val likes = "${likes?.toLong()?.numberFormatter()} ${Utility.LIKES}"
                val comments = "${comments?.toLong()?.numberFormatter()} ${Utility.COMMENTS}"
                var isMediaNull = true
                var isUserNull = true
                description.text = content
                likesCount.text = likes
                commentsCount.text = comments
                postedOn.text = createdAt?.timeFromNow().orEmpty()
                media.withNotNullNorEmpty { imageTitle.text = media?.get(0)?.image
                    imageUrl.text = media?.get(0)?.url
                    Picasso.with(itemView.context).load(media?.get(0)?.image).into(postedImage)
                isMediaNull = false}
                user.withNotNullNorEmpty {  userName.text = user?.get(0)?.name
                    designation.text = user?.get(0)?.designation
                    Picasso.with(itemView.context).load(user?.get(0)?.avatar).into(userImage)
                isUserNull = false}
                if(isMediaNull){
                    imageTitle.text = itemView.context.getString(R.string.No_Title)
                    imageUrl.text = itemView.context.getString(R.string.NO_URl)
                    Picasso.with(itemView.context).load(R.drawable.ic_launcher_background).into(postedImage)
                }
                if(isUserNull){
                    userName.text = user?.get(0)?.name
                    designation.text = user?.get(0)?.designation
                    Picasso.with(itemView.context).load(user?.get(0)?.avatar).into(userImage)
                }


            }

        }
    }
}

