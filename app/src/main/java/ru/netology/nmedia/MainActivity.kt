package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

fun countTransformation(count: Int): String {
    if (count >= 1000000) {
        val resultLeft = (count/1000000).toString()
        val resultRight = if (count%1000000/100000 > 0) {
            "." + (count%1000000/100000).toString()
        } else {
            ""
        }
        return "$resultLeft$resultRight" + "M"
    } else if (count >= 10000) {
        return (count/1000).toString() + "K"
    } else if (count >= 1000) {
        val resultLeft = (count/1000).toString()
        val resultRight = if (count%1000/100 > 0) {
                "." + (count%1000/100).toString()
            } else {
                ""
            }
        return "$resultLeft$resultRight" + "K"
    } else {
        return count.toString()
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val post = Post(
            id = 0,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb"
        )

        with(binding) {
            author.text = post.author
            content.text = post.content
            published.text = post.published


            if (post.likedByMe) { like.setImageResource(R.drawable.ic_liked_24) }
            likeCount.text = countTransformation(post.likes)
            like.setOnClickListener {
                post.likedByMe = !post.likedByMe
                post.likes = if (post.likedByMe) {
                    post.likes + 1
                } else {
                    post.likes - 1
                }
                likeCount.text = countTransformation(post.likes)
                like.setImageResource(
                    if (post.likedByMe) {
                        R.drawable.ic_liked_24
                    } else {
                        R.drawable.ic_like_24
                    }
                )
            }


            shareCount.text = countTransformation(post.shares)
            share.setOnClickListener {
                post.shares = post.shares + 1
                shareCount.text = countTransformation(post.shares)
            }


            viewCount.text = countTransformation(post.views)





        }
    }
}