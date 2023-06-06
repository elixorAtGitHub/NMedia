package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.utils.TextArg
import ru.netology.nmedia.viewmodel.PostViewModel


class NewPostFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by TextArg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(layoutInflater, container, false)
        val viewModel: PostViewModel by activityViewModels()

        arguments?.let {
            val text = it.textArg
            binding.content.setText(text)
        }

        binding.content.requestFocus()
        binding.ok.setOnClickListener {                               //действия по нажатию на floatingButtom OK
            if (!binding.content.text.isNullOrBlank()) {
                val content = binding.content.text.toString()
                viewModel.changeContent(content)
                viewModel.save()
            }
            findNavController().navigateUp()
        }

        binding.bottomAppBar.setOnMenuItemClickListener {menuItem ->        //действия по нажатиям кнопок меню bottomAppBar'а - на данный момент дублеж кнопки OK
            when (menuItem.itemId) {
                R.id.cancel_edit_new_post -> {
                    findNavController().navigateUp()
                    true
                }
                R.id.edit_new_post -> {
                    if (!binding.content.text.isNullOrBlank()) {
                        val content = binding.content.text.toString()
                        viewModel.changeContent(content)
                        viewModel.save()
                    }
                    findNavController().navigateUp()
                    true
                }
                else -> false
            }
        }

        return binding.root
    }


}