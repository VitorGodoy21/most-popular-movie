package com.vfdegodoy.most_popular_movies.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.annotation.LayoutRes
import androidx.core.view.children

fun LayoutInflater.safeInflate(@LayoutRes viewResourceId: Int, parent: ViewGroup?, attachToRoot: Boolean = false): View {
    val existingView : View?= parent?.children?.find { it.id == viewResourceId }
    parent?.clearDisappearingChildren()

    return if (existingView == null) {
        this.inflate(viewResourceId, parent, attachToRoot)
    } else {
        existingView.removeFromParent()
        existingView
    }
}

fun View.safeInflate(context: Context?, @LayoutRes resourceId: Int, root: ViewGroup?): View {
    val inflater : LayoutInflater = LayoutInflater.from(context)
    return inflater.safeInflate(resourceId, root, root != null)
}

fun View.removeFromParent(): Boolean {
    val parent: ViewParent = this.parent
    return if (parent != null && parent is ViewGroup) {
        parent.removeView(this)
        parent.endViewTransition(this)
        true
    } else {
        false
    }
}