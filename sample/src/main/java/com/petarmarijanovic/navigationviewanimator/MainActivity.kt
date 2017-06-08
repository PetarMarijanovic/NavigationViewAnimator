package com.petarmarijanovic.navigationviewanimator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.petarmarijanovic.navigationviewanimator.AnimationDirection.*
import kotlinx.android.synthetic.main.view_first.*
import kotlinx.android.synthetic.main.view_second.view.*

class MainActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val animator = NavigationViewAnimator(this)
    setContentView(animator)
    
    val first = layoutInflater.inflate(R.layout.view_first, null)
    val second = layoutInflater.inflate(R.layout.view_second, null)
    
    animator.showView(first)
    
    from_right.setOnClickListener {
      animator.showView(second, IN_RIGHT_OUT_LEFT)
      second.back.setOnClickListener { animator.showView(first, IN_LEFT_OUT_RIGHT) }
    }
    
    from_bottom.setOnClickListener {
      animator.showView(second, IN_BOTTOM_OUT_NOTHING)
      second.back.setOnClickListener { animator.showView(first, IN_NOTHING_OUT_BOTTOM) }
    }
  }
}
