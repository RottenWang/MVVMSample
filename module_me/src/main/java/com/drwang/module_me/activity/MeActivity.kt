package com.drwang.module_me.activity

import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import androidx.room.Room
import androidx.transition.TransitionManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.drwang.common.base.BaseMVVMActivity
import com.drwang.common.event.MyEvent
import com.drwang.common.utils.RouteClass
import com.drwang.common.utils.RouteField
import com.drwang.common.utils.toast
import com.drwang.module_me.R
import com.drwang.module_me.database.Person
import com.drwang.module_me.database.PersonDataBase
import com.drwang.module_me.leetcode.LeetCode
import com.drwang.module_me.viewmodel.MeViewModel
import com.jeremyliao.liveeventbus.LiveEventBus
import com.socks.library.KLog
import kotlinx.android.synthetic.main.module_me_activity_me.*
import org.jetbrains.anko.doAsync


@Route(path = RouteClass.ME.module_me_me)
class MeActivity : BaseMVVMActivity<MeViewModel>() {

    @Autowired(name = RouteField.name)
    @JvmField
    var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast(name)
        LeetCode.main(arrayOf())
        val treeNode = LeetCode.TreeNode(1)
        treeNode.left = LeetCode.TreeNode(2)
        treeNode.right = LeetCode.TreeNode(3)
        treeNode.right.left = LeetCode.TreeNode(4)
        treeNode.right.right = LeetCode.TreeNode(5)
        val serialize = LeetCode().serialize(treeNode);
        KLog.d("wangchen", "serialize = " + serialize)
        reSizeSoftInput()
//        doAsync {
//            val coinChange = LeetCode().coinChange(intArrayOf(1, 2, 5), 100);
//            KLog.d("wangchen","change = " + coinChange)
//        }
        val myPow = LeetCode().myPow(2.0, 10);
        KLog.d("wangchen", "pow = " + myPow)

        //创建动画
//        ViewAnimationUtils.createCircularReveal()
        constraintLayout()
//        if (!Settings.canDrawOverlays(this)){
//            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
//            intent.data = Uri.parse("package:$packageName")
//            startActivityForResult(intent, 0)
//        }else {
//            showWindow()
//        }


    }

    @TargetApi(23)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (Settings.canDrawOverlays(this)) {
                showWindow()
            } else {

            }
        }
    }

    private fun showWindow() {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val textView = TextView(this).apply {
            text = "testWindow"
            setBackgroundColor(Color.WHITE)
            setOnClickListener {
                windowManager.removeView(this)
            }
        }
        var layoutParams = WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
        layoutParams.width = 200;
        layoutParams.height = 200;
        layoutParams.flags = (WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        layoutParams.gravity = Gravity.CENTER;
        windowManager.addView(textView, layoutParams);
    }

    private fun constraintLayout() {
        //使用layer统一做旋转等
        val animator = ValueAnimator.ofFloat(0f, 360f).setDuration(2000)
        animator.addUpdateListener {
            layer.rotation = it.animatedValue as Float
        }
        animator.start()
        //这样不生效
//        layer.animate().rotation(360f).setDuration(2000).start();
//        layer.rotation = 50f
        place1.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraint)
            placeholder.setContentId(it.id)
        }
    }

    private fun reSizeSoftInput() {
        //rlRoot 当前窗体view
//        rlRoot.post {
//            rlRoot.viewTreeObserver.addOnGlobalLayoutListener {
//                val r = Rect()
//                //获取当前页面的指定view的位置 就是被软键盘遮挡的view的根view 一般就是xml中定义的根View
//                rlRoot.getWindowVisibleDisplayFrame(r)
//                //获取rootView的高度 DecorView
//                val screenHeight = rlRoot.rootView.height
//                //获取状态栏高度
//                val statusBarHeight = StatusBarUtil.getStatusBarHeight()
//                //获取底部导航栏高度
//                val navigationBarHeight = StatusBarUtil.getNavigationBarHeight()
//                //获取当前窗体的实际高度 可能因为软键盘的变化被遮挡
//                val heightDifference = screenHeight - (r.bottom - r.top) - statusBarHeight - navigationBarHeight
//                //如果变化的距离为负数,则移动到0并记录
//                if (heightDifference <=0 && heightDifference != mLastHeightDifferece){
//                    mLastHeightDifferece = heightDifference
//                    rlRoot.animate().translationY(0f).setDuration(200).start()
////                    return@addOnGlobalLayoutListener
//                }else if (heightDifference != mLastHeightDifferece) {
//                    //或者移动改变距离的-distance
//                    mLastHeightDifferece = heightDifference
//                    rlRoot.animate().translationY(-heightDifference.toFloat()).setDuration(200).start()
//                }
//            }
//        }

    }

    override fun initView() {
//        EventBus.getDefault().post(MyEvent().apply { name = "testtest" })
        LiveEventBus.get("test").post(MyEvent().apply { name = "hahahahah" })
//        doAsync {
        val personDb = Room.databaseBuilder(applicationContext, PersonDataBase::class.java, "persondb")
                .build()
        val personDAO = personDb.personDAO()
        doAsync {
            personDAO.insertPerson(Person(3, "haha", 30, "nima"))
            val queryPerson = personDAO.queryPerson()
            KLog.d("wangchen", "queryPerson=$queryPerson")
        }

//        }

    }

    override fun getLayoutId(): Int {
        return R.layout.module_me_activity_me
    }

    override fun initObservable() {

    }
}