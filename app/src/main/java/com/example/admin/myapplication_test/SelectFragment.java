package com.example.admin.myapplication_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SelectFragment extends Fragment {
    Button Next;
    RadioButton thucong;
    RadioButton tudong;

    Bundle bundle;

    public SelectFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_selectfragment, container, false);

        Next = (Button) rootView.findViewById(R.id.next);
        thucong = (RadioButton) rootView.findViewById(R.id.thucong);
        tudong = (RadioButton) rootView.findViewById(R.id.tudong);

        bundle = this.getArguments();

        CompoundButton.OnCheckedChangeListener listenerRadio = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Next.setEnabled(true);
            }
        };

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thucong.isChecked()){
                    Intent intent = new Intent(getActivity(), Activity_One.class);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
                else if(tudong.isChecked()){
                    Intent intent = new Intent(getActivity(), Activity_Two.class);
                    startActivity(intent);
                }

            }
        });


        return rootView;
    }

    public void showData(String data){
        //nhietdo = data;
        //Toast.makeText(getActivity(), data, Toast.LENGTH_LONG).show();
    }
}
