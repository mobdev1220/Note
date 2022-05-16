package uz.gita.noteAppMobDev.presentation.view.screen.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteAppMobDev.R
import uz.gita.noteAppMobDev.databinding.ScreenMainBinding
import uz.gita.noteAppMobDev.presentation.view.adapter.main.MainAdapter
import uz.gita.noteAppMobDev.presentation.view.screen.main.pager.NotesPager
import uz.gita.noteAppMobDev.presentation.view.screen.main.pager.TaskPager
import uz.gita.noteAppMobDev.presentation.viewmodel.main.MainViewModel
import uz.gita.noteAppMobDev.presentation.viewmodel.main.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val notesPager: NotesPager? = null
    private val taskPager: TaskPager? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        val mainAdapter = MainAdapter(childFragmentManager, lifecycle)
        viewPagerMain.adapter = mainAdapter
        viewPagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.pageName.setText(R.string.note)
                    add.setOnClickListener {
                        findNavController().navigate(R.id.action_mainScreen_to_addNoteScreen)
                    }
                } else {
                    binding.pageName.setText(R.string.task)
                    add.setOnClickListener {
                        findNavController().navigate(R.id.action_mainScreen_to_addTaskScreen)
                    }
                }
            }
        })
        notesPager?.setSendNoteDataUpdate {
            findNavController().navigate(MainScreenDirections.actionMainScreenToUpdateNoteScreen(it))
        }
        TabLayoutMediator(tabLayout, viewPagerMain) { tab, position ->
            when (position) {
                0 -> {
                    tab.setIcon(R.drawable.ic_sticky)
                }
                else -> {
                    tab.setIcon(R.drawable.ic_check)
                }
            }
        }.attach()
    }
}