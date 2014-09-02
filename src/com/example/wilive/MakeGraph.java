package com.example.wilive;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.wilive.util.SystemUiHider;
import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class MakeGraph extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_make_graph);
		drawGraph();
		
		
	
//	
	}

	protected void drawGraph()
	{
		// init example series data
		
		GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
		    new GraphViewData(1, 2.0d)
		    , new GraphViewData(2, 1.5d)
		    , new GraphViewData(3, 2.5d)
		    , new GraphViewData(4, 1.0d)
		    , new GraphViewData(5, 2.0d)
		    , new GraphViewData(6, 3.0d)
		    , new GraphViewData(7, 1.0d)
		    , new GraphViewData(8, 4.0d)
		    , new GraphViewData(9, 4.0d)
		    , new GraphViewData(10, 4.0d)
		    , new GraphViewData(11, 100.0d)
		    , new GraphViewData(12, 4.0d)
		 
		});
		 
		
		GraphView graphView = new BarGraphView(this, "Hello");
		graphView.setLegendWidth((float) 3.0);
		graphView.setScalable(true);
		graphView.setShowLegend(true);
		graphView.addSeries(exampleSeries); // data
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.graphContainer);
		
		layout.addView(graphView);
	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		
	}



	
}
