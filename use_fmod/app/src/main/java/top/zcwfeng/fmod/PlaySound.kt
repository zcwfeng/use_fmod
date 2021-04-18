package top.zcwfeng.fmod

import android.content.pm.PackageManager
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.fmod.FMOD


class PlaySound : AppCompatActivity(), View.OnTouchListener, Runnable {

    private lateinit var mTxtScreen: TextView
    private var mThread: Thread = Thread(this,"Example Main")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create the text area
        mTxtScreen = TextView(this)
        mTxtScreen.text = "Hello David"
        mTxtScreen.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10.0f)
        mTxtScreen.setTypeface(Typeface.MONOSPACE)

        // Create the buttons
        val buttons: Array<Button?> = arrayOfNulls<Button>(9)
        for (i in buttons.indices) {
            buttons[i] = Button(this)
            buttons[i]?.text = getButtonLabel(i)
            buttons[i]?.setOnTouchListener(this)
            buttons[i]?.id = i
        }

        // Create the button row layouts
        val llTopRowButtons = LinearLayout(this)
        llTopRowButtons.orientation = LinearLayout.HORIZONTAL
        val llMiddleRowButtons = LinearLayout(this)
        llMiddleRowButtons.orientation = LinearLayout.HORIZONTAL
        val llBottomRowButtons = LinearLayout(this)
        llBottomRowButtons.orientation = LinearLayout.HORIZONTAL

        // Create the main view layout
        val llView = LinearLayout(this)
        llView.orientation = LinearLayout.VERTICAL

        // Create layout parameters
        val lpLayout =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                1.0f
            )

        // Set up the view hierarchy
        llTopRowButtons.addView(buttons[0], lpLayout)
        llTopRowButtons.addView(buttons[6], lpLayout)
        llTopRowButtons.addView(buttons[1], lpLayout)
        llMiddleRowButtons.addView(buttons[4], lpLayout)
        llMiddleRowButtons.addView(buttons[8], lpLayout)
        llMiddleRowButtons.addView(buttons[5], lpLayout)
        llBottomRowButtons.addView(buttons[2], lpLayout)
        llBottomRowButtons.addView(buttons[7], lpLayout)
        llBottomRowButtons.addView(buttons[3], lpLayout)
        llView.addView(mTxtScreen, lpLayout)
        llView.addView(llTopRowButtons)
        llView.addView(llMiddleRowButtons)
        llView.addView(llBottomRowButtons)

        setContentView(llView)

        // Request necessary permissions
        // Request necessary permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val perms = arrayOf(
                "android.permission.RECORD_AUDIO",
                "android.permission.WRITE_EXTERNAL_STORAGE"
            )
            if (checkSelfPermission(perms[0]) == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(perms[1]) == PackageManager.PERMISSION_DENIED
            ) {
                requestPermissions(perms, 200)
            }
        }

        FMOD.init(this)


        mThread.start()

        setStateCreate()
    }

    fun updateScreen(text: String?) {
        runOnUiThread { mTxtScreen.text = text }
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            buttonDown(view.getId())
        } else if (motionEvent.action == MotionEvent.ACTION_UP) {
            buttonUp(view.getId())
        }
        return true
    }

    override fun run() {
        main()
    }

    override fun onStart() {
        super.onStart()
        setStateStart()
    }

    override fun onStop() {
        setStateStop()
        super.onStop()
    }

    override fun onDestroy() {
        setStateDestroy()
        try {
            mThread.join()
        } catch (e: InterruptedException) {
        }
        FMOD.close()
        super.onDestroy()
    }

    private external fun getButtonLabel(index: Int): String?
    private external fun buttonDown(index: Int)
    private external fun buttonUp(index: Int)
    private external fun setStateCreate()
    private external fun setStateStart()
    private external fun setStateStop()
    private external fun setStateDestroy()
    private external fun main()


}