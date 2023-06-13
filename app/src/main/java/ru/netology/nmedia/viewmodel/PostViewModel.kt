package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.db.AppDb
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositorySQLiteImpl

private val empty = Post(
    id = 0,
    author = "",
    content = "",
    published = "",
    likedByMe = false
)
class PostViewModel(application: Application): AndroidViewModel(application) {
    //private val repository: PostRepository = PostRepositoryInMemory(application)
    //val data: LiveData<List<Post>> = repository.getAll()
    //private val edited = MutableLiveData(empty)
    private val repository: PostRepository = PostRepositorySQLiteImpl(AppDb.getInstance(application).postDao)
    val data = repository.getAll()
    private val edited = MutableLiveData(empty)


    fun likeById(id:Long) = repository.likeById(id)
    fun share(id: Long) = repository.shareById(id)
    fun remove(id: Long) = repository.removeById(id)


    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun clearEdit() {
        edited.value = empty
    }

    fun changeContent(content: String) {
        edited.value?.let { post ->
            if (content != post.content) {
                edited.value = post.copy(content = content)
            }
        }
    }
}