package minato;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;

/**
 * Created by soar on 2016/11/5.
 */

public class DynamicAddEditText {

    public interface onTextChangeListener {
        void onTextChange(int currentTextNumber) ;
    }


    private LinearLayout linearLayout;
    private Context context;
    private final int LIMIT = 21;
    private final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private int focusIndex = -1,unfocusIndex = -1;
    private onTextChangeListener onTextChangeListener;


    private View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(v instanceof EditText) {
                int id = (Integer)v.getTag();
                if(hasFocus) {
                    focusIndex = id;
                }
                else {
                    unfocusIndex = id;
                    v.setFocusable(false);
                    v.setFocusableInTouchMode(false);
                    handleEmptyEdittext();

                }
            }
        }
    };

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(v instanceof EditText) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                v.requestFocus();
            }
            return false;
        }
    };

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length()>=2) {
                handleEditTextContent();
            }
        }
    };

    public DynamicAddEditText(Context context, LinearLayout linearLayout) {
        this.context = context;
        this.linearLayout = linearLayout;

        for(int index=0;index<LIMIT;index++) {
            EditText editText = genEditText(context,index);
            this.linearLayout.addView(editText);
        }

    }

    public void setOnTextChangeListener(onTextChangeListener listener) {
        this.onTextChangeListener = listener;
    }

    public void setText(String text) {
        int length = (text.length()<LIMIT)?text.length():LIMIT;

        for(int index = 0;index<length;index++) {
            EditText editText = (EditText)this.linearLayout.getChildAt(index);
            editText.setText(String.valueOf(text.charAt(index)));
        }

        this.callBackOnTextChange();
    }

    public String getContents() {
        String contents = "";
        for(int index=0;index<this.linearLayout.getChildCount();index++) {
            EditText editText = (EditText)this.linearLayout.getChildAt(index);
            if(editText.getText().length()!=0) {
                contents += editText.getText().toString();
            }
        }
        return (contents.equals(""))?null:contents;
    }

    private EditText genEditText(Context context,int indexId) {
        int color = context.getResources().getColor(android.R.color.white);
        EditText editText = new EditText(context);
        editText.setLayoutParams(layoutParams);
        editText.setTextSize(18);
        editText.setTextColor(color);
        editText.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        editText.setTag(indexId);
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setOnTouchListener(onTouchListener);
        editText.setOnFocusChangeListener(onFocusChangeListener);
        editText.addTextChangedListener(textWatcher);

        return editText;
    }

    private void handleEmptyEdittext() {
        EditText editText = (EditText)this.linearLayout.getChildAt(unfocusIndex);
        if(editText.getText().length()==0) {
            this.linearLayout.removeViewAt(unfocusIndex);
            this.updateEdittextID(unfocusIndex,false);
            if(this.linearLayout.getChildCount()<LIMIT) {
                this.linearLayout.addView(this.genEditText(this.context,LIMIT-1));
            }
            this.callBackOnTextChange();
        }
    }

    private void handleEditTextContent() {
        int newIndex = focusIndex+1;
        EditText editText = (EditText)this.linearLayout.getChildAt(focusIndex);
        EditText newEdittext = this.genEditText(context,newIndex);
        String content = editText.getText().toString();
        String secondText = content.substring(1);
        content = content.substring(0,1);
        editText.setText(content);
        newEdittext.setText(secondText);

        this.linearLayout.addView(newEdittext,newIndex);
        updateEdittextID(newIndex+1,true);
        this.callBackOnTextChange();
    }

    private void callBackOnTextChange() {
        if(onTextChangeListener!=null) {
            onTextChangeListener.onTextChange(linearLayout.getChildCount());
        }

    }

    private void updateEdittextID(int startIndex,boolean isIncrease) {

        for (;startIndex<this.linearLayout.getChildCount();startIndex++) {
            EditText editText = (EditText)this.linearLayout.getChildAt(startIndex);
            int id = (Integer)editText.getTag();
            id = (isIncrease)?id+1:id-1;
            editText.setTag(id);
        }
    }

}
