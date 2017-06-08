package com.petarmarijanovic.navigationviewanimator

import android.view.View
import com.petarmarijanovic.navigationviewanimator.AnimationDirection.IN_NOTHING_OUT_BOTTOM

/** Created by petar on 03/06/2017. */
data class Config(val view: View, val animationDirection: AnimationDirection) {
  
  fun newViewIndex() = if (shouldPlaceOnBottom()) 0 else 1
  fun oldViewIndex() = if (shouldPlaceOnBottom()) 1 else 0
  
  private fun shouldPlaceOnBottom() = animationDirection == IN_NOTHING_OUT_BOTTOM
}
