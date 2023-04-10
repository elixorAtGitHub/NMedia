package ru.netology.nmedia

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.adapter.PostListener
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: PostViewModel by viewModels()

        val adapter = PostAdapter(
            object: PostListener {
                override fun onRemove(post: Post) {
                    viewModel.remove(post.id)
                }
                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                }
                override fun onShare(post: Post) {
                    viewModel.share(post.id)
                }
                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }
            }
        )

        viewModel.edited.observe(this) {
            if (it.id == 0L) {



                return@observe
            }

            binding.editGroup.visibility = View.VISIBLE
            binding.content.requestFocus()
            binding.content.setText(it.content)
            binding.authorEdit.text = it.author
        }

        binding.cancel.setOnClickListener {
            with (binding.content) {

                binding.editGroup.visibility = View.GONE
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

        binding.save.setOnClickListener {
            with (binding.content) {


                val content = text?.toString()
                if (content.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity, 
                        R.string.empty_post_error,
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener

                }

                viewModel.changeContent(content)
                viewModel.save()

                setText("")
                clearFocus()
                binding.editGroup.visibility = View.GONE
                AndroidUtils.hideKeyboard(this)
            }
        }

        binding.list.adapter = adapter

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }



    }
}