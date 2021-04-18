package top.zcwfeng.fmod

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import org.fmod.FMOD
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    // 六个 点击事件
    fun onDemoClick(view: View) {
        when (view.id) {
            R.id.change_voice -> startActivity(FmodChangeVoice::class.java)
            R.id.play_sound -> startActivity(PlaySound::class.java)
        }
    }

    fun<T> startActivity(clazz: Class<T>){
        val intent = Intent(this,clazz);
        startActivity(intent);
    }



    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}