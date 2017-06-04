package com.petarmarijanovic.navigationviewanimator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.petarmarijanovic.navigationviewanimator.library.Config
import com.petarmarijanovic.navigationviewanimator.library.NavigationViewAnimator
import kotlinx.android.synthetic.main.view_first.*
import kotlinx.android.synthetic.main.view_second.view.*

class MainActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val navigationViewAnimator = NavigationViewAnimator(this)
    setContentView(navigationViewAnimator)
    
    val first = layoutInflater.inflate(R.layout.view_first, null)
    navigationViewAnimator.showView(Config(first, Config.AnimationDirection.NOTHING))
    
    val second = layoutInflater.inflate(R.layout.view_second, null)
    
    from_right.setOnClickListener {
      navigationViewAnimator.showView(Config(second,
                                             Config.AnimationDirection.IN_RIGHT_OUT_LEFT))
      second.back.setOnClickListener {
        navigationViewAnimator.showView(Config(first,
                                               Config.AnimationDirection.IN_LEFT_OUT_RIGHT))
      }
    }
    
    from_bottom.setOnClickListener {
      navigationViewAnimator.showView(Config(second,
                                             Config.AnimationDirection.IN_BOTTOM_OUT_NOTHING))
      second.back.setOnClickListener {
        navigationViewAnimator.showView(Config(first,
                                               Config.AnimationDirection.IN_NOTHING_OUT_BOTTOM))
      }
    }
  }
}
