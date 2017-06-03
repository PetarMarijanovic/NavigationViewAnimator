package com.petarmarijanovic.navigationviewanimator.library

import android.support.annotation.AnimRes
import android.view.View
import java.io.Serializable

/** Created by petar on 03/06/2017. */
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