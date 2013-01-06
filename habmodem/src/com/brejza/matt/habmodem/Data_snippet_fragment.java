package com.brejza.matt.habmodem;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import ukhas.Telemetry_string;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Data_snippet_fragment extends Fragment {

	private String _callsign = "";
	private boolean _loaded = false;
	private Telemetry_string onLoad;
	
	public Balloon_data_fragment containg_fragment;
	
	public Data_snippet_fragment() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void updateDisplay(Telemetry_string in_str, double ascent_rate)
	{
		updateDisplay(in_str);
		TextView txtasc = (TextView)getView().findViewById(R.id.txtAscentRate);
		DecimalFormat df = new DecimalFormat("#.#");
		txtasc.setText(df.format(ascent_rate));
	}
	
	public void updateDisplay(Telemetry_string in_str)
	{
		if (!_loaded)
		{
			onLoad = in_str;
			return;
		}
		_callsign = in_str.callsign;
		DecimalFormat df = new DecimalFormat("#.######");
		DateFormat tf = new SimpleDateFormat("HH:mm:ss");
		
		TextView txtTime = (TextView)getView().findViewById(R.id.txtTime);
		TextView txtlat  = (TextView)getView().findViewById(R.id.txtLatitude);
		TextView txtlong = (TextView)getView().findViewById(R.id.txtLongitude);
		TextView txtalt  = (TextView)getView().findViewById(R.id.txtAltitude);

		txtTime.setText(tf.format(in_str.time));
		
		txtlat.setText(df.format(in_str.coords.latitude));
		txtalt.setText(Integer.toString((int)in_str.coords.altitude) + "m");
		txtlong.setText(df.format(in_str.coords.longitude));
	}
	
	public void setCallsign(String Callsign)
	{
		_callsign = Callsign;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_data_snippet, container, false);
        
   
        ImageButton btn = (ImageButton) view.findViewById(R.id.btnClose);
        

        btn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
            	if (containg_fragment != null)
            		containg_fragment.removePayload(_callsign);
            }            
        });
        
        return view;
        
    }

    @Override
    public void onResume ()
    {
    	super.onResume();
    	_loaded = true;
    	TextView txt = (TextView)getView().findViewById(R.id.txtview_snippet_callsign);
    	txt.setText(_callsign);
    	
    	ImageView lin = (ImageView)getView().findViewById(R.id.colourID);
    	lin.setBackgroundColor(0xFFFF0000);
    	lin.invalidate();
    	
    	ImageView lin1 = (ImageView)getView().findViewById(R.id.colourID1);
    	lin1.setBackgroundColor(0xFFFF0000);
    	lin1.invalidate();

    	if (onLoad != null)
    		updateDisplay(onLoad);
    	
    }
    
   
    
    public void btnClose(View view)
    {
    	if (containg_fragment != null)
    		containg_fragment.removePayload(_callsign);
    }

}