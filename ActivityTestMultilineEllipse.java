/**
 * Copyright (C) 2009 Mark Wyszomierski.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.markww.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;


public class ActivityTestMultilineEllipse extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout llMain = new LinearLayout(this);
        llMain.setOrientation(LinearLayout.VERTICAL);
        llMain.setBackgroundColor(0xFFFFFFFF);
        addContentView(llMain, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        
        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        llMain.addView(scroll);
        
        LinearLayout llContent = new LinearLayout(this);
        llContent.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        llContent.setOrientation(LinearLayout.VERTICAL);
        llContent.setBackgroundColor(0xFFFFFFFF);
        scroll.addView(llContent);
        
        // Make one widget that won't ellipsize within three lines.
        TextViewMultilineEllipse tv1 = new TextViewMultilineEllipse(this);
        tv1.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        tv1.setEllipsis("...");
        tv1.setEllipsisMore(" Read More!");
        tv1.setText("This is some short text. It won't need to be ellipsized.");
        tv1.setMaxLines(3);
        tv1.setPadding(10, 10, 10, 10);
        tv1.setBackgroundColor(0xFFE4BEF1);
        llContent.addView(tv1);
        
        // Make one widget that is long enough to ellipsize within three lines. Add a click handler,
        // expand and collapse on click.
        final TextViewMultilineEllipse tv2 = new TextViewMultilineEllipse(this);
        tv2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        tv2.setEllipsis("...");
        tv2.setEllipsisMore(" Read More!");
        tv2.setMaxLines(3);
        tv2.setText("This is some longer text. It should wrap and then eventually be ellipsized once it gets way too long for the horizontal width of the current application screen. We should be fixed to max [N] lines height.");
        tv2.setPadding(10, 10, 10, 10);
        tv2.setBackgroundColor(0xFFFCDFB2);
        tv2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (tv2.getIsExpanded()) {
					tv2.collapse();
				}
				else {
					tv2.expand();
				}
			}
        });
        llContent.addView(tv2);
    }
}