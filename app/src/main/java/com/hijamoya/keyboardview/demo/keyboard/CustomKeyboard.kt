package com.hijamoya.keyboardview.demo.keyboard

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import com.hijamoya.keyboardview.Keyboard
import com.hijamoya.keyboardview.KeyboardView

class CustomKeyboard : Keyboard, KeyboardView.OnKeyboardActionListener {
    private val CODE_DELETE = -5
    private val CODE_CANCEL = -3

    private var keyboardView: KeyboardView? = null
    private var activity: Activity? = null
    private var hostDialog: Dialog? = null
    private var targetEditText: EditText? = null

    constructor(host: Activity?, layout: Int) : super(host, layout) {
        activity = host
    }

    constructor(dialog: Dialog, layout: Int) : super(dialog.context, layout) {
        hostDialog = dialog
    }

    fun release() {
        keyboardView?.setOnKeyboardActionListener(null)
        keyboardView = null
        activity = null
        targetEditText = null
        hostDialog = null
    }

    /**
     * Register the host [KeyboardView].
     *
     * @param view target view.
     */
    fun registerKeyboardView(view: KeyboardView?) {
        keyboardView = view
        keyboardView?.keyboard = this
        keyboardView?.isPreviewEnabled = false
        keyboardView?.setOnKeyboardActionListener(this)
    }

    /**
     * Returns whether the CustomKeyboard is visible.
     *
     * @return if the keyboard visible
     */
    fun isCustomKeyboardVisible(): Boolean {
        return keyboardView != null && keyboardView!!.visibility == View.VISIBLE
    }

    /**
     * Make the CustomKeyboard visible, and hide the system keyboard for view v.
     *
     * @param v target view
     */
    fun showCustomKeyboard(v: View?) {
        keyboardView?.visibility = View.VISIBLE
        keyboardView?.isEnabled = true
    }

    /**
     * Make the CustomKeyboard invisible.
     */
    fun hideCustomKeyboard() {
        keyboardView?.visibility = View.GONE
        keyboardView?.isEnabled = false
    }

    /**
     * Register [EditText] with resource id (on the hosting activity) for
     * using this custom keyboard.
     *
     * @param edittext    the [EditText] that registers to the custom keyboard
     * @param focusToShow show keyboard if focus
     */
    @SuppressLint("ClickableViewAccessibility", "CheckResult")
    fun registerEditText(edittext: EditText, focusToShow: Boolean) {
        edittext.onFocusChangeListener = OnFocusChangeListener { v: View, hasFocus: Boolean ->
            if (hasFocus && focusToShow) {
                showCustomKeyboard(v)
            } else {
                hideCustomKeyboard()
            }
        }
        edittext.setOnClickListener { showCustomKeyboard(it) }
        edittext.setOnTouchListener { v: View, event: MotionEvent? ->
            val edit = v as EditText
            val inType = edit.inputType // Backup the input type
            edit.inputType = InputType.TYPE_NULL // Disable standard keyboard
            edit.onTouchEvent(event) // Call native handler
            edit.inputType = inType // Restore input type
            edit.setSelection(edit.text.length)
            true
        }
        edittext.inputType = edittext.inputType or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        targetEditText = edittext
    }

    /**
     * Unregister [EditText] with resource id (on the hosting activity) for
     * using this custom keyboard.
     *
     * @param edittext the [EditText] that registers to the custom keyboard.
     */
    @SuppressLint("ClickableViewAccessibility")
    fun unregisterEditText(edittext: EditText) {
        edittext.onFocusChangeListener = null
        edittext.setOnClickListener(null)
        edittext.setOnTouchListener(null)
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        val editText: EditText?
        val focusCurrent = when {
            activity != null -> activity?.window?.currentFocus
            hostDialog != null && hostDialog?.window != null -> hostDialog?.window?.currentFocus
            else -> targetEditText
        }
        editText = if (focusCurrent is EditText) {
            focusCurrent
        } else {
            targetEditText
        }
        if (editText == null) {
            return
        }
        val editable = editText.text
        val start = editText.selectionStart
        if (primaryCode == CODE_DELETE) {
            if (editable != null && start > 0) {
                editable.delete(start - 1, start)
            }
        } else if (primaryCode == CODE_CANCEL) {
            hideCustomKeyboard()
        } else {
            editable!!.insert(start, Character.toString(primaryCode.toChar()))
        }
    }

    override fun onPress(arg0: Int) {
        // do nothing
    }

    override fun onRelease(primaryCode: Int) {
        // do nothing
    }

    override fun onText(text: CharSequence?) {
        // do nothing
    }

    override fun swipeDown() {
        // do nothing
    }

    override fun swipeLeft() {
        // do nothing
    }

    override fun swipeRight() {
        // do nothing
    }

    override fun swipeUp() {
        // do nothing
    }
}