package com.ndriqa.animatediconbutton

import android.content.Context
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.ndriqa.animatediconbutton.listeners.OnDrawableChangeListener
import kotlinx.coroutines.*

class AnimatedIconButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : androidx.appcompat.widget.AppCompatImageButton(context, attrs, defStyle) {
    private val resOutAnimation = AnimationUtils.loadAnimation(context, R.anim.res_zoom_out_fade_in)

    private var drawablesResources = mutableListOf<Int>()

    private var currentResource: Int? = null
        set(value) {
            when {
                field == null && value == null -> return
                value != null -> animateResourceSwitch(value)
                field != null -> animateResourceSwitch(field!!)
            }
            field = value
        }

    private var listener: OnDrawableChangeListener? = null

    private fun animateResourceSwitch(next: Int) {
        animateResOut(next)
    }

    private fun animateResOut(nextResource: Int) {
        resOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                MainScope().launch {
                    with(Dispatchers.IO) {
                        delay(150)
                    }
                    setImageResource(nextResource)
                    listener?.onDrawableChange(nextResource)
                }
            }

            override fun onAnimationEnd(p0: Animation?) { }

            override fun onAnimationRepeat(p0: Animation?) { }

        })
        startAnimation(resOutAnimation)
    }

    fun setResources(resources: List<Int>) {
        drawablesResources.clear()
        drawablesResources.addAll(resources)
        currentResource = resources.first()
    }

    fun setResources(vararg resources: Int) {
        drawablesResources.clear()
        resources.forEach { drawablesResources.add(it) }
        currentResource = resources.first()
    }

    /** You can toggle directly to the specified drawable, if it exists in the list, that
     * is optional, with the default being the current drawable*/
    fun toggle(drawableID: Int? = null) {
        if (drawablesResources.isEmpty()) return

        val currentPos = drawablesResources.indexOf(currentResource)
        val nextResourcePos: Int = if (drawableID != null) {
            val toggleToDrawablePos = drawablesResources.indexOf(drawableID)
            if (toggleToDrawablePos > drawablesResources.size - 1 || currentPos < 0) return
            else toggleToDrawablePos
        } else {
            when {
                currentPos >= drawablesResources.size - 1 -> 0
                currentPos < 0 -> return
                else -> currentPos + 1
            }
        }
        currentResource = drawablesResources[nextResourcePos]
    }

    fun setOnDrawableChangeListener(onDrawableChangeListener: OnDrawableChangeListener){
        listener = onDrawableChangeListener
    }
}
