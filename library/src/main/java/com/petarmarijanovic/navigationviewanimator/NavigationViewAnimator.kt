package com.petarmarijanovic.navigationviewanimator

import android.content.Context
import android.support.annotation.MainThread
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ViewAnimator
import com.petarmarijanovic.navigationviewanimator.AnimationDirection.NOTHING
import java.util.*

/** Created by petar on 03/06/2017. */
class NavigationViewAnimator @JvmOverloads constructor(context: Context,
                                                       attrs: AttributeSet? = null)
  : ViewAnimator(context, attrs) {

  private var isAnimationRunning = false
  private val queue = LinkedList<Config>()

  @MainThread
  fun showView(view: View, animationDirection: AnimationDirection = NOTHING) {
    if (currentChild() !== view) showView(Config(view, animationDirection))
  }

  private fun showView(config: Config) {
    if (isAnimationRunning) {
      // Some animation is already in progress, so add this one to queue
      queue.add(config)
      return
    }

    if (childCount == 0) addView(View(context)) // Add empty view so animations work as intended

    animateView(config)
  }

  private fun animateView(config: Config) {
    addView(config.view, config.newViewIndex())

    val animation = AnimationUtils.loadAnimation(context, config.animationDirection.inAnimRes)
    animation.setAnimationListener(inAnimationListener(config.oldViewIndex()))
    inAnimation = animation
    setOutAnimation(context, config.animationDirection.outAnimRes)

    for (i in 0..childCount) {
      if (getChildAt(i) === config.view) {
        isAnimationRunning = true
        displayedChild = i
        return
      }
    }

    throw IllegalArgumentException("No view " + config.view.toString())
  }

  fun currentChild(): View? = getChildAt(displayedChild)

  private fun inAnimationListener(oldViewIndex: Int): Animation.AnimationListener =
      object : EmptyAnimationListener() {
        override fun onAnimationEnd(animation: Animation) {
          post {
            inAnimation = null
            outAnimation = null
            removeViewAt(oldViewIndex)
            isAnimationRunning = false
            if (!queue.isEmpty()) showView(queue.poll())
          }
        }
      }
}
