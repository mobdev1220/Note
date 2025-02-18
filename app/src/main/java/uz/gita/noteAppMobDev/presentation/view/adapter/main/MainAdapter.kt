package uz.gita.noteAppMobDev.presentation.view.adapter.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.noteAppMobDev.presentation.view.screen.main.pager.NotesPager
import uz.gita.noteAppMobDev.presentation.view.screen.main.pager.TaskPager

class MainAdapter(fm: FragmentManager, lf: Lifecycle) : FragmentStateAdapter(fm, lf) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NotesPager()
            else -> TaskPager()
        }
    }
}