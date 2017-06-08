package com.petarmarijanovic.navigationviewanimator

import android.support.annotation.AnimRes
import com.petarmarijanovic.navigationviewanimator.library.R

/** Created by petar on 08/06/2017. */
enum class AnimationDirection(@AnimRes val inAnimRes: Int, @AnimRes val outAnimRes: Int) {
  IN_RIGHT_OUT_LEFT(R.anim.slide_in_right, R.anim.slide_out_left),
  IN_LEFT_OUT_RIGHT(R.anim.slide_in_left, R.anim.slide_out_right),
  IN_BOTTOM_OUT_NOTHING(R.anim.slide_in_bottom, R.anim.nothing),
  IN_NOTHING_OUT_BOTTOM(R.anim.nothing, R.anim.slide_out_bottom),
  NOTHING(R.anim.nothing, R.anim.nothing)
}
