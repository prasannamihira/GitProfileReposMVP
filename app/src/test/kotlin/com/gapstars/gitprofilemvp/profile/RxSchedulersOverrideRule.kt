package com.gapstars.gitprofilemvp.profile

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import rx.Scheduler
import rx.android.plugins.RxAndroidPlugins
import rx.android.plugins.RxAndroidSchedulersHook
import rx.plugins.RxJavaPlugins
import rx.plugins.RxJavaSchedulersHook
import rx.schedulers.Schedulers
import java.lang.reflect.InvocationTargetException

class RxSchedulersOverrideRule: TestRule {

    private val mRxJavaSchedulersHook: RxJavaSchedulersHook = object : RxJavaSchedulersHook() {
        override fun getIOScheduler(): Scheduler {
            return Schedulers.immediate()
        }

        override fun getNewThreadScheduler(): Scheduler {
            return Schedulers.immediate()
        }
    }

    private val mRxAndroidSchedulersHook: RxAndroidSchedulersHook =
        object : RxAndroidSchedulersHook() {
            override fun getMainThreadScheduler(): Scheduler {
                return Schedulers.immediate()
            }
        }

    // Hack to get around RxJavaPlugins.reset() not being public
    // See https://github.com/ReactiveX/RxJava/issues/2297
    // Hopefully the method will be public in new releases of RxAndroid and we can remove the hack.
    @Throws(
        InvocationTargetException::class,
        IllegalAccessException::class,
        NoSuchMethodException::class
    )
    private fun callResetViaReflectionIn(rxJavaPlugins: RxJavaPlugins) {
        val method = rxJavaPlugins.javaClass.getDeclaredMethod("reset")
        method.isAccessible = true
        method.invoke(rxJavaPlugins)
    }

    /**
     * Modifies the method-running [Statement] to implement this
     * test-running rule.
     *
     * @param base The [Statement] to be modified
     * @param description A [Description] of the test implemented in `base`
     * @return a new statement, which may be the same as `base`,
     * a wrapper around `base`, or a completely new Statement.
     */
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxAndroidPlugins.getInstance().reset()
                RxAndroidPlugins.getInstance().registerSchedulersHook(mRxAndroidSchedulersHook)
                callResetViaReflectionIn(RxJavaPlugins.getInstance())
                RxJavaPlugins.getInstance().registerSchedulersHook(mRxJavaSchedulersHook)
                base!!.evaluate()
                RxAndroidPlugins.getInstance().reset()
                callResetViaReflectionIn(RxJavaPlugins.getInstance())
            }
        }
    }
}