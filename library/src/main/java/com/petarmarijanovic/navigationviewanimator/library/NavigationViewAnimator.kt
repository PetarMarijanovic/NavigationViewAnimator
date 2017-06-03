package com.petarmarijanovic.navigationviewanimator.library

import android.content.Context
import android.support.annotation.MainThread
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ViewAnimator
import java.util.*

/** Created by petar on 03/06/2017. */
class NavigationViewAnimator @JvmOverloads constructor(context: Context,
                                                       attrs: AttributeSet? = null)
  : ViewAnimator(context, attrs) {
  
  private var isAnimationRunning = false
  private val queue = LinkedList<Config>()
  
  @MainThread
  fun showView(config: Config) {
    if (isAnimationRunning) {
      queue.add(config)
      return
    }
    
    val view = config.view
    if (currentChild() === view) return
    
    if (childCount == 0) {
      addView(view)
      return
    }
    
    addView(view, config.newViewIndex())
    
    val animation = AnimationUtils.loadAnimation(context, config.animationDirection.inAnimRes())
    animation.setAnimationListener(inAnimationListener(config.oldViewIndex()))
    inAnimation = animation
    setOutAnimation(context, config.animationDirection.outAnimRes())
    
    var i = 0
    while (i < childCount) {
      if (getChildAt(i) === view) {
        isAnimationRunning = true
        displayedChild = i
        return
      }
      i++
    }
    
    throw IllegalArgumentException("No view " + view.toString())
  }
  
  fun currentChild(): View? {
    return getChildAt(displayedChild)
  }
  
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
