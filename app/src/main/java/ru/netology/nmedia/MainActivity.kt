package ru.netology.nmedia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.adapter.PostListener
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: PostViewModel by viewModels()

        val newPostContract = registerForActivityResult(NewPostActivity.Contract) {result ->            // 1 - регистрируем контракт
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }

        val adapter = PostAdapter(
            object: PostListener {
                override fun onRemove(post: Post) {
                    viewModel.remove(post.id)
                }
                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                    newPostContract.launch(post.content)                                                // 2 - вызываем лаунч
                }
                override fun onShare(post: Post) {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
                    val startIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
                    startActivity(startIntent)

                    viewModel.share(post.id)
                }
                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onVideo(post: Post) {
                    val videoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                    startActivity(videoIntent)
                }
            }
        )

        binding.list.adapter = adapter

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.add.setOnClickListener {
            newPostContract.launch("")
        }


    }
}