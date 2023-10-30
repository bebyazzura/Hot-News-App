package com.bebyaww.hotnewsapp.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.bebyaww.hotnewsapp.DetailActivity
import com.bebyaww.hotnewsapp.R
import com.bebyaww.hotnewsapp.databinding.ItemNewsOneBinding
import com.bebyaww.hotnewsapp.model.network.ArticlesItem
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    private val listNews = ArrayList<ArticlesItem>()

    class MyViewHolder(val binding: ItemNewsOneBinding) :
        RecyclerView.ViewHolder(binding.root)

        fun setData(list: List<ArticlesItem>?){
            if (list == null) return
            listNews.clear()
            listNews.addAll(list)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder (
        ItemNewsOneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = listNews.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news = listNews[position]
        val date = news.publishedAt?.take(10) // it only takes the 10 first char
        val dateArray = date?.split("-")?.toTypedArray() //split the strimg to be an array
        val time= news.publishedAt?.takeLast(9)
        val timeArray = time?.split(":")?.toTypedArray()
        val calendar = Calendar.getInstance()
        dateArray?.let {
            calendar.set(Calendar.YEAR, it[0]. toInt())
            calendar.set(Calendar.MONTH, it[1].toInt() -1)
            calendar.set(Calendar.DAY_OF_MONTH, it[2].toInt())
        }

        timeArray?.let {
            calendar.set(Calendar.HOUR_OF_DAY, it[0].toInt())
            calendar.set(Calendar.MINUTE, it[1].toInt())
        }

        val dateResultFormat = SimpleDateFormat("EEE, dd MMM", Locale.getDefault()).format(calendar.time).toString()
        val timeResultFormat = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time).toString()

        Log.i("NewsAdapter", "onBindViewHolder: $dateResultFormat")
        Log.i("NewsAdapter", "onBindViewHolder: $timeResultFormat")
        val newsDate = "$dateResultFormat |"
        val newsTime = "$timeResultFormat UTC"
        holder.binding.apply {
            tvSources.text = news.source?.name
            tvTitle.text = news.title
            tvDate.text = newsDate
            tvTime.text = newsTime

            Picasso.get().load(news.urlToImage).placeholder(R.drawable.ic_logo).into(ivNews)
        }

        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.NEWS_DATA, news)
            intent.putExtra(DetailActivity.EXTRA_DATA_DATE, newsDate)
            intent.putExtra(DetailActivity.EXTRA_DATA_TIME, newsTime)
            it.context.startActivity(intent)
        }
    }
}