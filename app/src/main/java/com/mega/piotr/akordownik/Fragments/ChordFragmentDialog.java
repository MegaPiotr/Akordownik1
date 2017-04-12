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

    private static final String key_name="name";
    private static final String key_res="res";
        public ChordFragmentDialog(){
            super();
            setStyle(STYLE_NO_TITLE,0);
        }
        public void setRes(ChordActivity.GridItem resource) {
            Bundle bundle=new Bundle();
            bundle.putString(key_name,resource.name);
            bundle.putInt(key_res,resource.resource);
            this.setArguments(bundle);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view= inflater.inflate(R.layout.chord_item, container);
            ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
            imageView.setImageResource(getArguments().getInt(key_res));
            TextView textView=(TextView)view.findViewById(R.id.description);
            textView.setText(getArguments().getString(key_name));
            return view;
        }
}
