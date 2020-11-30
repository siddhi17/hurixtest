package com.example.HurixTest

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.HurixTest.repository.model.news.Doc
import kotlinx.android.synthetic.main.row_news_article.view.*

class NewsArticlesAdapter(
        private val listener: (Doc) -> Unit
) : RecyclerView.Adapter<NewsArticlesAdapter.NewsHolder>() {

    private var newsArticles: List<Doc> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            NewsHolder(parent.inflate(R.layout.row_news_article))

    override fun onBindViewHolder(newsHolder: NewsHolder, position: Int) =
            newsHolder.bind(newsArticles[position], listener)

    override fun getItemCount() = newsArticles.size

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(newsArticle: Doc, listener: (Doc) -> Unit) = with(itemView) {
            tvNewsItemId.text = newsArticle.id
            tvNewsItemArticleType.text = newsArticle.article_type
            tvListItemAbstract.text = newsArticle.abstract?.get(0)
            tvListItemDateTime.text = newsArticle.publication_date

            setOnClickListener { listener(newsArticle) }

        }
    }

    fun replaceItems(items: List<Doc>) {
        newsArticles = items
        notifyDataSetChanged()
    }
}