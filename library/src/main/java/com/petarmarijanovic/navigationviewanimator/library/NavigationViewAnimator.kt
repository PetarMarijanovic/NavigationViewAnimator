package com.petarmarijanovic.navigationviewanimator.library

import android.content.Context
import android.support.annotation.AnimRes
import android.support.annotation.MainThread
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ViewAnimator
import java.io.Serializable
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
  
  public fun currentChild(): View? {
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
  
  data class Config(val view: View, val animationDirection: AnimationDirection) {
    
    private val shouldPlaceOnBottom = animationDirection == AnimationDirection.IN_NOTHING_OUT_BOTTOM
    fun newViewIndex() = if (shouldPlaceOnBottom) 0 else 1
    fun oldViewIndex() = if (shouldPlaceOnBottom) 1 else 0
    
    sealed class AnimationDirection : Serializable {
      object IN_RIGHT_OUT_LEFT : AnimationDirection()
      object IN_LEFT_OUT_RIGHT : AnimationDirection()
      object IN_BOTTOM_OUT_NOTHING : AnimationDirection()
      object IN_NOTHING_OUT_BOTTOM : AnimationDirection()
      object NOTHING : AnimationDirection()
      
      @AnimRes
      fun inAnimRes() =
          when (this) {
            is AnimationDirection.IN_RIGHT_OUT_LEFT -> R.anim.slide_in_right
            is AnimationDirection.IN_LEFT_OUT_RIGHT -> R.anim.slide_in_left
            is AnimationDirection.IN_BOTTOM_OUT_NOTHING -> R.anim.slide_in_bottom
            is AnimationDirection.IN_NOTHING_OUT_BOTTOM -> R.anim.nothing
            is AnimationDirection.NOTHING -> R.anim.nothing
          }
      
      @AnimRes
      fun outAnimRes() =
          when (this) {
            is AnimationDirection.IN_RIGHT_OUT_LEFT -> R.anim.slide_out_left
            is AnimationDirection.IN_LEFT_OUT_RIGHT -> R.anim.slide_out_right
            is AnimationDirection.IN_BOTTOM_OUT_NOTHING -> R.anim.nothing
            is AnimationDirection.IN_NOTHING_OUT_BOTTOM -> R.anim.slide_out_bottom
            is AnimationDirection.NOTHING -> R.anim.nothing
          }
    }
  }
}

/** Use this if you don't want to override all AnimationListener methods. */
open class EmptyAnimationListener : Animation.AnimationListener {
  override fun onAnimationStart(animation: Animation) {}
  
  override fun onAnimationEnd(animation: Animation) {}
  
  override fun onAnimationRepeat(animation: Animation) {}
}