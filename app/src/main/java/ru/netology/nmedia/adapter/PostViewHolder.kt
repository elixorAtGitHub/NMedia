package ru.netology.nmedia.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.netology.nmedia.R
import ru.netology.nmedia.countTransformation
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeClicked: OnLikeClickListener,
    private val onShareClicked: OnShareClickListener,
) : ViewHolder(binding.root) {

    fun bind(post: Post) {
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            )
            viewCount.text = countTransformation(post.views)

            like.setOnClickListener {
                onLikeClicked(post)
            }
            likeCount.text = countTransformation(post.likes)


            share.setOnClickListener {
                onShareClicked(post)
            }
            shareCount.text = countTransformation(post.shares)
        }
    }
}