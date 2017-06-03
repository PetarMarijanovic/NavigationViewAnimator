package com.petarmarijanovic.navigationviewanimator.library

import android.view.animation.Animation

/** Created by petar on 03/06/2017.
 * Use this if you don't want to override all AnimationListener methods. */
open class EmptyAnimationListener : Animation.AnimationListener {
  override fun onAnimationStart(animation: Animation) {}
  
  override fun onAnimationEnd(animation: Animation) {}
  
  override fun onAnimationRepeat(animation: Animation) {}
}
