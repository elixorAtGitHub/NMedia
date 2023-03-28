package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) {post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                if (post.likedByMe) {
                    like.setImageResource(R.drawable.ic_liked_24)
                } else {
                    like.setImageResource(R.drawable.ic_like_24)
                }
                likeCount.text = countTransformation(post.likes)
                shareCount.text = countTransformation(post.shares)
                viewCount.text = countTransformation(post.views)
            }
        }

        binding.like.setOnClickListener {
            viewModel.like()
        }

        binding.share.setOnClickListener {
            viewModel.share()
        }

    }
}