package com.puji.customprogressbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * �Զ��������
 * 
 * @author Kevin
 * @date 2014/7/27
 */
public class CustomProgressBar extends View {

	private static final String TAG = "CustomProgressBar";

	/**
	 * ����������С���
	 */
	private int mMinWidth;
	/**
	 * ���ȵ������
	 */
	private int mMaxWidth;
	/**
	 * ����������С�߶�
	 */
	private int mMinHeight;
	/**
	 * �����������߶�
	 */
	private int mMaxHeight;

	/**
	 * �������̶ȼ�ļ��
	 */
	private int mSpacing;

	/**
	 * �������Ľ��ȿ̶ȸ�Ŀ��
	 */
	private int mGridWidth;

	/**
	 * δ��ɵĽ��ȿ̶ȸ����ɫ
	 */
	private int mUndoneColor;
	/**
	 * ��ɵĽ��ȿ̶ȸ����ɫ
	 */
	private int mDoneColor;

	private Paint mDoneProgressPaint;
	private Paint mUnDoneProgressPaint;

	/**
	 * ��ǰ����ֵ
	 */
	private int mCurrentProgress;
	/**
	 * ������ֵ
	 */
	private int mMaxProgress;
	private int mCurrent;

	/**
	 * ���õ�ǰ����ֵ
	 * 
	 * @param progress
	 */
	public synchronized void setProgress(int progress) {

		if (progress < 0) {
			progress = 0;
		}

		if (progress > mMaxProgress) {
			progress = mMaxProgress;
		}

		if (progress != mCurrentProgress) {
			mCurrentProgress = progress;
			invalidate();
		}

	}

	/**
	 * ���ص�ǰ����ֵ
	 * 
	 * @return
	 */
	public synchronized int getProgress() {
		return mCurrentProgress;
	}

	/**
	 * ����������ֵ
	 * 
	 * @param max
	 */
	public synchronized void setMax(int max) {
		mMaxProgress = max;
	}

	/**
	 * �õ�������ֵ
	 * 
	 * @return
	 */
	public synchronized int getMax() {
		return mMaxProgress;
	}

	/**
	 * ���ý������̶ȼ�ļ��
	 * 
	 * @param spacing
	 */
	public synchronized void setSpacing(int spacing) {
		mSpacing = spacing;
	}

	/**
	 * ��ý������̶ȼ�ļ��
	 * 
	 * @return
	 */
	public synchronized int getSpacing() {
		return mSpacing;
	}

	/**
	 * �õ��������Ľ��ȿ̶ȸ�Ŀ��
	 * 
	 * @return
	 */
	public synchronized int getGridWidth() {
		return mGridWidth;
	}

	/**
	 * ���ý������Ľ��ȿ̶ȸ�Ŀ��
	 * 
	 * @param mGridWidth
	 */
	public synchronized void setGridWidth(int mGridWidth) {
		this.mGridWidth = mGridWidth;
	}

	/**
	 * �õ�δ��ɿ̶ȸ����ɫ
	 * 
	 * @return
	 */
	public synchronized int getUndoneColor() {
		return mUndoneColor;
	}

	/**
	 * ����δ��ɿ̶ȸ����ɫ
	 * 
	 * @param mUndoneColor
	 */
	public synchronized void setUndoneColor(int mUndoneColor) {
		this.mUndoneColor = mUndoneColor;
	}

	/**
	 * �õ��Ѿ���ɿ̶ȸ����ɫ
	 * 
	 * @return
	 */
	public synchronized int getDoneColor() {
		return mDoneColor;
	}

	/**
	 * �����Ѿ���ɿ̶ȸ����ɫ
	 * 
	 * @param mDoneColor
	 */
	public synchronized void setDoneColor(int mDoneColor) {
		this.mDoneColor = mDoneColor;
	}

	/**
	 * �õ�����������С����
	 * 
	 * @return
	 */
	public synchronized int getMinWidth() {
		return mMinWidth;
	}

	/**
	 * ���ý���������С����
	 * 
	 * @param mMinWidth
	 */
	public synchronized void setMinWidth(int mMinWidth) {
		this.mMinWidth = mMinWidth;
	}

	/**
	 * �õ�����������󳤶�
	 * 
	 * @return
	 */
	public synchronized int getMaxWidth() {
		return mMaxWidth;
	}

	/**
	 * ���ý���������󳤶�
	 * 
	 * @param mMaxWidth
	 */
	public synchronized void setMaxWidth(int mMaxWidth) {
		this.mMaxWidth = mMaxWidth;
	}

	/**
	 * �õ�����������С�߶�
	 * 
	 * @return
	 */
	public synchronized int getMinHeight() {
		return mMinHeight;
	}

	/**
	 * ���ý���������С�߶�
	 * 
	 * @param mMinHeight
	 */
	public synchronized void setMinHeight(int mMinHeight) {
		this.mMinHeight = mMinHeight;
	}

	/**
	 * �õ������������߶�
	 * 
	 * @return
	 */
	public synchronized int getMaxHeight() {
		return mMaxHeight;
	}

	/**
	 * ���ý����������߶�
	 * 
	 * @param mMaxHeight
	 */
	public synchronized void setMaxHeight(int mMaxHeight) {
		this.mMaxHeight = mMaxHeight;
	}

	public CustomProgressBar(Context context) {
		super(context);

	}

	public CustomProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);

		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		mMaxWidth = windowManager.getDefaultDisplay().getWidth();
		mMaxHeight = windowManager.getDefaultDisplay().getHeight();

		float density = getResources().getDisplayMetrics().density;

		TypedArray typedArray = context.getTheme().obtainStyledAttributes(
				attrs, R.styleable.CustomProgressBar, 0, 0);

		try {

			mMaxProgress = typedArray.getInteger(
					R.styleable.CustomProgressBar_max, 100);
			mCurrentProgress = typedArray.getInteger(
					R.styleable.CustomProgressBar_progress, 0);
			mSpacing = (int) typedArray.getDimension(
					R.styleable.CustomProgressBar_spacing, 5 * density);
			mGridWidth = (int) typedArray.getDimension(
					R.styleable.CustomProgressBar_gridWidth, 10 * density);
			mDoneColor = typedArray.getColor(
					R.styleable.CustomProgressBar_doneColor, Color.BLUE);
			mUndoneColor = typedArray.getColor(
					R.styleable.CustomProgressBar_undoneColor, Color.GRAY);
			mMaxWidth = typedArray.getDimensionPixelSize(
					R.styleable.CustomProgressBar_maxWidth, mMaxWidth);
			mMaxHeight = typedArray.getDimensionPixelSize(
					R.styleable.CustomProgressBar_maxHeight, mMaxHeight);
			mMinWidth = typedArray.getDimensionPixelSize(
					R.styleable.CustomProgressBar_minWidth,
					(int) (10 * density));
			mMinHeight = typedArray.getDimensionPixelSize(
					R.styleable.CustomProgressBar_minHeight,
					(int) (density * 1));

		} catch (Exception e) {

			Log.e(TAG, "��ȡ����ʱ����", e);
		}

		typedArray.recycle();

	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		int count = getWidth() / (mGridWidth + mSpacing);
		mCurrent = (int) ((mCurrentProgress / (float) mMaxProgress) * count);

		mDoneProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mDoneProgressPaint.setStyle(Paint.Style.FILL);
		mDoneProgressPaint.setColor(mDoneColor);

		mUnDoneProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mUnDoneProgressPaint.setStyle(Paint.Style.FILL);
		mUnDoneProgressPaint.setColor(mUndoneColor);

		for (int i = 0; i < count; i++) {
			if (i < mCurrent) {
				canvas.drawRect((mGridWidth + mSpacing) * i, 0,
						(mGridWidth + mSpacing) * i + mGridWidth, getHeight(),
						mDoneProgressPaint);
			} else {
				canvas.drawRect((mGridWidth + mSpacing) * i, 0,
						(mGridWidth + mSpacing) * i + mGridWidth, getHeight(),
						mUnDoneProgressPaint);
			}
		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int width = 0;
		int height = 0;

		if (width < mMinWidth) {
			width = mMinHeight;
		}

		if (width > mMaxWidth) {
			width = mMaxWidth;
		}

		if (height < mMinHeight) {
			height = mMinHeight;
		}

		if (height > mMaxHeight) {
			height = mMaxHeight;
		}

		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else if (widthMode == MeasureSpec.AT_MOST) {
			width = widthSize;
		}

		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else if (heightMode == MeasureSpec.AT_MOST) {
			height = 20;
		}

		setMeasuredDimension(width, height);
	}

}
