package com.example.blogapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogapp.databinding.ItemBlogBinding

class BlogListAdapter(private val listener: BlogItemClicked) : RecyclerView.Adapter<blogsViewHolder>() {

    private val items: ArrayList<Blog> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): blogsViewHolder {
        val binding = ItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val bindHolder = blogsViewHolder(binding)
        binding.cardView.setOnClickListener{
            listener.onItemClicked(items[bindHolder.adapterPosition])
        }
        return bindHolder
    }

    override fun onBindViewHolder(holder: blogsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.contentView.text = currentItem.content
        Glide.with(holder.itemView.context).load(currentItem.imageURL).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    fun updateNews(updatedBlog: ArrayList<Blog>){
        items.clear()
        items.addAll(updatedBlog)

        notifyDataSetChanged()
    }
}

class blogsViewHolder(binding: ItemBlogBinding) : RecyclerView.ViewHolder(binding.root) {

    val titleView: TextView = binding.title
    val imageView: ImageView = binding.featuredImage
    val contentView : TextView = binding.content
}

interface BlogItemClicked{
    fun onItemClicked(item: Blog)
}