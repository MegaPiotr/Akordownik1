package com.mega.piotr.akordownik.Fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.mega.piotr.akordownik.Activities.ChordActivity;
import com.mega.piotr.akordownik.R;


public class ChordFragmentDialog extends  DialogFragment{

        private ChordActivity.GridItem res;
        public ChordFragmentDialog(){
            super();
            setStyle(STYLE_NO_TITLE,0);
        }
        public void setRes(ChordActivity.GridItem resource) {
            this.res = resource;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view= inflater.inflate(R.layout.chord_item, container);
            ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
            imageView.setImageResource(res.resource);
            TextView textView=(TextView)view.findViewById(R.id.description);
            textView.setText(res.name);
            return view;
        }
}
