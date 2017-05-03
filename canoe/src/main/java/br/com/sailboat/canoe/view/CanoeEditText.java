package br.com.sailboat.canoe.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.helper.StringHelper;

public class CanoeEditText extends LinearLayout {

    private TextView textViewLabel;
    private EditText editText;

    public CanoeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.edit_text, this, true);
        textViewLabel = (TextView) view.findViewById(R.id.label);
        editText = (EditText) view.findViewById(R.id.edit_text);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CanoeEditTextAttrs,
                0, 0);

        try {
            String labelText = a.getString(R.styleable.CanoeEditTextAttrs_labelText);
            if (StringHelper.isNotEmpty(labelText)) {
                textViewLabel.setText(labelText);
                textViewLabel.setVisibility(VISIBLE);
            } else {
                textViewLabel.setVisibility(GONE);
            }

        } finally {
            a.recycle();
        }


    }

    public String getText() {
        return editText.getText().toString();
    }

    public void putCursorAtTheEnd() {
        editText.setSelection(editText.getText().length());
        invalidate();
        requestLayout();
    }

}
