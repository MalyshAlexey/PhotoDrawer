package com.paintcolorltd.photodrawer

import android.app.Notification
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView


class DrawableImageView : AppCompatImageView, View.OnTouchListener {
    private var downX: Float = 0f
    private var downY: Float = 0f
    private var upX: Float = 0f
    private var upY: Float = 0f
    private var defaultPaintStrokeWidth = 5f
    private  var canvas: Canvas? = null
    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }




    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        setOnTouchListener(this)
    }
    public fun setImage(alteredBitmap: Bitmap, bitmap: Bitmap) {

        canvas = Canvas(alteredBitmap)

        val scaleXY = alteredBitmap.width * alteredBitmap.height / this.width / this.height
        paint.strokeWidth = scaleXY * defaultPaintStrokeWidth
        paint.isAntiAlias = true;
        canvas!!.drawBitmap(bitmap, Matrix(), paint)

        setImageBitmap(alteredBitmap)
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
       if(canvas!=null){
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = getPointerCoords(event)[0] //event.getX();
                downY = getPointerCoords(event)[1] //event.getY();
            }
            MotionEvent.ACTION_MOVE -> {
                upX = getPointerCoords(event)[0] //event.getX();
                upY = getPointerCoords(event)[1] //event.getY();

                    canvas!!.drawLine(downX, downY, upX, upY, paint)
                    invalidate()
                    downX = upX
                    downY = upY



            }
            MotionEvent.ACTION_UP -> {
                upX = getPointerCoords(event)[0] //event.getX();
                upY = getPointerCoords(event)[1] //event.getY();
                canvas!!.drawLine(downX, downY, upX, upY, paint)
                invalidate()
            }
            MotionEvent.ACTION_CANCEL -> {
            }
            else -> {
            }
        }
       }
       else{
           Toast.makeText(context, "First select an image!", Toast.LENGTH_SHORT).show()
       }
        return true
    }

    fun getPointerCoords(e: MotionEvent): FloatArray {
        val index = e.actionIndex
        val coords = floatArrayOf(e.getX(index), e.getY(index))
        val matrix = Matrix()
        imageMatrix.invert(matrix)
        matrix.postTranslate(scrollX.toFloat(), scrollY.toFloat())
        matrix.mapPoints(coords)
        return coords
    }

}