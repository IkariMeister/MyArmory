package com.jcgseco.myarmory.uicomponents.extensions

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

inline fun FragmentManager.inTransaction(
    @AnimatorRes @AnimRes
    enter: Int,
    @AnimatorRes @AnimRes
    exit: Int,
    func: FragmentTransaction.() -> FragmentTransaction
) = beginTransaction().setCustomAnimations(enter, exit).func().commit()

inline fun FragmentManager.inTransaction(
    @AnimatorRes @AnimRes
    enter: Int,
    @AnimatorRes @AnimRes
    exit: Int,
    @AnimatorRes @AnimRes
    popEnter: Int,
    @AnimatorRes @AnimRes
    popExit: Int,
    func: FragmentTransaction.() -> FragmentTransaction
) = beginTransaction().setCustomAnimations(enter, exit, popEnter, popExit).func().commit()
