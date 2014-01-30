package hkapps.tuts.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/***
 * This is an example of a simple ListView and is properly commented
 * for your better understanding. Feel free to use it in your projects in any way you want.
 * @author Harish_Kotra (http://harishkotra.me)
 *
 */

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//create a listview variable and add it to the id from the view.
		final ListView listview = (ListView) findViewById(R.id.listview);
		
		//Array of top 20 bollywood songs as on January 27, 2014
		String[] songsList = new String[] {"Sunny Sunny (Yaariyan)", "Kamili (Dhoom 3)", "ABCD (Yaariyan)", "Malang (Dhoom 3)", 
				"Allah Waariyan(Yaariyan)", "Photocopy (Jai Ho)", "Gandi Baat (R..Rajkumar)", "Tune Mari Entriyan (Gunday)", 
				"Drama Queen (Hasee Toh Phasee)", "Hamari Atariya (Dedh Isquiyan)", "Ram Chahe Leela (Ram Leela)", 
				"Dhoom Machale Dhoom (Dhoom 3)", "Horn Ok Please (Dedh Isquiyan)", "Nagada Sang Dhol (Ram Leela)", 
				"Kabhi Jo Badal Barse (Jackpot)", "Lahu Muh Lag Gaya (Ram Leela)", "Saree Ke Fall Sa (R..Rajkumar)", 
				"Party All Night (Boss)", "Tooh (Gori Tere Pyaar Mein)", "Har Kissi Ko Nahi Milta (Boss)" };
		
		final ArrayList<String> list = new ArrayList<String>();
		
		//adding values into the list using a loop.
		for(int i=0; i<songsList.length; i++){
			list.add(songsList[i]);
		}
		
		//writing an array adapter
		final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		
		//set adapter 
		listview.setAdapter(adapter);
		//set OnItemClickListener to perform actions on the ListView item clicked by the user.
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position,
					long id) {
				
				//display the location of the item you clicked using position
				Toast.makeText(getApplicationContext(),
					      "You clicked item at " + position + "th position.", Toast.LENGTH_LONG)
					      .show();
				
				//store clicked item of the list into a variable.
				final String item = (String) parent.getItemAtPosition(position);
				
				//attach a run method to the item clicked for it to delete or animate in anyway we want.
				view.animate().setDuration(200).alpha(0).withEndAction(new Runnable() {
					
					@Override
					public void run() {
						//remove the item clicked.
						list.remove(item);
						//we have removed an element so, lets refresh the complete listview using the 
						//notifyDataSetChanged() method to the adapter of the listview.
						adapter.notifyDataSetChanged();
						//setting completely opaque property to the view.
						view.setAlpha(1);
					}
				});
			}
		});
	}
	
	
	private class StableArrayAdapter extends ArrayAdapter<String>{
		
		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
		
		public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects){
		
		super(context, textViewResourceId, objects);
		for(int i=0; i<objects.size(); i++ ){
			mIdMap.put(objects.get(i), i);
		}
		
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
