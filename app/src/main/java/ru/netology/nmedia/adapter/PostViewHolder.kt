package ru.netology.nmedia.adapter

import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.netology.nmedia.R
import ru.netology.nmedia.countTransformation
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

class PostViewHolder(
    private val binding: CardPostBinding,
    private val listener: PostListener,
) : ViewHolder(binding.root) {

    fun bind(post: Post) {
        with(binding) {
            author.text = post.author
            published.text = post.published

            if (post.video == null) {                           //отображаем блок видео если ссылка не пустая
                video.visibility = View.GONE
            } else {
                video.visibility = View.VISIBLE
            }


            movie.setOnClickListener {
                listener.onVideo(post)
            }

            play.setOnClickListener {
                listener.onVideo(post)
            }

            content.text = post.content
            like.isChecked = post.likedByMe
            viewCount.text = countTransformation(post.views)

            like.setOnClickListener {
                listener.onLike(post)
            }
            like.text = countTransformation(post.likes)


            share.setOnClickListener {
                listener.onShare(post)
            }
            share.text = countTransformation(post.shares)

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_options)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                listener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                listener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }

            root.setOnClickListener {
                listener.onDetailsPost(post)
            }


        }
    }
}