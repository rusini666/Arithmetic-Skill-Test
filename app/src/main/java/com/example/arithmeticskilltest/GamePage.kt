import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView


class ShowPopUp : Activity() {
    var popUp: PopupWindow? = null
    var click = true
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popUp = PopupWindow(this)
        val layout = LinearLayout(this)
        val mainLayout = LinearLayout(this)
        val tv = TextView(this)
        val but = Button(this)
        but.setText("Click Me")
        but.setOnClickListener(object : OnClickListener() {
            fun onClick(v: View?) {
                click = if (click) {
                    popUp!!.showAtLocation(layout, Gravity.BOTTOM, 10, 10)
                    popUp!!.update(50, 50, 300, 80)
                    false
                } else {
                    popUp!!.dismiss()
                    true
                }
            }
        })
        val params = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        layout.orientation = LinearLayout.VERTICAL
        tv.text = "Hi this is a sample text for popup window"
        layout.addView(tv, params)
        popUp!!.contentView = layout
        // popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
        mainLayout.addView(but, params)
        setContentView(mainLayout)
    }
}