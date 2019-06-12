package com.harshgaba.loginkotlinsample.ui.views

import android.animation.ValueAnimator
import android.support.v7.widget.CardView
import android.view.ViewTreeObserver

/**
 * Created by Harsh Gaba on 2019-06-12.
 * harshgaba08@gmail.com
 */

class CollapsibleCardView(val cardView: CardView) {

    private var compactHeight = 0
    internal var expandedHeight = 0

    init {

        cardView.viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {

                    override fun onPreDraw(): Boolean {
                        cardView.viewTreeObserver.removeOnPreDrawListener(this)

                        compactHeight = cardView.height
                        if(expandedHeight == 0) expandedHeight = compactHeight * 2

                        return true
                    }
                })
    }

    private fun collapseView() {

        val anim = ValueAnimator.ofInt(
                cardView.measuredHeightAndState, compactHeight)

        anim.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams = cardView.layoutParams
            layoutParams.height = animatedValue
            cardView.layoutParams = layoutParams
        }

        anim.start()
    }

    private fun expandView(height: Int) {

        val anim = ValueAnimator.ofInt(
                cardView.measuredHeightAndState, height)

        anim.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams = cardView.layoutParams
            layoutParams.height = animatedValue
            cardView.layoutParams = layoutParams
        }

        anim.start()
    }

    fun toggle() {

        if (cardView.height == compactHeight)
            expandView(expandedHeight)
        else
            collapseView()

    }
}

fun CardView.buildCollapsible(): CollapsibleCardView {

    return CollapsibleCardView(this)
}

fun CardView.buildCollapsible(height: Int): CollapsibleCardView {

    val collapsible = CollapsibleCardView(this)
    collapsible.expandedHeight = height
    return collapsible
}