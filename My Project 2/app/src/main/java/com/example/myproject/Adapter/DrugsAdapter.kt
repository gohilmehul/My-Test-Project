package com.example.myproject.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.Model.Problems
import com.example.myproject.databinding.AssociatedDrugLayoutBinding


import javax.inject.Inject

class DrugsAdapter @Inject constructor() : RecyclerView.Adapter<DrugsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AssociatedDrugLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Problems>() {
        override fun areItemsTheSame(oldItem: Problems, newItem: Problems): Boolean {

            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Problems, newItem: Problems): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AssociatedDrugLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.apply {


            tvName.text = article.name
            tvDose.text = article.dose
            tvStrength.text = article.strength

        }

        holder.itemView.setOnClickListener {
            setArticleClickListener?.let {
                it(article)
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var setArticleClickListener : ((article: Problems)->Unit)? =null

    fun onArticleClicked(listener:(Problems)->Unit){
        setArticleClickListener =listener
    }
}