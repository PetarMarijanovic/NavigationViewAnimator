package com.petarmarijanovic.navigationviewanimator.library

import android.support.annotation.AnimRes
import android.view.View
import java.io.Serializable

/** Created by petar on 03/06/2017. */
data class Config(val view: View, val animationDirection: AnimationDirection) {
  
  fun newViewIndex() = if (shouldPlaceOnBottom()) 0 else 1
  fun oldViewIndex() = if (shouldPlaceOnBottom()) 1 else 0
  
  private fun shouldPlaceOnBottom() = animationDirection == AnimationDirection.IN_NOTHING_OUT_BOTTOM
  
  sealed class AnimationDirection(@AnimRes val inAnimRes: Int,
                                  @AnimRes val outAnimRes: Int) : Serializable {
    object IN_RIGHT_OUT_LEFT : AnimationDirection(R.anim.slide_in_right, R.anim.slide_out_left)
    object IN_LEFT_OUT_RIGHT : AnimationDirection(R.anim.slide_in_left, R.anim.slide_out_right)
    object IN_BOTTOM_OUT_NOTHING : AnimationDirection(R.anim.slide_in_bottom, R.anim.nothing)
    object IN_NOTHING_OUT_BOTTOM : AnimationDirection(R.anim.nothing, R.anim.slide_out_bottom)
    object NOTHING : AnimationDirection(R.anim.nothing, R.anim.nothing)
  }
}
