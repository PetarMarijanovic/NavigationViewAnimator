package com.petarmarijanovic.navigationviewanimator

import android.view.animation.Animation

/** Created by petar on 03/06/2017. */
open class EmptyAnimationListener : Animation.AnimationListener {
  override fun onAnimationStart(animation: Animation) {}
  
  override fun onAnimationEnd(animation: Animation) {}
  
  override fun onAnimationRepeat(animation: Animation) {}
}
