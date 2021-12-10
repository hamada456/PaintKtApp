package com.example.paintktapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

//Viewを継承したクラス
class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var path : Path = Path()//線を引く、図形を描く、グラフィック描画
    private var paint : Paint = Paint()//色、太さ等
    private var drawX : Float = 0F
    private var drawY : Float = 0F

    //描画の準備
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE//描画のスタイル
        paint.strokeWidth = 20F//幅
        canvas?.drawPath(path,paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //タッチポジション
        drawX = event!!.x
        drawY = event.y

        when(event.action) {  //event.actionは決まり文句
            MotionEvent.ACTION_DOWN -> path.moveTo(drawX,drawY)
            MotionEvent.ACTION_MOVE -> path.lineTo(drawX,drawY)
        }
        //再描画を実行
        invalidate()

        return true
    }
    //ボタンでクリア処理
    fun clearCanvas(){
        path.reset()
        invalidate()
    }
}